package com.example.ew8s.ew8zo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Looper;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Aliaksandr Kisel (EW8S) on 27.12.2017.
 */

public class WorkActivity extends Activity implements View.OnClickListener, IFormWorkAtivity, IWorkActivtyUpdateInterface {

    private LinearLayout mLayout;

    // TextView elements for the statistic
    private TextView successfully;
    private TextView unsuccessfully;
    private TextView skipped;
    private TextView allTask;

    private KeyboardLayout keyboardLayout = new KeyboardLayout(this);   // Work on creating a keyboard
    public Statistic statistic = new Statistic(this);   //object for work with statistic

    private AssetManager mAssetsManager;
    private TaskGenerator taskGenerator;    //This object generates tasks for the user

    private IForTaskGenerator iForTaskGenerator;    //interface for working with TaskGenerator
    private IStatisticUpdate iStatisticUpdate;      //interface for working with Statistic

    private String mWay;    //the full path to the replica in the assets folder
    private boolean isWaitingPressButton = false;   //enable or disable compare the task and the solution
    //----------------------------------------------------------------------------------------------
    private String mWaitingButton=null;     //the expected button according to the task

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    private String reg;

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        mLayout = (LinearLayout) findViewById(R.id.mainLayout);

        successfully = (TextView) findViewById(R.id.tvGood);
        unsuccessfully = (TextView) findViewById(R.id.tvBad);
        skipped = (TextView) findViewById(R.id.tvSkip);
        allTask = (TextView) findViewById(R.id.tvAll);

        mLayout.addView(keyboardLayout.createKeyboard());  //Create keyboard
        mAssetsManager = getAssets();

        //We get through Intent parameters
        Intent intent = getIntent();
        reg = intent.getStringExtra("work");

        //Create object
        taskGenerator = new TaskGenerator(this);
        //register for implantation of the interface for working with TaskGenerator
        iForTaskGenerator = taskGenerator;
        //register for implantation of the interface for working with Statictic
        iStatisticUpdate = statistic;

        //Start task
        iForTaskGenerator.newTask();


    }
    //---------------------------------------------------------------------------------------------
    //set text to the TextView elements counters
    private void setStatistic(int allTask, int successfully, int unsuccessfully, int skipped){
        this.allTask.setText(String.valueOf(allTask));
        this.successfully.setText(String.valueOf(successfully));
        this.unsuccessfully.setText(String.valueOf(unsuccessfully));
        this.skipped.setText(String.valueOf(skipped));
    }
    //---------------------------------------------------------------------------------------------
    //Processing button clicks
    @Override
    public void onClick(View view) {
        //if dasable reurn, see IFormMainAtivity
        if(!isWaitingPressButton) return;
        //Get Tag from clicked button
        String s = (String)view.getTag();
        //The only button that Tag has and the file name is different. Corresponding
        if(s.equals("/")) s="SL";
        //Comparison
        if(s.equals(mWaitingButton)) {
            //is't true
            //increment counter in object stasistic successfully answer
            iStatisticUpdate.CBackAddSuccessfully();
            //generate new task
            iForTaskGenerator.newTask();
        }else{
            //it's false
            //increment counter in object stasistic unsuccessfully answer
            iStatisticUpdate.CBackAddUnsuccessfully();
            //stopped timer in object taskGenerator
            iForTaskGenerator.iRestatrTimer();
            //Preparing and running the activity to work out an error in the solution
            Intent intent = new Intent(this, ActivityError.class);
            intent.putExtra("path", mWay);   //set path replica in assets folder
            intent.putExtra("letter", mWaitingButton);  //set text button of replica
            startActivityForResult(intent, 1);  //wait for response on closing
        }
    }

    //---------------------------------------------------------------------------------------------
    //received a response from activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //answer received, continue generating tasks
        iForTaskGenerator.newTask();
    }

    //---------------------------------------------------------------------------------------------
        @Override
    protected void onDestroy() {
    iForTaskGenerator.istopTimer();  //disable generation task
        super.onDestroy();
    }
    //---------------------------------------------------------------------------------------------
    //implements interface IFormWorkAtivity
    //AssetManager getter
    @Override
    public AssetManager getAssetM() {
        return mAssetsManager;
    }

    //---------------------------------------------------------------------------------------------
    //implements interface IFormWorkAtivity
    //AssetManager setter mWaitingButton
    @Override
    public void setWaitingButton(String nameButton) {
        this.mWaitingButton = nameButton;
    }

    //---------------------------------------------------------------------------------------------
    //implements interface IFormWorkAtivity
    //AssetManager setter mWay
    @Override
    public void setWay(String way){
        mWay = way;
    }

    //----------------------------------------------------------------------------------------------
    //implements interface IFormWorkAtivity
    //AssetManager setter isWaitingPressButton
    @Override
    public void setIsWaitingPressButton(boolean enable) {
        isWaitingPressButton = enable;
    }

    //----------------------------------------------------------------------------------------------
    //implements interface IWorkActivtyUpdateInterface
    //set text to the TextView elements counters, thread-safe method
    @Override
    public void fromCallBack(final int AllTask, final int Successfully, final int Unsuccessfully, final int Skipped) {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            // it's UI Thread.
            setStatistic(AllTask, Successfully, Unsuccessfully, Skipped);
        } else {
            // it's not UI Thread.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setStatistic(AllTask, Successfully, Unsuccessfully, Skipped);
                }
            });
        }
    }
}
