package com.app.interviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class CountryDetailsFragment extends Fragment {
    CountryModel countryModel;
    String TAG = "CountrDetailsFragment";
    public CountryDetailsFragment(CountryModel countryModel1){
        super(R.layout.country_details_fragment_layout);
        countryModel = countryModel1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_details_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView name =  view.findViewById(R.id.country_name);
        TextView capital =  view.findViewById(R.id.country_capital_value);
        TextView population =  view.findViewById(R.id.country_population_value);
        TextView area =  view.findViewById(R.id.country_area_value);
        TextView region =  view.findViewById(R.id.country_region_value);
        TextView subregion = view.findViewById(R.id.country_subregion_value);

        name.setText(countryModel.getName());
        capital.setText(countryModel.getCapital());
        population.setText(countryModel.getPopulation().toString());
        area.setText(countryModel.getArea().toString());
        region.setText(countryModel.getRegion().toString());
        subregion.setText(countryModel.getSubregion().toString());
    }

    public void onBackPressed() {

    }
}
