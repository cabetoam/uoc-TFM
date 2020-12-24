package com.confirming.reg.ofertante.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Ofertante")
public class Ofertante {

	@Id
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String contraseña;
	private String empresa;
	private String tipoIdentificacionEmpresa;
	private String identificacionEmpresa;
	private String telefono;
	
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTipoIdentificacionEmpresa() {
		return tipoIdentificacionEmpresa;
	}
	public void setTipoIdentificacionEmpresa(String tipoIdentificacionEmpresa) {
		this.tipoIdentificacionEmpresa = tipoIdentificacionEmpresa;
	}
	public String getIdentificacionEmpresa() {
		return identificacionEmpresa;
	}
	public void setIdentificacionEmpresa(String identificacionEmpresa) {
		this.identificacionEmpresa = identificacionEmpresa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
