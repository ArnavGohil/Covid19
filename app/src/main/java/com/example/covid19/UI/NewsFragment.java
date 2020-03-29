package com.example.covid19.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.covid19.Models.Card;
import com.example.covid19.Adapters.CardAdapter;
import com.example.covid19.R;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    static String URL ;
    public static HttpURLConnection urlConnection = null;
    public static InputStream inputStream = null;
    public static String jsonResponse = null;
    public static ArrayList<Card> cards = new ArrayList<>() ;
    View v ;
    ListView listView ;
    Bitmap bmp ;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_news, container, false);

        listView = v.findViewById(R.id.list_item);
        URL = "https://newsapi.org/v2/top-headlines?country=in&q=coronavirus&apiKey=4e7bc1a2ea6b402898153eb1d675db88" ;
        AsyncTask<Void, Void, Void> execute = new Utlis().execute();



        return v;
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
            cards.clear();
            Log.e("Final", "onPostExecute: " + jsonResponse );
            try {
                JSONObject jsonObject = new JSONObject(jsonResponse) ;
                JSONArray ini = jsonObject.getJSONArray("articles");
                for (int i = 0; i < ini.length() ; i++)
                {
                    JSONObject obj = ini.getJSONObject(i);
                    String tit = obj.getString("title");
                    String desc = obj.getString("url");
                    String url = obj.getString("urlToImage");

                    Log.e("NEWS", "Title: " + tit );

                    cards.add(new Card(tit , desc , url));

                }

                if(getActivity() == null)
                    return;
                CardAdapter flavorAdapter = new CardAdapter( getActivity() , cards);

                listView.setAdapter(flavorAdapter);

                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide);
                listView.startAnimation(animation);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Card item =cards.get(position);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getDesc()));
                        startActivity(browserIntent);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
