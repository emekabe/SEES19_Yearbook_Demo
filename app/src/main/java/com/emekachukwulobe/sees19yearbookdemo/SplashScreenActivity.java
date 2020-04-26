package com.emekachukwulobe.sees19yearbookdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashScreenActivity extends AppCompatActivity {

    private String file_name = "Authentication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            String authCheck;

            FileInputStream fileInputStream = openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();

            while ((authCheck = bufferedReader.readLine()) != null){
                stringBuffer.append(authCheck);
            }

            if (stringBuffer.toString().equals("authenticated")){
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
            else {
                startActivity(new Intent(SplashScreenActivity.this, PasswordActivity.class));
            }
        } catch (IOException e) {
            // File doesn't exist yet so show password guy
            startActivity(new Intent(SplashScreenActivity.this, PasswordActivity.class));

            e.printStackTrace();
        }

        finish();
    }
}
