package com.ew8s.ew8zo;

import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aliaksandr Kisel (EW8S) on 08.01.2018.
 */

public final class TaskGenerator extends Thread implements IForTaskGenerator{

    AssetManager mAssetsManager;
    WorkActivity workActivity;
    IFormWorkAtivity iFormWorkAtivity;  //Interface for interaction with the object mainActivity
    IStatisticUpdate iStatisticUpdate;  //Interface for interaction with the object statictic

    private int mQautityVotes;  //Variable for number of votes
    private Timer mTimer;   //Init Timer
    private MyTimerTask mMyTimerTask;   //Init TimerTask for Timer


    ISpeekTask iSpeekTask;
    WavPlayer wavPlayer = new WavPlayer();  //Object for playing wav files


    //class constructor, follow the link to MainActivity
    public TaskGenerator(WorkActivity workActivity) {
        //follow the link to WorkActivity
        this.workActivity = workActivity;
        //register for implantation of the interface for working with WorkActivity
        iFormWorkAtivity = workActivity;
        //through the interface we get AssetManager
        mAssetsManager = iFormWorkAtivity.getAssetM();
        //from the quantity.ini file get the number of votes
        mQautityVotes = getQuantityVoices();
        //register for implantation of the interface for working with statictic
        iStatisticUpdate = this.workActivity.statistic;

        //Получаем режим работы приложения
        String reg = workActivity.getReg();

        //Подключаем реализацию через интерфейс
        switch (reg){
            case "en":
                iSpeekTask = new SpeekOnlyEn(this);
                break;
            case  "ru":
                iSpeekTask = new SpeekOnlyRu(this);
                break;
            case "both":
                iSpeekTask = new SpeekBoth(this);
                break;
        }

    }
    //--------------------------------------------------------------------------
    //Clearing the Timer resources and initializing again
    public void reInitTimer(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
            mMyTimerTask = null;
        }

        mTimer= new Timer();
        mMyTimerTask = new MyTimerTask();
    }
    //--------------------------------------------------------------------------------------------
    //getting the list of files in the folder from asset as an array
    public String[] getFileList(String folder){
        String[] filesList = null;
        try {
            filesList = mAssetsManager.list(folder);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filesList;
    }
    //---------------------------------------------------------------------------------------------
    //returns the quantity voices
    private int getQuantityVoices(){
        int quantity = 0;
        try {
            InputStreamReader istream = new InputStreamReader(mAssetsManager.open("quantity.ini"));
            BufferedReader in = new BufferedReader(istream);
            String str = in.readLine();
            return Integer.parseInt(str);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return quantity;
    }


    //---------------------------------------------------------------------------------------------
    //playback of the wav file along the path indicated by the path "path" from assets
    public void playWav(String path){
        try {

            InputStream is = mAssetsManager.open(path);  //Get stream
            wavPlayer.play(is); //send stream to the method

        } catch (Exception r) {
            r.printStackTrace();

        }
    }


    //---------------------------------------------------------------------------------------------
    //Generate the task in the new Thead, execution in the insertedclass Say
    private void sayTask(){
        Runnable r = new Say();
        new Thread(r).start();
    }
    //--------------------------------------------------------------------------------------------
    //IForTaskGenerator interface implementation, generation of a new task
    @Override
    public void newTask() {
        sayTask();
    }

    //---------------------------------------------------------------------------------------------
    //IForTaskGenerator interface implementation, clearing the Timer resources and initializing again
    @Override
    public void iRestatrTimer() {
        reInitTimer();
    }

    //----------------------------------------------------------------------------------------------
    //IForTaskGenerator interface implementation, clearing the Timer resources
    @Override
    public void istopTimer() {
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
            mMyTimerTask = null;
        }
    }

    //---------------------------------------------------------------------------------------------
    //When the timer overflows, it is called
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            sayTask();  //generation of a new task
            // Through the interface implication IStatisticUpdate we increment the counter of missed tasks
            iStatisticUpdate.CBackAddSkipped();
        }
    }

    //--------------------------------------------------------------------------------------------
    private class Say implements Runnable {
        //This object is executed in a separate Thread to unload the UI stream
        //Generate random tasks, transfer data to other objects for comparison and reproduction of wav files
        public void run() {
            // Using the setIsWaitingPressButton method of the IFormMainAtivity interface
            // we prohibit the comparison of the job and the response
            iFormWorkAtivity.setIsWaitingPressButton(false);
            //Clearing the Timer resources and initializing again
            reInitTimer();
            //Waiting 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Create task
            Zadacha zadacha = iSpeekTask.getTask();

            // Using the setWaitingButton method of the IFormMainAtivity interface
            // send the MainActivity the expected button according to the task
            iFormWorkAtivity.setWaitingButton(zadacha.waitButton);
            // Using the CBackAddTask method of the IStatisticUpdate interface
            // increment of the counter of generated tasks
            iStatisticUpdate.CBackAddTask();

            //Playing replica
            playWav(zadacha.pathPalyFile);
            // Using the setWay method of the IFormMainAtivity interface
            // passing the full path of the replica to the MainActivity object
            iFormWorkAtivity.setWay(zadacha.pathPalyFile);
            // Using the setIsWaitingPressButton method of the IFormMainAtivity interface
            // enable compare the task and the solution
            iFormWorkAtivity.setIsWaitingPressButton(true);

            if(mTimer != null) {
                //repeated task after 10 seconds, see mMyTimerTask
                mTimer.schedule(mMyTimerTask, 10000);
            }
        }


    }

}
