package com.cibertec.webservicerestandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BuscarPeliActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtpeli,txtsala;
    EditText edtdni;
    Button btn;
    int id;
    ProgressDialog pd;
    String resultado="";
    private void inniciaComponenet(){
        txtpeli = (TextView)findViewById(R.id.txtpeli);
        txtsala = (TextView)findViewById(R.id.txtsala);
        edtdni = (EditText)findViewById(R.id.edtdni);
        btn = (Button)findViewById(R.id.btngrabar);
        btn.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_peli);
        inniciaComponenet();

        Intent rec=getIntent();
        PeliculaBean obj=(PeliculaBean)rec.getSerializableExtra("obj");
        txtpeli.setText(obj.getNombre());
        id=obj.getIdpelicula();
    }

    @Override
    public void onClick(View view) {
        if(view == btn){
            pd=ProgressDialog.show(this,"cone","espere porfa",true,false);
            new Pelis().execute();
        }
    }
    public class Pelis extends AsyncTask<String,Void,Object> {
        @Override
        protected Object doInBackground(String... strings) {
            try{
                HttpClient cliente= new DefaultHttpClient();
                HttpContext ctx=new BasicHttpContext();
                String URL="http://192.168.8.108:8080/WebServiceRestInicial/rest/miservicio/grabareserva";
                HttpPost post=new HttpPost(URL);
                String data="{dni:\"1\",idpeli:\"2\"}";
                StringEntity ent=new StringEntity(data);
                post.setEntity(ent);
                post.setHeader("Accept","application/json");
                post.setHeader("Content-type","application/json");

                HttpResponse response = cliente.execute(post,ctx);
                HttpEntity entity=response.getEntity();
                String res = EntityUtils.toString(entity,"UTF-8");
                resultado = res;
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object result){
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
            pd.dismiss();
        }
    }
}
