package com.EasyQ.API.queuemanagement;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class SQManager {
    AtomicInteger userCount = new AtomicInteger(0);
    public static LinkedList<User> queue;
    private int status = 0;

    private void updateEta() {
        for (int i = 0; i< queue.size(); i++) {
            queue.get(i).setEta(i*15);
        }
    }

    public void startQ() {
        status = 1;
        queue = new LinkedList<User>();
        System.out.println("Queue started");
    }

    public void stopQ() {
        queue = null;
        System.out.println("Queue stopped");
    }

    public int removeUser(long queueId, String emailAddress) {
        if(queue == null) {
            return -1;
        }
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).getQueueId() == queueId && queue.get(i).getEmailAddress().equals(emailAddress)) {
                System.out.println("inside if name:" + emailAddress);
                queue.remove(queue.get(i));
                updateEta();
                return 0;
            }
        }
        return -2;
    }

    public int addUser(User u) {
        System.out.println("Total number of users in queue before adding new user: " + userCount.incrementAndGet());
        if (status == 1) {
            if(queue == null) {
                return -1;
            }
            queue.add(u);
            updateEta();
            return 0;
        } else {
            System.out.println("queue not started");
            return -2;
        }
    }

    public void displayQ() {
        for (int i = 0; i< queue.size(); i++) {
            System.out.println(queue.get(i).getEmailAddress() + " " + String.valueOf(queue.get(i).getEta()));
        }
    }
}
