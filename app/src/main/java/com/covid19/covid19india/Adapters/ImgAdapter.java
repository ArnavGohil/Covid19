package com.covid19.covid19india.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.covid19.covid19india.Models.Img;
import com.covid19.covid19india.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ImgAdapter extends ArrayAdapter<Img> {
    public ImgAdapter(Activity context, ArrayList<Img> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_prec, parent, false);
        }
        final Img currentAndroidFlavor = getItem(position);

        MaterialButton button = listItemView.findViewById(R.id.download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAndroidFlavor.getID() == R.drawable.questions) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentAndroidFlavor.getURL()));
                    startActivity(getContext(), browserIntent, new Bundle());
                    return;
                }
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", currentAndroidFlavor.getURL());
                clipboard.setPrimaryClip(clip);
                Snackbar.make(view, "LINK COPIED", Snackbar.LENGTH_SHORT)
                        .setTextColor(Color.parseColor("#FFAEEA00"))
                        .show();
            }
        });

        ImageView iconView = listItemView.findViewById(R.id.imgView);
        iconView.setImageResource(currentAndroidFlavor.getID());

        return listItemView;
    }
}
