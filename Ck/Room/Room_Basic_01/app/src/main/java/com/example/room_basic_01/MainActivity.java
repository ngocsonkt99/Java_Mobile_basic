package com.example.room_basic_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String name, designation;
    EditText etName, etDesignation;

    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = AppDatabase.getInstance(this);

        name = "aaa";
        //((EditText)findViewById(R.id.etName)). getText().toString();""
        designation = "bbb";
                //((EditText)findViewById(R.id.etDesignation)).getText().toString();

    }

    public void ClickToAdd(View view) {
        //Hàm xử lý click
        Employee employee = new Employee();
        employee.name = name;
        employee.designation = designation;

        mDb.employDao().insertEmploy(employee);
        Toast.makeText(this,
                "Saved successfully", Toast.LENGTH_SHORT).show();
        //etName.setText("");
       // etDesignation.setText("");
        //TetName.requestFocus();
        //populateEmployList();
    }
}