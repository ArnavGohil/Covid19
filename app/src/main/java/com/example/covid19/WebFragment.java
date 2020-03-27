package com.example.covid19;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        final WebView mywebview = v.findViewById(R.id.web);
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.loadUrl("http://www.google.com/");
        mywebview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK){
                    if(mywebview.canGoBack())
                        mywebview.goBack();
                    else
                        startActivity(new Intent(getActivity() , HomeActivity.class),
                                ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                    return true;
                }
                return false;
            }
        });

        return v ;
    }
}
