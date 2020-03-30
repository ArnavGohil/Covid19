package com.covid19.covid19india.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.covid19.covid19india.R;


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
        final ProgressBar progressBar = v.findViewById(R.id.pbar);
        mywebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.INVISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.loadUrl("https://rzp.io/l/bxiRMlb");
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK){
                    
                    return true;
                }
                return false;
            }
        });

        return v ;
    }
}
