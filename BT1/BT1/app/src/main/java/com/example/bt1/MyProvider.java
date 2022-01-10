package com.example.bt1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyProvider extends ContentProvider {
    static final String Authority="com.example.BT1.app.MyProvider";
    static final String Content_Provider="contentprovider";
    static final String URL="content://"+Authority+"/"+Content_Provider;
    static final Uri Content_Uri=Uri.parse(URL);
    static final String Product_Table="Products";

    private SQLiteDatabase db;

    static final int once=1;
    static final int two=2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Authority,Product_Table,once);
        uriMatcher.addURI(Authority,Product_Table+"/#",two);
    }
    public MyProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long number_row=db.insert(Product_Table,"",values);
        if (number_row>0){
            Uri uri1=Content_Uri.withAppendedPath(Content_Uri, String.valueOf(number_row));
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Context context=getContext();
        dbHealper dbhp=new dbHealper(context);
        db=dbhp.getReadableDatabase();
        if(db==null)
            return false;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(Product_Table);
        switch (uriMatcher.match(uri)){
            case two:
                sqLiteQueryBuilder.appendWhere("id = "+uri.getPathSegments().get(0));
                break;
            case once:
                sqLiteQueryBuilder.appendWhere("id = "+uri.getPathSegments().get(0));
                break;
        }
        if (sortOrder==null|| sortOrder==""){
            sortOrder="name";
        }
        Cursor cursor=sqLiteQueryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}