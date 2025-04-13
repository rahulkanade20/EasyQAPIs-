package org.example.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.example.config.RestTemplateConfig;
import org.example.service.EasyQApiServiceI;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

// Use virtual threads introduced in java 21
public class EasyQApiMultithreadedClient3 implements EasyQApiClientI {

    private final AnnotationConfigApplicationContext context;
    private final EasyQApiServiceI easyQApiService;
    private final String QUE_ID = "6";
    private final String QUEUE_ID = "queueId";
    private final String EMAIL = "email";
    private final String URL = "http://localhost:8080/users/join";
    private final HttpHeaders httpHeaders;

    public EasyQApiMultithreadedClient3() {
        context = new AnnotationConfigApplicationContext(RestTemplateConfig.class);
        easyQApiService = context.getBean(EasyQApiServiceI.class);
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void loadTest() {
        long startTime = System.currentTimeMillis();
        int load = 10000;
        List<Thread> list = new ArrayList<>();
        int missedRequests = 0;
        for(int i = 1; i<=load; i++) {
            Thread thread = Thread.startVirtualThread(() -> {
                Map<String, Object> params = new HashMap<>();
                params.put(QUEUE_ID, QUE_ID);
                params.put(EMAIL, generateRandomEmail());
                easyQApiService.get(URL, httpHeaders, params, String.class);
            });
            try {
                Thread.sleep(2);  // Adjust sleep time based on your needs
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            list.add(thread);
        }

        for(Thread thread : list) {
            try {
                thread.join();
            } catch(Exception e) {
//                e.printStackTrace();
                System.out.println("Exception");
                missedRequests++;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Duration in milliseconds: " + (endTime - startTime));
        System.out.println("Duration in seconds: " + (endTime - startTime) / 1_000);
        System.out.println("Number of requests dropped by server: " + missedRequests);
    }

    public String generateRandomEmail() {
        return "test_" + UUID.randomUUID() + "@example.com";
    }
}
