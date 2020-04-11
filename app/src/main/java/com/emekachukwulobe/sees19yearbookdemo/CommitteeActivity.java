package com.emekachukwulobe.sees19yearbookdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommitteeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);


        ArrayList<Committee> committee = new ArrayList<>();
        committee.add(new Committee("Babajide A.E.", "Editor-in-chief, Lead Photographer, Designer.", R.drawable.committee_01_aanu, true));
        committee.add(new Committee("Oki V.O. ", "Co-Lead Designer.", R.drawable.committee_02_victory, true));
        committee.add(new Committee("Illuezi-Ogbaudu O.", "Co-Lead Designer.", R.drawable.committee_03_mano, true));
        committee.add(new Committee("Ekechi K.A.", "Designer.", R.drawable.committee_04_alan, true));
        committee.add(new Committee("Ajayi O.O.", "Editor, Data Collector.", R.drawable.committee_05_lumi, true));
        committee.add(new Committee("Balogun A.A.", "Editor, Data Collector, Secretary.", R.drawable.committee_06_abu, true));
        committee.add(new Committee("Badru A.D.", "Editor, Data Collector.", R.drawable.committee_07_dolapo, true));
        committee.add(new Committee("Obaseki O.T.", "Editor, Data Collector.", R.drawable.committee_08_sazi, true));
        committee.add(new Committee("Ugwu K.A.", "Editor, Data Collector, Photographer.", R.drawable.committee_09_kenecomp, true));
        committee.add(new Committee("Awosika T.M.", "Editor, Data Collector.", R.drawable.committee_10_tomibaby, true));
        committee.add(new Committee("Ogbekile C.S.", "Editor, Data Collector.", R.drawable.committee_11_ogbeks, true));
        committee.add(new Committee("Adeyemo P.I.", "Editor, Data Collector.", R.drawable.committee_12_adeyemop, true));
        committee.add(new Committee("Agbetile A.O.", "Editor, Data Collector.", R.drawable.committee_13_amos, true));
        committee.add(new Committee("Elegonye E.J.", "Editor, Data Collector.", R.drawable.committee_14_keneelect, true));
        committee.add(new Committee("Macaulay I.", "Data Collector.", R.drawable.committee_15_mac, true));
        committee.add(new Committee("John A.A.", "Editor, Data Collector.", R.drawable.committee_16_tunji, true));
        committee.add(new Committee("Owoade B.A.", "Editor.", R.drawable.committee_17_bolu, true));
        committee.add(new Committee("Adenikin O.S.", "Editor.", R.drawable.committee_18_dipo, true));
        committee.add(new Committee("PlugLink", "Printing Logistics.", R.drawable.committee_19_pluglink, false));

        CommitteeAdapter committeeAdapter = new CommitteeAdapter(this, committee);

        ListView listView = findViewById(R.id.listview_committee);
        listView.setAdapter(committeeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean canGreet = true;
                String whatsAppNumber, message;
                whatsAppNumber = "234...";
                message = "Ahh";
                String username = "n/a";
                switch (position){
                    case 0:
                        whatsAppNumber = "2349060204500";
                        message = "Big man!!!";
                        break;
                    case 1:
                        whatsAppNumber = "2347038845834";
                        message = "Victory! What's up";
                        break;
                    case 2:
                        whatsAppNumber = "2348024419462";
                        message = "Manooooooooo";
                        break;
                    case 3:
                        whatsAppNumber = "2348163610689";
                        message = "Fat man utd fan. No title for you people for the next decade";
                        break;
                    case 4:
                        whatsAppNumber = "2348185528439";
                        message = "Lumi!. How far na?";
                        break;
                    case 5:
                        whatsAppNumber = "2348169622830";
                        message = "Abu Bolt. I hail.";
                        break;
                    case 6:
                        whatsAppNumber = "2347017682632";
                        message = "Dolapo. What's up?";
                        break;
                    case 7:
                        whatsAppNumber = "2348162831365";
                        message = "Sazy. What's up crazy dude?";
                        break;
                    case 8:
                        whatsAppNumber = "2348182225993";
                        message = "Odogwu!!!";
                        break;
                    case 9:
                        whatsAppNumber = "2348160752339";
                        message = "Hello Tomibaby";
                        break;
                    case 10:
                        whatsAppNumber = "2348120764928";
                        message = "Ogbeks.. Mowe Junior. How far na?";
                        break;
                    case 11:
                        whatsAppNumber = "2347068726234";
                        message = "Dude.. How's it going?";
                        break;
                    case 12:
                        whatsAppNumber = "2349090222928";
                        message = "Amos. The peace of the lord be with you.";
                        break;
                    case 13:
                        whatsAppNumber = "2348115512207";
                        message = "Big man!!!";
                        break;
                    case 14:
                        whatsAppNumber = "2348169812321";
                        message = "You this guy";
                        break;
                    case 15:
                        whatsAppNumber = "2349017275978";
                        message = "SMD.. Coache for life.";
                        break;
                    case 16:
                        whatsAppNumber = "2348089536495";
                        message = "Little boy. What's good?";
                        break;
                    case 17:
                        whatsAppNumber = "2348135952611";
                        message = "Dipo DSP.. Dipo Mowe.. Dipo GEG!";
                        break;
                    case 18:
                        canGreet = false;
                        username = "pluglinkng";
                        break;
                    default:
                        Toast.makeText(CommitteeActivity.this, "Omo!, this one na strong thing", Toast.LENGTH_LONG).show();
                        break;
                }

                if (canGreet){
                    //Do stuff

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_VIEW);
                    String url = "https://api.whatsapp.com/send?phone=" + whatsAppNumber + "&text=" + message;
                    sendIntent.setData(Uri.parse(url));

                    startActivity(sendIntent);
                }
                else{
                    //Open that IG page
                    Intent openIntent = new Intent();
                    openIntent.setAction(Intent.ACTION_VIEW);
                    String url = "https://instagram.com/" + username;
                    openIntent.setData(Uri.parse(url));

                    startActivity(openIntent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
