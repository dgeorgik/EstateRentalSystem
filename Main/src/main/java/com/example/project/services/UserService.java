package com.example.project.services;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */


import com.example.project.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByNameAndEmail(String name, String rmail);
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUserById(Long id);

}
