package com.stokmate.external;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.stokmate.backend.sm.Sm;
import com.stokmate.backend.sm.model.GroupCollection;


import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
     Context context;
     protected GroupCollection groups;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

            Sm.Builder builder = new Sm.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            Sm sm = builder.build();


        context = params[0].first;
        String call = params[0].second;

        try {
            if(call.equals("GET_GROUPS")){
                groups = sm.getGroups().execute();

            }else{
                throw new Exception("Unsupported SM operation!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }


}