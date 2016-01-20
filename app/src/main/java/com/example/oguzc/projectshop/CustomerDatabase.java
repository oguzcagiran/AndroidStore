package com.example.oguzc.projectshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oguzc on 12/16/2015.
 */
public class CustomerDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ONLINE_SHOPPING";
    private static final int VERSION = 2;

    public CustomerDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE Customer (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT, surname TEXT, email TEXT UNIQUE, username TEXT UNIQUE, mpassword TEXT, birthdate TEXT);");
        database.execSQL("CREATE TABLE CustomerComment (id INTEGER PRIMARY KEY AUTOINCREMENT , email TEXT, comment TEXT);");
        database.execSQL("CREATE TABLE CustomerPurchased (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, itemId TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS Customer");
        database.execSQL("DROP TABLE IF EXISTS CustomerComment");
        database.execSQL("DROP TABLE IF EXISTS CustomerPurchased");
        onCreate(database);
    }

}