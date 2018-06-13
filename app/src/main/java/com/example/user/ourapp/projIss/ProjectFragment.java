package com.example.user.ourapp.projIss;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ourapp.ProductAdapter;
import com.example.user.ourapp.R;


import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends android.app.Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;

    Preferences data;
    RecyclerView recyclerView;
    List<Project> projectList;
    ProjectRecyclerViewAdapter adapter;

    public ProjectFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProjectFragment newInstance(int columnCount) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getActivity().getActionBar().setTitle("PROJECT");

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = new Preferences(getContext());

        projectList = data.getProjectData();

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProjectRecyclerViewAdapter(getActivity().getApplicationContext(), projectList, getFragmentManager());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.project_floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmAddIssues();
                adapter.dataSetChanged(data.getProjectData());
                recyclerView.scrollTo(100, 100);
            }
        });

        return view;
    }

    public void confirmAddIssues() {
        DialogFragment newFragment = new AddProjectDialogFragment(adapter);
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "addProject");
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
