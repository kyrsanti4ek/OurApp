package com.example.user.ourapp.weather;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ourapp.R;


public class FragmentOne extends Fragment {

    public FragmentOne() { // Required empty public constructor

    }

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //appCompatActivity.getSupportActionBar().hide();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)rootView.findViewById(R.id.humidity_field);
        pressure_field = (TextView)rootView.findViewById(R.id.pressure_field);
        weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);

        cityField.setTypeface(weatherFont);
        updatedField.setTypeface(weatherFont);
        detailsField.setTypeface(weatherFont);
        humidity_field.setTypeface(weatherFont);
        pressure_field.setTypeface(weatherFont);
        weatherIcon.setTypeface(weatherFont);

        Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
                Log.d("Weather", weather_temperature);
                cityField.setText(weather_city);
                //updatedField.setText(weather_updatedOn);
                updatedField.setText("широта-50.439188, долгота-30.618903");
                detailsField.setText(weather_description);
                currentTemperatureField.setText("Температура: "+weather_temperature);
                Log.d("Weather", humidity_field.getText().toString());
                humidity_field.setText("Влажность: "+weather_humidity);
                pressure_field.setText("Давление: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("50.439188", "30.618903"); //  ("Latitude", "Longitude") гугл координаты

        return rootView;
    }

}