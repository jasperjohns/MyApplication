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

import com.example.displayjoke.DisplayJokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

    CountDownLatch signal;
    String mString = null;
    Exception mError = null;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


/*
        epAyncTask2 = new EndpointsAsyncTask2();
        epAyncTask2.setListener(new EndpointsAsyncTask2.EndpointsListener2() {
            @Override
            public void onComplete(String jsonString, Exception e) {
                mString = jsonString;
                mError = e;
                Toast.makeText(getActivity(), mString, Toast.LENGTH_LONG).show();
                if (mString.length() > 0) {
                    Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
                    intent.putExtra(DisplayJokeActivity.JOKE_KEY, mString);
                    startActivity(intent);
                }

            }
        }).execute("Manfred2");
*/

/*
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
*/

        return root;
    }






}




