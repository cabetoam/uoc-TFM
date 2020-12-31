package com.confirming.reg.ofertante.resources;

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
import com.confirming.reg.ofertante.model.Ofertante;
import com.confirming.reg.ofertante.model.InsertResult;
import com.confirming.reg.ofertante.repository.OfertanteRepositoryImpl;
import com.confirming.reg.ofertante.repository.OfertanteRespository;

@RestController
public class OfertanteController {

	@Autowired
	private OfertanteRespository repository;
	
	@Autowired
	private OfertanteRepositoryImpl repositoryImpl;
	
	@PostMapping("/addOfertante")
	public InsertResult saveOfertante(@RequestBody Ofertante ofertante) {
		InsertResult insertResult = new InsertResult();		
		repository.save(ofertante);
		insertResult.setInsertR("Ok");
		insertResult.setError("no error");		
		return insertResult;
	}
	
	//@PostMapping("/findOfertante")
	@PostMapping(path="/findOfertante", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ofertante> findNombreOfertente(@RequestParam("username") String username) {
		List<Ofertante> ofertante = new ArrayList<Ofertante>();
		System.out.print(username);
		ofertante = repositoryImpl.query(username);
		System.out.print(ofertante.get(0).getNombre());
		return ofertante;
	}

	@GetMapping("/findAllOfertantes")
	public List<Ofertante> getOfertantes() {
		return repository.findAll();
	}
	
	@GetMapping("/findAllOfertantes/{id}")
	public Optional<Ofertante> getOfertantes(@PathVariable int id) {
		return repository.findById(id);
	}

	@GetMapping("/deleteOfertante/{id}")
	public String deleteOfertante(@PathVariable int id) {
		repository.deleteById(id);
		return "ofertante deleted with id : " + id;
	}
}
