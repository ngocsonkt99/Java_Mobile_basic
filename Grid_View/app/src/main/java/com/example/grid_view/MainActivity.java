package com.example.grid_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String [] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        arr = getResources().getStringArray(R.array.presidents_array);
        final TextView selection =(TextView) findViewById(R.id.selection);
        final GridView gv=(GridView) findViewById(R.id.gridView1);
        ArrayAdapter<String>da=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        gv.setAdapter(da);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selection.setText(arr[arg2]);
            }
        });

    }
}