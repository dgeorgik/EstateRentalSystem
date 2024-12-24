package com.example.project.repository;

import com.example.project.model.SubscriberGrpc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 09.09.2024
 */

@Repository
public interface SubscriberGrpcRepository extends JpaRepository<SubscriberGrpc, Long> {

    @Query(
            value = "SELECT * FROM subscriber_grpc sub " +
                    "WHERE (:userEmail IS NULL OR sub.user_email = :userEmail) " +
                    "AND (:subCity IS NULL OR sub.sub_city = :subCity) " +
                    "AND (:subPrice IS NULL OR sub.sub_price = :subPrice) " +
                    "AND (:subRooms IS NULL OR sub.sub_num_of_rooms = :subRooms)",
            nativeQuery = true)
    List<SubscriberGrpc> getSubscriberGrpcByParams(
            @Param("userEmail") String userEmail,
            @Param("subCity") String subCity,
            @Param("subPrice") Long subPrice,
            @Param("subRooms") Long subRooms
    );
}
