package com.EasyQ.API.services;

import com.EasyQ.API.dto.QueueDto;
import com.EasyQ.API.dto.QueueMapper;
import com.EasyQ.API.models.Queue;
import com.EasyQ.API.repository.QueueRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class QueueService {
    Logger logger = LoggerFactory.getLogger(QueueService.class);

//    @Autowired
//    public QueueMapper queueMapper; ... can be used but constructor injection preferred over field injection.

    private final QueueMapper queueMapper;

    private final QueueRepository queueRepository;

    public QueueService(QueueRepository queueRepository, QueueMapper queueMapper) {
        this.queueRepository = queueRepository;
        this.queueMapper = queueMapper;
    }

    public List<QueueDto> getAllQueues() {
        List<QueueDto> queues = new ArrayList<>();
        logger.info("Fetching all queues ...");
        queueRepository.findAll().forEach(q -> {
            try {
                QueueDto queue = new QueueDto();
                BeanUtils.copyProperties(queue, q);
                queues.add(queue);
            } catch (Exception e) {
                logger.error(e.toString());
            }
        });
        return queues;
    }

    public QueueDto getQueue(Long id) {
        logger.info("Fetching queue ...");
        QueueDto queue = new QueueDto();
        try {
            Optional<Queue> q = queueRepository.findById(id);
            BeanUtils.copyProperties(queue, q.get());
        } catch(Exception e) {
            logger.error(e.toString());
        }
        return queue;
    }

    public void addQueue(QueueDto dto) {
        logger.info("adding queue ...");
        Queue q = new Queue();
        queueMapper.updateQueueFromDto(dto, q);
        q.setCreation_time(LocalDateTime.now());
        q.setUpdation_time(null);
        queueRepository.save(q);
    }

    public void updateQueue(Long id, QueueDto dto) {
        logger.info("updating queue ...");
        Optional<Queue> queue = queueRepository.findById(id);
        try {
            Queue q = queue.get();
            queueMapper.updateQueueFromDto(dto, q);
            q.setUpdation_time(LocalDateTime.now());
            queueRepository.save(q);
        } catch (NoSuchElementException e) {
            logger.info(e.toString());
        }
    }

    public void deleteQueue(Long id) {
        logger.info("deleting queue ...");
        queueRepository.deleteById(id);
    }
}
