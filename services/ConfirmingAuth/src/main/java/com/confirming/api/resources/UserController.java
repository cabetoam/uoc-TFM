package com.confirming.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.confirming.api.model.User;
import com.confirming.api.model.UserInversor;
import com.confirming.api.model.UserOfertante;
import com.confirming.api.model.UserReturn;
import com.confirming.api.repository.DynamicQuery;
import com.confirming.api.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping("/addUser")
	public String saveUser(@RequestBody User user) {
		repository.save(user);
		return "Added user with id : " + user.getId();

	}

	@GetMapping("/findAllUsers")
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/validateUser2", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUser(@RequestParam(name = "log") String login, @RequestParam() String contrasena) {
		final DynamicQuery dynamicQuery = new DynamicQuery();
		dynamicQuery.setLogin(login);
		dynamicQuery.setContrasena(contrasena);
		System.out.print("login que llega : " + login);
		System.out.print(" contrasena que llega : " + contrasena);
		return repository.query(dynamicQuery);		
	}
	
	
	@GetMapping(path = "/validateUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserReturn getUser2(@RequestParam(name = "log") String login, @RequestParam() String contrasena) {
		List<User> user_ = new ArrayList<User>();
		List<UserInversor> userInversor = new ArrayList<UserInversor>();
		List<UserOfertante> userOfertante = new ArrayList<UserOfertante>();
		UserReturn userReturn = new UserReturn();
		final DynamicQuery dynamicQuery = new DynamicQuery();
		dynamicQuery.setLogin(login);
		dynamicQuery.setContrasena(contrasena);
		System.out.print("login que llega : " + login);
		System.out.print(" contrasena que llega : " + contrasena);
		user_ = repository.query(dynamicQuery);
		
		try
		{
			userReturn.setTipoUsuario(user_.get(0).getTipoUsuario());
			userReturn.setUser("true");
			userReturn.setNombre("no name");
		}
		catch (Exception e){
			userReturn.setTipoUsuario("no user");
			userReturn.setUser("false");
			userReturn.setNombre("no name");
		}
		
		if (userReturn.getisUser().equals("true")) {			
			userInversor = repository.queryNombreInversor(dynamicQuery);
			
			try
			{
				userReturn.setNombre(userInversor.get(0).getNombre());				
			}
			catch (Exception e){	
				userReturn.setNombre(" ");
			}
		}
		
		if (userReturn.getNombre().equals(" ")) {				
					
			userOfertante = repository.queryNombreOfertante(dynamicQuery);
			
			try
			{
				userReturn.setNombre(userOfertante.get(0).getNombre());				
			}
			catch (Exception ex){					
				userReturn.setNombre(" ");
			}					
		}			
		
		return userReturn;		
	}
	
	/*
	@GetMapping("/validateUser")
	public List<User> getUser(@RequestBody String name) {
		final DynamicQuery dynamicQuery = new DynamicQuery();
		dynamicQuery.setStrname(name);
		return repository.query(dynamicQuery);		
	}*/
		
	@GetMapping("/findAllUsers/{id}")
	public Optional<User> getUser(@PathVariable int id) {
		return repository.findById(id);
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		repository.deleteById(id);
		return "user deleted with id : " + id;
	}
	
}
