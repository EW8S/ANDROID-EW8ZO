package com.ew8s.ew8zo;

/**
 * Created by Aliaksandr Kisel (EW8S) on 29.12.2017.
 * The class is responsible for maintaining statistics
 */

public class Statistic implements IStatisticUpdate{

    private int mSuccessfully;      //Successful answers
    private int mUnsuccessfully;    //Unsuccessful answers
    private int mSkipped;           //Missed answers
    private int mAllTask;           //Number of questions asked

    WorkActivity wa;                //Link to object mainActivity
    IWorkActivtyUpdateInterface iWorkActivtyUpdateInterface;    //Interface

    //The constructor of the class, pass the reference to the WorkActivity object
    public Statistic(WorkActivity wa){

        iWorkActivtyUpdateInterface=wa;
        resetCounter(); //Resetting the static counter
    }

    //---------------------------------------------------------------------------------------------
    //Resetting the static counter
    public void resetCounter(){
        mSuccessfully=0;
        mUnsuccessfully=0;
        mSkipped=0;
        mAllTask=0;
    }

    //---------------------------------------------------------------------------------------------
    //increment counter in object stasistic successfully answer
    @Override
    public void CBackAddSuccessfully(){
        mSuccessfully++;
        iWorkActivtyUpdateInterface.fromCallBack(mAllTask,mSuccessfully,mUnsuccessfully,mSkipped);
    }

    //---------------------------------------------------------------------------------------------
    //increment counter in object stasistic unsuccessfully answer
    @Override
    public void CBackAddUnsuccessfully(){
        mUnsuccessfully++;
        iWorkActivtyUpdateInterface.fromCallBack(mAllTask,mSuccessfully,mUnsuccessfully,mSkipped);
    }

    //-------------------------------------------------------------------------------------------
    //increment the counter of missed tasks
    @Override
    public void CBackAddSkipped(){
        mSkipped++;
        iWorkActivtyUpdateInterface.fromCallBack(mAllTask,mSuccessfully,mUnsuccessfully,mSkipped);
    }

    //--------------------------------------------------------------------------------------------
    //increment the counter of new tasks
    @Override
    public void CBackAddTask() {
        mAllTask++;
        iWorkActivtyUpdateInterface.fromCallBack(mAllTask,mSuccessfully,mUnsuccessfully,mSkipped);
    }

}
