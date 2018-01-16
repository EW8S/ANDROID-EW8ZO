package com.example.ew8s.ew8zo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnEn;
    Button btnRu;
    Button btnBoth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEn = (Button) findViewById(R.id.en);
        btnRu = (Button) findViewById(R.id.ru);
        btnBoth = (Button) findViewById(R.id.both);

        // Dynamic calculation of the font size of the buttons
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int sizeText = displaymetrics.heightPixels / 25;

        // Setting the font size of the buttons
        btnEn.setTextSize(sizeText);
        btnRu.setTextSize(sizeText);
        btnBoth.setTextSize(sizeText);

        // Set event buttons
        btnEn.setOnClickListener(this);
        btnRu.setOnClickListener(this);
        btnBoth.setOnClickListener(this);
    }

    //---------------------------------------------------------------------------------------------
    // Implementation OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ru:
                doWork("ru");   //Only RU
                break;
            case R.id.en:
                doWork("en");   //Only EN
                break;
            case R.id.both:
                doWork("both"); //RU + EN
                break;
        }
    }

    //--------------------------------------------------------------------------------------------
    // Mode transfer through intent for WorkActivity
    private void doWork(String f){
        Intent intent = new Intent(this, WorkActivity.class);
        intent.putExtra("work", f);   //Mode
        startActivity(intent);        //Start WorkActivity
    }
}
