package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.demo.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qlnv";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tbl_nhanvien";
    private static final String KEY_MA = "maNV";
    private static final String KEY_TEN = "tenNV";
    private static final String KEY_GIOI_TINH = "gioiTinh";
    private static final String KEY_PHONG_BAN = "phongBan";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_MA + " TEXT PRIMARY KEY, " +
                KEY_TEN + " TEXT, " +
                KEY_GIOI_TINH + " INTEGER, " +
                KEY_PHONG_BAN + " TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public boolean themNhanVien(NhanVien nhanVien) {
        boolean isThemThanhCong = false;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_MA, nhanVien.getMaNV());
            values.put(KEY_TEN, nhanVien.getTenNV());
            values.put(KEY_GIOI_TINH, nhanVien.isNam());
            values.put(KEY_PHONG_BAN, nhanVien.getPhongBan());
            isThemThanhCong = db.insertOrThrow(TABLE_NAME, null, values) > 0;
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return isThemThanhCong;
    }

    public List<NhanVien> timKiem(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String SEARCH_QUERY = "";
        if (keyword.trim().isEmpty()) { // getAll
            SEARCH_QUERY = "SELECT * FROM " + TABLE_NAME;
        } else { // get By keyword
            SEARCH_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_MA + " = '" + keyword + "'";
        }

        if (!SEARCH_QUERY.isEmpty()) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(SEARCH_QUERY, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        NhanVien nv = new NhanVien();
                        nv.setMaNV(cursor.getString(cursor.getColumnIndex(KEY_MA)));
                        nv.setTenNV(cursor.getString(cursor.getColumnIndex(KEY_TEN)));
                        nv.setNam(cursor.getInt(cursor.getColumnIndex(KEY_GIOI_TINH)) > 0);
                        nv.setPhongBan(cursor.getString(cursor.getColumnIndex(KEY_PHONG_BAN)));
                        list.add(nv);
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

    public NhanVien getNhanVienByMa(String ma) {
        NhanVien nv = null;
        String SEARCH_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_MA + " = '" + ma + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SEARCH_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                nv = new NhanVien();
                nv.setMaNV(cursor.getString(cursor.getColumnIndex(KEY_MA)));
                nv.setTenNV(cursor.getString(cursor.getColumnIndex(KEY_TEN)));
                nv.setNam(cursor.getInt(cursor.getColumnIndex(KEY_GIOI_TINH)) > 0);
                nv.setPhongBan(cursor.getString(cursor.getColumnIndex(KEY_PHONG_BAN)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return nv;
    }
}
