package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DvdActivity extends AppCompatActivity {

    private ListView dvdList;
    private ArrayList<Dvd> dvdler = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dvd);

        getSupportActionBar().setTitle(" Dvd");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        dvdList = (ListView)findViewById(R.id.dvdListView);

        readItemsFromFile();

        DvdListAdapter adapter = new DvdListAdapter(this, dvdler);

        dvdList.setAdapter(adapter);
    }

    private void readItemsFromFile() {

        try {

            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = null;

            inputStream = assetManager.open("items");

            if ( inputStream != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;

                while((line = in.readLine()) != null) {

                    if(line.contains("Dvd")) {
                        String[] bookInformation = line.split(",");
                        String id = bookInformation[0];
                        String type = bookInformation[2];
                        String name = bookInformation[3];
                        String director = bookInformation[4];
                        String time = bookInformation[5];
                        String year = bookInformation[6];

                        Dvd dvd = new Dvd(id,type,name,director,time,year);
                        dvdler.add(dvd);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}