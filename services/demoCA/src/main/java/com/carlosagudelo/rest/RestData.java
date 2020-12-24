package com.carlosagudelo.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carlosagudelo.model.ModelData;

@RestController
@RequestMapping(path = "/rest/dataCA")
public class RestData {
	
	@GetMapping(path = "/obtenDatos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelData getData(@RequestParam(name = "msg") String message) {
		ModelData response = new ModelData();
		response.setCode(200);
		response.setStatus(true);
		response.setMessage("Mensaje recibido popr el servicio de prueba : " + message);
		return response;
		
	}
}
