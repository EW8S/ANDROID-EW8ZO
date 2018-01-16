package com.example.ew8s.ew8zo;

import java.util.Random;

/**
 * Created by Aliaksandr Kisel (EW8S) on 15.01.2018.
 */

public class SpeekOnlyRu extends Speek implements ISpeekTask{

    public SpeekOnlyRu(TaskGenerator tg) {
        super(tg);
    }

    //Create Task using RU language
    @Override
    public Zadacha getTask() {
        Random random = new Random(System.currentTimeMillis()); //Init random
        // folders 18 and 19 only russian
        int path = random.nextInt(2);
        path += 18;
        Zadacha z = newTask(path);
        return  z;
    }

}
