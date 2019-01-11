package com.ew8s.ew8zo;

import android.content.res.AssetManager;

/**
 * Created by Aliaksandr Kisel (EW8S) on 08.01.2018.
 * Interface for working with MainActivity
 */

public interface IFormWorkAtivity {
    public AssetManager getAssetM();    //Get AssetManager from MainActivity for work aseets
    public void setWaitingButton(String nameButton);    //Set waiting button form TaskGenerator for MainActivity
    public void setWay(String way);   //Set the file location way in case of user input error
    public void setIsWaitingPressButton(boolean enable);    //enable/disable compare button and task
}


