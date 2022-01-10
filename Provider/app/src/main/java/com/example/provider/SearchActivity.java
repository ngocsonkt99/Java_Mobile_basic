package com.example.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    Button bt_exit, bt_search;
    EditText et_name;
    ListView lv_result;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et_name = (EditText)findViewById(R.id.edit_name_search);
        lv_result = (ListView)findViewById(R.id.list_item_search);
        bt_search = (Button)findViewById(R.id.button_search);

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(et_name.getText().toString());
                ArrayList<Product> list = new ArrayList<>();
                list = dbHelper.getIDAuthors(id);
                ArrayAdapter<Product> adapter = new ArrayAdapter<>(SearchActivity.this,android.R.layout.simple_list_item_1,list);
                lv_result.setAdapter(adapter);
            }
        });
    }
}
