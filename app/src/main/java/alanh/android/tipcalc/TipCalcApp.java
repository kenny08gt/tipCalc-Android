package alanh.android.tipcalc;

import android.app.Application;

/**
 * Created by alan2 on 1/06/2016.
 */
public class TipCalcApp extends Application{
    private final static String ABOUT_URL="https://facebook.com/alan.hurtarte";

    public static String getAboutUrl() {
        return ABOUT_URL;
    }
}
