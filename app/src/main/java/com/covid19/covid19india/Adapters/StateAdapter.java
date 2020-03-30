package com.example.covid19.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.covid19.Models.State;
import com.example.covid19.R;

import java.util.ArrayList;

public class StateAdapter extends ArrayAdapter<State>
{
    public StateAdapter(Activity context, ArrayList<State> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_state , parent, false);
        }

        final State currentAndroidFlavor = getItem(position);

        TextView state = listItemView.findViewById(R.id.sTV);
        state.setText(currentAndroidFlavor.getNam());

        TextView tot = listItemView.findViewById(R.id.tTV);
        tot.setText(currentAndroidFlavor.getTot() + "");

        TextView act = listItemView.findViewById(R.id.aTV);
        act.setText(currentAndroidFlavor.getAct() + "");

        TextView rec = listItemView.findViewById(R.id.rTV);
        rec.setText(currentAndroidFlavor.getRec() + "");

        TextView dea = listItemView.findViewById(R.id.dTV);
        dea.setText(currentAndroidFlavor.getDea() + "");

        return listItemView;
    }
}
