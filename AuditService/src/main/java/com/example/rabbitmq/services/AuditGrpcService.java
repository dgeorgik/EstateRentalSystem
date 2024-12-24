package com.example.rabbitmq.services;

import com.example.rabbitmq.models.AuditGrpc;
import com.example.rabbitmq.repositories.AuditGrpcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class AuditGrpcService {

    private final AuditGrpcRepository auditGrpcRepository;

    @Autowired
    public AuditGrpcService(AuditGrpcRepository auditGrpcRepository) {
        this.auditGrpcRepository = auditGrpcRepository;
    }

    @Transactional
    public AuditGrpc saveAuditRecord(String queueName, String message, String userName, String userEmail, Boolean sub, Boolean pub) {
        AuditGrpc auditGrpc = new AuditGrpc();
        auditGrpc.setCreate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        auditGrpc.setRabbitQueue(queueName);
        auditGrpc.setMessage(message);
        auditGrpc.setUserName(userName);
        auditGrpc.setUserEmail(userEmail);
        auditGrpc.setSubscriber(sub);
        auditGrpc.setPublishing(pub);

        return auditGrpcRepository.save(auditGrpc);
    }
}
