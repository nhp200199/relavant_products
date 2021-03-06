package com.phucnguyen.khoaluan.webservice.productrelevance.demo;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.CommonProperty;
import com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.mongo.shopee.ShopeeProduct;
import com.phucnguyen.khoaluan.webservice.productrelevance.demo.model.mongo.tiki.TikiProduct;
import com.phucnguyen.khoaluan.webservice.productrelevance.demo.repository.mongo.shopee.ShopeeProductsRepo;
import com.phucnguyen.khoaluan.webservice.productrelevance.demo.repository.mongo.tiki.TikiProductsRepo;

import org.apache.commons.text.similarity.CosineDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.pipeline.Annotation;
import vn.pipeline.VnCoreNLP;

@RestController
public class AppController {
    final String TIKI_BASE_URL = "https://tiki.vn/";
    final String SHOPEE_BASE_URL = "https://shopee.vn/";
    final String SHOPEE_IMAGE_BASE_URL = "https://cf.shopee.vn/file/";
    final String REDIS_PRODUCT_ID_PREFIX = "relevant-products-";
    @Autowired
    private StopwordService stopwordService;
    @Autowired
    private TikiProductsRepo repo;
    @Autowired
    private RedisTemplate<String, String> relavantRepo;
    @Autowired
    private ShopeeProductsRepo shopeeRepo;
    @Autowired
    LastCategoryService categoryService;

    String sellId;
    String productId;
    TikiProduct currentProduct;

