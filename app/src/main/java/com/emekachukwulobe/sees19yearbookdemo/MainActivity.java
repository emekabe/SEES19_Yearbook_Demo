package com.emekachukwulobe.sees19yearbookdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private ImageView bgImage;

    LinearLayout alumnusButton;
    LinearLayout committeeButton;
    LinearLayout ebookButton;
    LinearLayout hardcopyButton;

    TextView randomStatButton;

    Random randomBg = new Random();

    int noToMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle("");

        alumnusButton = findViewById(R.id.alumnus_button);
        committeeButton = findViewById(R.id.committee_button);
        ebookButton = findViewById(R.id.ebook_button);
        hardcopyButton = findViewById(R.id.hardcopy_button);

        randomStatButton = findViewById(R.id.random_stat_text);

        randomStatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StatActivity.class);
                startActivity(i);
            }
        });

        alumnusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AlumnusActivity.class);
                startActivity(i);
            }
        });

        committeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CommitteeActivity.class);
                startActivity(i);
            }
        });

        ebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent();
                openIntent.setAction(Intent.ACTION_VIEW);
                String url = "https://bit.ly/YourYearbook";
                openIntent.setData(Uri.parse(url));
                startActivity(openIntent);
            }
        });

        hardcopyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noToMessage = randomBg.nextInt(2);
                String phoneNo;
                String message = "I want to get the hardcopy of the yearbook";
                if (noToMessage == 0){//Message Aanu
                    phoneNo = "2349060204500";
                }
                else { //Message lumi
                    phoneNo = "2348185528439";
                }

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + phoneNo + "&text=" + message;
                sendIntent.setData(Uri.parse(url));

                //Probably optional
                //sendIntent.setPackage("com.whatsapp");

                startActivity(sendIntent);
            }
        });
//
//        bgImage = findViewById(R.id.bg_image);
//        setNewBackgroundImage ();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bgImage = findViewById(R.id.bg_image);
        setNewBackgroundImage ();
        noToMessage = randomBg.nextInt(2);
    }

    private void setNewBackgroundImage (){
        int selectedImage = randomBg.nextInt(10);
        int bgPicture;
        switch (selectedImage){
            case 0:
                bgPicture = R.drawable.ma_background_01;
                break;
            case 1:
                bgPicture = R.drawable.ma_background_02;
                break;
            case 2:
                bgPicture = R.drawable.ma_background_03;
                break;
            case 3:
                bgPicture = R.drawable.ma_background_04;
                break;
            case 4:
                bgPicture = R.drawable.ma_background_05;
                break;
            case 5:
                bgPicture = R.drawable.ma_background_06;
                break;
            case 6:
                bgPicture = R.drawable.ma_background_07;
                break;
            case 7:
                bgPicture = R.drawable.ma_background_08;
                break;
            case 8:
                bgPicture = R.drawable.ma_background_09;
                break;
            default:
                bgPicture = R.drawable.ma_background_10;
                break;
        }
        bgImage.setImageResource(bgPicture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            //Open about
            return true;
        }
        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
