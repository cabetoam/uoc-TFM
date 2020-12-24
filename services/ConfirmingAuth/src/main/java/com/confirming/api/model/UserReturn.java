package com.confirming.api.model;

import java.io.Serializable;

public class UserReturn implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String isUser;
	private String tipoUsuario;	
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getisUser() {
		return isUser;
	}
	public void setUser(String isUser) {
		this.isUser = isUser;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
