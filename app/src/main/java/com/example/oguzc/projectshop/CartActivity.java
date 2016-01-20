package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    public static ArrayList<String> cartItemId = new ArrayList<>();
    private ListView itemListView;
    private Button cartBuyButton;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        final String username = getIntent().getStringExtra("username");

        getSupportActionBar().setTitle(" Cart");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));


        cartBuyButton = (Button)findViewById(R.id.buyCartButton);
        itemListView = (ListView)findViewById(R.id.itemListView);

        CartListAdapter adapter = new CartListAdapter(this, cartItemId);

        itemListView.setAdapter(adapter);

        cartBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i <cartItemId.size() ; i++) {
                    saveCustomerItem(username, cartItemId.get(i));
                }

                cartItemId.clear();
                onBackPressed();
            }
        });
    }

    private void saveCustomerItem(String username, String itemID){

        CustomerDatabase customerDatabase= new CustomerDatabase(this);
        SQLiteDatabase db = customerDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();

        try {

            cv.put("username", username);
            cv.put("itemId", itemID);

            db.insertOrThrow("CustomerPurchased", null, cv);

        }catch (android.database.SQLException e) {

        }

    }
}
