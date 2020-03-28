package com.example.covid19;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card>
{
    public CardAdapter(Activity context, ArrayList<Card> cards) {

        super(context, 0, cards);
    }

    URL url;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                   R.layout.news_item, parent, false);
        }


        Card currentAndroidFlavor = getItem(position);


        TextView title = listItemView.findViewById(R.id.head);


        title.setText(currentAndroidFlavor.getTitle());


        ImageView iconView = listItemView.findViewById(R.id.Nimg);

        Picasso.with(this.getContext())
                .load(currentAndroidFlavor.getBmp())
                .centerCrop()
                .fit()
                .into(iconView);



        return listItemView;
    }
}
