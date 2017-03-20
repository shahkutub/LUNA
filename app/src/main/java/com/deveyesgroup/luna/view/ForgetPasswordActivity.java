package com.deveyesgroup.luna.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.deveyesgroup.luna.Utils.AlertMessage;
import com.luna.R;


public class ForgetPasswordActivity extends Activity {

	//UpdateResponse updateresponse;
	Context con;
	EditText etEmailForgot;
	LinearLayout fblayout, logInForgotPas;
	TextView tvSendBtn, tvRegNowBtn, tvRemember, tvForLogin;
	MediaPlayer mp;
	String forgetEmil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.for_got_pass_screen);
		con = this;
		initUI();
	}

	private void initUI() {

		etEmailForgot = (EditText)findViewById(R.id.etEmailForgot);
		tvSendBtn = (TextView)findViewById(R.id.tvSendBtn);
		tvRegNowBtn = (TextView)findViewById(R.id.tvRegNowBtn);

		tvRegNowBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(con, SignUpActivity.class));
			}
		});

		tvSendBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(etEmailForgot.getText().toString())){
					AlertMessage.showMessage(con,getString(R.string.insertEmail),"");
				}else {
					forgetEmil = etEmailForgot.getText().toString();
				}
			}
		});
	}

	public void imageBack(View view) {
		finish();
	}

//	private void initUI() {
//		etForgotPassEmail = (EditText) findViewById(R.id.etForgotPassEmail);
//		tvSendPass = (TextView) findViewById(R.id.tvSendPass);
//		tvTitle = (TextView) findViewById(R.id.tvTitle);
//		tvRemember = (TextView) findViewById(R.id.tvRemember);
//		tvForLogin = (TextView) findViewById(R.id.tvForLogin);
//		logInForgotPas = (LinearLayout) findViewById(R.id.logInForgotPas);
//		String fontPathB = "fonts/calibril.ttf";
//		String fontPath = "fonts/calibril.ttf";
//
//		Typeface tfB = Typeface.createFromAsset(getAssets(), fontPathB);
//		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
//
//		// Applying font
//		etForgotPassEmail.setTypeface(tf);
//		tvSendPass.setTypeface(tfB);
//		tvTitle.setTypeface(tfB);
//		//tvForLogin.setTypeface(tfB);
//		tvRemember.setTypeface(tfB);
//
//		logInForgotPas.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				// makeSound();
//				// StartActivity.toActivity(con, LoginActivity.class);
//				finish();
//			}
//		});
//
//		tvSendPass.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				// makeSound();
//				checkForgetData();
//			}
//		});
//
////		if (!PersistData.getBooleanData(con, AppConstant.soundFlageOff)) {
////			makeSound();
////		}
//	}
//
//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
////		if (!PersistData.getBooleanData(con, AppConstant.soundFlageOff)) {
////			mp.stop();
////		}
//	}
//
//	private void makeSound() {
//
//		mp = MediaPlayer.create(this, R.raw.ambiance);
//		mp.start();
//
//		mp.setOnCompletionListener(new OnCompletionListener() {
//
//			@Override
//			public void onCompletion(MediaPlayer mp) {
//				// TODO Auto-generated method stub
//				mp.start();
//			}
//		});
//	}

//	private void checkForgetData() {
//		// TODO Auto-generated method stub
//
//		if (TextUtils.isEmpty(etForgotPassEmail.getText().toString())) {
//			AlertMessage.showMessage(con, getString(R.string.Status),
//					getString(R.string.PleaseEnterUserEmail));
//			return;
//		} else if (!ValidateEmail.validateEmail(etForgotPassEmail.getText()
//				.toString())) {
//			AlertMessage.showMessage(con, getString(R.string.Status),
//					getString(R.string.PleaseEnterUserEmail));
//			return;
//		} else {
//			forgetEmil = etForgotPassEmail.getText().toString();
//
//			getForgerPassword(AllURL.getForgetPassword(forgetEmil));
//
//		}
//
//	}

