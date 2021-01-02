package com.confirming.oportunidades.resources;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.confirming.oportunidades.model.InsertResult;
import com.confirming.oportunidades.model.Inversion;
import com.confirming.oportunidades.repository.InversionRepository;
import com.confirming.oportunidades.repository.InversionesRepository;

@RestController
public class InversionesController {
	
	@Autowired
	private InversionRepository inversionRepository;
	
	@Autowired
	private InversionesRepository inversionesRepository;
	
	@PostMapping("/addInversion")
	public InsertResult saveInversion(@RequestBody Inversion inversion) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println("Valor Compra : " + numberFormat.format(inversion.getValorCompra()));
		System.out.println("Ganancia : " + numberFormat.format(inversion.getGanancia()));

		inversion.setId(ObjectId.get().toString());
		InsertResult insertResult = new InsertResult();
		inversionRepository.save(inversion);
		insertResult.setInsertR("Ok");
		insertResult.setError("no error");
		return insertResult;
	}
	
	
	@PostMapping(path="/findInversiones", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Inversion> findInversiones(@RequestParam("username") String username) {
		List<Inversion> inversion = new ArrayList<Inversion>();
		// NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

		System.out.println(" username : " + username);
		inversion = inversionesRepository.queryInversiones(username); //queryInversiones(username);
		System.out.println(" data : " + inversion.get(0).getPlazoPago());
		System.out.println(" data : " + inversion.size());
		return inversion;		
	}

}