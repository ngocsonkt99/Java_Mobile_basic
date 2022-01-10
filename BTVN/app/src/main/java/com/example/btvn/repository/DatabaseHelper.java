package com.example.btvn.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "Sales";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1); // 1: version
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = ProductRepository.getCreateTableSQL();
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
