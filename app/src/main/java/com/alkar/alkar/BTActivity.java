package com.alkar.alkar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
//import android.content.BroadcastReceiver;
//import android.content.Context;
import android.content.Intent;
//import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

//import java.util.ArrayList;
import java.util.Set;

public class BTActivity extends AppCompatActivity
{
    Intent btEnablingIntent;
    int requestCodeForEnable;
    ListView listView;
    //Button scanButton;
    //ArrayList<String> stringArrayList = new ArrayList<>();
    //ArrayAdapter<String> arrayAdapter;

    BluetoothAdapter myBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt);

        // Encendido del Bluetooth
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForEnable = 1;

        bluetoothONMethod();

        // Ver los dispositivos
        listView = findViewById(R.id.listView1);

        Set<BluetoothDevice> bt = myBluetoothAdapter.getBondedDevices();

        String[] strings = new String[bt.size()];

        int index = 0;

        if(bt.size()>0){
            for(BluetoothDevice device:bt){
                strings[index] = device.getName();
                index++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,strings);
            listView.setAdapter(arrayAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Pasamos a la siguiente pantalla Main2activity
                Intent intentPantalla3 = new Intent(BTActivity.this, Main3Activity.class);
                startActivity(intentPantalla3);
            }
        });

    }

/*
        // Scan de los dispositivos

        scanButton = findViewById(R.id.btnScan);
        scanListView = findViewById(R.id.listView1);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBluetoothAdapter.startDiscovery();
            }
        });

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(myReceiver,intentFilter);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArrayList);
        scanListView.setAdapter(arrayAdapter);
*/


/*
    BroadcastReceiver myReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                stringArrayList.add(device.getName());
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCodeForEnable) {
            if (resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(), "Bluetooth Enable", Toast.LENGTH_LONG).show();
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Bluetooth Enable Cancelado", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void bluetoothONMethod() {
        if(myBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Bluetooth no Soportado",Toast.LENGTH_LONG).show();
        }
        else {
            if (!myBluetoothAdapter.isEnabled()) {
                startActivityForResult(btEnablingIntent,requestCodeForEnable);
            }
        }
    }
}
