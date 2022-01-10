package com.example.room_basic_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonDatabase  appDb = PersonDatabase.getInstance(this);
        //appDb.personDao().getPesonList();

//        PersonDatabase  appDb =
//                Room.databaseBuilder(getApplicationContext(),
//                        PersonDatabase.class, "DB_NAME").build();

    }
}