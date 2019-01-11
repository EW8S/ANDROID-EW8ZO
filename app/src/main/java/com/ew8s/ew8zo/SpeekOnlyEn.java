package com.ew8s.ew8zo;

import java.util.Random;

/**
 * Created by Aliaksandr Kisel (EW8S) on 15.01.2018.
 */

public class SpeekOnlyEn extends Speek implements ISpeekTask {

    public SpeekOnlyEn(TaskGenerator tg) {
        super(tg);
    }

    //Create Task using EN language
    @Override
    public Zadacha getTask() {
        Random random = new Random(System.currentTimeMillis()); //Init random
        // folders 0-17 only english
        int path = random.nextInt(18);
        //the task is formed
        Zadacha z = newTask(path);
        return  z;
    }
}
