package com.app.interviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    Button startDemo;
    List<String> countries;
    Fragment info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countries = new ArrayList<String>();
        startDemo = findViewById(R.id.startDemo);
        startDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countries = getCountriesList();
                Intent intent = new Intent(MainActivity.this, CountryListActivity.class);
                intent.putStringArrayListExtra("countrylist", (ArrayList<String>) countries);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.masters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.scroll_view).setVisibility(View.GONE);
                findViewById(R.id.fragment_info).setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_info, info = new InfoFragment(R.string.coursework_masters), "infoDetails").addToBackStack(null).commit();
            }
        });

        findViewById(R.id.undergrad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.scroll_view).setVisibility(View.GONE);
                findViewById(R.id.fragment_info).setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_info, info = new InfoFragment(R.string.coursework_undergrad), "infoDetails").addToBackStack(null).commit();
            }
        });

        findViewById(R.id.harman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.scroll_view).setVisibility(View.GONE);
                findViewById(R.id.fragment_info).setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_info, info = new InfoFragment(R.string.experience_harman), "infoDetails").addToBackStack(null).commit();
            }
        });

        findViewById(R.id.gstzen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.scroll_view).setVisibility(View.GONE);
                findViewById(R.id.fragment_info).setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_info, info = new InfoFragment(R.string.experience_gstzen), "infoDetails").addToBackStack(null).commit();
            }
        });

        findViewById(R.id.transmute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.scroll_view).setVisibility(View.GONE);
                findViewById(R.id.fragment_info).setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_info, info = new InfoFragment(R.string.experience_transmute_learning), "infoDetails").addToBackStack(null).commit();
            }
        });
    }

    private List<String> getCountriesList() {
        try {
            InputStream is = getAssets().open("countries.xml");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, null);

            // Start parsing the XML
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();
                    if(tagName.equals("item")){
                        Log.i(TAG, "item");
                        String result = "";
                        if (parser.next() == XmlPullParser.TEXT) {
                            result = parser.getText();
                            //Log.i(TAG, result);
                            countries.add(result);
                            parser.nextTag();
                        }
                    }
                }
                eventType = parser.next();
            }
            // Close the input stream
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void onBackPressed() {
        if (info != null ) {
            Log.i(TAG, "fragment is instance of info " + info.getTag());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(info);
            transaction.commitNow();
            info = null;
            findViewById(R.id.scroll_view).setVisibility(View.VISIBLE);
            findViewById(R.id.fragment_info).setVisibility(View.GONE);
        } else {
            Log.i(TAG, "close activity");
            finish();
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}