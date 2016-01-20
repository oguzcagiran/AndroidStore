package com.example.oguzc.projectshop;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class PurchasedItemAcitivity extends AppCompatActivity {

    private ListView purchasedItemListView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_item_acitivity);

        String username = getIntent().getStringExtra("username");

        getSupportActionBar().setTitle(" Purchased Items");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#187190")));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));

        List<String> itemList = getCustomerItemsFromDatabase(username);

        purchasedItemListView = (ListView)findViewById(R.id.purchasedItemListView);

        CartListAdapter adapter = new CartListAdapter(this, itemList);

        purchasedItemListView.setAdapter(adapter);

    }

    private List<String> getCustomerItemsFromDatabase(String username) {

        List<String> customerProducts = new ArrayList<>();

        try {


            CustomerDatabase customerDatabase = new CustomerDatabase(this);
            SQLiteDatabase db = customerDatabase.getReadableDatabase();

            Cursor cursor = db.query("CustomerPurchased", new String[]{"itemId"}, "username=?", new String[]{username}, null, null, null, null);
            if (cursor .moveToFirst()) {

                while (cursor.isAfterLast() == false) {

                    String itemId = cursor.getString(cursor.getColumnIndex("itemId"));
                    customerProducts.add(itemId);
                    cursor.moveToNext();

                }

            }

        } catch (Exception e) {

        }

        return customerProducts;
    }

}

