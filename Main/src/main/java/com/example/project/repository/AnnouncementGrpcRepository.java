package com.example.project.repository;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

import com.example.project.model.AnnouncementGrpc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementGrpcRepository extends JpaRepository<AnnouncementGrpc, Long> {
}



