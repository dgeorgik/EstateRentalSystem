package com.example.rabbitmq.repositories;

import com.example.rabbitmq.models.AnnouncementSubscriberResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project Grpc-backend
 * @Date 05.12.2024
 */

@Repository
public interface AnnouncementSubscriberRepository extends JpaRepository<AnnouncementSubscriberResult, Integer> {

    @Query(value = "SELECT DISTINCT * " +
            "    FROM subscriber_announcement_view " +
            "    WHERE sub_city = :city " +
            "      AND sub_price >= :price " +
            "      AND sub_num_of_rooms = :rooms", nativeQuery = true)
        List<AnnouncementSubscriberResult> findMatchingSubscribers(@Param("city") String city,
    @Param("price") Integer price,
    @Param("rooms") Integer rooms);

}