package com.example.provider;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TimKienActivity extends AppCompatActivity {
    private Button btnThoat, btnTimKiem;
    private EditText etMaNV;
    private GridView gvListNV;
    private ArrayAdapter<Product> adapter;
    private List<Product> list;
    private DatabaseHelper dbHelper;
    private TextView noResult;
    private ArrayAdapter<String> resultAdapter;
    private List<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        init();

//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        gvListNV.setAdapter(adapter);

        result = new ArrayList<>();
        resultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
        gvListNV.setAdapter(resultAdapter);

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTimKiem();
            })
        }


        private void doTimKiem () {
            String maNV = etMaNV.getText().toString();
            // adapter.clear();
            list = dbHelper.timKiem(maNV);
//        adapter.addAll(list);
//
            resultAdapter.clear();
            result.add("id");
            result.add("name");
            result.add("unit");
            result.add("madein");
            for (Product pd : list) {
                result.add(pd.getId());
                result.add(pd.getName());
                result.add(pd.getUnit());
                result.add(pd.getMadein());
            }
            resultAdapter.addAll(result);

            gvListNV.setAdapter(resultAdapter);
            resultAdapter.notifyDataSetChanged();

            if (list.size() == 0) {
                noResult.setVisibility(View.VISIBLE);
                gvListNV.setVisibility(View.GONE);
            } else {
                noResult.setVisibility(View.GONE);
                gvListNV.setVisibility(View.VISIBLE);
            }
        }

        private void init () {
            btnThoat = findViewById(R.id.btnThoatTimKiem);
            btnTimKiem = findViewById(R.id.btnTimKiem);
            etMaNV = findViewById(R.id.etNVTimKiem);
            gvListNV = findViewById(R.id.gvListNV);
            list = new ArrayList<>();
            dbHelper = new DatabaseHelper(this);
            noResult = findViewById(R.id.no_data);
        }

    }