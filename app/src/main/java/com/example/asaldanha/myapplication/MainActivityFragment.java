package com.example.asaldanha.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//port android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.myapplication.backend.myApi.MyApi;
import android.widget.Toast;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EndpointsAsyncTask epAyncTask;
    EndpointsAsyncTask2 epAyncTask2;
    CountDownLatch signal;
    String mJsonString = null;
    Exception mError = null;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        epAyncTask2 = new EndpointsAsyncTask2();
        epAyncTask2.setListener(new EndpointsAsyncTask2.EndpointsListener2() {
            @Override
            public void onComplete(String jsonString, Exception e) {
                mJsonString = jsonString;
                mError = e;
//                signal.countDown();
                Toast.makeText(getActivity(), mJsonString, Toast.LENGTH_LONG).show();

            }
        }).execute("Manfred2");



/*
        epAyncTask = new EndpointsAsyncTask();
        epAyncTask.setListener(new EndpointsAsyncTask.EndpointsListener() {
            @Override
            public void onComplete(String jsonString, Exception e) {
                mJsonString = jsonString;
                mError = e;
//                signal.countDown();
            }
        }).execute(new Pair<Context, String>(getActivity(), "Manfred"));
*/




/*
        try {
            signal.await();

        } catch (InterruptedException name) {

        }

*/





//        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Manfred"));
        return inflater.inflate(R.layout.fragment_main, container, false);
    }




}

class EndpointsAsyncTask2 extends AsyncTask<String, Void, String> {
    private  MyApi myApiService = null;
    private Context context;


    private EndpointsListener2 epListener;
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

    public EndpointsAsyncTask2 setListener(EndpointsListener2 listener) {
        this.epListener = listener;
        return this;
    }



    @Override
    protected void onPostExecute(String result) {

        if (this.epListener != null)
            this.epListener.onComplete(result, mError);

  //      Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public interface EndpointsListener2
    {
//        void downloadCompleted();

//        void processCompleted(String result);

        public void onComplete(String jsonString, Exception e);


    }


}



class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private  MyApi myApiService = null;
    private Context context;


    private EndpointsListener epListener;
    private String name;
    private Exception mError = null;


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
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

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public EndpointsAsyncTask setListener(EndpointsListener listener) {
        this.epListener = listener;
        return this;
    }



    @Override
    protected void onPostExecute(String result) {

        if (this.epListener != null)
            this.epListener.onComplete(result, mError);

        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public interface EndpointsListener
    {
//        void downloadCompleted();

//        void processCompleted(String result);

        public void onComplete(String jsonString, Exception e);


    }


}
