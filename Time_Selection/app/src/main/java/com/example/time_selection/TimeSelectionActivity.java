package com.example.time_selection;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Date;

public class TimeSelectionActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_selection);
        doWork();
    }
    public void doWork(){
        Button b1 = (Button) findViewById(R.id.btnAnalogClock);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnalogClock analog=new AnalogClock(TimeSelectionActivity.this);
                ((LinearLayout)findViewById(R.id.mylayout))
                        .addView(analog);
            }
        });
        Button b2 = (Button) findViewById(R.id.btnChronometer);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chronometer cro=new Chronometer(TimeSelectionActivity.this);
                ((LinearLayout)findViewById(R.id.mylayout))
                        .addView(cro);
            }
        });
        Button b3 = (Button) findViewById(R.id.btnTimePicker);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ((TextView)findViewById(R.id.txtdate)).setText(hourOfDay +" - "+ minute+"@@@"+
                                view.getCurrentHour() +" -" +
                                view.getCurrentMinute());
                    }
                };
                TimePickerDialog time=new TimePickerDialog(
                        TimeSelectionActivity.this, callback, 11, 30, true
                );
                time.show();
            }
        });
        Button b4 = (Button) findViewById(R.id.btnDatePickerDialog);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ((TextView)findViewById(R.id.txtdate)).setText((dayOfMonth+1)+"/" +(month+1)+"/"+year);
                    }
                };
                DatePickerDialog pic=new DatePickerDialog(
                        TimeSelectionActivity.this, callback, 2012 , 11, 30);
                pic.setTitle("My datetime picker");
                pic.show();

            }
        });
    }
}
