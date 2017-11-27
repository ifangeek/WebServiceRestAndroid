package com.cibertec.webservicerestandroid;

/**
 * Created by admin on 18/11/2017.
 */
public class LocalBean {
    private String idlocal;
    private String nnomlocal;

    public String toString(){
        return nnomlocal;
    }
    public String getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(String idlocal) {
        this.idlocal = idlocal;
    }

    public String getNnomlocal() {
        return nnomlocal;
    }

    public void setNnomlocal(String nnomlocal) {
        this.nnomlocal = nnomlocal;
    }
}
