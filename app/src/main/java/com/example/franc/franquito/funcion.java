package com.example.franc.franquito;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;

public class funcion extends AppCompatActivity {
    Button btn_Regresar1;
    ToggleButton led1,led2;
    Button avanza,retrocede,izquierda,derecha;
    boolean est1;
    String dato="";
    http durl =new http();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcion);
        btn_Regresar1= findViewById(R.id.btn_Regresar);
        avanza = findViewById(R.id.id_avanza);
        retrocede = findViewById(R.id.id_retrocede);
        izquierda = findViewById(R.id.id_izq);
        derecha = findViewById(R.id.id_der);

        led1 = findViewById(R.id.LED1);
        led2 = findViewById(R.id.LED2);


        btn_Regresar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(funcion.this, menu.class);
                funcion.this.startActivity(intentReg);
            }
        });


        led1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campo="led_1";
                //apagar
                if(led1.getText().equals("ENCENDER")){
                    new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo="+campo+"&cambio=LOW");
                }
                //encerder
                else if(led1.getText().equals("APAGAR")) {
                    new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo="+campo+"&cambio=HIGH");
                }
            }
        });

        led2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campo="led_2";
                //apagar
                if(led1.getText().equals("ENCENDER")){
                    new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo="+campo+"&cambio=LOW");
                }
                //encerder
                else if(led1.getText().equals("APAGAR")) {
                    new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo="+campo+"&cambio=HIGH");
                }
            }
        });
        avanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo=mover&cambio=ADELANTE");
            }
        });
        retrocede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo=mover&cambio=ATRAS");
            }
        });
        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo=mover&cambio=IZQUIERDA");
            }
        });
        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.1.2/java/update_led1.php?campo=mover&cambio=DERECHA");
            }
        });

    }






    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return durl.downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
    }


}
