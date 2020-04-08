package com.emekachukwulobe.sees19yearbookdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AlumnusActivity extends AppCompatActivity {

    private ImageView alimniPic;
    //private TextView name;

    private TextView alumniName;
    private TextView alumniNickname;
    private TextView alumniProgramme;
    private TextView alumniQuestion;
    private TextView alumniAnswer;

    Button twitterButton;
    Button linkedinButton;
    Button igButton;
    Button emailButton;

    private  TextView pageButton;

    private StorageReference mStorageReference;

    private RelativeLayout loadingView;
    private RelativeLayout profileView;
//    private LinearLayout profileViewLandscape;

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

//        name = findViewById(R.id.alumni_name);
//        name.setText("" + actionBarHeight);

        sizeindp = actionBarHeight;
        sizeindp *= -1;

        //int marginindp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeindp, getResources().getDisplayMetrics());
//
//

        profileView = findViewById(R.id.profile_view);
        loadingView = findViewById(R.id.pb_view);
        //profileViewLandscape = findViewById(R.id.profile_view_landscape);

        alimniPic = findViewById(R.id.alumni_pic);
//

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) alimniPic.getLayoutParams();
        //params.setMargins(0, sizeindp, 0, 0);
        params.setMargins(0, 0, 0, 0);



        //mStorageReference = FirebaseStorage.getInstance().getReference();

        //StorageReference ahhh = mStorageReference.child("Java_certificate.jpg");

        StorageReference ahhh = FirebaseStorage.getInstance().getReference(Alumni.ALUMNI_PIC);

        Glide.with(this /* context */)
                .load(ahhh)
                .into(alimniPic);

        alumniName = findViewById(R.id.alumni_name);
        alumniNickname = findViewById(R.id.alumni_nickname);
        alumniQuestion = findViewById(R.id.alumni_question);
        alumniAnswer = findViewById(R.id.alumni_answer);
        alumniProgramme = findViewById(R.id.alumni_programme);

        //Set values to the dummy variables first before fetching them from the database (Just a little precaution.. to avoid 'null' things)
        alumniName.setText(Alumni.ALUMNI_NAME);
        alumniNickname.setText("(" + Alumni.ALUMNI_NICKNAME + ")");
        alumniQuestion.setText(Alumni.ALUMNI_QUESTION);
        alumniAnswer.setText(Alumni.ALUMNI_ANSWER);
        alumniProgramme.setText(Alumni.ALUMNI_PROGRAMME);

        //Buttons
        twitterButton = findViewById(R.id.twitter_button);
        linkedinButton = findViewById(R.id.linkedin_button);
        igButton = findViewById(R.id.ig_button);
        emailButton = findViewById(R.id.email_button);

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent();
                openIntent.setAction(Intent.ACTION_VIEW);
                String url = "https://m.twitter.com/" + Alumni.TWITTER_HANDLE;
                openIntent.setData(Uri.parse(url));
//                sendIntent.setPackage("com.android.chrome");
                startActivity(openIntent);
            }
        });

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent();
                openIntent.setAction(Intent.ACTION_VIEW);
                String url = Alumni.LINKEDIN_URL;  // Probably concatenate with "https://" later
                openIntent.setData(Uri.parse(url));
//                sendIntent.setPackage("com.android.chrome");
                startActivity(openIntent);
            }
        });

        igButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openIntent = new Intent();
                openIntent.setAction(Intent.ACTION_VIEW);
                String url = "https://instagram.com/" + Alumni.IG_HANDLE;
                openIntent.setData(Uri.parse(url));
//                sendIntent.setPackage("com.android.chrome");
                startActivity(openIntent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + Alumni.EMAIL)); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello " + Alumni.ALUMNI_NICKNAME);
//                intent.putExtra(Intent.EXTRA_TEXT, message);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        pageButton = findViewById(R.id.page_button);

        pageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlumnusActivity.this, PageActivity.class));
            }
        });


        //////////////////////////////////////////////////////////////////////
//    Firebaseeeeeeeee
        ///////////////////////////

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference controlGroup = database.getReference("control_center");
        DatabaseReference alumniGroup = database.getReference("alumni_otd");

        alumniGroup.child("alumni_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.ALUMNI_NAME= dataSnapshot.getValue(String.class);
                alumniName.setText(Alumni.ALUMNI_NAME);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("alumni_nickname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.ALUMNI_NICKNAME= dataSnapshot.getValue(String.class);
                alumniNickname.setText("(" + Alumni.ALUMNI_NICKNAME + ")");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("alumni_programme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.ALUMNI_PROGRAMME= dataSnapshot.getValue(String.class);
                alumniProgramme.setText(Alumni.ALUMNI_PROGRAMME);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("alumni_pic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.ALUMNI_PIC= dataSnapshot.getValue(String.class);
                StorageReference loadedPic = FirebaseStorage.getInstance().getReference(Alumni.ALUMNI_PIC);

                Glide.with(AlumnusActivity.this /* context */)
                        .load(loadedPic)
                        .into(alimniPic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("twitter_handle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.TWITTER_HANDLE= dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("linkedin_url").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.LINKEDIN_URL= dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("ig_handle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.IG_HANDLE= dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.EMAIL= dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("page_link").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Alumni.PAGE_LINK= dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("question").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.ALUMNI_QUESTION= dataSnapshot.getValue(String.class);
                alumniQuestion.setText(Alumni.ALUMNI_QUESTION);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        alumniGroup.child("answer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Alumni.ALUMNI_ANSWER = dataSnapshot.getValue(String.class);
                alumniAnswer.setText(Alumni.ALUMNI_ANSWER);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        controlGroup.child("loaded").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ControlCenter.LOADED = dataSnapshot.getValue(String.class);

                if (ControlCenter.LOADED.toLowerCase().equals("yes")){
                    loadingView.setVisibility(View.GONE);
                    profileView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        controlGroup.child("student_available").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ControlCenter.STUDENT_AVAILABLE = dataSnapshot.getValue(String.class);
                // Delete this child later.. It isn't necessary
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
