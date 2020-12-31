package com.caam.confirming.models;

public class Inversion {

    private String id;
    private String email;
    private String ofertante;
    private String moneda;
    private double valor;
    private String fechaCompra;
    private String plazoPago;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfertante() {
        return ofertante;
    }

    public void setOfertante(String ofertante) {
        this.ofertante = ofertante;
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

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getPlazoPago() {
        return plazoPago;
    }

    public void setPlazoPago(String plazo) {
        this.plazoPago = plazo;
    }
}
