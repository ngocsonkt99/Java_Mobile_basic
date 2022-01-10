package com.example.btvn2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btvn2.Adapter.ProductAdapter;
import com.example.btvn2.models.SaleManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvKQ;
    ListView lv;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ=(TextView)findViewById(R.id.tvKQ);
        //Khởi tạo các sản phẩm
        SaleManager saleManager = SaleManager.get();
        saleManager.generateProducts();

        //lấy các product từ class saleManager
        ArrayList products = saleManager.getProducts();
        adapter = new ProductAdapter(this, products);//khởi tạo adapter
        //hiển lên listview
        lv.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 9999 )
        {
            if(resultCode == RESULT_OK) {
                String id = data.getStringExtra("masp");
                String tensanpham = data.getStringExtra("tensp");
                String soluong = data.getStringExtra("soluong");
                String kq = "Thong tin san pham: \n" ;
                kq+="- Ma khach hang: "+id+"\n";
                kq+="- Ten khach hang: "+tensanpham+"\n";
                kq+="- Dia chi: "+soluong+"\n";

                tvKQ.setText(kq);
            }
            else
            {
                tvKQ.setText("Thong tin khach hang khong co");
            }
        }
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnxemds:
                Toast.makeText(this, "Xem danh sach", Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnxemdssp:
                Toast.makeText(this, "xem danh sach sinh vien", Toast.LENGTH_LONG).show();
                return true;
        }

        switch (item.getItemId()) {
            case R.id.mnnhapsp:
                //processing here
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 9999);
                Toast.makeText(this, "CREATE", Toast.LENGTH_LONG).show();
        }

        switch (item.getItemId()) {
            case R.id.mntimsp:
                    final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.search_product);
                    EditText text1 =
                            (EditText) dialog.findViewById(R.id.tim);
                    Button proceed =
                            (Button)dialog.findViewById(R.id.button);

                    proceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    dialog.dismiss();

                            }
                    });

                    dialog.show();
                //processing here
                return true;
        }
        switch (item.getItemId()) {
            case R.id.mnxoads:
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    String[] lstsp = new String[]{
                            "SP 16",
                            "SP 12",
                            "SP 13",
                            "SP 14",
                            "SP 15"
                    };
                    final boolean[] checkedSP = new boolean[]{
                            false,
                            true,
                            false,
                            true,
                            false

                    };
                    final List<String> lstSPChon = Arrays.asList(lstsp);
                    builder.setMultiChoiceItems(lstsp, checkedSP, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                    checkedSP[which] = isChecked;
                                    String currentItem = lstSPChon.get(which);
                                    Toast.makeText(getApplicationContext(),
                                            currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                            }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    // Do something when click positive button
                                    String kq = tvKQ.getText().toString();
                                    kq += "\n Danh sach san pham chon: ";
                                    for (int i = 0; i < checkedSP.length; i++) {
                                            boolean checked = checkedSP[i];
                                            if (checked) {
                                                    kq += "- " + lstSPChon.get(i) + "\n";
                                            }
                                    }
                                    tvKQ.setText(kq);
                            }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    //processing here
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}