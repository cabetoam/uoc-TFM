package com.confirming.reg.inversor.publisher;

//import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.confirming.reg.inversor.config.MessagingConfig;
import com.confirming.reg.inversor.dto.LoginStatus;
import com.confirming.reg.inversor.model.InsertResult;
import com.confirming.reg.inversor.model.Login;



@RestController
@RequestMapping("/loginInsert")
public class LoginPublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/{email}")
	public InsertResult loginToInsert(@RequestBody Login login, @PathVariable String email) {
		//login.setOrderId(UUID.randomUUID().toString());
		InsertResult insertResult = new InsertResult();
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setLogin(login);
		loginStatus.setStatus("PROCESS");
		loginStatus.setMessage("order placed succesfully in " + email);
		System.out.println("email en MQ : " + email);
		
    	template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, loginStatus);
    	insertResult.setInsertR("Ok");
    	insertResult.setError("Sucess !!");
    	System.out.println("insertResult : " + insertResult.getInsertR());
		return insertResult;
	}	
	
/*	@PostMapping("/{email}")
	public String loginToInsert(@RequestBody Login login, @PathVariable String email) {
		//login.setOrderId(UUID.randomUUID().toString());
		
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setLogin(login);
		loginStatus.setStatus("PROCESS");
		loginStatus.setMessage("order placed succesfully in " + email);
		
    	template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, loginStatus);
		return "Sucess !!";
	}*/
	
	
/*	public String loginToInsert(@RequestBody Login login, @PathVariable String email) {
		//login.setOrderId(UUID.randomUUID().toString());
		System.out.println("email to RabbitMq : " + email);
		System.out.println("login to RabbitMq : " + login.getLogin());
		LoginStatus loginStatus = new LoginStatus();
		loginStatus.setLogin(login);
		loginStatus.setStatus("PROCESS");
		loginStatus.setMessage("order placed succesfully in " + email);
		
		System.out.println("email to RabbitMq : " + loginStatus.getStatus());
		System.out.println("login to RabbitMq : " + loginStatus.getLogin().getLogin());
		
    	template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, loginStatus);
		return "Sucess !!";
	}	*/
}
