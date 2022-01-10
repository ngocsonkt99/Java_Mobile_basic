package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    EditText edId, edName, edAddress, edEmail;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    GridView gv_display;
    dbHealper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        edId = (EditText)findViewById(R.id.edId);
        edName = (EditText)findViewById(R.id.edName);
        edAddress = (EditText)findViewById(R.id.edAddress);
        edEmail = (EditText)findViewById(R.id.edEmail);

        gv_display = findViewById(R.id.drvUser);

        dbHelper = new dbHealper(this);
        bt_exit=findViewById(R.id.btnExit);
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_select = (Button)findViewById(R.id.btnSelect);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Product> lstProduct = new ArrayList<>();
                ArrayList<String> list_string = new ArrayList<>();

                if (edId.getText().toString().equals(""))
                    lstProduct = dbHelper.getAllProduct();
                else
                    lstProduct = dbHelper.getIDAuthors(Integer.parseInt(edId.getText().toString()));
                if (lstProduct.size() > 0) {
                    for (Product author : lstProduct) {
                        list_string.add(author.getId() + "");
                        list_string.add(author.getName());
                        list_string.add(author.getAddress());
                        list_string.add(author.getEmail());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProductActivity.this, android.R.layout.simple_list_item_1, list_string);
                    gv_display.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Co so du lieu rong", Toast.LENGTH_LONG).show();
                }
            }
        });
        bt_save = (Button)findViewById(R.id.btnSave);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product author = new Product();
                author.setId(Integer.parseInt(edId.getText().toString()));
                author.setName(edName.getText().toString());
                author.setAddress((edAddress.getText().toString()));
                author.setEmail(edEmail.getText().toString());
                if(dbHelper.insertAuthor(author) > 0)
                    Toast.makeText(getApplicationContext(),"Da luu thanh cong", Toast.LENGTH_LONG ).show();
                else
                    Toast.makeText(getApplicationContext(), "Luu khong thanh cong", Toast.LENGTH_LONG).show();
                edId.setText("");
                edName.setText("");
                edAddress.setText("");
                edEmail.setText("");
            }
        });
    }
}