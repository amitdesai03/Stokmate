package com.stokmate.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.stokmate.R;
import com.stokmate.external.EndpointsAsyncTask;
import com.stokmate.helper.UserSessionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView nameView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserSessionManager session = new UserSessionManager(getActivity().getApplicationContext());
        if ( session==null || !session.isUserLoggedIn()) {
            //Go back to login
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        HashMap<String, String> userDetails = session.getUserDetails();

//        new EndpointsAsyncTask(){
//            @Override
//            protected void onPostExecute(String result) {
//                try {
//                    nameView.setText(groups != null ? groups.toPrettyString() : "No Groups!");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.execute(new Pair<Context, String>(this.getActivity(), "GET_GROUPS"));

        return rootView;
    }


}
