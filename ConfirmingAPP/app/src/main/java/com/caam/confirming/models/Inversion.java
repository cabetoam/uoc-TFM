package com.caam.confirming.models;

import java.io.Serializable;

public class Inversion implements Serializable {

    private String id;
    private String nombreOfertante;
    private String moneda;
    private double valorCompra;
    private double ganancia;
    private String plazoPago;
    private String fechaCompra;
    private String usernameComprador;
    private String idFactura;

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

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public String getPlazoPago() {
        return plazoPago;
    }

    public void setPlazoPago(String plazoPago) {
        this.plazoPago = plazoPago;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getUsernameComprador() {
        return usernameComprador;
    }

    public void setUsernameComprador(String usernameComprador) {
        this.usernameComprador = usernameComprador;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }
}
