package com.ew8s.ew8zo;

import java.util.Random;

/**
 * Created by Aliaksandr Kisel (EW8S) on 16.01.2018.
 */

public abstract class Speek {

    TaskGenerator mTg;

    public Speek(TaskGenerator tg){
        mTg = tg;

    }

    //---------------------------------------------------------------------------------------------
    //Get the name of the expected button for comparison. The input file name is a replica. At the output of the letter.
    public String getKey(String name){
        String rez = null;
        if(name.indexOf(".")==1)  rez = name.substring(0, 1);
        else rez = name.substring(0, name.indexOf(".")-1);
        return rez;
    }

    //---------------------------------------------------------------------------------------------
    public Zadacha newTask(int nomberFolder){
        Zadacha zadacha = new Zadacha();
        Random random = new Random(System.currentTimeMillis()); //Init random

        String strPath = String.valueOf(nomberFolder);
        //Get the list of files in the selected folder
        String[] tmpFileList = mTg.getFileList(strPath);
        //Selecting a replica from an array with a list of files
        int tmpInt = random.nextInt(tmpFileList.length);
        //Filename with replica
        String file = tmpFileList[tmpInt];
        //Expected button on the keyboard
        zadacha.waitButton = getKey(file);
        //form the full path to the replica in the assets folder
        zadacha.pathPalyFile = strPath+"/"+file;
        return zadacha;

    }

    //Create task, implemented in the child class в дочернем классе
    public abstract Zadacha getTask();


}
