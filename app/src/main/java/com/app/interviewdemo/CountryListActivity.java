package com.app.interviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class CountryListActivity extends AppCompatActivity {
    ListView listView;
    String TAG = "CountryList";
    Fragment countryDetailsFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list_layout);
        Intent intent = getIntent();
        if(intent.hasExtra("countrylist")){
            List<String> countries = intent.getStringArrayListExtra("countrylist");

            CountryDataService countryDataService = new CountryDataService(this);

            listView = findViewById(R.id.list_view);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_layout, countries);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i(TAG, "index = " + i + " " + countries.get(i));
                    countryDataService.getCountryDetails(countries.get(i), new VolleyResponseListener() {
                        @Override
                        public void onResponse(CountryModel countryModel) {
                            Log.i(TAG, countryModel.toString());
                            findViewById(R.id.list_view).setVisibility(View.GONE);
                            findViewById(R.id.heading).setVisibility(View.GONE);
                            findViewById(R.id.fragment_country_details_view).setVisibility(View.VISIBLE);

                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(R.id.fragment_country_details_view, countryDetailsFragment = new CountryDetailsFragment(countryModel), "countryDetails").addToBackStack(null).commit();
                        }
                        @Override
                        public void onError(String s) {
                            Toast.makeText(CountryListActivity.this , s , Toast.LENGTH_LONG);
                            Log.i(TAG, s);
                        }
                    });
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        // Check if the countryDetailsFragment is null and handle back press event accordingly
        if (countryDetailsFragment != null ) {
            Log.i(TAG, "fragment is instance of countrydetailsfragment " + countryDetailsFragment.getTag());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(countryDetailsFragment);
            transaction.commitNow();
            countryDetailsFragment = null;
            findViewById(R.id.list_view).setVisibility(View.VISIBLE);
            findViewById(R.id.heading).setVisibility(View.VISIBLE);
            findViewById(R.id.fragment_country_details_view).setVisibility(View.GONE);
        } else {
            Log.i(TAG, "close activity");
            finish();
            super.onBackPressed();
        }
    }
}
