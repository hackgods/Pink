package com.forevercoders.pink;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class cyclecalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyclecalc);
        String mydate = (String) getIntent().getStringExtra("mydate");
        String mydate2 = (String) getIntent().getStringExtra("mydate2");
        String tdays = (String) getIntent().getStringExtra("tdays");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date d = dateFormat.parse(mydate);
            TextView nxtperiod = (TextView) findViewById(R.id.nxtperiod);
            nxtperiod.setText(dateFormat.format(d));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Date d2 = dateFormat.parse(mydate2);
            TextView nxtovulation = (TextView) findViewById(R.id.nxtovulation);
            nxtovulation.setText(dateFormat.format(d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }





    }
}
