package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> list = new ArrayList<>();
    ImageView imageView;
    Gallery gallery;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(R.drawable.shv1);
        list.add(R.drawable.shv2);
        list.add(R.drawable.shv3);
        list.add(R.drawable.shv4);
        list.add(R.drawable.shv5);
        list.add(R.drawable.shv6);
        list.add(R.drawable.shv7);
        list.add(R.drawable.shv8);
        list.add(R.drawable.shv9);
        list.add(R.drawable.shv10);
        imageView = (ImageView)findViewById(R.id.image_shv);
        gallery = (Gallery)findViewById(R.id.gallery_shv);
        adapter = new MyAdapter(list, MainActivity.this);

        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageView.setImageResource(list.get(i));
            }
        });


    }
}