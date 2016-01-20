package com.example.oguzc.projectshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by oguzc on 12/18/2015.
 */
public class CdListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Cd> cdList;

    public CdListAdapter(Activity activity, List<Cd> cdList) {

        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cdList = cdList;

    }

    @Override
    public int getCount() {
        return cdList.size();
    }

    @Override
    public Cd getItem(int position) {
        return cdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lineView;

        lineView = mInflater.inflate(R.layout.list_cd_item, null);

        final ImageView cdImageView = (ImageView)lineView.findViewById(R.id.cdImageView);
        TextView cdNameView = (TextView) lineView.findViewById(R.id.cdName);
        TextView cdTypeView = (TextView) lineView.findViewById(R.id.cdType);
        TextView cdArtistView = (TextView) lineView.findViewById(R.id.artist);
        TextView cdNumberView = (TextView) lineView.findViewById(R.id.cdNumber);
        Button addButton = (Button)lineView.findViewById(R.id.cdAddButton);

        final Cd cd = cdList.get(position);
        int id = cdImageView.getContext().getResources().getIdentifier(cd.getId(), "drawable", cdImageView.getContext().getPackageName());

        if(id != 0) {
            cdImageView.setImageResource(id);
        }

        cdNameView.setText(cd.getName());
        cdTypeView.setText(cd.getType());
        cdArtistView.setText(cd.getArtist());
        cdNumberView.setText(cd.getCdNumber());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.cartItemId.add(cd.getId());
                Toast.makeText(cdImageView.getContext(), "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        return lineView;
    }

}