    @RequestMapping("/get-stopword-name")
    public String getRelevantProducts(@RequestParam String url) {
        // WebClient client = WebClient.builder().build();
        // List<Evaluation> evaluations;

        // if (url.contains("tiki.vn")) {
        // // find the product id from the url
        // int endSubStringIndex = url.lastIndexOf('.');
        // String subString = url.substring(0, endSubStringIndex);
        // int startProductStringIndex = subString.lastIndexOf('-') + 2;
        // productId = subString.substring(startProductStringIndex, subString.length());

        // // start sending request to Tiki
        // String productItemApiUrl = "https://tiki.vn/api/v2/products/" + productId;
        // String jsonResponseString =
        // client.get().uri(productItemApiUrl).retrieve().bodyToMono(String.class).block();
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        // JsonNode actualObj = mapper.readTree(jsonResponseString);
        // currentProduct = mappingToProduct(actualObj, null, "tiki");
        // evaluations =
        // evaluationService.getEvaluationByTikiCateId(currentProduct.getCategoryId());
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonProcessingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // } else if (url.contains("shopee.vn")) {
        // // find the product id and category's id from the url
        // // Note: shopee can have 2 url
        // // eg:
        // //
        // 1.https://shopee.vn/product/283338743/9918567180?smtt=0.174867900-1616510545.9
        // // // Shop id first, then item id
        // // 2.
        // //
        // https://shopee.vn/bach-tuoc-cam-xuc-2-mat-cam-xuc-do-choi-bach-tuoc-co-the-dao-nguoc-tam-trang-bach-tuoc-sang-trong-i.283338743.9918567180
        // // //id shop first, then id item
        // if (url.contains("?")) {
        // int endSubStringIndex = url.lastIndexOf('?');
        // String subString = url.substring(0, endSubStringIndex);
        // String[] splittedStrings = subString.split("/");
        // productId = splittedStrings[splittedStrings.length - 1];
        // sellId = splittedStrings[splittedStrings.length - 2];
        // } else {
        // String[] splittedStrings = url.split("\\.");
        // productId = splittedStrings[splittedStrings.length - 1];
        // sellId = splittedStrings[splittedStrings.length - 2];
        // }
        // // sending requests to get product and seller
        // String productItemApiUrl = "https://shopee.vn/api/v2/item/get?itemid=" +
        // productId + "&shopid=" + sellId
        // + "&fbclid=-";
        // String sellerApiUrl = SHOPEE_BASE_URL +
        // "api/v4/product/get_shop_info?shopid=" + sellId;
        // String jsonProductResponseString =
        // client.get().uri(productItemApiUrl).header("If-None-Match-", "-")
        // .retrieve().bodyToMono(String.class).block();
        // String jsonSellerResponseString =
        // client.get().uri(sellerApiUrl).retrieve().bodyToMono(String.class)
        // .block();

        // // mapping
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        // // mapping for product
        // JsonNode actualObj = mapper.readTree(jsonProductResponseString);
        // // mapping for seller
        // JsonNode actuaSellerlObj = mapper.readTree(jsonSellerResponseString);
        // currentProduct = mappingToProduct(actualObj, actuaSellerlObj, "shopee");
        // evaluations =
        // evaluationService.getEvaluationByShopeeCateId(currentProduct.getCategoryId());
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonProcessingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        // // give category's id to database to receive the stopwords
        // stopwordsSet = new
        // HashSet<String>(stopwordService.getStopwordsByRootCate(currentProduct.getCategoryId()));
        // String optimizedWord = optimizeWord(currentProduct.getName(), stopwordsSet);

        // List<Map<String, String>> productCandidates = new ArrayList<Map<String,
        // String>>();
        // // sending query to ecommerce platform for candidates
        // // send to tiki
        // String jsonTikiProductsResponse = client.get()
        // .uri("https://tiki.vn/api/v2/products?limit=30&q=" +
        // optimizedWord).retrieve().bodyToMono(String.class)
        // .block();
        // JsonNode acturalTikiNode;
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        // acturalTikiNode = mapper.readTree(jsonTikiProductsResponse);
        // acturalTikiNode.get("data").forEach(product -> {
        // Map<String, String> productMap = new HashMap<String, String>();
        // productMap.put("name", optimizeWord(product.get("name").textValue(),
        // stopwordsSet));
        // productMap.put("url", TIKI_BASE_URL + product.get("url_path").textValue());
        // productMap.put("platform", "tiki");
        // productCandidates.add(productMap);
        // });
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonProcessingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // // send to shopee
        // String jsonShopeeProductsResponse = client.get()
        // .uri("https://shopee.vn/api/v4/search/search_items?keyword=" + optimizedWord
        // + "&limit=30").retrieve()
        // .bodyToMono(String.class).block();
        // try {
        // JsonNode acturalShopeeNode = mapper.readTree(jsonShopeeProductsResponse);
        // acturalShopeeNode.get("items").forEach(product -> {
        // Map<String, String> productMap = new HashMap<String, String>();
        // productMap.put("name",
        // optimizeWord(product.get("item_basic").get("name").textValue(),
        // stopwordsSet));
        // productMap.put("url", SHOPEE_BASE_URL + "product/" +
        // product.get("item_basic").get("itemid").asText()
        // + "/" + product.get("item_basic").get("shopid").asText());
        // productMap.put("platform", "shopee");
        // productCandidates.add(productMap);
        // });
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonProcessingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // // l???n l???c ?????u ti??n (d???a theo s??? gi???ng nhau c???a t??n c??c s???n ph???m)
        // CosineDistance cosineDistance = new CosineDistance();
        // productCandidates.removeIf(productMap -> {
        // return cosineDistance.apply(optimizedWord, productMap.get("name")) > 0.2;
        // });

        // // sau khi l???c xong l???n ?????u, l???y c??c url c???a l???n l???c 1, request l???y th??ng tin
        // v???
        // // ????? ti???p t???c so s??nh t??nh li??n quan c???a c??c s???n ph???m (d???a tr??n product's
        // // attributes)-
        // List<Product> secondRoundProducts = new ArrayList<Product>();
        // productCandidates.forEach(productMap -> {
        // String productSellerApiUrl = null;
        // String productItemApiUrl = null;

        // // make product api's url (and seller api's url with shopee) from product url
        // populateProductAndSellerApiFromUrl(productMap.get("url"), productItemApiUrl,
        // productSellerApiUrl);

        // // send request to receive product (and seller with shopee)
        // if (productMap.get("platform").equals("tiki")) {
        // client.get().uri(productItemApiUrl).retrieve().bodyToMono(String.class).doOnSuccess(response
        // -> {
        // ObjectMapper jsonMapper = new ObjectMapper();
        // try {
        // JsonNode actualObj = jsonMapper.readTree(response);
        // Product candidate = mappingToProduct(actualObj, null, "tiki");

        // calculateRelavanceRate(currentProduct, candidate);
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonProcessingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // });
        // } else {
        // JsonNode a = null;
        // client.get().uri(productItemApiUrl).header("If-None-Match-",
        // "-").retrieve().bodyToMono(String.class)
        // .doOnSuccess(response -> {
        // // mapping
        // ObjectMapper jsonMapper = new ObjectMapper();
        // try {
        // // mapping for product
        // JsonNode actualObj = jsonMapper.readTree(response);
        // // mapping for seller
        // candi = mappingToProduct(actualObj, a, "shopee");
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonProcessingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // });
        // }

        // });

        // return currentProduct.toString();
        Set<String> stopwordsSet = new HashSet<String>(stopwordService.getStopwordsByTikiCate(productId));
        return stopwordsSet.toString();

        // the comment section below is u?sed for crawl product by html
        // Document document = null;
        // try {
        // document = Jsoup.connect(url)
        // .userAgent("Mozilla/5.0 (Compatible; Googlebot/2.1;
        // +http://www.google.com/bot.html)").get();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // if (document != null) {
        // String productUrl =
        // document.head().getElementsByTag("link").select("[rel='canonical']").attr("href");
        // String categoryId =
        // document.head().getElementsByTag("meta").select("[name='category']").attr("content");
        // String name = document.getElementsByClass("title").first().text();
        // String seller =
        // document.select("span.seller-name").first().children().get(0).text();
        // String thumbnailUrl =
        // document.head().getElementsByTag("link").select("[rel='preload'],[as=''image]").attr("href");
        // Elements elements =
        // document.getElementsByClass("product-price_current-price");
        // int price = Integer.parseInt(priceString.replace("??", "").trim());

        // Element tableElement = document.getElementsByTag("table").first();
        // extract evaluation fields from html based on fields received from database
        // Map<String, String>
        // List<Evaluation> evaluations =
        // evaluationService.getEvaluationByCateId(categoryId);
        // for (Evaluation evaluation : evaluations) {

        // }
        // Product product = new Product();
        // product.setName(name);
        // product.setPrice(price);
        // product.setCategoryId(categoryId);

        // return document.toString();
        // }

        // else
        // return null;
    }

