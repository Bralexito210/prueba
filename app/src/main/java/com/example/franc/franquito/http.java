package com.example.franc.franquito;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class http {

    private String ip = "10.144.87.15";

    public String downloadUrl(String myurl) throws IOException{
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","20");
        InputStream is =null;
        int len =500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            int response = connection.getResponseCode();
            Log.d("respuesta","respondio :"+response);
            is = connection.getInputStream();

            String contenedor = readIt(is,len);
            return  contenedor;

        }finally {
            if(is!=null){
                is.close();
            }
        }

    }


    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream,"UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    protected void json(String result,String[] content) {

        JSONArray ja = null;
        try {
            ja = new JSONArray(result);
            for(int i=0;i<ja.length();i++){
                content[i]=ja.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getIp() {
        return ip;
    }
}
