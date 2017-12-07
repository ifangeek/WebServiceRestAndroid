package com.cibertec.webservicerestandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText edtfecha;

    Spinner splocal;
    ListView lst;
    Button btn;
    ArrayList<LocalBean> locales = new ArrayList<LocalBean>();
    //    ArrayList<PeliculaBean> peliculas=new ArrayList<PeliculaBean>();
    ProgressDialog pd;
    String idlocal = "", fecha = "";
    ArrayAdapter<PeliculaBean> adapter;


    private void IniciaComponente() {
        edtfecha = (EditText) findViewById(R.id.edtFecha);
        splocal = (Spinner) findViewById(R.id.spnLocal);
        lst = (ListView) findViewById(R.id.lstPeliculas);
        btn = (Button) findViewById(R.id.btnConsultar);
        btn.setOnClickListener(this);
        lst.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciaComponente();
        pd = ProgressDialog.show(this, "Conectando", "Espere porfavor", true, false);
        new Locales().execute();
        adapter = new ArrayAdapter<PeliculaBean>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                new ArrayList<PeliculaBean>());
        lst.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {

        if (view == btn) {

            LocalBean l = (LocalBean) splocal.getItemAtPosition(splocal.getSelectedItemPosition());
            idlocal = l.getIdlocal();
            fecha = edtfecha.getText().toString();
            pd = ProgressDialog.show(this, "Conectando", "Espere porfavor", true, false);
            new Peliculas().execute();


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent ir = new Intent(this, BuscarPeliActivity.class);
        PeliculaBean obj = (PeliculaBean) lst.getItemAtPosition(i);
        ir.putExtra("obj", obj);
        startActivity(ir);
        ;
    }

    public class Locales extends AsyncTask<String, Void, Object> {
        @Override
        protected Object doInBackground(String... strings) {
            try {
                HttpClient cliente = new DefaultHttpClient();
                HttpContext ctx = new BasicHttpContext();
                String URL = "http://34.239.106.144:8080/restService/ListaLocales";
                HttpGet get = new HttpGet(URL);
                HttpResponse response = cliente.execute(get, ctx);
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity, "UTF-8");
                JSONArray jsonobj = new JSONArray(data);

                Log.v("JSON", jsonobj.toString());
                LocalBean bean;
                JSONArray jsonarray = jsonobj;
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonrow = jsonarray.getJSONObject(i);
                    bean = new LocalBean();
                    bean.setIdlocal(jsonrow.getString("idlocal"));
                    bean.setNnomlocal(jsonrow.getString("nomlocal"));
                    locales.add(bean);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Object result) {

            ArrayAdapter<LocalBean> adapter = new ArrayAdapter<LocalBean>(getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    locales);
            splocal.setAdapter(adapter);
            pd.dismiss();


        }
    }

    public class Peliculas extends AsyncTask<String, Void, ArrayList<PeliculaBean>> {
        @Override
        protected ArrayList<PeliculaBean> doInBackground(String... strings) {
            ArrayList<PeliculaBean> lstPeliculasBean = new ArrayList<>();
            try {
                HttpClient cliente = new DefaultHttpClient();
                HttpContext ctx = new BasicHttpContext();
                String URL = "http://34.239.106.144:8080/restService/consultaPelicula?tbLocal=" + idlocal + "&fecha=" + fecha;
                Log.v("URL", URL);
                HttpGet get = new HttpGet(URL);
                HttpResponse response = cliente.execute(get, ctx);
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity, "UTF-8");
                JSONArray jsonobj = new JSONArray(data);
                PeliculaBean bean;
                LocalBean beanlocal;
                JSONArray jsonarray = jsonobj;
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonrow = jsonarray.getJSONObject(i);
                    bean = new PeliculaBean();
                    Log.v("IDPELICULA", bean.getIdpelicula() + "");
                    bean.setIdpelicula(jsonrow.getInt("codPelicula"));
                    bean.setNombre(jsonrow.getString("nombre"));
                    bean.setSala(jsonrow.getString("sala"));
                    bean.setInicio(jsonrow.getString("inicio"));
                    bean.setFin(jsonrow.getString("fin"));
                    JSONObject jsonlocal = jsonrow.getJSONObject("tbLocal");
                    Log.v("JSON", jsonlocal + "");
                    bean.setIdlocal(jsonlocal.getString("nomlocal"));
                    lstPeliculasBean.add(bean);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                lstPeliculasBean.clear();
            }
            return lstPeliculasBean;
        }

        protected void onPostExecute(ArrayList<PeliculaBean> result) {
            adapter.clear();
            adapter.addAll(result);
            adapter.notifyDataSetChanged();


            pd.dismiss();

        }
    }
}
