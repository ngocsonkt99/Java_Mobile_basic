package com.example.ungngocson_17072921;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;

public class NhanvienActivity extends AppCompatActivity {
    EditText et_manv, et_tennv, et_gioitinh, et_phongban;
    Button bt_them, bt_thoat;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);

        et_manv = (EditText)findViewById(R.id.edittext_manv);
        et_tennv = (EditText)findViewById(R.id.edittext_tennv);
        et_gioitinh = (EditText)findViewById(R.id.edittext_gioitinh);
        et_phongban = (EditText)findViewById(R.id.edittext_phongban);

        dbHelper = new DBHelper(this);

        bt_thoat = (Button)findViewById(R.id.button_thoat);
        bt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_them = (Button)findViewById(R.id.button_them);
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nhanvien nhanvien = new Nhanvien();
                nhanvien.setMaso(Integer.parseInt(et_manv.getText().toString()));
                nhanvien.setTennv(et_tennv.getText().toString());
                nhanvien.setGioitinh((et_gioitinh.getText().toString()));
                nhanvien.setPhongban(et_phongban.getText().toString());
                if(dbHelper.insertNhanvien(nhanvien) > 0)
                    Toast.makeText(getApplicationContext(),"Da them thanh cong", Toast.LENGTH_LONG ).show();
                else
                    Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_LONG).show();
                et_manv.setText("");
                et_tennv.setText("");
                et_gioitinh.setText("");
                et_phongban.setText("");
            }
        });
    }

}
