package com.confirming.oportunidades.consumer;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.confirming.oportunidades.dto.InversionStatus;
import com.confirming.oportunidades.model.Factura;
import com.confirming.oportunidades.model.Inversion;
import com.confirming.oportunidades.model.Oportunidad;
import com.confirming.oportunidades.repository.DynamicQuery;
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
		inversion.setValorCompra(inversionStatus.getInversion().getValorCompra());
		inversion.setFechaCompra(inversionStatus.getInversion().getFechaCompra());
		inversion.setUsernameComprador(inversionStatus.getInversion().getUsernameComprador());
		
		repositoryInversor.save(inversion);		
	}	
}
