package com.alkar.alkar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnHora = findViewById(R.id.btnHora);
        Button btnMode = findViewById(R.id.btnMode);
        Button btnPresion = findViewById(R.id.btnPresion);
        Button btnLeer = findViewById(R.id.btnLeer);
        final EditText txtMode = findViewById(R.id.txtMode);
        final EditText txtPresion = findViewById(R.id.txtPresion);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Enviando Hora a la Herramienta...", Toast.LENGTH_LONG).show();
            }
        });

        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Enviando Mode: " + txtMode.getText().toString() + " a la Herramienta...", Toast.LENGTH_LONG).show();
            }
        });

        btnPresion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Enviando Presion: " + txtPresion.getText().toString() + " a la Herramienta...", Toast.LENGTH_LONG).show();
            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Leyendo CSV de la Herramienta...", Toast.LENGTH_LONG).show();
            }
        });
    }
}
