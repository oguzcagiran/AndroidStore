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

public class BookActivity extends AppCompatActivity {

    private ListView bookList;
    private ArrayList<Book> kitaplar = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setTitle(" Book");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        bookList = (ListView)findViewById(R.id.bookListView);

        readItemsFromFile();

        BookListAdapter adapter = new BookListAdapter(this, kitaplar);

        bookList.setAdapter(adapter);

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

                    if(line.contains("Book")) {
                        String[] bookInformation = line.split(",");
                        String id = bookInformation[0];
                        String type = bookInformation[2];
                        String name = bookInformation[3];
                        String writer = bookInformation[4];
                        String page = bookInformation[5];
                        String publisher = bookInformation[6];

                        Book book = new Book(type,name,writer,page,publisher,id);
                        kitaplar.add(book);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
