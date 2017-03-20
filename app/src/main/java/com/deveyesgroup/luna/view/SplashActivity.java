package com.deveyesgroup.luna.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.deveyesgroup.luna.Utils.AppConstant;
import com.deveyesgroup.luna.Utils.KeyHashManager;
import com.deveyesgroup.luna.Utils.PersistData;
import com.deveyesgroup.luna.Utils.PersistentUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.luna.R;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {
    Context context;
    //private AdView mAdView;
    private String EXTRA_MESSAGE = "message";
    private String PROPERTY_REG_ID = "registration_id";
    private String PROPERTY_APP_VERSION = "appVersion";
    private int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private String regid="";
    // please enter your sender id
    String SENDER_ID = "916162964334";

    static final String TAG = "GCMDemo";
    GoogleCloudMessaging gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        context=this;
        KeyHashManager.getKeyHash(context);
        initUI();

    }

    private void initUI() {

        if (checkPlayServices()) {
//            gcm = GoogleCloudMessaging.getInstance(context);
            Log.e("GcmId", ":" + PersistData.getStringData(context, AppConstant.GCMID));

            if (PersistData.getStringData(context, AppConstant.GCMID).length() == 0) {
                new RegisterBackground().execute();
            }

        }
        //mAdView = (AdView) findViewById(R.id.spalshAdsView);
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("44BCE162B358396873336CB87AC476C1").build();
       // mAdView.loadAd(adRequest);
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PersistData.setBooleanData(context, AppConstant.isFromSplash, true);
                if(PersistentUser.isLogged(context)){
                    startActivity(new Intent(context, MainActivity.class));
                }else{
                    startActivity(new Intent(context, LoginActivity.class));
                }
                finish();
            }
        }, 3000);
    }

    private boolean checkPlayServices() {
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();

            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    class RegisterBackground extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);
                PersistData.setStringData(context, AppConstant.GCMID, regid);

                msg = "Dvice registered, registration ID=" + regid;
                Log.e("Google Registration ID", "---------" + msg);

                // Persist the regID - no need to
            } catch (final IOException ex) {
                msg = "Error :" + ex.getMessage();

            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mAdView != null) {
//            mAdView.resume();
//        }
    }

    @Override
    public void onDestroy() {
//        if (mAdView != null) {
//            mAdView.destroy();
//        }
        super.onDestroy();
    }

}
