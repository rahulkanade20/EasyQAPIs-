package com.EasyQ.API.controllers;

import com.EasyQ.API.dto.QueueDto;
import com.EasyQ.API.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/queues")
public class QueueController {
    Logger logger = LoggerFactory.getLogger(QueueController.class);

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/test")
    public String getTestString() {
        return "hello";
    }

    @GetMapping("")
    public List<QueueDto> getAllQueues() {
        return queueService.getAllQueues();
    }

    @GetMapping("/{id}")
    public QueueDto getQueue(@PathVariable Long id) {
        return queueService.getQueue(id);
    }

    @PostMapping("")
    public void addQueue(@RequestBody QueueDto dto) {
        queueService.addQueue(dto);
    }

    @PatchMapping("/{id}")
    public void updateQueue(@RequestBody QueueDto queueDto, @PathVariable Long id) {
        logger.info(queueDto.toString());
        queueService.updateQueue(id, queueDto);
    }

    @DeleteMapping("/{id}")
    public void deleteQueue(@PathVariable Long id) {
        queueService.deleteQueue(id);
    }
}
