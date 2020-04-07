package com.emekachukwulobe.sees19yearbookdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AlumnusActivity extends AppCompatActivity {

    private ImageView alimniPic;
    private TextView name;

    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnus);

        ActionBar actionBar = getSupportActionBar();

//        int sizeindp = actionBar.getHeight();

        int sizeindp = 4;

        TypedValue tv = new TypedValue();

        int actionBarHeight = 1;

        if(getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        name = findViewById(R.id.alumni_name);
//        name.setText("" + actionBarHeight);

        sizeindp = actionBarHeight;
        sizeindp *= -1;

        //int marginindp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeindp, getResources().getDisplayMetrics());
//
//
        alimniPic = findViewById(R.id.alumni_pic);
//

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) alimniPic.getLayoutParams();
        //params.setMargins(0, sizeindp, 0, 0);
        params.setMargins(0, 0, 0, 0);



        //mStorageReference = FirebaseStorage.getInstance().getReference();

        //StorageReference ahhh = mStorageReference.child("Java_certificate.jpg");

        StorageReference ahhh = FirebaseStorage.getInstance().getReference("Java_certificate.jpg");

        Glide.with(this /* context */)
                .load(ahhh)
                .into(alimniPic);

    }
}
