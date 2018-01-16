package com.example.ew8s.ew8zo;

/**
 * Created by Aliaksandr Kisel (EW8S) on 08.01.2018.
 * Interface for working with MainActivity
 */

public interface IWorkActivtyUpdateInterface {
    //Update in MainActivity of all TextViews belonging to the counter
    public void fromCallBack(final int mAllTask, final int mSuccessfully, final int mUnsuccessfully, final int mSkipped);

}
