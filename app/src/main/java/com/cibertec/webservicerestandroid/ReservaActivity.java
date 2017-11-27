package com.cibertec.webservicerestandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReservaActivity extends AppCompatActivity {

    TextView ticket,pelicula,local,sala,inicio,fin,dni,cliente;
    Button imprimir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
    }
}
