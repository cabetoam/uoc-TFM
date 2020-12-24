package com.confirming.oportunidades.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Factura")
public class Factura {
	
	@Id
	private String id;
	private String nombre;
	private String moneda;
	private double valor;
	private String fechaCobro;
	private String Detalle;
	private String plazoPago;
	private String status;

	
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public String getPlazoPago() {
		return plazoPago;
	}
	public void setPlazoPago(String plazoPago) {
		this.plazoPago = plazoPago;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}