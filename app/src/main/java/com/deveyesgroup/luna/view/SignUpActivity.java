package com.deveyesgroup.luna.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.luna.R;

public class SignUpActivity extends AppCompatActivity {
    private Context context;
   // private TextView textviewCreateAcc,textviewfacebookRegister,textviewHaveAcc;
    private TextView toolbarTitle,textviewRegisterLove;
    private Toolbar toolbar;
    private ImageView imgRegProPic;
    private EditText etFname,etLname,etEmail,etPass,etPhone,etCardHolderName,etCardNumber,etCvv;
    private TextView tvRegBtnReg;
    private RadioButton radioMale,radioFemale;
    private String email,password,phoneNumber,fname,lName,cardNumber,cardHolderName,cvv,expDate,countryCode,gender;
    private RadioGroup radioGroup;
    private CallbackManager callbackManager;
    private String fbId,firstName,lastName,profileImg,link,birthday,fbEmail;
    private boolean isNormal=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_screen);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        context=this;
        initUI();

    }

    private void initUI() {
        // =========== get country code =================
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        countryCode = manager.getSimCountryIso();
//        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
//        radioMale=(RadioButton)findViewById(R.id.radioMale);
//        radioFemale=(RadioButton)findViewById(R.id.radioFemale);
        etFname = (EditText) findViewById(R.id.etFname);
        tvRegBtnReg = (TextView) findViewById(R.id.tvRegBtnReg);
        etLname = (EditText) findViewById(R.id.etLname);
        etEmail = (EditText) findViewById(R.id.etEmail);


        etPass = (EditText) findViewById(R.id.etPass);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etCardHolderName = (EditText) findViewById(R.id.etCardHolderName);

        etCardNumber = (EditText) findViewById(R.id.etCardNumber);
        etCvv = (EditText) findViewById(R.id.etCvv);


        gender = "Male";
        // ========== Set Font =========================
//        etEmailLog.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
//        etPssLog.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
//        tvKeepLogIn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
//        tvRegBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
//        tvPassBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
//        tvOr.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-LT.OTF"));
//        tvFbBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-MD.OTF"));
//        tvTwittBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-MD.OTF"));
//        tvLogInBtn.setTypeface(Typeface.createFromAsset(getAssets(),"HELVETICANEUELTSTD-MD.OTF"));


//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.radioMale) {
//                    gender = "Male";
//                } else {
//                    gender = "Female";
//                }
////                Toast.makeText(context, gender, Toast.LENGTH_SHORT).show();
//            }
//        });
        tvRegBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ValidationActivity.class));
                finish();

//                if (TextUtils.isEmpty(etFname.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter First Name!");
//                } else if (TextUtils.isEmpty(etLname.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter Last Name!");
//                } else if (TextUtils.isEmpty(etEmail.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.insertEmail));
//                } else if (!ValidateEmail.validateEmail(etEmail.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.insertValidEmail));
//                } else if (TextUtils.isEmpty(etPass.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.insertPassword));
//                } else if (etPass.getText().toString().length() < 6) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), getString(R.string.passwordMust));
//                } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter Phone Number!");
//                }else if (TextUtils.isEmpty(etCardHolderName.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter Card Holder Name!");
//                } else if (TextUtils.isEmpty(etCardNumber.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter Card Number!");
//                } else if (TextUtils.isEmpty(etCvv.getText().toString())) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter CVV!");
//                } else if (TextUtils.isEmpty(expDate)) {
//                    AlertMessage.showMessage(context, getString(R.string.app_name), "Enter Exp date!");
//                } else {
//                    isNormal = true;
//                    email = etEmail.getText().toString();
//                    password = etPass.getText().toString();
//                    fname = etFname.getText().toString();
//                    lastName = etLname.getText().toString();
//                    cardHolderName = etCardHolderName.getText().toString();
//                    cardNumber = etCardNumber.getText().toString();
//                    cvv = etCvv.getText().toString();
//                    phoneNumber = etPhone.getText().toString();
//
//                    //requestRegister(AllURL.getRegisterUrl());
//                }
           }
        });

    }

    public void imageBack(View view) {
        finish();
    }

