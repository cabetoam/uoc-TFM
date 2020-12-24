package com.caam.confirming.models;

import java.io.Serializable;

public class Oportunidad implements Serializable {

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
