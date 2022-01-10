package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edt_input,edt_filename;
    Spinner spinner_select;
    Button btn_nhapmoi,btn_luu,btn_mo;
    ArrayList<String> filesname=new ArrayList<>();
    ArrayAdapter arrayAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgedsControl();
        btn_nhapmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_filename.setText("");
                edt_input.setText("");
            }
        });
        filesname.add("hello");
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,filesname);
        spinner_select.setAdapter(arrayAdapter);
        spinner_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edt_filename.setText(filesname.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //------SharePref--------
//                String filename=edt_filename.getText().toString(),data=null;
//                SharedPreferences sharedPref=getSharedPreferences(filename,MODE_PRIVATE);
//                data=sharedPref.getString("data",null);
//                if(data!=null){
//                    edt_input.setText(data);
//                }
                //----Internal-----
//                StringBuffer buffer=new StringBuffer();
//                String line=null;
//
//                int c;
//                try{
//                    FileInputStream fin=openFileInput(filename);
//                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fin));
//                    while ((line=bufferedReader.readLine())!=null){
//                            buffer.append(line).append("\n");
//                }
//                    edt_input.setText(buffer.toString());
//                    fin.close();
//                }
//                catch (Exception e){
//                    Toast.makeText(MainActivity.this, "Không lể mở file", Toast.LENGTH_SHORT).show();
//                }
                //----------------Externa--------------


            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename=edt_filename.getText().toString();
                filesname.add(filename);
                //------SharePref--------
//                SharedPreferences sharedPref=getSharedPreferences(filename,MODE_PRIVATE);
//                SharedPreferences.Editor editor=sharedPref.edit();
//                editor.putString("data",edt_input.getText().toString());
//                editor.commit();
                //----Internal-----
//                try{
//                    FileOutputStream fout=openFileOutput(filename, Context.MODE_PRIVATE);
//                    fout.write(edt_input.getText().toString().getBytes());
//                    fout.close();
//                }
//                catch (Exception e){
//                    Toast.makeText(MainActivity.this, "Lỗi lưu file", Toast.LENGTH_SHORT).show();
//                }
                //--------External---------------
                String state= Environment.getExternalStorageState();
                if(state.equals(Environment.MEDIA_MOUNTED)){
                    File folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File myfile=new File(folder,filename);
                    try{
                        FileOutputStream fstream=new FileOutputStream(myfile);
                        fstream.write(edt_input.getText().toString().getBytes());
                        fstream.close();
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this, "Lỗi lưu file", Toast.LENGTH_SHORT).show();
                    }

                }
                else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){

                }
                else {

                }
            }
        });

    }
    private void setWidgedsControl(){
        edt_input       =   findViewById(R.id.edt_input);
        edt_filename    =   findViewById(R.id.edt_filename);
        spinner_select  =   findViewById(R.id.spinner_select);
        btn_luu         =   findViewById(R.id.btn_luu);
        btn_mo          =   findViewById(R.id.btn_mo);
        btn_nhapmoi     =   findViewById(R.id.btn_nhapmoi);
    }
}
