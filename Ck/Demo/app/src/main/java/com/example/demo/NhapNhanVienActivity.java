package com.example.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.model.NhanVien;

public class NhapNhanVienActivity extends AppCompatActivity {
    private Button btnThoat, btnThem;
    private EditText etMaNV, etTenNV;
    private RadioGroup rgGioiTinh;
    private RadioButton rbNam, rbNu;
    private Spinner spnPhongBan;
    private MyDatabaseHelper dbHelper;
    private ArrayAdapter<String> adapterPhongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_nhan_vien);
        init();
        setupSpinner();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nv = validateForm();
                if(nv != null && dbHelper.themNhanVien(nv)) {
                    Toast.makeText(getApplicationContext(), "Them nhan vien thanh cong!", Toast.LENGTH_SHORT).show();
                    clearForm();
                }
            }
        });

//        btnThem.setOnClickListener((View v) -> {
//            NhanVien nv = validateForm();
//            if(nv != null && dbHelper.themNhanVien(nv)) {
//                Toast.makeText(this, "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
//                clearForm();
//            }
//        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Bạn có chắn muốn Thoát?")
                        .setMessage("Tác vụ sẽ không được hoàn lại")
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
//                    .setMessage("Tác vụ sẽ không được hoàn lại")
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
    }

    private void clearForm() {
        etMaNV.setText("");
        etTenNV.setText("");
        spnPhongBan.setSelection(0);
        rgGioiTinh.check(rbNam.getId());
        etMaNV.requestFocus();
    }

    private void setupSpinner() {
        adapterPhongBan = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ds_phong_ban));
        spnPhongBan.setAdapter(adapterPhongBan);
    }

    private NhanVien validateForm() {
        etMaNV.setError(null);
        etTenNV.setError(null);
        String maNV = etMaNV.getText().toString();
        String tenNV = etTenNV.getText().toString();
        boolean isMale =  rbNam.isChecked();
        String phongBan = (String) spnPhongBan.getSelectedItem();

        if (maNV.isEmpty()) {
            etMaNV.setError("Bắt buộc nhập");
            return null;
        } else {
            if(dbHelper.getNhanVienByMa(maNV) != null) {
                etMaNV.setError("Mã nhân viên này đã tồn tại");
                return null;
            }
        }

        if (tenNV.isEmpty()) {
            etTenNV.setError("Bắt buộc nhập");
            return null;
        }

        return new NhanVien(maNV, tenNV, isMale, phongBan);
    }

    private void init() {
        btnThem = findViewById(R.id.btnThemNV);
        btnThoat = findViewById(R.id.btnThoatTimKiem);
        etMaNV = findViewById(R.id.etMaNV);
        etTenNV = findViewById(R.id.etTenNV);
        rgGioiTinh = findViewById(R.id.rgGioiTinh);
        rbNam = findViewById(R.id.rbNam);
        rbNu = findViewById(R.id.rbNam);
        spnPhongBan = findViewById(R.id.spnPhongBan);
        dbHelper = new MyDatabaseHelper(this);
    }
}