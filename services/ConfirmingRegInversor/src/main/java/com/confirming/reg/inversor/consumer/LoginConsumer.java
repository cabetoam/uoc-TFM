package com.confirming.reg.inversor.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.confirming.reg.inversor.dto.LoginStatus;
import com.confirming.reg.inversor.model.Login;
import com.confirming.reg.inversor.repository.LoginRepository;

@Component
public class LoginConsumer {
	
	@Autowired
	private LoginRepository repositoryLogin;
	
	@RabbitListener(queues = com.confirming.reg.inversor.config.MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(LoginStatus loginStatus) {
		
		System.out.println("Message Request from queue : "+ loginStatus.getStatus() + " - " + loginStatus.getMessage());
		Login login = new Login();
		login.setId(loginStatus.getLogin().getId());
		login.setLogin(loginStatus.getLogin().getLogin());
		login.setContrasena(loginStatus.getLogin().getContrasena());
		login.setTipoUsuario(loginStatus.getLogin().getTipoUsuario());
		repositoryLogin.save(login);

		
	}
}
