package com.caam.confirming.models;

import java.io.Serializable;

public class Factura implements Serializable {

    private String id;
    private String nombre;
    private String moneda;
    private double valor;
    private double valorVenta;
    private String fechaCobro;
    private String plazoPago;
    private String detalle;
    private String status;
    private String username;
    private int idOfertante;


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
        return detalle;
    }
    public void setDetalle(String detalle) {
        detalle = detalle;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getIdOfertante() {
        return idOfertante;
    }
    public void setIdOfertante(int idOfertante) {
        this.idOfertante = idOfertante;
    }
    public double getValorVenta() {
        return valorVenta;
    }
    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

}
