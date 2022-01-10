package com.example.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {
    private Button btnThoat, btnTimKiem;
    private EditText etMaNV;
    private GridView gvListNV;
    private ArrayAdapter<NhanVien> adapter;
    private List<NhanVien> list;
    private MyDatabaseHelper dbHelper;
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

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Bạn có chắn muốn Thoát?")
                        .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });

//        btnThoat.setOnClickListener((View v) -> {
//            new AlertDialog.Builder(this)
//                    .setTitle("Bạn có chắn muốn Thoát?")
//                    .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .create()
//                    .show();
//        });
//
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTimKiem();
            }
        });
//        btnTimKiem.setOnClickListener(v -> {
//            doTimKiem();
//        });
    }

    private void doTimKiem() {
        String maNV = etMaNV.getText().toString();
        // adapter.clear();
        list = dbHelper.timKiem(maNV);
//        adapter.addAll(list);
//
        resultAdapter.clear();
        result.add("Mã NV");
        result.add("Tên NV");
        result.add("Phòng Ban");
        result.add("Giới Tính");
        for(NhanVien nv : list) {
            result.add(nv.getMaNV());
            result.add(nv.getTenNV());
            result.add(nv.getPhongBan());
            result.add(nv.isNam() ? "Nam" : "Nữ");
        }
        resultAdapter.addAll(result);

        gvListNV.setAdapter(resultAdapter);
        resultAdapter.notifyDataSetChanged();

        if(list.size() == 0) {
            noResult.setVisibility(View.VISIBLE);
            gvListNV.setVisibility(View.GONE);
        } else {
            noResult.setVisibility(View.GONE);
            gvListNV.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        btnThoat = findViewById(R.id.btnThoatTimKiem);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        etMaNV = findViewById(R.id.etNVTimKiem);
        gvListNV = findViewById(R.id.gvListNV);
        list = new ArrayList<>();
        dbHelper = new MyDatabaseHelper(this);
        noResult = findViewById(R.id.no_data);
    }
}