package com.example.franc.franquito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class usuario extends AppCompatActivity {
    Button botonregresar6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario);
        botonregresar6= (Button) findViewById(R.id.botonregresar6);
        botonregresar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(usuario.this, menu.class);
                usuario.this.startActivity(intentReg);
            }
        });
    }
}
