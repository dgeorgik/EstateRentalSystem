package com.example.project.repository;

import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM users_grpc usr " +
                    "WHERE (:nameUser IS NULL OR usr.name = :nameUser)" +
                    "AND (:userEmail IS NULL OR usr.email = :userEmail)",
            nativeQuery = true)
    User getUserByNameAndEmail(@Param("nameUser") String nameUser,
                                   @Param("userEmail") String userEmail);

}
