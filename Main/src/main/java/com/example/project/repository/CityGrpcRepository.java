package com.example.project.repository;

import com.example.project.model.CityGrpc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
@Repository
public interface CityGrpcRepository extends JpaRepository<CityGrpc, Long> {
}
