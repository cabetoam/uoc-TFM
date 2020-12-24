package com.confirming.oportunidades.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Factura")
public class Oportunidad {
	
	private String id;
	private double valor;
	private String plazoPago;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getPlazoPago() {
		return plazoPago;
	}
	public void setPlazoPago(String plazoPago) {
		this.plazoPago = plazoPago;
	}

}