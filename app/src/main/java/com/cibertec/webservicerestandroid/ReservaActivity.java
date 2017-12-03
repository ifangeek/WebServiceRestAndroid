package com.cibertec.webservicerestandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ReservaActivity extends AppCompatActivity {

    TextView ticket,pelicula,local,sala,inicio,fin,dni,cliente;
    Button imprimir;

    private void iniciaComponente(){
        ticket = (TextView)findViewById(R.id.txtpeli);
        pelicula = (TextView)findViewById(R.id.txtsala);
        local = (TextView)findViewById(R.id.txtlocal);
        sala = (TextView)findViewById(R.id.txtsala);
        inicio = (TextView)findViewById(R.id.txtinicio);
        fin = (TextView)findViewById(R.id.txtfin);
        dni = (TextView)findViewById(R.id.txtdni);
        cliente = (TextView)findViewById(R.id.txtcliente);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        iniciaComponente();
    }


}
