package com.confirming.oportunidades.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.confirming.oportunidades.dto.FacturaStatus;
import com.confirming.oportunidades.repository.OportunidadesRepositoryImpl;

@Component
public class FacturaConsumer {
	
	@Autowired
	private OportunidadesRepositoryImpl repository;
	
	@RabbitListener(queues = com.confirming.oportunidades.config.MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(FacturaStatus facturaStatus) {
		
		System.out.println("Message Request from queue : "+ facturaStatus.getStatus() + " - " + facturaStatus.getMessage());		
		repository.queryUpdateStatusFactura(facturaStatus.getIdFactura());		
	}
	

}
