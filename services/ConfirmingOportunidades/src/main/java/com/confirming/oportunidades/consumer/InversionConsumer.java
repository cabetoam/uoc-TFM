package com.confirming.oportunidades.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.confirming.oportunidades.dto.InversionStatus;
import com.confirming.oportunidades.model.Inversion;
import com.confirming.oportunidades.repository.InversionRepository;

@Component
public class InversionConsumer {
	
	@Autowired
	private InversionRepository repositoryInversor;
	
	@RabbitListener(queues = com.confirming.oportunidades.config.MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(InversionStatus inversionStatus) {
		
		System.out.println("Message Request from queue : "+ inversionStatus.getStatus() + " - " + inversionStatus.getMessage());
		Inversion inversion = new Inversion();
		
		inversion.setId(inversionStatus.getInversion().getId());
		inversion.setNombreOfertante(inversionStatus.getInversion().getNombreOfertante());
		inversion.setMoneda(inversionStatus.getInversion().getMoneda());
		inversion.setValor(inversionStatus.getInversion().getValor());
		inversion.setFechaCompra(inversionStatus.getInversion().getFechaCompra());
		inversion.setEmailComprador(inversionStatus.getInversion().getEmailComprador());
		
		repositoryInversor.save(inversion);

		
	}

}
