package com.example.asaldanha.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.displayjoke.DisplayJokeActivity;

import com.example.displayjoke.DisplayJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.myapplication.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class MainActivity extends Activity {

    EndpointsAsyncTask4 epAyncTask4;
    CountDownLatch signal;
    String mString = null;
    Exception mError = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        epAyncTask4 = new EndpointsAsyncTask4();

        epAyncTask4.setListener (new EndpointsAsyncTask4.EndpointsListener4() {
            @Override
            public void onComplete(String jsonString, Exception e) {
                mString = jsonString;
                mError = e;
//                Toast.makeText(this, mString, Toast.LENGTH_LONG).show();
                if (mString.length() > 0) {
                    Intent intent = new Intent(getBaseContext(), DisplayJokeActivity.class);
                    intent.putExtra(DisplayJokeActivity.JOKE_KEY, mString);
                    startActivity(intent);
                }

            }
        }).execute("Manfred2");
    }
}



/*
class EndpointsAsyncTask3 extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private Context context;


    private EndpointsListener3 epListener;
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

    public EndpointsAsyncTask3 setListener(EndpointsListener3 listener) {
        this.epListener = listener;
        return this;
    }



    @Override
    protected void onPostExecute(String result) {

        if (this.epListener != null)
            this.epListener.onComplete(result, mError);

        //      Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public interface EndpointsListener3
    {
//        void downloadCompleted();

//        void processCompleted(String result);

        public void onComplete(String jsonString, Exception e);


    }


}
*/
