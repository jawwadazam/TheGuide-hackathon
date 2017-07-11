package com.example.jawwad.theapp;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Floorplans extends AppCompatActivity {


    private ArrayList<String> floorplans ;
    Spinner mSpinner;
    String floormap_name;
    ScaleGestureDetector scaleGestureDetector;
    private ImageView imageView;
    private float scale = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floorplans);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        String markerid = bundle.getString("markerid");
        getDrawables(markerid);
        mSpinner = (Spinner) findViewById(R.id.spinner) ;
        // use default spinner item to show options in spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,floorplans);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                floormap_name = adapter.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Selected Country : " + floormap_name, Toast.LENGTH_SHORT).show();

                imageView = (ImageView) findViewById(R.id.expandedimage);
                Log.wtf("myLog", "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF ==== "+floormap_name);
                String uri = "@drawable/"+floormap_name;  // where myresource (without the extension) is the file
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                Drawable res = getResources().getDrawable(imageResource);
                imageView.setImageDrawable(res);

                scaleGestureDetector = new ScaleGestureDetector(Floorplans.this, new ScaleListener());
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        float onScaleBegin = 0;
        float onScaleEnd = 0;


        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            onScaleBegin = scale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            onScaleEnd = scale;
        }
    }


    public void getDrawables(String markerid){
        Field[] ID_Fields = R.drawable.class.getFields();
        int[] resArray = new int[ID_Fields.length];
        floorplans = new ArrayList<String>();
        for(int i = 0; i < ID_Fields.length; i++) {
            try {
                resArray[i] = ID_Fields[i].getInt(null);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < resArray.length ;i++){
            if (resArray[i] != 0){
                String resource_name =  getResources().getResourceEntryName(resArray[i]);
                if(resource_name.startsWith(markerid)){
                    floorplans.add(""+resource_name);
                    for(String s:floorplans){
                        Log.wtf("myLog", "FINAL-IMAGES == "+ s);
                    }
                }
            }
        }


    }

}
