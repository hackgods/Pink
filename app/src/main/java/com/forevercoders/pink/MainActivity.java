package com.forevercoders.pink;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String[] quotes;
    SharedPreferences sos;
    String locURL;
    String usos;
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
        sos = getSharedPreferences("sos", Context.MODE_PRIVATE);
        sos = getSharedPreferences("sos", Context.MODE_PRIVATE);
        usos = sos.getString("sos","");
        Log.i("sostxt",usos);
        if(!ApplicationUtils.getBooleanPreferenceValue(this,"isFirstTimeExecution")){
            Log.d("TAG", "First time Execution");
            ApplicationUtils.setBooleanPreferenceValue(this,"isFirstTimeExecution",true);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_number, null);
            final EditText mPhone = (EditText) mView.findViewById(R.id.etPhone);
            Button mLogin = (Button) mView.findViewById(R.id.btnLogin);

            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!mPhone.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,
                                "SOS SET",
                                Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sos.edit();
                        editor.putString("sos",mPhone.getText().toString());
                        editor.apply();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(MainActivity.this,
                                "ERR",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }

    public void soshelp(View view) {
        sos = getSharedPreferences("sos", Context.MODE_PRIVATE);
        sos = getSharedPreferences("sos", Context.MODE_PRIVATE);
        usos = sos.getString("sos","");
        try {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null ) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();
                    locURL = "https://maps.google.com/maps?q=" + wayLatitude + "," + wayLongitude;
                    locationText = "HELP ME !!!\n" + locURL;
                    Log.i("loc", locationText);


                }
            });
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(usos, null, locationText, null, null);
            Toast.makeText(this, "Check your SMS APP, Help is on way", Toast.LENGTH_LONG).show();
            Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
            vb.vibrate(100);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "TAP AGAIN TO CONFIRM",
                    Toast.LENGTH_LONG).show();
            Log.i("ex",ex.getLocalizedMessage());
        }

    }

    public static class ApplicationUtils {


        public static void setBooleanPreferenceValue(Context context, String key, boolean value) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            sp.edit().putBoolean(key, value).apply();
        }

        public static boolean getBooleanPreferenceValue(Context context, String key) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getBoolean(key, false);
        }

    }
    public void helpline(View view) {
        startActivity(new Intent(MainActivity.this, HelpLine.class));

    }

}
