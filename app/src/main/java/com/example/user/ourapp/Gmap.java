package com.example.user.ourapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link Gmap.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link Gmap#newInstance} factory method to
// * create an instance of this fragment.
// */


public class Gmap extends Fragment implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    ImageButton button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_gmap, container, false);

        button = (ImageButton) mView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMapTypeSelectorDialog();
            }
        });

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }


    @Override
    public void onMyLocationClick (@NonNull Location location){
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick () {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Normal", "Terrain", "Hybrid", "Satellite"};

    private void showMapTypeSelectorDialog() {
        // Prepare the dialog by setting up a Builder.
        final String fDialogTitle = "Select Map Type";
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(fDialogTitle);

        // Find the current map type to pre-check the item representing the current state.
        int checkItem = mGoogleMap.getMapType() - 1;

        // Add an OnClickListener to the dialog, so that the selection will be handled.
        builder.setSingleChoiceItems(
                MAP_TYPE_ITEMS,
                checkItem,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        // Locally create a finalised object.



                        // Perform an action depending on which item was selected.
                        switch (item) {
                            case 1:
                                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case 2:
                                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;
                            case 3:
                                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                            default:
                                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }
                        dialog.dismiss();
                    }
                }
        );

        // Build the dialog and show it.
        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.setCanceledOnTouchOutside(true);
        fMapTypeDialog.show();
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {

        //MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setOnMyLocationButtonClickListener(this);
        mGoogleMap.setOnMyLocationClickListener(this);


        try {
            // set the map style
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.mapstyle));
            if (!success) {
                Log.e("Gmap", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Gmap", "Can't find style. Error: ", e);
        }


//        //current place
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_CODE);
//            }
//            return;
//        }
//        else {
//            googleMap.setMyLocationEnabled(true);
//              }

        // Sets the map types
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
   //     googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(50.45, 30.50)).title("My current location"));
        //CameraPosition cur = CameraPosition.builder().target(new LatLng(0, 0)).zoom(2).build();
        //googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cur));
    }

}


