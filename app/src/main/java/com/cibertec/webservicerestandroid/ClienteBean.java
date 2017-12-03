package com.cibertec.webservicerestandroid;

import java.io.Serializable;

/**
 * Created by DIEGO on 29/11/2017.
 */

public class ClienteBean implements Serializable{

    private String dni;
    private String nombre;


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
