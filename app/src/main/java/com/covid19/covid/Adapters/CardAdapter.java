package com.covid19.covid.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.covid19.covid.CircleTransform;
import com.covid19.covid.Models.Card;
import com.covid19.covid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card> {
    public CardAdapter(Activity context, ArrayList<Card> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_news, parent, false);
        }


        Card currentAndroidFlavor = getItem(position);


        TextView title = listItemView.findViewById(R.id.head);


        title.setText(currentAndroidFlavor.getTitle());


        ImageView iconView = listItemView.findViewById(R.id.Nimg);

        Picasso.with(this.getContext())
                .load(currentAndroidFlavor.getBmp())
                .centerCrop()
                .transform(new CircleTransform(40, 0))
                .fit()
                .into(iconView);


        return listItemView;
    }
}
