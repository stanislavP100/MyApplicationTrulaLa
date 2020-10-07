package com.example.myapplicationtrulala;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class OpenWeatherJsonUtils {


    public static String[] getSimpleWeatherStringsFromJson( String forecastJsonStr)
            throws JSONException {

        /* Weather information. Each day's forecast info is an element of the "list" array */
        final String OWM_LIST ="all goods"; //"electroTools"; //"all goods"

        /* All temperatures are children of the "temp" object */
        final String OWM_NAME = "name";
        final String OWM_IMAGE = "image";



        /* Max temperature for the day */
        final String OWM_ID = "id";
        final String OWM_PRICE = "price";



        /* String array to hold each day's weather String */
        String[] parsedWeatherData = null;

        JSONObject allGoodsJson = new JSONObject(forecastJsonStr);

        parsedWeatherData = new String[allGoodsJson.getJSONArray(OWM_LIST).length()];



        for (int i = 0; i < allGoodsJson.getJSONArray(OWM_LIST).length(); i++) {

            String name;

            String image;

            Long id;

            Double price;


            JSONObject oneGood = allGoodsJson.getJSONArray(OWM_LIST).getJSONObject(i);

            image =oneGood.getString(OWM_IMAGE);

            name=oneGood.getString(OWM_NAME);

            id=oneGood.getLong(OWM_ID);

            price=oneGood.getDouble(OWM_PRICE);


            parsedWeatherData[i] =id+" "+ name+" "+ price+" "+image;


        }


        return parsedWeatherData;
    }

    public static ArrayList<Product> getDetailsFromJson(String st)throws JSONException

    {
        ArrayList<Product> products=new ArrayList<>();

        final String OWM_LIST ="all goods"; //"electroTools"; //"all goods"

        /* All temperatures are children of the "temp" object */
        final String OWM_NAME = "name";
        final String OWM_IMAGE = "image";



        /* Max temperature for the day */
        final String OWM_ID = "id";
        final String OWM_PRICE = "price";


        JSONObject allGoodsJson = new JSONObject(st);



        for (int i = 0; i < allGoodsJson.getJSONArray(OWM_LIST).length(); i++) {

            Product tmp=new
                    Product();

            JSONObject oneGood = allGoodsJson.getJSONArray(OWM_LIST).getJSONObject(i);

            tmp.setImage(oneGood.getString(OWM_IMAGE));

            tmp.setName(oneGood.getString(OWM_NAME));

            tmp.setId(oneGood.getLong(OWM_ID));

            tmp.setPrice(oneGood.getDouble(OWM_PRICE));


            products.add(i,tmp);


        }



        return products;
    }
}