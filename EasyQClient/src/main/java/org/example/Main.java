package org.example;

import org.example.client.EasyQApiClient;
import org.example.client.EasyQApiClientI;
import org.example.client.EasyQApiMultithreadedClient;
import org.example.client.EasyQApiMultithreadedClient2;
import org.example.client.EasyQApiMultithreadedClient1;
import org.example.client.EasyQApiMultithreadedClient3;

public class Main {
    public static void main(String[] args) {
        EasyQApiClientI client = new EasyQApiClient();
        EasyQApiClientI multiThreadedClient = new EasyQApiMultithreadedClient();
        EasyQApiClientI multiThreadedClient1 = new EasyQApiMultithreadedClient1();
        EasyQApiClientI multiThreadedClient2 = new EasyQApiMultithreadedClient2();
        EasyQApiClientI multiThreadedClient3 = new EasyQApiMultithreadedClient3();


//        client.loadTest();
//        multiThreadedClient.loadTest();
        multiThreadedClient1.loadTest();
//        multiThreadedClient2.loadTest();
//        multiThreadedClient3.loadTest();
    }
}