package com.example.covid19.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.example.covid19.Models.Img;
import com.example.covid19.R;


import java.util.ArrayList;

public class ImgAdapter extends ArrayAdapter<Img>
{
    public ImgAdapter(Activity context, ArrayList<Img> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_prec, parent, false);
        }

        Img currentAndroidFlavor = getItem(position);

        ImageView iconView = listItemView.findViewById(R.id.imgView);
        iconView.setImageResource(currentAndroidFlavor.getID());

        return listItemView;
    }
}
