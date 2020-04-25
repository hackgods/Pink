package com.forevercoders.pink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
public class cycle extends AppCompatActivity {
CalendarView calendarView;
String myDate;
String myDate2;
String totaldays;
EditText tdays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
            String date = (i2+28) + "-" + (i1 + 1)+ "-"+ i;
            String date2 = (i2+38) + "-" + (i1 + 1)+ "-"+ i;

            myDate= date;
            myDate2= date2;
            Log.i("cycledate",myDate);
        }
    });
    }

    public void calculate(View view) {
        tdays = (EditText) findViewById(R.id.totaldays);
        totaldays = tdays.getText().toString();


        Intent intent = new Intent(this, cyclecalc.class);
        intent.putExtra("mydate", myDate);
        intent.putExtra("mydate2", myDate2);
        intent.putExtra("tdays", totaldays);
        startActivity(intent);

    }
}
