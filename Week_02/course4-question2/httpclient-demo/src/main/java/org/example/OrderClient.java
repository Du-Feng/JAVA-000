package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OrderClient {
    public void getCoffeeMenu() throws IOException, InterruptedException {
        System.out.println("\nGet coffee menu");
        String uri = "http://localhost:8080/coffee/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

    public void getCoffeeByName(String name) throws IOException, InterruptedException {
        System.out.println("\nGet coffee by name: " + name);
        String uri = "http://localhost:8080/coffee/?name=" + name;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

    public void getCoffeeById(int id) throws IOException, InterruptedException {
        System.out.println("\nGet coffee by id: " + id);
        String uri = "http://localhost:8080/coffee/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Accept", "application/json;charset=UTF-8")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

    public void createOrder(String uri, Order order) throws IOException {
        System.out.println("\nCreate an order");
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
}
