package com.carlosagudelo.model;

import java.io.Serializable;

public class ModelData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2768520451580106914L;
	
	private int code;
	private String message;
	private boolean status;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
