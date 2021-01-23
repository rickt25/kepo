package com.example.kepo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.kepo.activities.LoginActivity;
import com.example.kepo.activities.MenuActivity;
import com.example.kepo.model.object.User;

import static com.example.kepo.activities.LoginActivity.getCredential;
import static com.example.kepo.activities.LoginActivity.getFlag;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("LoginActivity", 0);
                Boolean flag = getFlag("flag",SplashScreen.this);

                if(flag){
                    User data = new User();
                    data.setUser_id(getCredential("user_id",SplashScreen.this));
                    data.setUsername(getCredential("username",SplashScreen.this));
                    data.setName(getCredential("name",SplashScreen.this));

                    startActivity(new Intent(getApplicationContext(), MenuActivity.class).putExtra("data",data));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }

            }
        }, 1500L); //3000 L = 3 detik
    }
}