package com.deveyesgroup.luna.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.luna.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Context context;
    static LoginActivity instance;
    private TextView tvLogInBtn,tvFbBtn,tvTwittBtn,tvPassBtn,tvRegBtn,tvKeepLogIn,tvOr;
    private CallbackManager callbackManager;
    private String fbId,gender,fullName;
    private EditText etEmailLog,etPssLog;
    private String email,password,countryCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_screen);
        context=this;
        instance = this;
        initUI();

    }

    public static LoginActivity getInstance() {
        return instance;
    }

    private void initUI() {
        // =========== get country code =================
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        countryCode = manager.getSimCountryIso();

        tvRegBtn=(TextView)findViewById(R.id.tvRegBtn);
        tvPassBtn=(TextView)findViewById(R.id.tvPassBtn);
        tvKeepLogIn=(TextView)findViewById(R.id.tvKeepLogIn);
        tvFbBtn=(TextView)findViewById(R.id.tvFbBtn);
        tvTwittBtn=(TextView)findViewById(R.id.tvTwittBtn);
        tvOr=(TextView)findViewById(R.id.tvOr);
        tvLogInBtn=(TextView)findViewById(R.id.tvLogInBtn);
        etEmailLog=(EditText)findViewById(R.id.etEmailLog);
        etPssLog=(EditText)findViewById(R.id.etPssLog);

        etEmailLog.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
        etPssLog.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
        tvKeepLogIn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
        tvRegBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
        tvPassBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
        tvOr.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
        tvFbBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-MD.OTF"));
        tvTwittBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-MD.OTF"));
        tvLogInBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-MD.OTF"));

        tvLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
                //finish();
//                if (TextUtils.isEmpty(etEmailLog.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.insertEmail));
//                } else if (!ValidateEmail.validateEmail(etEmailLog.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.insertValidEmail));
//                } else if (TextUtils.isEmpty(etPssLog.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.insertPassword));
//                }else{
//                    email = etEmailLog.getText().toString();
//                    password = etPssLog.getText().toString();
//                   // requestLogin(AllURL.getLoginUrl());
//                }



            }
        });

        tvFbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile, email, user_birthday"));
            }
        });

        tvRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,SignUpActivity.class));
            }
        });

        tvPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ForgetPasswordActivity.class));
            }
        });

        //Register a callback
        FacebookSdk.sdkInitialize(LoginActivity.this);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // login successful
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        // Application code
                                        Log.e("LoginActivity", ">>" + response.getJSONObject());
                                        try {
                                            JSONObject jholder = new JSONObject(response.getJSONObject().toString());
                                            fbId = jholder.optString("id");
                                            fullName = jholder.optString("name");
                                            email = jholder.optString("email");
                                            gender = jholder.optString("gender");
//                                            birthday = jholder.optString("birthday");
//                                            link = jholder.optString("link");
//                                            JSONObject jPicture = jholder.optJSONObject("picture");
//                                            profileImg = jPicture.optJSONObject("data").optString("url");
                                           // requestFacebookLogin(AllURL.getRegisterUrl());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday,picture.type(large),link");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // login cancelled
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // login error
                    }
                });
    }

//    private void requestLogin(final String url) {
//        // TODO Auto-generated method stub
//        if (!NetInfo.isOnline(context)) {
//            AlertMessage.showMessage(context, getString(R.string.app_name),getString(R.string.NoInternet));
//            return;
//        }
//        Log.e("URL : ", url);
//        final BusyDialog busyNow = new BusyDialog(context, true,false);
//        busyNow.show();
//        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
//            String response="";
//
//            @Override
//            public void run() {
//
//                Map<String,String> param =new HashMap();
//                param.put("email",email);
//                param.put("password",password);
//                param.put("device_type","Android");
//                param.put("push_id", PersistData.getStringData(context, AppConstant.GCMID));
//                try {
//                    response= AAPBDHttpClient.post(url).form(param).body();
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    Log.e("MYAPP", "exception", e);
//                    if(busyNow!=null){
//                        busyNow.dismis();
//                    }
//                }
//
//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        if (busyNow != null) {
//                            busyNow.dismis();
//                        }
//                        try {
//                            Log.e("Response", ">>" + new String(response));
//                            if (!TextUtils.isEmpty(new String(response))) {
//                                Gson g = new Gson();
//                              /  LoginResponse mLoginResponse = g.fromJson(new String(response), LoginResponse.class);
//                                    if (mLoginResponse.getStatus().equalsIgnoreCase("1")) {
//                                        PersistData.setStringData(context,AppConstant.loginRespone,response);
//                                        PersistentUser.setUserEmail(context, mLoginResponse.getResults().getEmail());
//                                        PersistentUser.setUserID(context, mLoginResponse.getResults().getId());
//                                        PersistData.setStringData(context, AppConstant.GCMID,mLoginResponse.getResults().getPush_id());
//                                        PersistData.setStringData(context, AppConstant.TOKEN, mLoginResponse.getToken());
//                                        PersistData.setStringData(context,AppConstant.fullName,mLoginResponse.getResults().getFullname());
//                                        PersistentUser.setLogin(context);
//                                        startActivity(new Intent(context, MainActivity.class));
//                                        finish();
//
//                                    } else {
//                                        AlertMessage.showMessage(context, getString(R.string.app_name), mLoginResponse.getMsg() + "");
//
//                                    }
//
//                            }
//
//
//                        } catch (final Exception e) {
//
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
//            }
//        });
//
//
//    }

