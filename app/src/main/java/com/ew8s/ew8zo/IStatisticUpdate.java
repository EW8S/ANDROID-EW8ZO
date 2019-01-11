package com.ew8s.ew8zo;

/**
 * Created by Aliaksandr Kisel (EW8S) on 08.01.2018.
 * Interface for working with Statistic
 */

public interface IStatisticUpdate {
    public void CBackAddTask();             //increment the counter of new tasks
    public void CBackAddSkipped();          //increment the counter of missed tasks
    public void CBackAddUnsuccessfully();   //increment counter in object stasistic unsuccessfully answer
    public void CBackAddSuccessfully();     //increment counter in object stasistic successfully answer
}
