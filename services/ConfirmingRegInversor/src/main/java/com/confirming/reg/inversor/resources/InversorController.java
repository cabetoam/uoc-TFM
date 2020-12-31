package com.confirming.reg.inversor.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.confirming.reg.inversor.model.InsertResult;
import com.confirming.reg.inversor.model.Inversor;
import com.confirming.reg.inversor.model.Login;
import com.confirming.reg.inversor.publisher.LoginPublisher;
import com.confirming.reg.inversor.repository.DynamicQueryLogin;
import com.confirming.reg.inversor.repository.InversorRepository;
import com.confirming.reg.inversor.repository.LoginRepository;
import org.springframework.http.MediaType;

@RestController
public class InversorController {

	@Autowired
	private InversorRepository repository;
	
	@Autowired
	private LoginRepository repositoryLogin;
	
	//@PostMapping(path="/addInversor", consumes=MediaType.APPLICATION_JSON_VALUE)
	//@PostMapping("/addInversor")
	@PostMapping(path="addInversor", produces=MediaType.APPLICATION_JSON_VALUE)
	public InsertResult saveUser(@RequestBody Inversor inversor) throws Exception {
		InsertResult insertResult = new InsertResult();
		String email = inversor.getEmail();
		System.out.println("email : " + email);
		repository.save(inversor);
		insertResult.setInsertR("Ok");
		insertResult.setError("no error");
		
	/*	Login login = new Login();
		boolean loginExist = false;
		final DynamicQueryLogin dynamicQueryLogin = new DynamicQueryLogin();
		dynamicQueryLogin.setLogin(email);	
		loginExist = repository.query(dynamicQueryLogin);	
		if (loginExist)
			messageResponse = "User Inversor Exist";
		else
		{			
			login.setId(inversor.getId());
			login.setLogin(inversor.getEmail());
			login.setContrasena(inversor.getContraseña());
			login.setTipoUsuario("inversor");
			//repositoryLogin.save(login);
			repository.save(inversor);
			
	/*		LoginPublisher loginPublisher = new LoginPublisher();
			String responseMQSend = loginPublisher.loginToInsert(login, email);	
			System.out.println("response URL to RabbitMq : " + responseMQSend);
			messageResponse = "Added inversor with id : " + inversor.getId();
		}*/
		return insertResult;
	}
	
	

	@GetMapping("/findAllInversores")
	public List<Inversor> getInversores() {
		return repository.findAll();
	}
	
	@GetMapping("/findAllInversores/{id}")
	public Optional<Inversor> getInversor(@PathVariable int id) {
		return repository.findById(id);
	}

	@GetMapping("/deleteInversor/{id}")
	public String deleteInversor(@PathVariable int id) {
		repository.deleteById(id);
		return "inversor deleted with id : " + id;
	}	
}
