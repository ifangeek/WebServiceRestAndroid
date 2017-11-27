package com.cibertec.webservicerestandroid;

import java.io.Serializable;

/**
 * Created by admin on 18/11/2017.
 */
public class PeliculaBean implements Serializable {
    private int idpelicula;
    private String nombre;
    private String sala;
    private String inicio;
    private String fin;
    private String fecha;
    private String idlocal;

    public String toString(){
        return nombre;
    }
    public int getIdpelicula() {
        return idpelicula;
    }

    public void setIdpelicula(int idpelicula) {
        this.idpelicula = idpelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(String idlocal) {
        this.idlocal = idlocal;
    }
}
