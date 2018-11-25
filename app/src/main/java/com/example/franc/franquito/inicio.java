package com.example.franc.franquito;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class inicio extends AppCompatActivity {
    Button boton1;
    TextView botoncontraseña1, crearcuenta;
    EditText user, pass;
    String existe_v="";
    ArrayList<String> lista = new ArrayList<String>();
    String ip = "192.168.1.5";
    String tab = "" ;

    http durl =new http();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        conecta();
        boton1 = (Button) findViewById(R.id.botoninicio);
        botoncontraseña1 = (TextView) findViewById(R.id.botoncontraseña1);
        crearcuenta = (TextView) findViewById(R.id.crearcuenta);
        user = findViewById(R.id.txt_user);
        pass = findViewById(R.id.txt_pass);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab= "usuario";
                String[] cont= new String[1];
                if(user.getText().equals("") || pass.getText().equals("")){

                try {
                    //http://localhost/java/iinicio.php?n=david&p=david
                    String a = durl.downloadUrl("http://"+ip+"/java/iinicio.php?n="+user.getText().toString()+"&p="+pass.getText().toString());
                    durl.json(a,cont);
                    if(Integer.parseInt(cont[0])==1){

                        Intent intentReg = new Intent(inicio.this, menu.class);
                        Toast.makeText(getApplicationContext(), "bienbenido "+user.getText().toString(), Toast.LENGTH_LONG).show();
                        inicio.this.startActivity(intentReg);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "el usuario no existe", Toast.LENGTH_LONG).show();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
                else{
                    Toast.makeText(getApplicationContext(), "por favor completar los campos", Toast.LENGTH_LONG).show();
                }
                //conectar();
            }
        });


        botoncontraseña1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intentReg = new Intent(inicio.this, recucon.class);
                inicio.this.startActivity(intentReg);
            }
        });

        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(inicio.this, cuenta.class);

                inicio.this.startActivity(intentReg);
            }
        });

    }

    public void conecta() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), "conectado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "sin coneccion", Toast.LENGTH_LONG).show();
        }
    }

    private class existe extends AsyncTask<String,Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return durl.downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            lista.clear();
            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                    existe_v=ja.getString(0);
                //Toast.makeText(getApplicationContext(), "existe "+existe_v, Toast.LENGTH_LONG).show();

                ja = null;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void conectar(){
        new existe().execute("http://"+ip+"/java/consulta_t.php?nom="+user.getText().toString()+"&tab="+tab);
        Toast.makeText(getApplicationContext(), "existe "+existe_v, Toast.LENGTH_LONG).show();
    }


}