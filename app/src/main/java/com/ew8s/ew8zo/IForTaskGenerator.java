package com.ew8s.ew8zo;

/**
 * Created by Aliaksandr Kisel (EW8S) on 08.01.2018.
 * Interface for working with TaskGenerator
 */

public interface IForTaskGenerator {
    public void newTask();          //Creates a new task
    public void iRestatrTimer();    //Re-initializing the timer
    public void istopTimer();       //Disable timer in TaskGenerator
}
