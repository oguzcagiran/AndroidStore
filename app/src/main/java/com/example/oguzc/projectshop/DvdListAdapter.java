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
public class DvdListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Dvd> dvdList;

    public DvdListAdapter(Activity activity, List<Dvd> dvdList) {

        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dvdList = dvdList;

    }

    @Override
    public int getCount() {
        return dvdList.size();
    }

    @Override
    public Dvd getItem(int position) {
        return dvdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lineView;

        lineView = mInflater.inflate(R.layout.list_dvd_item, null);

        final ImageView dvdImageView = (ImageView)lineView.findViewById(R.id.dvdImageView);
        TextView dvdNameView = (TextView) lineView.findViewById(R.id.dvdName);
        TextView dvdTypeView = (TextView) lineView.findViewById(R.id.dvdType);
        TextView dvdDirectorView = (TextView) lineView.findViewById(R.id.director);
        TextView dvdTimeView = (TextView) lineView.findViewById(R.id.time);
        TextView dvdYearView = (TextView) lineView.findViewById(R.id.year);
        Button addButton = (Button)lineView.findViewById(R.id.addDvdButton);

        final Dvd dvd = dvdList.get(position);
        int id = dvdImageView.getContext().getResources().getIdentifier(dvd.getId(), "drawable", dvdImageView.getContext().getPackageName());

        if(id != 0) {
            dvdImageView.setImageResource(id);
        }

        dvdNameView.setText(dvd.getName());
        dvdTypeView.setText(dvd.getType());
        dvdDirectorView.setText(dvd.getDirector());
        dvdTimeView.setText(dvd.getTime());
        dvdYearView.setText(dvd.getYear());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.cartItemId.add(dvd.getId());
                Toast.makeText(dvdImageView.getContext(), "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        return lineView;
    }


}
