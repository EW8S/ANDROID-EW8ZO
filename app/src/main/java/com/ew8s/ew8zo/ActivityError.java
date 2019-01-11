package com.ew8s.ew8zo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by Aliaksandr Kisel (EW8S) on 27.12.2017.
 * This class is designed to work out incorrect decisions
 */

public class ActivityError extends AppCompatActivity{

    TextView mtextView;             //Output letters on the screen
    WavPlayer wp = new WavPlayer(); //The instance of the reproducing object
    AssetManager assetsManager;     //AssetManager
    String path;                    //The path to the file from the assets folder
    Intent intent = new Intent();   //Intent
    Thread tr;                      //Thread for background playback of replicas
    boolean nah = false;            //Exit flag from the background replication Thread

    //--------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        mtextView = (TextView) findViewById(R.id.textView);

        //We get through Intent parameters
        Intent intent = getIntent();
        String letter = intent.getStringExtra("letter");
        path = intent.getStringExtra("path");
        if (letter.equals("SL")) letter = "/";
        mtextView.setText(letter);      //Display the text in the TextView
        assetsManager = getAssets();    //Get access to assets

        Runnable r = new Say(); // create a Say instance and implement the Runnable interface
        new Thread(r).start();  // Running in a new thread
    }

    //--------------------------------------------------------------------------------------------
    //Событие закрытие Activity
    @Override
    protected void onDestroy() {
        nah = true; //Setting the flag to exit the thread where the replica is played
        super.onDestroy();
    }

    //--------------------------------------------------------------------------------------------
    // Internal class, is responsible for replicating replicas, pause between replicas
    // and closing of the activates after 4 repetitions
    // Running in a separate thread
    private class Say implements Runnable {
        @Override
        public void run() {
            for (int t = 0; t < 4; t++) {  //4 repetitions

                try {
                    Thread.sleep(1500);   //pause between replicas, ms
                    if(nah) return;     //flag for exit and end of thread, see the onDestroy method
                    InputStream is = assetsManager.open(path);  //get the stream of the file from the assets folder
                    wp.play(is);    //Playable file
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            finish();   //Close activity
        }
    }
}


