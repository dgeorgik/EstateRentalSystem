package com.example.project.services.impl;

import com.example.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.repository.UserRepository;
import com.example.project.services.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
}

    @Override
    public User getUserByNameAndEmail(String name, String rmail) {
        return userRepository.getUserByNameAndEmail(name,rmail);
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Users found: " + users.size()); // Выводим количество пользователей
        return users;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
