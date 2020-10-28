# [返回Week_02主页](index.md)

# 作业要求
第四节课作业实践
2、写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到Github。

# 简述
使用之前练习的一个 [咖啡 Rest API 小程序](course4-question2/complex-controller)，同时参考如下链接，写了一个简单的 [HttpClient小程序](course4-question2/httpclient-demo)，演练如何使用 HttpClient 访问咖啡Rest API：
- [Java HTTP Client](Java HttpClient API Tutorial with Examples)
- [Introduction to the Java HTTP Client](https://openjdk.java.net/groups/net/httpclient/intro.html)
- [Examples and Recipes](https://openjdk.java.net/groups/net/httpclient/recipes.html)
- [Java HttpClient API Tutorial with Examples](https://hellokoding.com/java-http-client-api/)

# 讲解
1. 首先要运行 course4-question2/complex-controller 这个咖啡系统。
2. 然后运行 course4-question2/httpclient-demo 示例工程，其中 **OrderClient.java** 演示了HttpClient的基本用法。

## getCoffeeMenu
Source Code:

    public void getCoffeeMenu() throws IOException, InterruptedException {
        System.out.println("\nGet coffee menu");
        String uri = "http://localhost:8801/coffee/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

给 http://localhost:8801/coffee/ 发送一个GET请求。

## getCoffeeByName
Source Code:

    public void getCoffeeByName(String name) throws IOException, InterruptedException {
        System.out.println("\nGet coffee by name: " + name);
        String uri = "http://localhost:8801/coffee/?name=" + name;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

给 http://localhost:8801/coffee/?name=name 发送一个GET请求。

## getCoffeeById
Source Code:

    public void getCoffeeById(int id) throws IOException, InterruptedException {
        System.out.println("\nGet coffee by id: " + id);
        String uri = "http://localhost:8801/coffee/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Accept", "application/json;charset=UTF-8")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

给 http://localhost:8801/coffee/id 发送一个GET请求，同时使用Header:
- Accept: application/json;charset=UTF-8

## createOrder
Source Code:

    public void createOrder(Order order) throws IOException {
        System.out.println("\nCreate an order");
        String uri = "http://localhost:8801/order/";
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        CompletableFuture<HttpResponse<String>> completableFuture =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        completableFuture.thenApplyAsync(HttpResponse::headers)
                .thenAcceptAsync(System.out::println);
        HttpResponse<String> response = completableFuture.join();
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

给 http://localhost:8801/order/ 发送一个POST请求，同时使用Header:
- Content-Type", "application/json
- Accept: application/json;charset=UTF-8

## 程序运行结果

    Get coffee menu
    Response status code: 200
    Response body: [{"id":1,"createTime":"2020-10-28T14:27:28.993+00:00","updateTime":"2020-10-28T14:27:28.993+00:00","name":"espresso","price":{"amountMinor":2000,"amountMajorInt":20,"amountMajorLong":20,"amountMinorInt":2000,"minorPart":0,"positiveOrZero":true,"amountMinorLong":2000,"negativeOrZero":false,"amountMajor":20,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":20.00,"scale":2,"negative":false,"zero":false,"positive":true}},{"id":2,"createTime":"2020-10-28T14:27:29.014+00:00","updateTime":"2020-10-28T14:27:29.014+00:00","name":"latte","price":{"amountMinor":2500,"amountMajorInt":25,"amountMajorLong":25,"amountMinorInt":2500,"minorPart":0,"positiveOrZero":true,"amountMinorLong":2500,"negativeOrZero":false,"amountMajor":25,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":25.00,"scale":2,"negative":false,"zero":false,"positive":true}},{"id":3,"createTime":"2020-10-28T14:27:29.015+00:00","updateTime":"2020-10-28T14:27:29.015+00:00","name":"capuccino","price":{"amountMinor":2500,"amountMajorInt":25,"amountMajorLong":25,"amountMinorInt":2500,"minorPart":0,"positiveOrZero":true,"amountMinorLong":2500,"negativeOrZero":false,"amountMajor":25,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":25.00,"scale":2,"negative":false,"zero":false,"positive":true}},{"id":4,"createTime":"2020-10-28T14:27:29.015+00:00","updateTime":"2020-10-28T14:27:29.015+00:00","name":"mocha","price":{"amountMinor":3000,"amountMajorInt":30,"amountMajorLong":30,"amountMinorInt":3000,"minorPart":0,"positiveOrZero":true,"amountMinorLong":3000,"negativeOrZero":false,"amountMajor":30,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":30.00,"scale":2,"negative":false,"zero":false,"positive":true}},{"id":5,"createTime":"2020-10-28T14:27:29.016+00:00","updateTime":"2020-10-28T14:27:29.016+00:00","name":"macchiato","price":{"amountMinor":3000,"amountMajorInt":30,"amountMajorLong":30,"amountMinorInt":3000,"minorPart":0,"positiveOrZero":true,"amountMinorLong":3000,"negativeOrZero":false,"amountMajor":30,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":30.00,"scale":2,"negative":false,"zero":false,"positive":true}}]

    Get coffee by id: 2
    Response status code: 200
    Response body: {"id":2,"createTime":"2020-10-28T14:27:29.014+00:00","updateTime":"2020-10-28T14:27:29.014+00:00","name":"latte","price":{"amountMinor":2500,"amountMajorInt":25,"amountMajorLong":25,"amountMinorInt":2500,"minorPart":0,"positiveOrZero":true,"amountMinorLong":2500,"negativeOrZero":false,"amountMajor":25,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":25.00,"scale":2,"negative":false,"zero":false,"positive":true}}

    Get coffee by name: latte
    Response status code: 200
    Response body: {"id":2,"createTime":"2020-10-28T14:27:29.014+00:00","updateTime":"2020-10-28T14:27:29.014+00:00","name":"latte","price":{"amountMinor":2500,"amountMajorInt":25,"amountMajorLong":25,"amountMinorInt":2500,"minorPart":0,"positiveOrZero":true,"amountMinorLong":2500,"negativeOrZero":false,"amountMajor":25,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":25.00,"scale":2,"negative":false,"zero":false,"positive":true}}

    Create an order
    Response status code: 201
    Response body: {"id":2,"createTime":"2020-10-28T14:29:45.020+00:00","updateTime":"2020-10-28T14:29:45.020+00:00","customer":"Feng Du","items":[{"id":2,"createTime":"2020-10-28T14:27:29.014+00:00","updateTime":"2020-10-28T14:27:29.014+00:00","name":"latte","price":{"amountMinor":2500,"amountMajorInt":25,"amountMajorLong":25,"amountMinorInt":2500,"minorPart":0,"positiveOrZero":true,"amountMinorLong":2500,"negativeOrZero":false,"amountMajor":25,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":25.00,"scale":2,"negative":false,"zero":false,"positive":true}},{"id":4,"createTime":"2020-10-28T14:27:29.015+00:00","updateTime":"2020-10-28T14:27:29.015+00:00","name":"mocha","price":{"amountMinor":3000,"amountMajorInt":30,"amountMajorLong":30,"amountMinorInt":3000,"minorPart":0,"positiveOrZero":true,"amountMinorLong":3000,"negativeOrZero":false,"amountMajor":30,"currencyUnit":{"code":"CNY","numericCode":156,"decimalPlaces":2,"pseudoCurrency":false,"numeric3Code":"156","countryCodes":["CN"],"symbol":"CN¥"},"amount":30.00,"scale":2,"negative":false,"zero":false,"positive":true}}],"state":"INIT"}
