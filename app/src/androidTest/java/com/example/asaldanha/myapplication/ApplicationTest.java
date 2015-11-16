package com.example.asaldanha.myapplication;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.test.UiThreadTest;
import android.util.Pair;
import android.widget.Toast;

import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    EndpointsAsyncTask2 epAyncTask2;
    CountDownLatch signal;
    String mJsonString = null;
    Exception mError = null;


    public ApplicationTest() {
        super(Application.class);
    }


    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
//        gepAyncTask = new GEPAsyncTask(this);

    }

    @UiThreadTest
    public void testDownload() throws InterruptedException
    {


        epAyncTask2 = new EndpointsAsyncTask2();
        epAyncTask2.setListener(new EndpointsAsyncTask2.EndpointsListener2() {
            @Override
            public void onComplete(String jsonString, Exception e) {
                mJsonString = jsonString;
                mError = e;
                signal.countDown();

            }
        }).execute("Manfred2");
       signal.await(30, TimeUnit.SECONDS);
        assertEquals("Hi, Manfred2", mJsonString);



    }



}