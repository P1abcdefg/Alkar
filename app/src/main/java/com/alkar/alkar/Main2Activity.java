package com.alkar.alkar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity
{
    Button botonHerramienta;
    Button botonEnviarFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        botonHerramienta = findViewById(R.id.btnHerramienta);
        botonEnviarFichero = findViewById(R.id.btnEnviarFich);

        botonHerramienta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentBluetooth = new Intent(Main2Activity.this, BTActivity.class);
                startActivity(intentBluetooth);
            }
        });

        botonEnviarFichero.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentEnviarFichero = new Intent(Main2Activity.this, EnviarFicheroActivity.class);
                startActivity(intentEnviarFichero);
            }
        });
    }
}
