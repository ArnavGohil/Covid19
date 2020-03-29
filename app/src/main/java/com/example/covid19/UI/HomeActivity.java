package com.example.covid19.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.covid19.Adapters.CardAdapter;
import com.example.covid19.Adapters.StateAdapter;
import com.example.covid19.Models.Card;
import com.example.covid19.Models.State;
import com.example.covid19.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity {

    int flag = 0 ;
    FragmentManager fragmentManager = getSupportFragmentManager();
    NewsFragment newsFragment = new NewsFragment();
    PrecautionsFragment precautionsFragment = new PrecautionsFragment();
    WebFragment webFragment = new WebFragment();
    ListView lv ;
    static String URL ;
    public static HttpURLConnection urlConnection = null;
    public static InputStream inputStream = null;
    public static String jsonResponse = null;
    public static ArrayList<State> states = new ArrayList<>() ;
    TextView tot , rec,act,dea ;

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
        MobileAds.initialize(this, "ca-app-pub-1629522666877826~3226934303");
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

        tot = findViewById(R.id.textView);
        rec = findViewById(R.id.recTV);
        act = findViewById(R.id.actTV);
        dea = findViewById(R.id.deaTV);

        lv = findViewById(R.id.lv);
        URL = "https://api.covid19india.org/data.json" ;
        AsyncTask<Void, Void, Void> execute = new Utlis().execute();

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



    class Utlis extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            java.net.URL url = null;
            try
            {
                url = new URL(URL);
                Log.e("JSON", "URL WALA" + url);
            }
            catch (MalformedURLException e) { }

            try
            {

                try
                {

                    urlConnection = (HttpsURLConnection) url.openConnection();


                    // If the request was successful (response code 200),
                    // then read the input stream and parse the response.
                    if (urlConnection.getResponseCode() == 200)
                    {
                        inputStream = urlConnection.getInputStream();
                        StringBuilder output = new StringBuilder();
                        if (inputStream != null)
                        {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            String line = reader.readLine();
                            while (line != null)
                            {
                                output.append(line);
                                line = reader.readLine();
                            }
                        }
                        jsonResponse = output.toString();
                        Log.e("JSON", "JSON WALA" + jsonResponse);

                    }
                }
                catch (IOException e) {
                }
                finally
                {
                    if (urlConnection != null)
                    {
                        urlConnection.disconnect();
                    }
                    if (inputStream != null)
                    {
                        inputStream.close();
                    }
                }
            }
            catch (IOException e)
            {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            states.clear();
            Log.e("Final", "onPostExecute: " + jsonResponse );
            try {
                JSONObject jsonObject = new JSONObject(jsonResponse) ;
                JSONArray ini = jsonObject.getJSONArray("statewise");
                JSONObject o = ini.getJSONObject(0);
                tot.setText("Total Cases - " + o.getString("confirmed"));
                act.setText("Active Cases - " + o.getInt("active"));
                rec.setText("Recovered - " + o.getInt("recovered"));
                dea.setText("Deaths - " + o.getInt("deaths"));
                for (int i = 1; i < ini.length() ; i++)
                {
                    JSONObject obj = ini.getJSONObject(i);
                    String st = obj.getString("state");
                    int to = obj.getInt("confirmed");
                    int ac = obj.getInt("active");
                    int re = obj.getInt("recovered");
                    int de = obj.getInt("deaths");


                    states.add(new State(st,to ,ac , re , de ));

                }

                StateAdapter flavorAdapter = new StateAdapter( HomeActivity.this , states);
                lv.setAdapter(flavorAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
