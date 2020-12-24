package com.confirming.oportunidades.dto;

import com.confirming.oportunidades.model.Inversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InversionStatus {
	
	private Inversion inversion;
	private String status;
	private String message;
	
	public Inversion getInversion() {
		return inversion;
	}
	public void setInversion(Inversion inversion) {
		this.inversion = inversion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
