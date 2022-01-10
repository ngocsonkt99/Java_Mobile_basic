package com.example.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.provider.MyProvider.URL;

public class ProductActivity extends AppCompatActivity {
//    private static final String URL = "content://com.example.provider";
    private static final Uri CONTENT_URI = Uri.parse(URL);
    static final String uri = "com.example.provider";
    EditText et_idproduct, et_name, et_unit, et_madein;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    GridView gv_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        et_idproduct = (EditText) findViewById(R.id.edittext_idproduct);
        et_name = (EditText) findViewById(R.id.edittext_name);
        et_unit = (EditText) findViewById(R.id.edittext_unit);
        et_madein = (EditText) findViewById(R.id.edittext_made_in);
        gv_display = (GridView) findViewById(R.id.gridview_display);
//        dbhelper =  new DatabaseHelper(this);

        bt_exit = (Button) findViewById(R.id.button_exit);
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_save = (Button) findViewById(R.id.button_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values =  new ContentValues();
                values.put("id", et_idproduct.getText().toString());
                values.put("name", et_name.getText().toString());
                values.put("unit", et_unit.getText().toString());
                values.put("madein", et_madein.getText().toString());
                Uri product = Uri.parse(uri);
                Uri insert_uri = getContentResolver().insert(CONTENT_URI, values);
                Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_LONG).show();
            }
        });

        bt_select = (Button)findViewById(R.id.button_select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_string = new ArrayList<>();
                Uri product = Uri.parse(uri);
                Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, "name");
                if (cursor != null){
                    cursor.moveToFirst();
                    do{
                        list_string.add(cursor.getInt(0) + "");
                        list_string.add(cursor.getString(1));
                        list_string.add(cursor.getString(2));
                        list_string.add(cursor.getString(3));
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter =  new ArrayAdapter<String>(ProductActivity.this, android.R.layout.simple_list_item_1, list_string);
                    gv_display.setAdapter(adapter);
                }
                else
                    Toast.makeText(getApplicationContext()," khong co ket qua" , Toast.LENGTH_LONG).show();
            }
        });

        bt_delete = (Button)findViewById(R.id.button_delete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int delete = getContentResolver().delete(CONTENT_URI, "id=?", new String[]{et_idproduct.getText().toString()});
                if (delete != 0)
                    Toast.makeText(ProductActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProductActivity.this, "Không có mã để xóa", Toast.LENGTH_SHORT).show();
            }
        });


        bt_update = (Button)findViewById(R.id.button_update);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put("name",et_name.getText().toString());
                values.put("madein",et_madein.getText().toString());
                values.put("unit",et_unit.getText().toString());

                int update = getContentResolver().update(CONTENT_URI,values,"id=?", new String[] {et_idproduct.getText().toString()});
                if (update!=0)
                    Toast.makeText(ProductActivity.this, "Update thành công!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProductActivity.this, "Khong có mã này để update", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
