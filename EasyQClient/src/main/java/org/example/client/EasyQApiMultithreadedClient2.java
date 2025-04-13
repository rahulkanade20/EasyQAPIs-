package org.example.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.config.RestTemplateConfig;
import org.example.service.EasyQApiServiceI;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

// Use features introduced in java 8, Completable Future
public class EasyQApiMultithreadedClient2 implements EasyQApiClientI {

    private final AnnotationConfigApplicationContext context;
    private final EasyQApiServiceI easyQApiService;
    private final String QUE_ID = "6";
    private final int THREAD_POOL_SIZE = 10;
    private final String QUEUE_ID = "queueId";
    private final String EMAIL = "email";
    private final String URL = "http://localhost:8080/users/join";
    private final HttpHeaders httpHeaders;
    private final ExecutorService executorService;

    public EasyQApiMultithreadedClient2() {
        context = new AnnotationConfigApplicationContext(RestTemplateConfig.class);
        easyQApiService = context.getBean(EasyQApiServiceI.class);
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    @Override
    public void loadTest() {
        long startTime = System.currentTimeMillis();
        int load = 10000;
        AtomicInteger missedRequests = new AtomicInteger();
        List<CompletableFuture<?>> list = new ArrayList<>();
        for(int i = 1; i<=load; i++) {
            CompletableFuture<?> future = CompletableFuture.runAsync(() -> {
                Map<String, Object> params = new HashMap<>();
                params.put(QUEUE_ID, QUE_ID);
                params.put(EMAIL, generateRandomEmail());
                easyQApiService.get(URL, httpHeaders, params, String.class);
            }, executorService)
                    .exceptionally((ex) -> {
                        ex.printStackTrace();
                        missedRequests.getAndIncrement();
                        return null;
                    });
            list.add(future);
        }
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
        long endTime = System.currentTimeMillis();
        System.out.println("Duration in milliseconds: " + (endTime - startTime));
        System.out.println("Duration in seconds: " + (endTime - startTime) / 1_000);
        System.out.println("Number of requests dropped by server: " + missedRequests);
    }

    public String generateRandomEmail() {
        return "test_" + UUID.randomUUID() + "@example.com";
    }
}
