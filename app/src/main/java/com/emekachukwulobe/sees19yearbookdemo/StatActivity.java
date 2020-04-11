package com.emekachukwulobe.sees19yearbookdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.ActionBar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class StatActivity extends AppCompatActivity {

    Random statChooser;
    private ImageView statImage;
    ConstraintLayout mainGuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        ActionBar actionBar = getSupportActionBar();

        TextView textView = new TextView(this);
        textView.setText("Stat");
        textView.setTextSize(20);
        textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

        textView.setTextColor(Color.parseColor("#000000"));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(textView);


        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        mainGuy = findViewById(R.id.main_guy);
        mainGuy.setBackgroundColor(Color.rgb(248, 245, 246));

        statImage = findViewById(R.id.stat_image);

        statChooser = new Random();
        int chosenStat = statChooser.nextInt(8) + 1; //Range: 1 - 8

        switch (chosenStat){
            case 1:
                statImage.setImageResource(R.drawable.stat_01);
                break;
            case 2:
                statImage.setImageResource(R.drawable.stat_02);
                break;
            case 3:
                statImage.setImageResource(R.drawable.stat_03);
                break;
            case 4:
                statImage.setImageResource(R.drawable.stat_04);
                break;
            case 5:
                statImage.setImageResource(R.drawable.stat_05);
                break;
            case 6:
                statImage.setImageResource(R.drawable.stat_06);
                break;
            case 7:
                statImage.setImageResource(R.drawable.stat_07);
                break;
            case 8:
                statImage.setImageResource(R.drawable.stat_08);
                break;
            default:
                Toast.makeText(this, "Omo.. This one na strong tin o", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}
