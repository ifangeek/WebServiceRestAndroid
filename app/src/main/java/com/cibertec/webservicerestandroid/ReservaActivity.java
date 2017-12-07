package com.cibertec.webservicerestandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ReservaActivity extends AppCompatActivity {

    TextView txtpelicula,txtlocal,txtsala,txtinicio,txtfin,txtdni,txtcliente,txtreserva;
    Button btnimprimir;

    private void iniciaComponente(){
        txtpelicula = (TextView)findViewById(R.id.txtpelicula);
        txtlocal = (TextView)findViewById(R.id.txtlocal);
        txtsala = (TextView)findViewById(R.id.txtsala);
        txtinicio = (TextView)findViewById(R.id.txtinicio);
        txtfin = (TextView)findViewById(R.id.txtfin);
        txtdni = (TextView)findViewById(R.id.txtdni);
        txtcliente = (TextView)findViewById(R.id.txtcliente);
        txtreserva = (TextView)findViewById(R.id.txtreserva);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        iniciaComponente();

        Bundle bundle = getIntent().getExtras();
        txtpelicula.setText(bundle.getString("pelicula"));
        txtlocal.setText(bundle.getString("local"));
        txtinicio.setText(bundle.getString("inicio"));
        txtfin.setText(bundle.getString("fin"));
        txtsala.setText(bundle.getString("sala"));
        txtcliente.setText(bundle.getString("cliente"));
        txtdni.setText(bundle.getString("dni"));

        txtreserva.setText(("TICKET - "+bundle.getInt("id")));





    }


}
