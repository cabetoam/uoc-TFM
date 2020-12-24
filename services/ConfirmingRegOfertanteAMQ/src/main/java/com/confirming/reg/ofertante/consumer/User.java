package com.confirming.reg.ofertante.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.confirming.reg.ofertante.config.MessagingConfig;
import com.confirming.reg.ofertante.dto.OrderStatus;

@Component
public class User {
	
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
		
		System.out.println("Message Request from queue : "+ orderStatus.getStatus() + " - " + orderStatus.getMessage());
		
	}

}
