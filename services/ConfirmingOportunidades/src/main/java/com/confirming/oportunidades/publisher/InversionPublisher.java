package com.confirming.oportunidades.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.confirming.oportunidades.dto.InversionStatus;
import com.confirming.oportunidades.model.Inversion;
import com.confirming.oportunidades.model.InsertResult;
import com.confirming.oportunidades.config.MessagingConfig;


@RestController
@RequestMapping("/inversionInsert")
public class InversionPublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/{id}")
	public InsertResult inversionInsert(@RequestBody Inversion inversion, @PathVariable String id) {
		//login.setOrderId(UUID.randomUUID().toString());
		com.confirming.oportunidades.model.InsertResult insertResult = new InsertResult();
		InversionStatus inversionStatus = new InversionStatus();
		inversionStatus.setInversion(inversion);
		inversionStatus.setMessage("order placed succesfully in " + id);
		inversionStatus.setStatus("PROCESS");
		
		System.out.println("id en MQ : " + id);
		
    	template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, inversionStatus);
    	insertResult.setInsertR("Ok");
    	insertResult.setError("Sucess !!");
    	System.out.println("insertResult : " + insertResult.getInsertR());
		return insertResult;
	}

}