package com.ew8s.ew8zo;

import java.util.Random;

/**
 * Created by Aliaksandr Kisel (EW8S) on 15.01.2018.
 */

public class SpeekBoth extends Speek implements ISpeekTask {

    public SpeekBoth(TaskGenerator tg) {
        super(tg);
    }
    //Create Task using both language
    @Override
    public Zadacha getTask() {
        Random random = new Random(System.currentTimeMillis()); //Init random

        //if sel == 0 - EN, else RU
        int path;
        int sel = random.nextInt(2);

        if(sel==0){
            path = random.nextInt(18);      //Choose EN Task
        }else{
            path = random.nextInt(2);       //Choose RU Task
            path += 18;
        }

        Zadacha z = newTask(path);
        return  z;
    }
}
