package com.example.menu_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Demo webview
        WebView webView = (WebView)findViewById(R.id.webview1);
        webView.loadUrl("http://www.iuh.edu.vn/");
        webView.getSettings().setJavaScriptEnabled(true);
    }
        //Demo menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.mymenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.mnxemds:
//                Toast.makeText(this,"Xem danh sach", Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.mnxemdssv:
//                Toast.makeText(this, "xem danh sach sinh vien",Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.mnsuads:
//                //processing here
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}