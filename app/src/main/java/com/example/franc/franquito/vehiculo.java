package com.example.franc.franquito;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class vehiculo extends AppCompatActivity {
    Button botonregresar5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datosvehiculo);
        botonregresar5= (Button) findViewById(R.id.btn_Regresar);
        botonregresar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(vehiculo.this, menu.class);
                vehiculo.this.startActivity(intentReg);
            }
        });

    }

    }


