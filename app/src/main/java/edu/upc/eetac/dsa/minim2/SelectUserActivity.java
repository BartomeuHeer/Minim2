package edu.upc.eetac.dsa.minim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectUserActivity extends AppCompatActivity {
    SharedPreferences shp;
    SharedPreferences.Editor myEdit;
    Retrofit retrofit;
    Button butonSend;
    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        shp = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        myEdit = shp.edit();
        butonSend = (Button) findViewById(R.id.btnSend);
        txtName = (EditText) findViewById(R.id.etUsername);
        butonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }

    private void openProfile() {
        String userName = txtName.getText().toString();
        Log.d("dsa",userName);
        saveSharedPref(userName);
        openMainActivity();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveSharedPref(String userName){
        myEdit.putString("userName", userName);
        //myEdit.putBoolean("existsUsername", true);
        myEdit.apply();
    }

}