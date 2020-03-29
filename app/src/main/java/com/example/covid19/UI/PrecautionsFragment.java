package com.example.covid19.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid19.Models.Img;
import com.example.covid19.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrecautionsFragment extends Fragment {

    public PrecautionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_precautions, container, false);

        final ArrayList<Img> imgArrayList = new ArrayList<>();
//        imgArrayList.add(new Img());


        return v ;
    }
}
