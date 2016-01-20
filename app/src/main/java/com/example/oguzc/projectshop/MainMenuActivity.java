package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private ImageButton cdButton;
    private ImageButton dvdButton;
    private ImageButton bookButton;
    private Button cartButton;
    private Button exitButton;
    private Button purchasedItemButton;
    public static List<String> allItemLine = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        final String username = intent.getStringExtra("username");

        getSupportActionBar().setTitle("Welcome " + name + " " + surname);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        setContentView(R.layout.activity_main_menu);

        cdButton = (ImageButton)findViewById(R.id.musicCdButton);
        dvdButton = (ImageButton)findViewById(R.id.filmDvdButton);
        bookButton = (ImageButton)findViewById(R.id.bookButton);
        cartButton = (Button)findViewById(R.id.cartButton);
        exitButton = (Button)findViewById(R.id.exitButton);
        purchasedItemButton = (Button)findViewById(R.id.purchasedButton);

        readItemsFromFile();

        cartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        cdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CdActivity.class);
                startActivity(intent);
            }
        });

        dvdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DvdActivity.class);
                startActivity(intent);
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CartActivity.cartItemId.clear();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        purchasedItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PurchasedItemAcitivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

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
                    allItemLine.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}