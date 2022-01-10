package com.example.btvn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.btvn.Adapter.ProductAdapter;
import com.example.btvn.models.Product;
import com.example.btvn.models.SaleManager;
import com.example.btvn.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ProductAdapter adapter;
    ArrayList products = null;
    SaleManager saleManager;
    ProductRepository repo;

    private EditText inputSearch;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lvProducts);
        //Khởi tạo các sản phẩm
        //lấy sản phẩm từ database
        repo = new ProductRepository(this);
        //lưu lấy ra mảng
            SaleManager.get().setProducts(repo.getAllProduct());

        inputSearch = (EditText) findViewById(R.id.inputSearch);


        adapter = new ProductAdapter(this,SaleManager.get().getProducts());//khởi tạo adapter
        lv.setAdapter(adapter);//hiển lên listview

        //set sự kiện khi click vào mỗi item
        lv.setOnItemClickListener(new ItemClickListener());
        lv.setOnItemLongClickListener(new ItemLongClickRemove());

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int a, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int name, int unit, int price) {
                MainActivity.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //Hàm tự động gọi khi trở lại activity này
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //cập nhật lại listview
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            adapter.notifyDataSetChanged();
        }

    }
    class ItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id)
        {
            // đến màng hình ProductActivity
            Intent intent = new Intent(MainActivity.this,ProductActivity.class);
            intent.putExtra(ProductActivity.EXTRA_POSITION, position);
            startActivityForResult(intent, 0);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnxemds:
                //Tb Click
                Toast.makeText(this, "Xem danh sach", Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnxemdssp:
                //Tb Click
                Toast.makeText(this, "xem danh sach sp", Toast.LENGTH_LONG).show();
                return true;
        }

        switch (item.getItemId()) {
            // mnnhapsp được click
            case R.id.mnnhapsp:
                //processing here
                //đến màng hình ProductActivity
                Intent intent = new Intent(this, ProductActivity.class);
                //Tb Create
                Toast.makeText(this, "CREATE", Toast.LENGTH_LONG).show();
                //tham số -1 tức ta không truyền 1 position của item nào cả
                //ta mở ProductActivity để thêm sp mới
                intent.putExtra(ProductActivity.EXTRA_POSITION, -1);
                startActivityForResult(intent, 0);
                return true;
        }

        switch (item.getItemId()) {
            // mntimsp click
            case R.id.mntimsp:
                final Dialog dialog = new Dialog(this);
                //Dialog screen
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
                //processing here
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sản phẩm này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ

                    Product pro = SaleManager.get().getProducts().get(position);
                    repo.deleteProduct(pro.getProductID());
                    SaleManager.get().getProducts().remove(position);
                    //cập nhật lại listview
                    adapter.notifyDataSetChanged();

                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
}
