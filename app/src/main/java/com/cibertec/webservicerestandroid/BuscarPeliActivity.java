package com.cibertec.webservicerestandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuscarPeliActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtpeli,txtsala,txtinicio,txtfin,txtlocal,txtCliente;
    EditText edtdni;
    Button btnGrabar,btnDNI;
    int id;
    ArrayList<ClienteBean> clientes = new ArrayList<ClienteBean>();
    ProgressDialog pd;
    String resultado="";
    ClienteBean bean = new ClienteBean();
    PeliculaBean peli = new PeliculaBean();
    private void iniciaComponente(){
        txtpeli = (TextView)findViewById(R.id.txtpeli);
        txtsala = (TextView)findViewById(R.id.txtsala);
        txtinicio = (TextView)findViewById(R.id.txtInicio);
        txtfin = (TextView)findViewById(R.id.txtFin);
        txtlocal = (TextView)findViewById(R.id.txtlocal);
        edtdni = (EditText)findViewById(R.id.edtDNI);
        txtCliente = (TextView)findViewById(R.id.tvNombreCli);
        btnGrabar = (Button)findViewById(R.id.btngrabar);
        btnDNI = (Button)findViewById(R.id.btnValidaDNI);
        btnGrabar.setOnClickListener(this);
        btnDNI.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_peli);
        iniciaComponente();



        Intent rec=getIntent();
        PeliculaBean obj=(PeliculaBean)rec.getSerializableExtra("obj");
        txtpeli.setText(obj.getNombre());
        txtsala.setText(obj.getSala());
        txtinicio.setText(obj.getInicio());
        txtfin.setText(obj.getFin());
        txtlocal.setText(obj.getIdlocal());
        id=obj.getIdpelicula();
        grabar(btnGrabar);
    }

    @Override
    public void onClick(View view) {
        if(view == btnDNI){
            new Cliente().execute();
        }

    }

    public class Cliente extends AsyncTask<String,Void,Object> {
        @Override
        protected Object doInBackground(String... strings) {
            try{
                HttpClient cliente= new DefaultHttpClient();
                HttpContext ctx=new BasicHttpContext();
                String URL="http://34.239.106.144:8080/restService/consultaCliente?codCliente="+edtdni.getText();
                HttpGet get=new HttpGet(URL);
                HttpResponse response = cliente.execute(get,ctx);
                HttpEntity entity=response.getEntity();
                String data = EntityUtils.toString(entity,"UTF-8");
                JSONObject jsonobj =new JSONObject(data);

                            bean.setDni(jsonobj.getString("dni"));
                            bean.setNombre(jsonobj.getString("nombre"));
                            clientes.add(bean);
                            txtCliente.setText(bean.getNombre());

            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object result){


        }

    }
    public void grabar(View v){
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("paso","XD");
                Intent i = new Intent(getApplicationContext(), ReservaActivity.class);
                //txtpeli,txtsala,txtinicio,txtfin,txtlocal,txtCliente
                i.putExtra("pelicula",txtpeli.getText().toString());
                Log.v("Peli",txtpeli.getText().toString());
                i.putExtra("sala",txtsala.getText().toString());
                i.putExtra("inicio",txtinicio.getText().toString());
                i.putExtra("fin",txtfin.getText().toString());
                i.putExtra("local",txtlocal.getText().toString());
                i.putExtra("cliente",txtCliente.getText().toString());
                i.putExtra("dni",bean.getDni().toString());
                i.putExtra("id", ((Integer) id));
                Log.v("ID",id+"");
                startActivity(i);


            }
        });




    }


}
