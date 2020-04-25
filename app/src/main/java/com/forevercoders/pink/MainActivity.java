package com.forevercoders.pink;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String[] quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quotes = getResources().getStringArray(R.array.quotes);
        int randomIndex = new Random().nextInt(quotes.length);
        String randomName = quotes[randomIndex];
        TextView quotestxt = (TextView) findViewById(R.id.quotestxt);
        quotestxt.setText(randomName);
    }
}
