package com.example.user.ourapp.projIss;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ourapp.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class IssuesFragment extends android.app.Fragment{
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;

    Preferences data;
    RecyclerView recyclerView;
    List<Issues> issuesList;
    IssuesRecyclerViewAdapter adapter;
    String projectName;
    TextView issuesProjectName;

    public IssuesFragment(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getActivity().getActionBar().setTitle("ISSUES");

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = new Preferences(getContext());

        issuesList = data.getIssuesData();
        View view = inflater.inflate(R.layout.issues_fragment_item_list, container, false);


        issuesProjectName = (TextView) view.findViewById(R.id.issues_project_name);
        issuesProjectName.setText(projectName);

        recyclerView = (RecyclerView) view.findViewById(R.id.issues_fragment_item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new IssuesRecyclerViewAdapter(getActivity().getApplicationContext(), issuesList, projectName, getFragmentManager());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmAddIssues();
                adapter.dataSetChanged(data.getIssuesData());
                recyclerView.scrollTo(100, 100);
            }
        });

        return view;
    }

    public void confirmAddIssues() {
        DialogFragment newFragment = new AddIssuesDialogFragment(adapter);
        Bundle bundle = new Bundle();
        bundle.putString("projectName", projectName);
        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "addIssues");
    }

//    private void fragmentJump(Project mItemSelected) {
//        mFragment = new IssuesFragment("BugFinders");
//        mBundle = new Bundle();
//        mBundle.putParcelable("item_selected_key", mItemSelected);
//        mFragment.setArguments(mBundle);
//        switchContent(R.id.frag1, mFragment);
//    }


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

