package com.EasyQ.API.controllers;

import com.EasyQ.API.queuemanagement.SQManager;
import com.EasyQ.API.queuemanagement.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserQueueController {

    private final SQManager sqManager;

    public UserQueueController(SQManager sqManager) {
        this.sqManager = sqManager;
    }

    @GetMapping("/test")
    public String getTestString() {
        return "hello from user controller";
    }

    @GetMapping("/queue")
    public ResponseEntity<?> printQueue() {
        return ResponseEntity.ok(SQManager.queue);
    }

    @GetMapping("/join")
    public ResponseEntity<?> join(@RequestParam("email") String emailId, @RequestParam("queueId") long queueId) {
        int response = sqManager.addUser(new User(queueId, emailId));
        if(response == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("User added successfully to the queue");
        } else if(response == -1) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Some error occurred, please try again");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid email id or queue id");
    }

    @GetMapping("/leave")
    public ResponseEntity<?> leave(@RequestParam("email") String emailId, @RequestParam("queueId") long queueId) {
        int response = sqManager.removeUser(queueId, emailId);
        if(response == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("User removed successfully from the queue");
        } else if(response == -1) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Some error occurred, please try again");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid email id or queue id");
    }
}
