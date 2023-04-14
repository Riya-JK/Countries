package com.app.interviewdemo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryDataService {

    Context context;
    String TAG = "com.app.interviewdemo.CountryDataService";
    public CountryDataService(Context context) {
        this.context = context;
    }

    public void getCountryDetails(String countryname, VolleyResponseListener volleyResponseListener){

        String url = "https://restcountries.com/v3.1/name/" + countryname;
        CountryModel countryModel = new CountryModel();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    if(jsonObject != null) {
                        if(jsonObject.has("name")) {
                            String name = jsonObject.getJSONObject("name").getString("common");
                            countryModel.setName(name);
                        }else{
                            countryModel.setName("");
                        }
                        if(jsonObject.has("capital")) {
                            String capital = jsonObject.getJSONArray("capital").getString(0);
                            countryModel.setCapital(capital);
                        }else{
                            countryModel.setCapital("");
                        }

                        if(jsonObject.has("region")) {
                            String region = jsonObject.getString("region");
                            countryModel.setRegion(region);
                        }else{
                            countryModel.setRegion("");
                        }
                        if(jsonObject.has("subregion")) {
                            String subregion = jsonObject.getString("subregion");
                            countryModel.setSubregion(subregion);
                        }else{
                            countryModel.setSubregion("");
                        }
                        if(jsonObject.has("area")) {
                            Double area = jsonObject.getDouble("area");
                            countryModel.setArea(area);
                        }else{
                            countryModel.setArea(0.0);
                        }
                        if(jsonObject.has("population")) {
                            Double population = jsonObject.getDouble("population");
                            countryModel.setPopulation(population);
                        }else{
                            countryModel.setPopulation(0.0);
                        }

                    }
                    volleyResponseListener.onResponse(countryModel);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
                volleyResponseListener.onError(error.toString());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
