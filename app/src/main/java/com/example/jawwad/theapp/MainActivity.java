package com.example.jawwad.theapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button qrButton;
    Button mapButton;
    private BroadcastReceiver broadcastReceiver;
    private float current_long, current_lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapButton=(Button)findViewById(R.id.button3);
        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
                }
            }
        );
    }

    public void onButtonTap(View v){
        Toast myToast = Toast.makeText(getApplicationContext(),"Fire in the hole!!!", Toast.LENGTH_LONG);
        myToast.show();
    }

    }



