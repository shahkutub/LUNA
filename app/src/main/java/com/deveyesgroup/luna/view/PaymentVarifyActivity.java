package com.deveyesgroup.luna.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luna.R;


/**
 * Created by User on 6/3/2016.
 */
public class PaymentVarifyActivity extends Activity{

    Context con;
    EditText etCardHolderName,etCardNum,etCvv;
    LinearLayout fblayout, logInForgotPas;
    TextView tvVarifyBtn, tvSendPass, tvRemember, tvForLogin;
    MediaPlayer mp;
    String cardholderName,cardNumber,cvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.payment_verify);
        con = this;
        initUI();
    }

    public void imageBack(View view) {
        finish();
    }

    private void initUI() {
        tvVarifyBtn = (TextView)findViewById(R.id.tvVarifyBtn);
        etCardHolderName = (EditText)findViewById(R.id.etCardHolderName);
        etCardNum = (EditText)findViewById(R.id.etCardNum);
        etCvv = (EditText)findViewById(R.id.etCvv);
        tvVarifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(con, MainActivity.class));
                finish();

//                if(TextUtils.isEmpty(etCardHolderName.getText().toString())){
//                    AlertMessage.showMessage(con,getString(R.string.app_name),"Enter Card Holder Name!");
//                }else if(TextUtils.isEmpty(etCardNum.getText().toString())){
//                    AlertMessage.showMessage(con,getString(R.string.app_name),"Enter Card Number!");
//                }else if(TextUtils.isEmpty(etCvv.getText().toString())){
//                    AlertMessage.showMessage(con,getString(R.string.app_name),"Enter Cvv!");
//                }else {
//                    cardholderName = etCardHolderName.getText().toString();
//                    cardNumber = etCardNum.getText().toString();
//                    cvNumber = etCvv.getText().toString();
//                }
            }
        });
    }

}


