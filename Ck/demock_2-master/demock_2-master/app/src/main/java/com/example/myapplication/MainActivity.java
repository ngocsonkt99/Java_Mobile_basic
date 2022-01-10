package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    static  final String AUTHORITY = "com.example.demock";
    static final String CONTEN_PATH="bookdata";
    static final String URL="content://" + AUTHORITY + "/" + CONTEN_PATH;
    static final Uri CONTENT_URL = Uri.parse(URL);
    static final String TABLE_NAME = "Books";
    private SQLiteDatabase db;

    private static HashMap<String, String> BOOKS_PROJECTION_MAP;

    public static final int ALLITEMS = 1;
    public static final int ONEITEM = 2;

    static final UriMatcher uriMatcher ;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,CONTEN_PATH,ALLITEMS);
        uriMatcher.addURI(AUTHORITY,CONTEN_PATH + "/#",ONEITEM);
    }
    EditText et_id,et_title,et_author;
    Button bt_savebook,bt_select,bt_exit,bt_update,bt_delete;
    GridView gv_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv_display = findViewById(R.id.grvDisplay);
        bt_savebook = findViewById(R.id.btn_Savebook);
        et_id = findViewById(R.id.edtNhapms);
        et_author = findViewById(R.id.edtNhaptentg);
        et_title = findViewById(R.id.edtNhaptd);
        bt_savebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id_book", Integer.parseInt(et_id.getText().toString()));
                values.put("title", et_title.getText().toString());
                values.put("id_author", et_author.getText().toString());
                Uri insert = getContentResolver().insert(CONTENT_URL, values);
                Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
            }
        });
        bt_select = findViewById(R.id.btnSelect);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> lst = new ArrayList<>();
                Cursor cursor = getContentResolver().query(CONTENT_URL, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        lst.add(cursor.getInt(0) + "");
                        lst.add(cursor.getString(1));
                        lst.add(cursor.getInt(2) + "");
                    }
                    while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lst);
                    gv_display.setAdapter(adapter);
                }
            }
        });
    }
    
}
