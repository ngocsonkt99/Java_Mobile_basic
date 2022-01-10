package com.example.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable View.OnClickListener context) {
        super(context, "sqlite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Products(" +
                "id integer primary key," +
                "name text," +
                "unit text," +
                "madein text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Products");
        onCreate(sqLiteDatabase);
    }

    public int insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", product.getId());
        contentValues.put("name", product.getName());
        contentValues.put("unit", product.getUnit());
        contentValues.put("madein", product.getMadein());
        int result = (int) db.insert("Products", null, contentValues);
        db.close();
        return result;
    }

    public List<Product> timKiem(String keyword) {
        List<Product> list = new ArrayList<>();
        String SEARCH_QUERY = "";
        if (keyword.trim().isEmpty()) { // getAll
            SEARCH_QUERY = "SELECT * FROM Products";
        } else { // get By keyword
            SEARCH_QUERY = "SELECT * FROM Products" + " WHERE " + "id" + " = '" + keyword + "'";
        }

        if (!SEARCH_QUERY.isEmpty()) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(SEARCH_QUERY, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Product pd = new Product();
                        pd.setId(cursor.getString(cursor.getColumnIndex("id")));
                        pd.setName(cursor.getString(cursor.getColumnIndex("name")));
                        pd.setUnit(cursor.getInt(cursor.getColumnIndex("unit")) > 0);
                        pd.setMadein(cursor.getString(cursor.getColumnIndex("madein")));
                        list.add(pd);
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return list;
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Products", null);
        if (cursor != null)
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            list.add(new Product(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Product> getIDAuthors(int id) {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Products where id=" + id, null);
        if (cursor != null)
            cursor.moveToFirst();
        Product product = new Product(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        list.add(product);
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Product> getSearch(String name){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Products where name=" + name,null);
        if(cursor != null)
            cursor.moveToFirst();
        Product product = new Product(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        list.add(product);
        cursor.close();
        db.close();
        return list;
    }
}