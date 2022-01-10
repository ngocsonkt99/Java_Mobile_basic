package com.example.app1_provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "content://com.example.app1_provider/bookdata";
    private static final Uri CONTENT_URI = Uri.parse(URL);

    EditText edtID,edtTitle,edtAuthor;
    Button btnSelect,btnDelete,btnSave,btnUpdate,btnExit;
    GridView grvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edt_id);
        edtTitle = findViewById(R.id.edt_title);
        edtAuthor = findViewById(R.id.edt_author);
        btnDelete = findViewById(R.id.btn_delete);
        btnExit = findViewById(R.id.btn_thoat);
        btnSave = findViewById(R.id.btn_save);
        btnSelect = findViewById(R.id.btn_select);
        btnUpdate = findViewById(R.id.btn_update);
        grvDisplay = findViewById(R.id.grv_thongtin);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtID.getText().toString().equals("")){
                try {
                    ContentValues values = new ContentValues();
                    values.put("id",edtID.getText().toString());
                    values.put("title",edtTitle.getText().toString());
                    values.put("author",edtAuthor.getText().toString());

                    Uri insert_uri = getContentResolver().insert(CONTENT_URI,values);
                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }
                catch (SQLException ex)
                {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                }
                else
                    Toast.makeText(MainActivity.this, "Chưa nhập mã để lưu", Toast.LENGTH_SHORT).show();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                Cursor cursor = getContentResolver().query(CONTENT_URI,null,null,null,"id");
                if(cursor!=null && cursor.moveToFirst()){

                    do {
                        list.add(cursor.getInt(0)+"");
                        list.add(cursor.getString(1));
                        list.add(cursor.getString(2));
                    }while (cursor.moveToNext());
                    {
                        ArrayAdapter<String> adapter =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                        grvDisplay.setAdapter(adapter);
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Khong co id can tim", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int delelte = getContentResolver().delete(CONTENT_URI, "id=?", new String[]{edtID.getText().toString()});
                    if (delelte != 0)
                        Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Không có mã để xóa", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put("title",edtTitle.getText().toString());
                values.put("author",edtAuthor.getText().toString());
                int update = getContentResolver().update(CONTENT_URI,values,"id=?", new String[] {edtID.getText().toString()});
                if (update!=0)
                    Toast.makeText(MainActivity.this, "Update thành công!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Khong có mã này để update", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
