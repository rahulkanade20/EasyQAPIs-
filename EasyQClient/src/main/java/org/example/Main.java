package org.example;

import org.example.client.EasyQApiClient;
import org.example.client.EasyQApiClientI;
import org.example.client.EasyQApiMultithreadedClient;

public class Main {
    public static void main(String[] args) {
        EasyQApiClientI client = new EasyQApiClient();
        EasyQApiClientI multiThreadedClient1 = new EasyQApiMultithreadedClient();

//        client.loadTest();
        multiThreadedClient1.loadTest();
    }
}