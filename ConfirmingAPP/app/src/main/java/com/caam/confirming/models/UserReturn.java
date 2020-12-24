package com.caam.confirming.models;

public class UserReturn {

    private String isUser;
    private String tipoUsuario;
    private String nombre;

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    public String getNombre() {

        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getTipoUsuario() {

        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {

        this.tipoUsuario = tipoUsuario;
    }

}
