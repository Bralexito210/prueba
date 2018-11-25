package com.example.franc.franquito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {
    Button botonregresar;
    Button botonusuario;
    Button botonfuncion;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        botonregresar= (Button) findViewById(R.id.botonregresar);
        botonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(menu.this, inicio.class);
                menu.this.startActivity(intentReg);
            }
        });
        botonusuario= (Button) findViewById(R.id.botonusuario);
        botonusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(menu.this, usuario.class);
                menu.this.startActivity(intentReg);
            }
        });
        botonfuncion=(Button) findViewById(R.id.botonfuncion);
        botonfuncion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(menu.this, funcion.class);
                menu.this.startActivity(intentReg);
            }
        });
        boton =(Button) findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(menu.this, vehiculo.class);
                menu.this.startActivity(intentReg);
            }
        });
    }
}
