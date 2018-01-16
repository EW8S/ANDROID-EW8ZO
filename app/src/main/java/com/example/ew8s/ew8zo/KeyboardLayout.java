package com.example.ew8s.ew8zo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Aliaksandr Kisel (EW8S) on 20.12.2017.
 */

public class KeyboardLayout {

    WorkActivity wa;
    //Array for TextView
    private final String[] mtextButtons =
            {"","1","2","3","4","5","6","7","8","9","0","","","","",
            "","Q","W","E","R","T","Y","U","I","O","P","","","","",
            "","A","S","D","F","G","H","J","K","L","","","",
            "","Z","X","C","V","B","N","M","","","/","","",
            "","","","","","","","","","",""};

    private int mCount = 0;  //Counter for array mtextButtons

    //---------------------------------------------------------------------------------------
    public KeyboardLayout(WorkActivity wa) {
        this.wa = wa;
    }

    //---------------------------------------------------------------------------------------
    /* The method returns text from the array by the serial number
     * If the called number is outside of the return "Er" */
    private String getTextButton(int nomber){
        if(nomber < mtextButtons.length)
            return mtextButtons[nomber];
        else
            return "Er";
    }

    //---------------------------------------------------------------------------------------
    // The method creates in the fields for a rows of keyboard
    public LinearLayout getLayoutY(){
        LinearLayout linearLayout = new LinearLayout(wa);

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);

        layoutParams.setMargins(5,5,5,5);
        linearLayout.setLayoutParams(layoutParams);

        return linearLayout;
    }

    //---------------------------------------------------------------------------------------
    /* The method is designed to form a LinearLayout with a TextView embedded in it
     * Input - width of the button's field and the button's text
     * The text is also assigned to the TextView Tag
     * Events for the TextView are passed to the MainActivity */

    private LinearLayout getLayoutX(float layoutParamsWeight, String textButton){
        //Create LinearLayout
        LinearLayout linearLayout = new LinearLayout(wa);
        // Set layout options
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setBackgroundColor(Color.rgb(36,36,36));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                layoutParamsWeight);

        layoutParams.setMargins(5,5,5,5);
        linearLayout.setLayoutParams(layoutParams);

        // Create TextView
        TextView textView = new TextView(wa);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        textView.setText(textButton);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTag(textButton);
        //Enable Clickable option and assigning event processing to a class MainActivity
        textView.setClickable(true);
        textView.setOnClickListener(wa);

        //Add TextView to LinearLayout
        linearLayout.addView(textView);

        //Return a finished button item
        return linearLayout;
    }
    //--------------------------------------------------------------------------------------
    private LinearLayout getLayoutStatistic(float layoutParamsWeight, String text, String id){
        LinearLayout linearLayout = new LinearLayout(wa);
        // Set layout options
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setBackgroundColor(Color.rgb(36,36,36));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                layoutParamsWeight);

        layoutParams.setMargins(5,5,5,5);
        linearLayout.setLayoutParams(layoutParams);

        // Create TextView
        TextView textView = new TextView(wa);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        textView.setTypeface(null, Typeface.BOLD);
        //textView.setId(text);

        //Add TextView to LinearLayout
        linearLayout.addView(textView);

        //Return a finished button item
        return linearLayout;
    }
    //--------------------------------------------------------------------------------------
    //Adding LinearView to the statistics output field
    private LinearLayout addStatistics(LinearLayout parent){
        //parent.addView(getLayoutX(1.5f, "Удачно");
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        return parent;
    }

    //--------------------------------------------------------------------------------------
    //Adding LinearView to the first row of the keyboard
    private LinearLayout addToKeyboradFirstRow(LinearLayout parent){

        // Because the buttons are sized, the cycle for all buttons can not be applied
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));

        //The following 13 buttons have the same size, filling in a cycle
        for(int x=0; x<13; x++) parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));

        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        return parent;
    }

    //--------------------------------------------------------------------------------------
    //Adding LinearView to the second row of the keyboard
    private LinearLayout addToKeyboradSecondRow(LinearLayout parent){
        parent.addView(getLayoutX(2.0f, getTextButton(mCount++)));
        for(int x=0; x<14; x++) parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        return parent;
    }

    //-------------------------------------------------------------------------------------
    //Adding LinearView to the third row of the keyboard
    private LinearLayout addToKeyboradThirdRow(LinearLayout parent){
        parent.addView(getLayoutX(3.0f, getTextButton(mCount++)));
        for(int x=0; x<11; x++) parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(2.0f, getTextButton(mCount++)));
        return parent;
    }
    //-------------------------------------------------------------------------------------
    //Adding LinearView to the fourth row of the keyboard
    private LinearLayout addToKeyboradFourthRow(LinearLayout parent){
        parent.addView(getLayoutX(3.0f, getTextButton(mCount++)));
        for(int x=0; x<11; x++) parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(2.0f, getTextButton(mCount++)));
        return parent;
    }

    //--------------------------------------------------------------------------------------
    //Adding LinearView to the fifth row of the keyboard
    private LinearLayout addToKeyboradFifthRow(LinearLayout parent){
        //The bottom row of the keyboard has a complex view and therefore all buttons are manual
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(5.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.5f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        parent.addView(getLayoutX(1.0f, getTextButton(mCount++)));
        return parent;
    }

    //--------------------------------------------------------------------------------------
    /* This method programmatically generates a GUI layout
     * Forms the appearance of the keyboard, which includes LinerView and TextView
     * Requires onClickListener implementation in MainActivity for TextView
     * Event identification by Tag for TextView*/

    public LinearLayout createKeyboard(){
        LinearLayout linearLayout = new LinearLayout(wa);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);

        //Adding LinerView as a gag, it will be needed for further exercises
        //linearLayout.addView(getLayoutY());
        //linearLayout.addView(addStatistics(getLayoutY()));

        //Adding LinearView to the first row buttons 1 - 0 of the keyboard
        linearLayout.addView(addToKeyboradFirstRow(getLayoutY()));

        //Adding LinearView to the second row buttons q - p of the keyboard
        linearLayout.addView(addToKeyboradSecondRow(getLayoutY()));

        //Adding LinearView to the third row buttons a - l of the keyboard
        linearLayout.addView(addToKeyboradThirdRow(getLayoutY()));

        //Adding LinearView to the fourth row buttons z - m, / of the keyboard
        linearLayout.addView(addToKeyboradFourthRow(getLayoutY()));

        //Adding LinearView to the fifth row no buttons of the keyboard
        linearLayout.addView(addToKeyboradFifthRow(getLayoutY()));

        //Return the ready layout with the keyboard
        return linearLayout;
    }
}