//	protected void getForgerPassword(final String url) {
//
//		if (!NetInfo.isOnline(con)) {
//			AlertMessage.showMessage(con, getString(R.string.Status),
//					getString(R.string.checkInternet));
//			return;
//		}
//		final StylusBusyDialogue busy =StylusBusyDialogue.show(con, "Please wait...", true);
////		final BusyDialog busyNow = new BusyDialog(con, true, false);
////		busyNow.show();
//
//		final AsyncHttpClient client = new AsyncHttpClient();
//
//		// String credentials = "demo" + ":" + "demo";
//		// String base64EncodedCredentials =
//		// Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//		// client.addHeader("Authorization", "Basic " +
//		// base64EncodedCredentials);
//		client.addHeader("X-Auth-Token",
//				PersistData.getStringData(con, AppConstant.apiToken));
//
//		final RequestParams param = new RequestParams();
//
//		try {
//
//			param.put("email", forgetEmil);
//
//			// param.put("first_name",
//			// SharedPreferencesHelper.getFullName(con));
//			// param.put("email", SharedPreferencesHelper.getEmail(con));
//			// param.put("phone", SharedPreferencesHelper.getPhone(con));
//			// param.put("address", SharedPreferencesHelper.getAddress(con));
//			// param.put("post_no", SharedPreferencesHelper.getPostNo(con));
//			// param.put("city", SharedPreferencesHelper.getCity(con));
//			// param.put("country", SharedPreferencesHelper.getCountry(con));
//			// param.put("social_id", SharedPreferencesHelper.getSocialId(con));
//			// param.put("bank_name", SharedPreferencesHelper.getBankName(con));
//			// param.put("account", SharedPreferencesHelper.getAccountNo(con));
//			// param.put("iban", SharedPreferencesHelper.getIBAN(con));
//			// param.put("age", SharedPreferencesHelper.getBirthDate(con));
//			// param.put("old_password", SharedPreferencesHelper.getPass(con));
//			// //param.put("password", Newpassword);
//			//
//			// //param.put("device_type", Device_type);
//			// param.put("push_id", SharedPreferencesHelper.getGcmId(con));
//
//		} catch (final Exception e1) {
//			e1.printStackTrace();
//		}
//
//		client.post(url, param, new AsyncHttpResponseHandler() {
//
//			@Override
//			public void onStart() {
//				// called before request is started
//			}
//
//			@Override
//			public void onSuccess(int statusCode, Header[] headers,
//					byte[] response) {
//				// called when response HTTP status is "200 OK"
//
//				if (busy != null) {
//					busy.dismiss();
//				}
//
//				Log.e("resposne ", ">>" + new String(response));
//
//				Gson g = new Gson();
//				updateresponse = g.fromJson(new String(response),
//						UpdateResponse.class);
//
//				Log.e("status", "" + updateresponse.getStatus());
//
//				if (updateresponse.getStatus().equalsIgnoreCase("true")) {
////
////					Toast.makeText(con, updateresponse.getMessage() + "",
////							Toast.LENGTH_LONG).show();
//
//
//					finish();
//
//				} else {
//
//					AlertMessage.showMessage(con, "Error",
//							updateresponse.getMessage() + "");
//					return;
//				}
//
//			}
//
//			@Override
//			public void onFailure(int statusCode, Header[] headers,
//					byte[] errorResponse, Throwable e) {
//				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
//
//				Log.e("errorResponse", new String(errorResponse));
//
//				if (busy != null) {
//					busy.dismiss();
//				}
//			}
//
//			@Override
//			public void onRetry(int retryNo) {
//				// called when request is retried
//
//			}
//		});
//
//	}

	// protected void getForgerPassword(final String url) {
	//
	// final BusyDialog busyNow = new BusyDialog(con, true);
	// busyNow.show();
	//
	// Log.e("FB login URL", url + "");
	//
	// final AsyncHttpClient client = new AsyncHttpClient();
	// client.get(url, new AsyncHttpResponseHandler() {
	//
	// @Override
	// public void onStart() {
	// // called before request is started
	// }
	//
	// @Override
	// public void onSuccess(int statusCode, Header[] headers,
	// byte[] response) {
	// // called when response HTTP status is "200 OK"
	//
	// if (busyNow != null) {
	// busyNow.dismis();
	// }
	//
	// JSONObject json_ob = null;
	// try {
	// json_ob = new JSONObject(new String(response));
	//
	// } catch (final JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// final String status = json_ob.optString("status");
	// final String message = json_ob.optString("message");
	// if (status.equalsIgnoreCase("1")) {
	//
	// final Intent i = new Intent(con, LoginActivity.class);
	// startActivity(i);
	// finish();
	// } else {
	//
	// AlertMessage.showMessage(con, "", message + "");
	// return;
	// }
	//
	// }
	//
	// @Override
	// public void onFailure(int statusCode, Header[] headers,
	// byte[] errorResponse, Throwable e) {
	// // called when response HTTP status is "4XX" (eg. 401, 403, 404)
	//
	// if (busyNow != null) {
	// busyNow.dismis();
	// }
	// }
	//
	// @Override
	// public void onRetry(int retryNo) {
	// // called when request is retried
	//
	// }
	// });
	//
	// }

}
