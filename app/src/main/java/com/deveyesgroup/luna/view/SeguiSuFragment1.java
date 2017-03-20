package com.deveyesgroup.luna.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.luna.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class SeguiSuFragment1 extends BaseFragment {

    Context con;
    Dialog dd,dd2;
    WebView webview;
    View rootView;
    ProgressBar progress;

    public SeguiSuFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_segui_su, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        webview = (WebView)getView().findViewById(R.id.webView);
        progress = (ProgressBar) getView().findViewById(R.id.progress);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDefaultZoom(ZoomDensity.MEDIUM);
        webview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        Bundle bundle = getArguments();
        if(bundle!=null){
            webview.loadUrl(bundle.getString("url"));
        }

        //webview.loadUrl(AppConstant.webUrl);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress.setVisibility(View.INVISIBLE);
            }

            /*
             * (non-Javadoc)
             *
             * @see
             * android.webkit.WebViewClient#shouldOverrideUrlLoading(android
             * .webkit.WebView, java.lang.String)
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //CookieSyncManager.getInstance().sync();
                view.loadUrl(url);
                return true;
            }

        });

    }


}
