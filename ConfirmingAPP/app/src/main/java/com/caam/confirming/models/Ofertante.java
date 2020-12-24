package com.caam.confirming.models;

public class Ofertante {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
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
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
