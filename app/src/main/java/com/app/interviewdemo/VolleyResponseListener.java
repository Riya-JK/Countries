package com.app.interviewdemo;

public interface VolleyResponseListener {

    void onResponse(CountryModel countryModel);

    void onError(String s);
}