//    private void requestFacebookLogin(final String url) {
//        // TODO Auto-generated method stub
//        if (!NetInfo.isOnline(context)) {
//            AlertMessage.showMessage(context, getString(R.string.app_name),getString(R.string.NoInternet));
//            return;
//        }
//
//        Log.e("URL : ", url);
//
//        final BusyDialog busyNow = new BusyDialog(context, true,false);
//        busyNow.show();
//        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
//            String response="";
//
//            @Override
//            public void run() {
//
//                Map<String,String> param =new HashMap();
//                param.put("email",email);
//                param.put("password",password);
//                param.put("fullname",fullName);
//                param.put("country_code",countryCode);
//                param.put("gender",gender);
//                param.put("device_type","android");
//                param.put("push_id", PersistData.getStringData(context, AppConstant.GCMID));
//                param.put("type","facebook");
//                param.put("facebook_id",fbId);
//
//                try {
//                    response= AAPBDHttpClient.post(url).form(param).body();
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    Log.e("MYAPP", "exception", e);
//                    if(busyNow!=null){
//                        busyNow.dismis();
//                    }
//                }
//
//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        if (busyNow != null) {
//                            busyNow.dismis();
//                        }
//                        try {
//                            Log.e("Response", ">>" + new String(response));
//                            if (!TextUtils.isEmpty(new String(response))) {
//                                Gson g = new Gson();
//                                LoginResponse mLoginResponse = g.fromJson(new String(response), LoginResponse.class);
//                                if (mLoginResponse.getStatus().equalsIgnoreCase("1")) {
//                                    if (mLoginResponse.getMsg().equalsIgnoreCase("registration success")){
//                                        PersistData.setStringData(context,AppConstant.loginRespone,response);
//                                        PersistentUser.setUserEmail(context, mLoginResponse.getResults().getEmail());
//                                        PersistentUser.setUserID(context, mLoginResponse.getResults().getId());
//                                        PersistData.setStringData(context, AppConstant.GCMID, mLoginResponse.getResults().getPush_id());
//                                        PersistData.setStringData(context, AppConstant.TOKEN, mLoginResponse.getToken());
//                                        PersistentUser.setLogin(context);
//                                        PersistData.setStringData(context, AppConstant.fullName, mLoginResponse.getResults().getFullname());
//                                        startActivity(new Intent(context, AfterRegisterActivity.class));
//                                        finish();
//                                    }else{
//                                        PersistData.setStringData(context,AppConstant.loginRespone,response);
//                                        PersistentUser.setUserEmail(context, mLoginResponse.getResults().getEmail());
//                                        PersistentUser.setUserID(context, mLoginResponse.getResults().getId());
//                                        PersistData.setStringData(context, AppConstant.GCMID, mLoginResponse.getResults().getPush_id());
//                                        PersistData.setStringData(context, AppConstant.TOKEN, mLoginResponse.getToken());
//                                        PersistentUser.setLogin(context);
//                                        PersistData.setStringData(context, AppConstant.fullName, mLoginResponse.getResults().getFullname());
//                                        startActivity(new Intent(context, MainActivity.class));
//                                        finish();
//                                    }
//
//                                } else {
//                                    AlertMessage.showMessage(context, getString(R.string.app_name), mLoginResponse.getMsg() + "");
//                                }
//
//                            }
//
//
//                        } catch (final Exception e) {
//
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
//            }
//        });
//
//
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
