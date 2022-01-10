package com.example.ungngocson_17072921;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.view.menu.MenuAdapter;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.nhapnv:
                Intent intent_nhanvien = new Intent(MainActivity.this, NhanvienActivity.class);
                startActivity(intent_nhanvien);
                return true;
            case  R.id.timnv:
                Intent intent_author = new Intent(MainActivity.this, TimActivity.class);
                startActivity(intent_author);
                return true;
            case  R.id.thoat:
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}