package com.example.rabbitmq;

import com.example.rabbitmq.repositories.AnnouncementSubscriberRepository;
import com.example.rabbitmq.services.GrpcBackendService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

	private final GrpcBackendService grpcBackendService;

	@Autowired
	public KafkaApplication(GrpcBackendService grpcBackendService) {
		this.grpcBackendService = grpcBackendService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Server server = ServerBuilder.forPort(9091)
				.addService(grpcBackendService)
				.build();

		System.out.println("gRPC сервер запущен на порту 9091...");
		server.start();
		server.awaitTermination();
	}
}

/**

 SELECT
 a.id AS announcement_id,
 a.price AS announcement_price,
 a.number_of_rooms AS announcement_rooms,
 a.market_price AS announcement_market_price,
 a.id_city_grpc AS announcement_city_id,
 s.id AS subscriber_id,
 s.user_email AS subscriber_email,
 s.sub_city AS subscriber_city,
 s.sub_price AS subscriber_price,
 s.sub_num_of_rooms AS subscriber_rooms
 FROM
 public.subscriber_grpc s
 JOIN
 public.city_grpc c
 ON c.city = s.sub_city
 JOIN
 public.announcement_grpc a
 ON a.id_city_grpc = c.id
 AND a.price <= s.sub_price
 AND a.number_of_rooms = s.sub_num_of_rooms
 ORDER BY
 a.price;

 */