package com.deveyesgroup.luna;

import android.app.Application;

/**
 * Created by oneenam on 02/11/15.
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        //CommonMethods.writeToDefaults(getApplicationContext(), "user_id", "832");
    }
}
