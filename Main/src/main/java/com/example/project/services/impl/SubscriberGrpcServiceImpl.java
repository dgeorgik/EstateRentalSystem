package com.example.project.services.impl;

import com.example.project.model.SubscriberGrpc;
import com.example.project.model.User;
import com.example.project.model.sub_class.SubscriptionResponse;
import com.example.project.repository.SubscriberGrpcRepository;
import com.example.project.repository.UserRepository;
import com.example.project.services.SubscriberGrpcService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 09.09.2024
 */

@Service
public class SubscriberGrpcServiceImpl implements SubscriberGrpcService {


   private final SubscriberGrpcRepository subscriberGrpcRepository;
   private final UserRepository userRepository;

    public SubscriberGrpcServiceImpl(SubscriberGrpcRepository subscriberGrpcRepository, UserRepository userRepository) {
        this.subscriberGrpcRepository = subscriberGrpcRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SubscriberGrpc getSubscriberGrpcById(Long id) {
        return subscriberGrpcRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscriber not found with id: " + id));

    }

    @Override
    public List<SubscriberGrpc> getSubscriberGrpcByParams(String userEmail, String subCity, Long subPrice, Long subRooms) {
        return subscriberGrpcRepository.getSubscriberGrpcByParams(userEmail, subCity, subPrice, subRooms);
    }


    @Override
    public List<SubscriberGrpc> getAllSubscriberGrpc() {
        return subscriberGrpcRepository.findAll();
    }

    @Override
    public ResponseEntity<SubscriptionResponse> createSubscriberGrpc(SubscriberGrpc subscriberGrpc) {
        try {
            if (subscriberGrpc.getUserEmail() == null || subscriberGrpc.getUserEmail().getEmail() == null) {
                throw new IllegalArgumentException("User email must be provided.");
            }
            User user = new User();
            user.setEmail(subscriberGrpc.getUserEmail().getEmail());

            User foundedUser = userRepository.getUserByNameAndEmail(user.getEmail(), subscriberGrpc.getUserEmail().getEmail());
            System.out.println("User found: " + foundedUser);

            if (foundedUser != null) {
                System.out.println("User [" + subscriberGrpc.getUserEmail().getEmail() + "] found: " + foundedUser.getName());
                 SubscriberGrpc newSubscriber = new SubscriberGrpc();
                newSubscriber.setUserEmail(foundedUser);
                newSubscriber.setSubCity(subscriberGrpc.getSubCity());
                newSubscriber.setSubPrice(subscriberGrpc.getSubPrice());
                newSubscriber.setSubNumOfRooms(subscriberGrpc.getSubNumOfRooms());

                 SubscriberGrpc savedSubscriber = subscriberGrpcRepository.save(newSubscriber);

                 SubscriptionResponse response = new SubscriptionResponse("Вы подписались", savedSubscriber);

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                System.out.println("User not found for email: " + subscriberGrpc.getUserEmail().getEmail());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SubscriptionResponse("Пользователь не найден", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SubscriptionResponse("Ошибка при создании подписки", null));
        }
    }





    @Override
    public void deleteSubscriberGrpcById(Long id) {
        if (!subscriberGrpcRepository.existsById(id)) {
            throw new EntityNotFoundException("Subscriber not found with id: " + id);
        }
        subscriberGrpcRepository.deleteById(id);
    }


}
