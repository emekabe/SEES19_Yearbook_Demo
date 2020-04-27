package com.emekachukwulobe.sees19yearbookdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


public class PasswordActivity extends AppCompatActivity {

    private EditText passwordField;
    private Button continueButton;

    private String correctPassword;

    private String file_name = "Authentication";

    private TextView passwordLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordField = findViewById(R.id.password_field);
        continueButton = findViewById(R.id.continue_button);

        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = myDatabase.getReference("password_sources");
        DatabaseReference passwordReference = myDatabase.getReference("password");

        passwordReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                correctPassword = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (correctPassword == null){
                    Toast.makeText(PasswordActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
                else if (passwordField.getText().toString().equals(correctPassword)){
                    Toast.makeText(PasswordActivity.this, "Welcome", Toast.LENGTH_LONG).show();

                    //Set file to authenticated
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write("authenticated".getBytes());
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    startActivity(new Intent(PasswordActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(PasswordActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    passwordField.setText("");
                }
            }
        });

        final String[] link = {null, null, null, null, null};


        for (int i = 0; i < 5; i++){
            final int x = i;
            reference.child("source_" + (i+1)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    link[x] = dataSnapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        passwordLink = findViewById(R.id.password_link);
        passwordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int linkOptions = r.nextInt(5);

                String chosenLink = null;

                chosenLink = link[linkOptions];

                if (chosenLink == null){
                    Toast.makeText(PasswordActivity.this,"Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Do the implicit intent
                    Intent openIntent = new Intent();
                    openIntent.setAction(Intent.ACTION_VIEW);
                    openIntent.setData(Uri.parse(chosenLink));
                    startActivity(openIntent);
                }
            }

        });
    }
}
