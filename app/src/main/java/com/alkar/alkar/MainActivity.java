package com.alkar.alkar;

import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    Button botonSesion;
    EditText textoUsuario, textoPassword;
    EditText textoUsuario1, textoPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonSesion = findViewById(R.id.btnSesion);
        textoUsuario = findViewById(R.id.txtUsuario);
        textoPassword = findViewById(R.id.txtPassword);

        botonSesion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Falta leer la base de datos y ver que es correcto el usuario y password
                new ConsultarDatos().execute("http://192.168.1.50/CursoAndroid/consulta.php?id=2");

                //Toast.makeText(getApplicationContext(), " "+textoUsuario+" "+textoUsuario1, Toast.LENGTH_LONG).show();

                if((textoUsuario == textoUsuario) && (textoPassword == textoPassword))
                {
                    // Pasamos a la siguiente pantalla Main2activity
                    Intent intentPantalla2 = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intentPantalla2);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Usuario o Password Incorrectos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private class ConsultarDatos extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            // params comes from the execute() call: params[0] is the url.
            try
            {
                return downloadUrl(urls[0]);
            }
            catch (IOException e)
            {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result)
        {
            JSONArray ja;
            try
            {
                ja = new JSONArray(result);
                textoUsuario.setText(ja.getString(1));
                textoPassword.setText(ja.getString(2));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            return readIt(is, len);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
