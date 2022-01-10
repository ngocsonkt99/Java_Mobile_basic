package com.example.app1_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {

    static final String AUTHORITY = "com.example.app1_provider";
    static final String CONTENT_PATH = "bookdata";
    static final String URL = "content://" + AUTHORITY + "/" +CONTENT_PATH;
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String TABLE_NAME = "Books";
    private SQLiteDatabase db;

    private  static HashMap<String,String> BOOK_PROJECT_MAP;
    static final int ALLITEMS = 1;
    static final int ONEITEM = 2;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,CONTENT_PATH,ALLITEMS);
        uriMatcher.addURI(AUTHORITY,CONTENT_PATH+"/#",ONEITEM);
    }

    private static class BookDBHeplper extends SQLiteOpenHelper
    {
        BookDBHeplper(Context context)
        {
            super(context, "Books", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table Books(id integer primary key,title text,author text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
            onCreate(sqLiteDatabase);
        }
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        BookDBHeplper dbHeplper = new BookDBHeplper(context);
        db = dbHeplper.getWritableDatabase();
        if (db == null)
            return false;
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TABLE_NAME);
        switch (uriMatcher.match(uri)) {
            case ALLITEMS:
                sqLiteQueryBuilder.setProjectionMap(BOOK_PROJECT_MAP);
                break;
            case ONEITEM:
                sqLiteQueryBuilder.appendWhere("id" + "" + uri.getPathSegments().get(0));
                break;
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = "title";
        }
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert( Uri uri,ContentValues values) {
        long number_row = db.insert(TABLE_NAME,"",values);
        if (number_row>0){
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI,number_row);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new SQLException("Trùng mã");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case ALLITEMS:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case ONEITEM:
                String id = uri.getPathSegments().get(1);
                count = db.delete(TABLE_NAME, "id" + "=" + id + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                try {
                    throw  new IllegalAccessException("Unknown URI"+ uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        getContext().getContentResolver().notifyChange(uri,null); //thieu
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case ALLITEMS:
                count = db.update(TABLE_NAME,values,selection,selectionArgs);
                break;
            case ONEITEM:
                count = db.update(TABLE_NAME,values,"id"+"="+uri.getPathSegments().get(1)+
                        (!TextUtils.isEmpty(selection)? "AND (" + selection + ')':""),selectionArgs);
            default:
                try {
                    throw  new IllegalAccessException("Unknown URI"+ uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return  count;
    }
}
