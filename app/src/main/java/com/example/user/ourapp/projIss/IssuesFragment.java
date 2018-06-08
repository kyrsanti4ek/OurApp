package com.example.user.ourapp.projIss;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
public class IssuesFragment extends android.app.Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;

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

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.issues_fragment_item_list, container, false);
        issuesList = new ArrayList<>();

        issuesProjectName = (TextView) view.findViewById(R.id.issues_project_name);
        issuesProjectName.setText(projectName);

                recyclerView = (RecyclerView) view.findViewById(R.id.issues_fragment_item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        issuesList.add(new Issues("Simple Project", 1, "Summary Simple Project", "high", "Critical", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("Simple Project", 2, "Summary Simple Project", "high", "Blocker", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "Open"));
        issuesList.add(new Issues("Simple Project", 3, "Summary Simple Project", "high", "Critical", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "Close"));
        issuesList.add(new Issues("Simple Project", 4, "Summary Simple Project", "high", "Minor", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("Simple Project", 5, "Summary Simple Project", "high", "Major", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("BugFinders", 1, "Summary BugFinders", "high", "Critical", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("BugFinders", 2, "Summary BugFinders", "high", "Trivial", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("BugFinders", 3, "Summary BugFinders", "high", "Blocker", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("BugFinders", 4, "Summary BugFinders", "high", "Minor", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("BugFinders", 5, "Summary BugFinders", "high", "Major", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("ListBoxer", 1, "Summary ListBoxer", "high", "Minor", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("ListBoxer", 2, "Summary ListBoxer", "high", "Blocker", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("ListBoxer", 3, "Summary ListBoxer", "high", "Trivial", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("ListBoxer", 4, "Summary ListBoxer", "high", "Minor", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("ListBoxer", 5, "Summary ListBoxer", "high", "Critical", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("OurApp", 1, "Summary OurApp", "high", "Minor", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("OurApp", 2, "Summary OurApp", "high", "Trivial", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("OurApp", 3, "Summary OurApp", "high", "Trivial", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("OurApp", 4, "Summary OurApp", "high", "Major", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));
        issuesList.add(new Issues("OurApp", 5, "Summary OurApp", "high", "Major", "qwpuihqr qpiud qwidqhw qwihdq wqiwhdquiwh qwh diqwh dowiquhw  dhwqupwdh whqsdlksa w dqhjwd ", "inProgress"));

        adapter = new IssuesRecyclerViewAdapter(getActivity().getApplicationContext(), issuesList, projectName);
        recyclerView.setAdapter(adapter);

        return view;
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

