package com.example.oguzc.projectshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by oguzc on 12/19/2015.
 */
public class CartListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> itemIdList;
    private List<String> allItemLine;

    public CartListAdapter(Activity activity, List<String> itemIdList) {

        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemIdList = itemIdList;

    }

    @Override
    public int getCount() {
        return itemIdList.size();
    }

    @Override
    public String getItem(int position) {
        return itemIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lineView;

        lineView = mInflater.inflate(R.layout.simple_list_item, null);

        final ImageView itemImageView = (ImageView)lineView.findViewById(R.id.itemImageView);
        TextView itemNameView = (TextView) lineView.findViewById(R.id.itemName);
        TextView itemTypeView = (TextView) lineView.findViewById(R.id.itemType);

        final String itemId = itemIdList.get(position);
        int id = itemImageView.getContext().getResources().getIdentifier(itemId, "drawable", itemImageView.getContext().getPackageName());

        String name="";
        String itemType="";

        allItemLine = MainMenuActivity.allItemLine;

        for (int i = 0; i <allItemLine.size() ; i++) {

            String line = allItemLine.get(i);
            String[] lineInfo = line.split(",");

            if(lineInfo[0].equals(itemId)) {

                    itemType = lineInfo[1];
                    name = lineInfo[3];
                    break;
            }
        }

        if(id != 0) {
            itemImageView.setImageResource(id);
        }

        itemNameView.setText(name);
        itemTypeView.setText(itemType);

        return lineView;
    }


}
