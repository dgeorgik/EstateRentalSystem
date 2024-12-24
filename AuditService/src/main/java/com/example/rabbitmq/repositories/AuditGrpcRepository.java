package com.example.rabbitmq.repositories;

import com.example.rabbitmq.models.AuditGrpc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditGrpcRepository extends JpaRepository<AuditGrpc, Long> {

    @Query("SELECT a FROM AuditGrpc a WHERE FUNCTION('MONTH', a.create) = :month AND FUNCTION('YEAR', a.create) = :year")
    List<AuditGrpc> findByMonthAndYear(int month, int year);

}
