package com.example.rabbitmq;

import com.example.rabbitmq.services.GrpcClientService;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args)  {
		SpringApplication.run(KafkaApplication.class, args);

	}
}
