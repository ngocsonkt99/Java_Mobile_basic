package com.example.ungngocson_17072921;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TimActivity extends AppCompatActivity {
    EditText et_manv;
    Button bt_timkiem, bt_thoat;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim);

        et_manv = (EditText) findViewById(R.id.editText_nhapmanv);
        dbHelper = new DBHelper(this);

        bt_thoat = (Button) findViewById(R.id.button_thoat);
        bt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_timkiem = (Button) findViewById(R.id.button_tim);
        bt_timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nhanvien nhanvien = new Nhanvien();
                nhanvien.setMaso(Integer.parseInt(et_manv.getText().toString()));
                if(dbHelper.insertNhanvien(nhanvien) < 0)
                    Toast.makeText(getApplicationContext(),"khong tim thay", Toast.LENGTH_LONG ).show();
                else
                    Toast.makeText(getApplicationContext(), "tim thay", Toast.LENGTH_LONG).show();
                et_manv.setText("");
            }
        });
    }
}