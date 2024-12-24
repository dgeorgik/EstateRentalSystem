package com.example.kafka.Kafka;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class KafkaApplication {
	private final SocketIOServer socketIOServer;

	public KafkaApplication(SocketIOServer socketIOServer) {
		this.socketIOServer = socketIOServer;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void startSocketServer() {
		socketIOServer.start();
	}

	@PreDestroy
	public void stopSocketServer() {
		socketIOServer.stop();
	}
}