//    private void requestRegister(final String url) {
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
//            String response = "";
//
//            @Override
//            public void run() {
//
//                Map<String, String> param = new HashMap();
//                param.put("email", email);
//                param.put("password", password);
//                param.put("fullname", fullName);
//                param.put("country_code", countryCode);
//                param.put("gender", gender);
//                param.put("device_type", "android");
//                param.put("push_id", PersistData.getStringData(context, AppConstant.GCMID));
//                if (isNormal) {
//                    param.put("type", "normal");
//                } else {
//                    param.put("type", "facebook");
//                    param.put("facebook_id", fbId);
//                }
//
//                try {
//                    response = AAPBDHttpClient.post(url).form(param).body();
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    Log.e("MYAPP", "exception", e);
//                    if (busyNow != null) {
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
//                                if (isNormal) {
//                                    if (mLoginResponse.getStatus().equalsIgnoreCase("1")) {
//                                        PersistData.setStringData(context,AppConstant.loginRespone,response);
//                                        PersistentUser.setUserEmail(context, mLoginResponse.getResults().getEmail());
//                                        PersistentUser.setUserID(context, mLoginResponse.getResults().getId());
//                                        PersistData.setStringData(context, AppConstant.GCMID, mLoginResponse.getResults().getPush_id());
//                                        PersistData.setStringData(context, AppConstant.TOKEN, mLoginResponse.getToken());
//                                        PersistData.setStringData(context, AppConstant.fullName, mLoginResponse.getResults().getFullname());
//                                        PersistentUser.setLogin(context);
//                                        startActivity(new Intent(context, AfterRegisterActivity.class));
//                                        finish();
//                                    } else {
//                                        AlertMessage.showMessage(context, getString(R.string.app_name), mLoginResponse.getMsg() + "");
//                                    }
//
//                                } else {
//                                    if (mLoginResponse.getStatus().equalsIgnoreCase("1")) {
//                                        if (mLoginResponse.getMsg().equalsIgnoreCase("registration success")){
//                                            PersistData.setStringData(context,AppConstant.loginRespone,response);
//                                            PersistentUser.setUserEmail(context, mLoginResponse.getResults().getEmail());
//                                            PersistentUser.setUserID(context, mLoginResponse.getResults().getId());
//                                            PersistData.setStringData(context, AppConstant.GCMID, mLoginResponse.getResults().getPush_id());
//                                            PersistData.setStringData(context, AppConstant.TOKEN, mLoginResponse.getToken());
//                                            PersistentUser.setLogin(context);
//                                            PersistData.setStringData(context, AppConstant.fullName, mLoginResponse.getResults().getFullname());
//                                            startActivity(new Intent(context, AfterRegisterActivity.class));
//                                            finish();
//                                        }else{
//                                            PersistData.setStringData(context,AppConstant.loginRespone,response);
//                                            PersistentUser.setUserEmail(context, mLoginResponse.getResults().getEmail());
//                                            PersistentUser.setUserID(context, mLoginResponse.getResults().getId());
//                                            PersistData.setStringData(context, AppConstant.GCMID, mLoginResponse.getResults().getPush_id());
//                                            PersistData.setStringData(context, AppConstant.TOKEN, mLoginResponse.getToken());
//                                            PersistentUser.setLogin(context);
//                                            PersistData.setStringData(context, AppConstant.fullName, mLoginResponse.getResults().getFullname());
//                                            startActivity(new Intent(context, MainActivity.class));
//                                            finish();
//                                        }
//
//                                    } else {
//                                        AlertMessage.showMessage(context, getString(R.string.app_name), mLoginResponse.getMsg() + "");
//                                    }
//
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
}
