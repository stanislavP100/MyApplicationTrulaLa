package com.example.myapplicationtrulala;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Adapter mAdapter;

    private ArrayList <Bitmap> bb=new ArrayList<>();

    private RecyclerView mNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



          loadWeatherData();
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




    public class WeaterQueryTask extends AsyncTask<String, Void, ArrayList<Product> > {


        @Override
        protected ArrayList <Product> doInBackground(String... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            ArrayList<Product> tt=null;

            URL weatherRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl);

               tt=OpenWeatherJsonUtils.getDetailsFromJson(jsonWeatherResponse);


                Bitmap tmp, tmp2;

                ArrayList <String> imageName=new ArrayList<>(17);


                for( Product i : tt)
                {
                    String oo="https://chitadrita.herokuapp.com/get-image?image="+i.getImage();
                    imageName.add(oo);;
                }


                for(String i : imageName){

                    System.out.println(i);

                   tmp=getBitmap(i);

                   if(tmp==null)
                       tmp=getBitmap("https://chitadrita.herokuapp.com/get-image?image=bol.jpeg");

                    tmp2=Bitmap.createScaledBitmap(tmp,300,300,true);
                    bb.add(tmp2);
                }

                System.out.println(tt.size()+" IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");


            return tt;



            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Product> product) {



            mNumbersList = findViewById(R.id.rv_numbers);

            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mNumbersList.setLayoutManager(layoutManager);

            mAdapter = new Adapter(MainActivity.this, product, bb);
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
