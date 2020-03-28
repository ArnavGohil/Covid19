package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    int flag = 0 ;
    FragmentManager fragmentManager = getSupportFragmentManager();
    NewsFragment newsFragment = new NewsFragment();
    PrecautionsFragment precautionsFragment = new PrecautionsFragment();
    WebFragment webFragment = new WebFragment();

    BottomAppBar.OnMenuItemClickListener itemClickListner
            = new  BottomAppBar.OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId() ;

            if(id == R.id.ind  && flag != 0)
            {
                fragmentManager.beginTransaction().remove(precautionsFragment).remove(webFragment).remove(newsFragment).commit();
                flag = 0 ;
                return false;
            }
            else if(id == R.id.news  && flag != 1)
            {
                fragmentManager.beginTransaction()
                        .remove(precautionsFragment).remove(webFragment)
                        .add(R.id.frame, newsFragment)
                        .commit();
                flag = 1 ;
                return false;
            }
            else if(id == R.id.prec  && flag != 2)
            {

                fragmentManager.beginTransaction()
                        .remove(newsFragment).remove(webFragment)
                        .add(R.id.frame, precautionsFragment)
                        .commit();
                flag = 2 ;
                return false;
            }
            return false;
        }
    };

    FloatingActionButton fab ;
    FrameLayout frameLayout ;
    AdView mAdView ;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MobileAds.initialize(this, "ca-app-pub-9644485909591428~2743433168");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        BottomAppBar bar = findViewById(R.id.bottomAppBar);
        bar.setOnMenuItemClickListener(itemClickListner);

        fab = findViewById(R.id.fab);
        frameLayout = findViewById(R.id.frame) ;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(savedInstanceState == null && flag != 3) {
                    flag = 3;
                    fragmentManager.beginTransaction()
                            .add(R.id.frame, webFragment)
                            .remove(precautionsFragment)
                            .remove(newsFragment)
                            .commit();
                }
            }
        });

    }


}
