package com.confirming.oportunidades.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Inversion")
public class Inversion {
	
	private String id;
	private String nombreOfertante;
	private String moneda;
	private double valor;
	private Date fechaCompra;
	private String emailComprador;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreOfertante() {
		return nombreOfertante;
	}
	public void setNombreOfertante(String nombreOfertante) {
		this.nombreOfertante = nombreOfertante;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getEmailComprador() {
		return emailComprador;
	}
	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}

}
