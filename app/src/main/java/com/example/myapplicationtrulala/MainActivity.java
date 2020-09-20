package com.example.myapplicationtrulala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private Adapter mAdapter;

    private Bitmap bb;

    private RecyclerView mNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



          loadWeatherData(); //на first page не праює, до по цепочці все далі грузить а вигружать нема куди (мабуть)
    }




    private void loadWeatherData() {

        new WeaterQueryTask().execute("https://chitadrita.herokuapp.com/");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idItemThetWasSelected=item.getItemId();

        if(idItemThetWasSelected==R.id.action_refresh)
        {
            loadWeatherData();
        }

        return super.onOptionsItemSelected(item);
    }




    public class WeaterQueryTask extends AsyncTask<String, Void, String[]> {


        @Override
        protected String[] doInBackground(String... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }


            URL weatherRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl);



                String[] simpleJsonWeatherData= OpenWeatherJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);



               bb=getBitmap("https://chitadrita.herokuapp.com/get-image?image=bol.jpeg");

                return simpleJsonWeatherData;


            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }

        }



        @Override
        protected void onPostExecute(String[] strings) {

            mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mNumbersList.setLayoutManager(layoutManager);

            mAdapter = new Adapter(MainActivity.this, strings, bb);
            mNumbersList.setAdapter(mAdapter);




        }

        public Bitmap getBitmap(String url) {
            try {

                InputStream is = (InputStream) new URL(url).getContent();

                Bitmap d = BitmapFactory.decodeStream(is);
                is.close();

                return d;
            } catch (Exception e) {

                return null;
            }
        }
    }


}
