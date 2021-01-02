package com.confirming.oportunidades.resources;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.confirming.oportunidades.repository.DynamicQuery;

import com.confirming.oportunidades.model.Oportunidad;
import com.confirming.oportunidades.repository.OportunidadesRepository;


@RestController
public class OportunidadesController {
	
	@Autowired
	private OportunidadesRepository oportunidadesRepository;
	

	@PostMapping(path="/findOportunidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Oportunidad> findOportunidades(@RequestParam("moneda") String moneda, @RequestParam("monto") double monto) {
		List<Oportunidad> oportunidad = new ArrayList<Oportunidad>();
		// NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

		System.out.println(" moneda : " + moneda);
		System.out.println(" monto : " + monto);
		
		final DynamicQuery dynamicQuery = new DynamicQuery();
		
		dynamicQuery.setMoneda(moneda);
		dynamicQuery.setMonto(monto);
		oportunidad = oportunidadesRepository.queryOportunidades(dynamicQuery);
		System.out.println(" monto : " + oportunidad.get(0).getPlazoPago());
		System.out.println(" monto2 : " + oportunidad.size());
		return oportunidad;		
	}
	
}
