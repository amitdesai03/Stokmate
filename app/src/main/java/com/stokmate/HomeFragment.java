package com.stokmate;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.googleplus.GooglePlusSocialNetwork;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;

import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {
    private Button logout;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        UserSessionManager session = new UserSessionManager(getActivity().getApplicationContext());
        HashMap<String, String> userDetails = session.getUserDetails();
        TextView nameView = (TextView) rootView.findViewById(R.id.textView2);
        TextView emailView = (TextView) rootView.findViewById(R.id.textView3);
        nameView.setText(userDetails.get(UserSessionManager.KEY_NAME));
        emailView.setText(userDetails.get(UserSessionManager.KEY_EMAIL));

        logout = (Button) rootView.findViewById(R.id.logout);
        logout.setOnClickListener(logoutClick);

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this.getActivity(), "GET_GROUPS"));

        return rootView;
    }

    private View.OnClickListener logoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SocialNetworkManager mSocialNetworkManager = MainFragment.mSocialNetworkManager;
            if(mSocialNetworkManager!=null) {
                if (!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                    List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                    for (SocialNetwork socialNetwork : socialNetworks) {
                        if (socialNetwork.isConnected()) {
                            socialNetwork.logout();
                        }
                    }
                }
            }
            UserSessionManager session = new UserSessionManager(getActivity().getApplicationContext());
            session.logoutUser();
            getActivity().getSupportFragmentManager().popBackStack();
        }
    };
}
