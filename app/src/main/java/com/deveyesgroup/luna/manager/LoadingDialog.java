package com.deveyesgroup.luna.manager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Window;
import android.widget.TextView;

import com.luna.R;


public class LoadingDialog {

	private final Dialog dialog;
	private TextView busyText;

	
	public LoadingDialog(Context c, boolean cancelable, String text) {
		dialog = new Dialog(c,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// here we set layout of progress dialog
		dialog.setContentView(R.layout.loading_layout);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		busyText = (TextView) dialog.findViewById(R.id.busytextview);
		busyText.setText(text + "");
		busyText.setTextColor(Color.WHITE);
	}

	/*
	 * constructor for colors
	 */

	public LoadingDialog(Context c, boolean cancelable, String text, int textColor) {
		dialog = new Dialog(c,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// here we set layout of progress dialog
		dialog.setContentView(R.layout.loading_layout);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		busyText = (TextView) dialog.findViewById(R.id.busytextview);
		busyText.setText(text + "");
		busyText.setTextColor(textColor);
	}

	public LoadingDialog(Context c, boolean cancelable) {
		dialog = new Dialog(c,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// here we set layout of progress dialog
		dialog.setContentView(R.layout.loading_layout);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
	}
	
	
	public LoadingDialog(Context c, boolean cancelable, String text, boolean isFulScreen) {
		if(isFulScreen){
			dialog = new Dialog(c,
					android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		else{
			dialog = new Dialog(c,
					android.R.style.Theme_Translucent_NoTitleBar);
//			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}

		// here we set layout of progress dialog
		dialog.setContentView(R.layout.loading_layout);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		busyText = (TextView) dialog.findViewById(R.id.busytextview);
		busyText.setText(text + "");
		busyText.setTextColor(Color.WHITE);
	}

	/*
	 * constructor for colors
	 */

	public LoadingDialog(Context c, boolean cancelable, String text, int textColor, boolean isFulScreen) {
		if(isFulScreen){
			dialog = new Dialog(c,
					android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		else{
			dialog = new Dialog(c,
					android.R.style.Theme_Translucent_NoTitleBar);
//			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		// here we set layout of progress dialog
		dialog.setContentView(R.layout.loading_layout);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		busyText = (TextView) dialog.findViewById(R.id.busytextview);
		busyText.setText(text + "");
		busyText.setTextColor(textColor);
	}

	public LoadingDialog(Context c, boolean cancelable, boolean isFulScreen) {
		if(isFulScreen){
			dialog = new Dialog(c,
					android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		else{
			dialog = new Dialog(c,
					android.R.style.Theme_Translucent_NoTitleBar);
//			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		// here we set layout of progress dialog
		dialog.setContentView(R.layout.loading_layout);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
	}
	
	
	
	

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

}
