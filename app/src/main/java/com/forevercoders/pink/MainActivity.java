package com.forevercoders.pink;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String[] quotes;
    SharedPreferences sos;
    String locURL;
    String locationText;
    double wayLatitude;
    double wayLongitude;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quotes = getResources().getStringArray(R.array.quotes);
        int randomIndex = new Random().nextInt(quotes.length);
        String randomName = quotes[randomIndex];
        TextView quotestxt = (TextView) findViewById(R.id.quotestxt);
        quotestxt.setText(randomName);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    public void soshelp(View view) {
        Toast.makeText(this, "SOS sent, Help is on way", Toast.LENGTH_LONG).show();

        try {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();
                    locURL = "https://maps.google.com/maps?q=" + wayLatitude + "," + wayLongitude;
                    locationText = "HELP ME, I'm in danger !!!\n" + locURL;
                    Log.i("loc", locURL);
                }
            });
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("00916282649802", null, locationText, null, null);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }

    }
}
