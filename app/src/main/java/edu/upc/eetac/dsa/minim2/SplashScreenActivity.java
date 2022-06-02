package edu.upc.eetac.dsa.minim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    SharedPreferences shp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getSupportActionBar().hide();
        shp = getSharedPreferences("UserData",MODE_PRIVATE);
        String username = shp.getString("userName", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(username.isEmpty())
                    intent = new Intent(SplashScreenActivity.this, SelectUserActivity.class);
                else
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}