    @RequestMapping("/get-relevant-products")
    public String getProduct(@RequestParam long id, @RequestParam int categoryId, @RequestParam String name,
            @RequestParam String platform) {
        List<CommonProperty> relevantProducts = new ArrayList<CommonProperty>();
        String concatRedisProductId = REDIS_PRODUCT_ID_PREFIX + platform + "-" + id;
        if (relavantRepo.hasKey(concatRedisProductId)) {
            // if there are already relavant products in redis, return them
            return relavantRepo.opsForValue().get(concatRedisProductId);
        }
        // get stopwords based on currentProduct's category. Remember that we have to
        // map the product's category to its root category to receive stopwords
        // of that root category
        Set<String> stopwordsSet = null;
        if (platform.equals("tiki")) {
            stopwordsSet = new HashSet<String>(stopwordService.getStopwordsByTikiCate(String.valueOf(categoryId)));
        } else {
            stopwordsSet = new HashSet<String>(stopwordService.getStopwordsByShopeeCate(String.valueOf(categoryId)));
        }
        final Set<String> resultSet = stopwordsSet;
        // optimize the product's name
        String optimizedName = optimizeWord(name, stopwordsSet);
        // query the optimized name on db for candidates (tiki db and shopee db)
        List<TikiProduct> tikiCandidates = repo.findProductsByRelevantName(optimizedName, platform);
        List<ShopeeProduct> shopeeCandidates = shopeeRepo.findProductsByRelevantName(optimizedName, platform);
        System.out.println("Tiki candidates: " + tikiCandidates.size());
        System.out.println("shopee candidates: " + shopeeCandidates.size());
        List<MappedLastCategory> categories = null;
        // remove the product with same id of currentProduct and perform logic on
        // receiving categories
        int[] currentProductPrice = new int[1];
        if (platform.equals("tiki")) {
            tikiCandidates.removeIf(product -> {
                if (product.getId() == id) {
                    currentProductPrice[0] = product.getCurrentPrice();
                    return true;
                } else
                    return false;
            });
            categories = categoryService.getCategoriesBasedOnTikiCate(String.valueOf(categoryId));
        } else if (platform.equals("shopee")) {
            shopeeCandidates.removeIf(product -> {
                if (product.getId() == id) {
                    currentProductPrice[0] = product.getCurrentPrice();
                    return true;
                } else
                    return false;
            });
            categories = categoryService.getCategoriesBasedOnShopeeCate(String.valueOf(categoryId));
        }
        Set<String> tikiEqualCateStrings = new HashSet<String>();
        Set<String> shopeeEqualCateStrings = new HashSet<String>();
        if (platform.equals("tiki")) {
            tikiEqualCateStrings.add(String.valueOf(categoryId));
        } else {
            shopeeEqualCateStrings.add(String.valueOf(categoryId));
        }
        for (MappedLastCategory category : categories) {
            String[] shopeeCateStrings = category.getShopeeId().split("\\|");
            for (String categoryString : shopeeCateStrings) {
                shopeeEqualCateStrings.add(categoryString);
            }
            String[] tikiCateStrings = category.getTikiId().split("\\|");
            for (String categoryString : tikiCateStrings) {
                tikiEqualCateStrings.add(categoryString);
            }
        }

        // for each of the candidates, remove those are not same category as the
        // currentProduct's.
        tikiCandidates
                .removeIf(tikiProduct -> !tikiEqualCateStrings.contains(String.valueOf(tikiProduct.getCategoryId())));
        shopeeCandidates.removeIf(
                shopeeProduct -> !shopeeEqualCateStrings.contains(String.valueOf(shopeeProduct.getCategoryId())));

        // Then eliminate those have a text similarity less then 80%
        // CosineDistance cosineDistance = new CosineDistance();
        // tikiCandidates.removeIf(product -> {
        // // String test = optimizeWord(product.getName(), resultSet);
        // return cosineDistance.apply(optimizedName, optimizeWord(product.getName(),
        // resultSet)) > 0.2;
        // });
        // shopeeCandidates.removeIf(product -> {
        // // String test = optimizeWord(product.getName(), resultSet);
        // return cosineDistance.apply(optimizedName, optimizeWord(product.getName(),
        // resultSet)) >= 0.2;
        // });

        // remove products that are more expensive than the current one
        List<TikiProduct> belowCurrentPriceTikiProducts = new ArrayList<TikiProduct>();
        List<ShopeeProduct> belowCurrentPriceShopeeProducts = new ArrayList<ShopeeProduct>();
        for (ShopeeProduct shopeeProduct : shopeeCandidates) {
            if (shopeeProduct.getCurrentPrice() <= currentProductPrice[0]) {
                belowCurrentPriceShopeeProducts.add(shopeeProduct);
            }
        }
        for (TikiProduct tikiProduct : tikiCandidates) {
            if (tikiProduct.getCurrentPrice() <= currentProductPrice[0]) {
                belowCurrentPriceTikiProducts.add(tikiProduct);
            }
        }
        if (belowCurrentPriceTikiProducts.size() != 0) {
            relevantProducts.addAll(belowCurrentPriceTikiProducts);
        } else {
            relevantProducts.addAll(tikiCandidates);
        }
        if (belowCurrentPriceShopeeProducts.size() != 0) {
            relevantProducts.addAll(belowCurrentPriceShopeeProducts);
        } else {
            relevantProducts.addAll(shopeeCandidates);
        }
        relevantProducts.sort(new Comparator<CommonProperty>() {

            @Override
            public int compare(CommonProperty o1, CommonProperty o2) {
                // sort in decending order
                return -(Float.compare(o1.getCommonScoreProperty(), o2.getCommonScoreProperty()));
            }

        });
        // give back results to client
        ObjectMapper objectMapper = new ObjectMapper();
        String relavantProductsJsonString = null;
        try {
            relavantProductsJsonString = objectMapper.writeValueAsString(relevantProducts);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(relavantProductsJsonString.length() > 2){
            relavantRepo.opsForValue().setIfAbsent(concatRedisProductId, relavantProductsJsonString);
            relavantRepo.expire(concatRedisProductId, 4, TimeUnit.HOURS);
        }
        return relavantProductsJsonString;
    }

    private int calculateRelavanceRate(TikiProduct currentProduct, TikiProduct candidate) {
        return 0;
    }

    private void populateProductAndSellerApiFromUrl(String url, String productApi, String sellerApi) {
        String candidateProductId;
        String candidateSellerId;
        if (url.contains("tiki.vn")) {
            // find the product id from the url
            int endSubStringIndex = url.lastIndexOf('.');
            String subString = url.substring(0, endSubStringIndex);
            int startProductStringIndex = subString.lastIndexOf('-') + 2;
            candidateProductId = subString.substring(startProductStringIndex, subString.length());

            productApi = "https://tiki.vn/api/v2/products/" + candidateProductId;
        } else {
            int endSubStringIndex = url.lastIndexOf('?');
            String subString = url.substring(0, endSubStringIndex);
            String[] splittedStrings = subString.split("/");
            candidateProductId = splittedStrings[splittedStrings.length - 1];
            candidateSellerId = splittedStrings[splittedStrings.length - 2];

            productApi = "https://shopee.vn/api/v2/item/get?itemid=" + candidateProductId + "&shopid="
                    + candidateSellerId + "&fbclid=-";
            sellerApi = SHOPEE_BASE_URL + "api/v4/product/get_shop_info?shopid=" + candidateSellerId;
        }
    }

    // private Product mappingToProduct(JsonNode jsonProductNode, JsonNode
    // jsonSellerNode, String platform) {
    // Product currentProduct = new Product();
    // if (platform.equals("tiki")) {
    // currentProduct.setId(jsonProductNode.get("id").asText());
    // currentProduct.setName(jsonProductNode.get("name").textValue());
    // currentProduct.setPrice(jsonProductNode.get("price").intValue());
    // currentProduct.setThumbnailUrl(jsonProductNode.get("thumbnail_url").textValue());
    // currentProduct.setSeller(jsonProductNode.get("current_seller").get("name").textValue());
    // currentProduct.setUrl(TIKI_BASE_URL +
    // (jsonProductNode.get("url_path").textValue()));
    // String categoriesString =
    // (jsonProductNode.get("productset_group_name").asText());
    // String categories[] = categoriesString.split("/");
    // currentProduct.setCategoryId(categories[categories.length - 1]);
    // // retrieve specification node for working with product's attributes
    // Map<String, String> attributeMap = new HashMap<String, String>();
    // JsonNode attributeNode =
    // (jsonProductNode.get("specifications").elements().next());
    // attributeNode.get("attributes").forEach(node -> {
    // attributeMap.put(node.get("name").textValue(), node.get("value").asText());
    // });
    // currentProduct.setProductAttribute(attributeMap);
    // } else {
    // currentProduct.setId(jsonProductNode.get("item").get("itemid").asText());
    // currentProduct.setName(jsonProductNode.get("item").get("name").textValue());
    // long price = jsonProductNode.get("item").get("price_min").longValue();
    // currentProduct.setPrice((int) (price / 100000)); // dont know why shopee give
    // me price like this @#$#
    // currentProduct
    // .setThumbnailUrl(SHOPEE_IMAGE_BASE_URL +
    // jsonProductNode.get("item").get("image").textValue());
    // currentProduct.setUrl(SHOPEE_BASE_URL + "product/" + sellId + "/" +
    // productId);
    // if (jsonSellerNode != null) {
    // currentProduct.setSeller(jsonSellerNode.get("data").get("name").textValue());
    // }
    // Iterator<JsonNode> categoryIterator =
    // jsonProductNode.get("item").get("categories").elements();
    // List<JsonNode> categoryList   = new ArrayList<JsonNode>();
    // while (categoryIterator.hasNext()) {
    // categoryList.add(categoryIterator.next());
    // }
    // currentProduct.setCategoryId(categoryList.get(categoryList.size() -
    // 1).get("catid").asText());
    // // retrieve specification node for working with product's attributes
    // Map<String, String> attributeMap = new HashMap<String, String>();
    // JsonNode attributeNode = jsonProductNode.get("item").get("attributes");
    // attributeNode.forEach(node -> {
    // attributeMap.put(node.get("name").textValue(), node.get("value").asText());
    // });
    // currentProduct.setProductAttribute(attributeMap);
    // }
    // return currentProduct;
    // }

    public String optimizeWord(String name, Set<String> stopwordsSet) {
        // First, remove the special characters from string
        String bracketsAndContentsPattern = "\\[.*?\\]";
        String specialCharsPattern = "[\\@\\!\\#\\$\\%\\^\\&\\*\\(\\)\\-\\'\\;\\,\\.\\/\\?\\>\\<\\+\\[\\]\\_\\|]";
        String bracketsRemovedText = name.replaceAll(bracketsAndContentsPattern, " ");
        String removedSpecialCharsString = bracketsRemovedText.replaceAll(specialCharsPattern, " ");

        // segment words and compare them to the stopwords list.But before that if the
        // stopwordsSet contains 0 element, we could just return the
        // removedSpecialCharsString
        if (stopwordsSet.size() == 0) {
            return removedSpecialCharsString;
        }
        List<String> seperatedWordsList = seperateWordFromString(removedSpecialCharsString);
        StringBuilder result = new StringBuilder();
        for (String word : seperatedWordsList) {
            if (!stopwordsSet.contains(word)) {
                result.append(word);
                result.append(" ");
            }
        }
        String optimizedWord = result.toString().replaceAll("_", " ").trim();

        return optimizedWord;
    }

    private List<String> seperateWordFromString(String removedSpecialCharsString) {
        String[] annotators = { "wseg" };
        List<String> words = new ArrayList<String>();
        Annotation annotation = null;
        try {
            VnCoreNLP pipeline = new VnCoreNLP(annotators);
            annotation = new Annotation(removedSpecialCharsString);
            pipeline.annotate(annotation);
            String segmentedTextLines = annotation.getSentences().get(0).toString();
            List<String> wordLines = Arrays.asList(segmentedTextLines.split("\\n"));
            for (String line : wordLines) {
                words.add(line.split("\\t")[1].toLowerCase());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IndexOutOfBoundsException outOfBoundsException) {
        }
        return words;
    }
}