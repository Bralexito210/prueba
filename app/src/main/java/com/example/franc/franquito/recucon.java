package com.example.franc.franquito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recucon extends AppCompatActivity {
    Button botonregresar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recucontra);
        botonregresar1= (Button) findViewById(R.id.botonregresar1);
        botonregresar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(recucon.this, inicio.class);
                recucon.this.startActivity(intentReg);
            }
        });
    }
}
