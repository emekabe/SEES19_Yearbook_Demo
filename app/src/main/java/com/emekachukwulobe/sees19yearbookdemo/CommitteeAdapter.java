package com.emekachukwulobe.sees19yearbookdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommitteeAdapter extends ArrayAdapter <Committee>{

    private static final String LOG_TAG = CommitteeAdapter.class.getSimpleName();

    public CommitteeAdapter(Activity context, ArrayList<Committee> committees) {
        super(context, 0, committees);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Committee currentCommittee = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(currentCommittee.getName());

        TextView roleTextView = (TextView) listItemView.findViewById(R.id.role);
        roleTextView.setText(currentCommittee.getRole());

        ImageView picView = (ImageView) listItemView.findViewById(R.id.picture);
        picView.setImageResource(currentCommittee.getPicture());

        TextView greetView = listItemView.findViewById(R.id.greet_committee);

        if (!currentCommittee.isGreeatable()){
            greetView.setVisibility(View.INVISIBLE);
        }
        else {
            greetView.setVisibility(View.VISIBLE);
        }

        return listItemView;
    }
}
