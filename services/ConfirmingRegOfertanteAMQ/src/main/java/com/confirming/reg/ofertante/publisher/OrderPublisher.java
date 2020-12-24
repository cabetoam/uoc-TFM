package com.confirming.reg.ofertante.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.confirming.reg.ofertante.config.MessagingConfig;
import com.confirming.reg.ofertante.dto.Order;
import com.confirming.reg.ofertante.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder(order);
		orderStatus.setStatus("PROCESS");
		orderStatus.setMessage("order placed succesfully in " + restaurantName);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, orderStatus);
		return "Sucess !!";
	}	
}
