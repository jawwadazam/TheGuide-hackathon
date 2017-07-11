package com.example.jawwad.theapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BuildingInfo extends AppCompatActivity {

    Button floorplanButton;
    Button lecturerInfoButton;
    private String markerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        markerid = bundle.getString("markerid");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_info);
        floorplanButton=(Button)findViewById(R.id.floorplanButton);
        floorplanButton.setOnClickListener(new View.OnClickListener(){
            @Override
             public void onClick(View v){
                  Intent i = new Intent(BuildingInfo.this, Floorplans.class);
                  i.putExtra("markerid", markerid);
                  startActivity(i);
                       }
                   }
        );

    }



}
