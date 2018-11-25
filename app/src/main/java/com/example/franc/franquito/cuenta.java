package com.example.franc.franquito;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.sql.Types.NULL;

public class cuenta extends AppCompatActivity  {
Button botonregresar2,enviar;
EditText nom,ape,mail,tel,fecha;
RadioButton gen1,gen2;
RadioGroup rg;
Calendar cal = Calendar.getInstance();
http durl =new http();
String radio,fec;
int var=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuenta);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        botonregresar2 = (Button) findViewById(R.id.botonregresar2);
        enviar = findViewById(R.id.id_enviar);
        nom = findViewById(R.id.id_mon);
        ape = findViewById(R.id.id_ape);
        mail = findViewById(R.id.id_mail);
        tel = findViewById(R.id.id_tel);
        fecha = findViewById(R.id.id_fec);
        gen1 =findViewById(R.id.radioButton1);
        gen2 = findViewById(R.id.radioButton2);

        rg = findViewById(R.id.rg1);




        botonregresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(cuenta.this, inicio.class);

                cuenta.this.startActivity(intentReg);
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new registrar().execute("");
                int v=validad();
                String fec= fecha.getText().toString();
                if(v==0){
                    new registrar().execute("http://"+ durl.getIp()+"/java/insert_cuenta.php?nom="+nom.getText()+"&ape="+ape.getText()+"&mail="+mail.getText()+"&tel="+tel.getText()+"&gen="+radio+"&fec="+fec);

                    Toast.makeText(getApplicationContext(), "cuenta registrada", Toast.LENGTH_LONG).show();

                }
                else{
                    //Toast.makeText(getApplicationContext(), "contador "+v, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "por favor, completar todos los campos", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), fec, Toast.LENGTH_LONG).show();
                    v=0;
                }
            }
        });
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment dialogfragment = new DatePickerDialogTheme();
                dialogfragment.show(getFragmentManager(), "Theme");
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton1){
                    radio= "masculino";
                }
                if(i == R.id.radioButton2){
                    radio="femenino";
                }

            }
        });
    }



    private int validad(){
        if(nom.getText().toString().equals("")){
            var+=1;
        }
        if(ape.getText().toString().equals("")){
            var+=1;
        }
        if(mail.getText().toString().equals("")){
            var+=1;
        }
        if(tel.getText().toString().equals("")){
            var+=1;
        }
        if(fecha.getText().toString().equals("")){
            var+=1;
        }
        if(rg.getCheckedRadioButtonId()==-1){
            var+=1;
        }
        return var;
    }

    private class registrar extends AsyncTask<String, Void, String> {
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

    public static class DatePickerDialogTheme extends DialogFragment implements DatePickerDialog.OnDateSetListener {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                   AlertDialog.THEME_HOLO_DARK, this, year, month, day);

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            TextView textview = (TextView) getActivity().findViewById(R.id.id_fec);
            textview.setText(year + "-" +(month + 1) + "-" + day);


        }
    }

}
