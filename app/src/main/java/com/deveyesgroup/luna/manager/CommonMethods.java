package com.deveyesgroup.luna.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.TimeoutException;


/**
 * Created by formatting bit on 02/11/15.
 */

public class CommonMethods {
    public static String baseURL = "http://ws.kimik.org/KimikWebService.asmx/";


    /** SharedPreferences **/
    public static String getStringFromDefaults(Context context, String key)
    {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedPreferences.getString(key, null);
        } else
            return null;
    }

    public static boolean getBooleanFromDefaults(Context context, String key)
    {
        if (context != null)
        {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedPreferences.getBoolean(key, false);
        } else
            return false;
    }

    public static int getIntFromDefaults(Context context, String key)
    {
        if (context != null)
        {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedPreferences.getInt(key, -1);
        } else
            return -1;
    }

    public static void writeToDefaults(Context context, String key, int value)
    {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public static void writeToDefaults(Context context, String key, boolean value)
    {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public static void writeToDefaults(Context context, String key, String value)
    {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static void applyCustomFont(Context context, View rootView) {
        if (rootView instanceof  ViewGroup) {
            ViewGroup list = (ViewGroup)rootView;
            for (int i = 0; i < list.getChildCount(); i++) {
                View view = list.getChildAt(i);
                if (view instanceof ViewGroup) {
                    applyCustomFont(context, (ViewGroup) view);
                } else if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    Typeface customTypeface = textView.getTypeface();
                    if (customTypeface != null) {
                        if (customTypeface.getStyle() == Typeface.BOLD) {
                            customTypeface = Typeface.createFromAsset(context.getAssets(), "bold.ttf");
                        } else {
                            customTypeface = Typeface.createFromAsset(context.getAssets(), "normal.ttf");
                        }
                        textView.setTypeface(customTypeface);
                    } else {
                        customTypeface = Typeface.createFromAsset(context.getAssets(), "normal.ttf");
                        textView.setTypeface(customTypeface);
                    }
                }
            }
        } else if (rootView instanceof TextView) {
            TextView textView = (TextView) rootView;
            Typeface customTypeface = textView.getTypeface();
            if (customTypeface != null) {
                if (customTypeface.getStyle() == Typeface.BOLD) {
                    customTypeface = Typeface.createFromAsset(context.getAssets(), "bold.ttf");
                } else {
                    customTypeface = Typeface.createFromAsset(context.getAssets(), "normal.ttf");
                }
                textView.setTypeface(customTypeface);
            } else {
                customTypeface = Typeface.createFromAsset(context.getAssets(), "normal.ttf");
                textView.setTypeface(customTypeface);
            }
        }
    }



    /** using background*/
    public static void setBackground(Context context, View view, int resource){

        int sdk = Build.VERSION.SDK_INT;

        if(view!=null){
            if(view instanceof ImageView){

                ImageView imageView = (ImageView)view;
                Drawable drawable = imageView.getBackground();
                if(drawable!=null) drawable.setCallback(null);

                if(sdk < Build.VERSION_CODES.JELLY_BEAN)
                    imageView.setBackgroundResource(resource);
                else
                    imageView.setBackground(context.getResources().getDrawable(resource));

            }else if(view instanceof Button){
                Button button = (Button)view;
                if(sdk < Build.VERSION_CODES.JELLY_BEAN)
                    button.setBackgroundResource(resource);
                else
                    button.setBackground(context.getResources().getDrawable(resource));

            }else if(view instanceof RelativeLayout){
                RelativeLayout relativeLayout = (RelativeLayout)view;
                if(sdk < Build.VERSION_CODES.JELLY_BEAN)
                    relativeLayout.setBackgroundResource(resource);
                else
                    relativeLayout.setBackground(context.getResources().getDrawable(resource));
            }else if(view instanceof LinearLayout){
                LinearLayout linearLayout = (LinearLayout)view;
                if(sdk < Build.VERSION_CODES.JELLY_BEAN)
                    linearLayout.setBackgroundResource(resource);
                else
                    linearLayout.setBackground(context.getResources().getDrawable(resource));
            }else if(view instanceof ImageButton){
                ImageButton imageButton = (ImageButton)view;
                if(sdk < Build.VERSION_CODES.JELLY_BEAN)
                    imageButton.setBackgroundResource(resource);
                else
                    imageButton.setBackground(context.getResources().getDrawable(resource));
            }else{
                if(sdk < Build.VERSION_CODES.JELLY_BEAN)
                    view.setBackgroundResource(resource);
                else
                    view.setBackground(context.getResources().getDrawable(resource));
            }

        }

    }


    /** close keyboard */
    public static void closeKeyBoard(Context context, View view){
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager.isAcceptingText()) {
            //check if no view has focus:
            //View v = this.getCurrentFocus();
            if(view!=null)
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    public static void showKeyboard(Context context, View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    /** check internet connection */
    public static boolean isConnectingToInternet(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }

        return false;
    }


    //checking exception for the backend response
    public static boolean checkException(Context context, Exception e){
        if(e!=null){
            if (e instanceof UnknownHostException) {
                if(context!=null){
                    //SnackbarManager.show(Snackbar.with(context.getApplicationContext()).type(SnackbarType.MULTI_LINE).color(Color.RED).textColor(context.getResources().getColor(R.color.color2)).text(context.getResources().getString(R.string.Internet_connection_does_not_seem_to_be_available)));
                }

                else
                    Log.e("UnknownHostException", "Internet connection does not seem to be available");
            }else if(e instanceof TimeoutException){
                if(context!=null){
                    //SnackbarManager.show(Snackbar.with(context.getApplicationContext()).type(SnackbarType.MULTI_LINE).color(Color.RED).textColor(context.getResources().getColor(R.color.color2)).text("" + e.toString()));
                }
                else
                    Log.e("TimeoutException", "" + e.toString());

            }else {
                if(context!=null) {
                    //SnackbarManager.show(Snackbar.with(context.getApplicationContext()).type(SnackbarType.MULTI_LINE).color(Color.RED).textColor(context.getResources().getColor(R.color.color2)).text((context.getResources().getString(R.string.Something_wrong) + " : " + e.toString())));
                }
                else
                    Log.e("checkException", "Something wrong:"+ e.toString());

            }
            return false;
        }
        return true;
    }

    //checking which language is this
    public static boolean isItalian(Context context){
        String lng = Locale.getDefault().getDisplayLanguage();
        if(lng.contains("English")){
            return false;
        }
        return true;
    }



    /**
     * @detail get device id
     * */
    public String getDeviceId(Context context){
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String str = telephonyManager.getDeviceId();
        return str;
    }


    /**
     * @detail application version
     * */
    public String appVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //Log.e(tag, "appVersion()" + e.toString());
            return "";
        }
    }

    /**
     * @detail application version
     * */
    public String osVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @detail user device manufacturer and model
     * */

    public String deviceModel(){
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        return manufacturer +"-"+model;
    }

    //encode string
    public static String encode(String s) {
        final StringBuilder sbuf = new StringBuilder();
        int ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            switch (ch) {
                case ' ': {
                    sbuf.append("%20");
                    break;
                }
                case '+': {
                    sbuf.append("%20");
                    break;
                }
                case '!': {
                    sbuf.append("%21");
                    break;
                }
                case '*': {
                    sbuf.append("%2A");
                    break;
                }
                case '\'': {
                    sbuf.append("%27");
                    break;
                }
                case '(': {
                    sbuf.append("%28");
                    break;
                }

                case ')': {
                    sbuf.append("%29");
                    break;
                }
                case ';': {
                    sbuf.append("%3B");
                    break;
                }
			/*
			 * case ':': { sbuf.append("%3A"); break; }
			 */

                case '@': {
                    sbuf.append("%40");
                    break;
                }

                case '$': {
                    sbuf.append("%24");
                    break;
                }
                case ',': {
                    sbuf.append("%2C");
                    break;
                }
			/*
			 * case '/': { sbuf.append("%2F"); break; }
			 */
                case '?': {
                    sbuf.append("%3F");
                    break;
                }

                case '#': {
                    sbuf.append("%23");
                    break;
                }
                case '[': {
                    sbuf.append("%5B");
                    break;
                }
                case ']': {
                    sbuf.append("%5D");
                    break;
                }
                default:
                    sbuf.append((char) ch);
            }
        }
        return sbuf.toString();
    }


    //building complete url to access backend
    public static String getBackendServiceUrl(String action, Vector<KeyValue> params){

        if (params == null || params.size() == 0) {
            return baseURL + action;
        } else {
            String pString = "";

            for (final KeyValue obj : params) {

                pString += obj.getKey().trim() + "=" + obj.getValue().trim()
                        + "&";
            }

            if (pString.endsWith("&")) {
                pString = pString.subSequence(0, pString.length() - 1)
                        .toString();
            }

            return baseURL + action + "?" + encode(pString);
        }
    }


    public static String md5(String string)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(string.getBytes(Charset.forName("US-ASCII")),0,string.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
