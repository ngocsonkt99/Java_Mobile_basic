package com.example.btvn2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText edMaSP, edTenSP, edSoLuong;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edMaSP = (EditText) findViewById(R.id.edMaSP);
        edTenSP = (EditText) findViewById(R.id.edTenSP);
        edSoLuong = (EditText) findViewById(R.id.edSL);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.putExtra("tensp", edTenSP.getText().toString());
                intent.putExtra("masp", edMaSP.getText().toString());
                intent.putExtra("soluong", edSoLuong.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}