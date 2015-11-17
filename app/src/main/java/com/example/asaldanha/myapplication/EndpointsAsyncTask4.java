package com.example.asaldanha.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.myapplication.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by asaldanha on 11/17/2015.
 */

public class EndpointsAsyncTask4 extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private Context context;


    private EndpointsListener4 epListener;
    private String name;
    private Exception mError = null;


    @Override
    protected String doInBackground(String ... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            myApiService = builder.build();
        }

//        context = params[0].first;
        String name = params[0];


//        String name = "something";


        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public EndpointsAsyncTask4 setListener(EndpointsListener4 listener) {
        this.epListener = listener;
        return this;
    }



    @Override
    protected void onPostExecute(String result) {

        if (this.epListener != null)
            this.epListener.onComplete(result, mError);

        //      Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public interface EndpointsListener4
    {
//        void downloadCompleted();

//        void processCompleted(String result);

        public void onComplete(String jsonString, Exception e);


    }


}

