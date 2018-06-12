package com.example.user.ourapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FragmenPhoto extends android.app.Fragment {

    RecyclerView recyclerView;

    List<Product> productList;

    ProductAdapter adapter;

    public FragmenPhoto() {
        // Required empty public constructor
    }



    public static FragmenPhoto newInstance(String param1, String param2) {
        FragmenPhoto fragment = new FragmenPhoto();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_fragmen_photo, container, false);





        productList = new ArrayList<>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList.add(
                new Product(
                        1,
                        "Create Code",
                        "100 kg",
                        5,
                        1000,
                        R.drawable.macbook));

        productList.add(
                new Product(
                        1,
                        "Study the Code",
                        "700 hours",
                        5,
                        700,
                        R.drawable.dellinspiron));

        productList.add(
                new Product(
                        1,
                        "Global Code",
                        "All",
                        5,
                        00000,
                        R.drawable.surface));

        productList.add(
                new Product(
                        1,
                        "Server",
                        "WWW",
                        5,
                        100000,
                        R.drawable.serv));

        productList.add(
                new Product(
                        1,
                        "Contact with the code",
                        "Always",
                        5,
                        00000,
                        R.drawable.kiborg));

        productList.add(
                new Product(
                        1,
                        "Internet",
                        "All",
                        5,
                        00000,
                        R.drawable.planet));

        productList.add(
                new Product(
                        1,
                        "Generate",
                        "Always",
                        5,
                        00000,
                        R.drawable.generate));

        adapter = new ProductAdapter(getActivity().getApplicationContext(), productList);
        recyclerView.setAdapter(adapter);



        return rootView;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

}
