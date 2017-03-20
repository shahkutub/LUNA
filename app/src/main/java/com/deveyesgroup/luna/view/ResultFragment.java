package com.deveyesgroup.luna.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deveyesgroup.luna.manager.AppConstant;
import com.deveyesgroup.luna.manager.CommonMethods;
import com.deveyesgroup.luna.manager.KeyValue;
import com.deveyesgroup.luna.manager.LoadingDialog;
import com.deveyesgroup.luna.manager.PersistData;
import com.deveyesgroup.luna.models.E;
import com.deveyesgroup.luna.models.Result;
import com.deveyesgroup.luna.models.User;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.luna.R;

import java.util.List;
import java.util.Vector;



/**
 * A placeholder fragment containing a simple view.
 */
public class ResultFragment extends BaseFragment {

    public ResultFragment() {
    }

    //Context con;
    TextView textViewName;
    TextView textViewElemento;
    TextView textViewElemento1;
    TextView textViewNumeroAtomico;
    TextView textViewPesoAtomico;
    TextView textViewProgress1;
    TextView textViewProgress2;
    TextView textViewProgress3;
    TextView textViewSimbolo;
    TextView textViewFacebook;
    DonutProgress donutProgress1;
    DonutProgress donutProgress2;
    DonutProgress donutProgress3;


    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //con=getActivity();
        //update by S.Rana
        PersistData.setBooleanData(getActivity(), AppConstant.isResultFragmentView, true);

        textViewName = (TextView)getView().findViewById(R.id.textViewName);
        textViewName.setText("");
        textViewElemento = (TextView)getView().findViewById(R.id.textViewElemento);
        textViewElemento.setText("");
        textViewElemento1 = (TextView)getView().findViewById(R.id.textViewElemento1);
        textViewElemento1.setText("");
        textViewSimbolo = (TextView)getView().findViewById(R.id.textViewSimbolo);
        textViewSimbolo.setText("");
        textViewPesoAtomico = (TextView)getView().findViewById(R.id.textViewPesoAtomico);
        textViewPesoAtomico.setText("");
        textViewNumeroAtomico = (TextView)getView().findViewById(R.id.textViewNumeroAtomico);
        textViewNumeroAtomico.setText("");
        textViewProgress1 = (TextView)getView().findViewById(R.id.textViewProgress1);
        textViewProgress2 = (TextView)getView().findViewById(R.id.textViewProgress2);
        textViewProgress3 = (TextView)getView().findViewById(R.id.textViewProgress3);
        textViewFacebook = (TextView)getView().findViewById(R.id.textViewFacebook);

        donutProgress1 = (DonutProgress)getView().findViewById(R.id.donutProgress1);
        donutProgress2 = (DonutProgress)getView().findViewById(R.id.donutProgress2);
        donutProgress3 = (DonutProgress)getView().findViewById(R.id.donutProgress3);

        donutProgress1.setProgress(0);
        donutProgress2.setProgress(0);
        donutProgress3.setProgress(0);
        //get user

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), true, false);
        loadingDialog.show();

        final String user_id = CommonMethods.getStringFromDefaults(getActivity(), "user_id");

        if(user_id!=null) {
            Vector<KeyValue> params = new Vector<>();
            params.addElement(new KeyValue("idUtente", user_id));
            String url = CommonMethods.getBackendServiceUrl("GetUtente", params);
            if(url!=null) {
                Ion.with(getActivity())
                        .load(url)
                        .as(new TypeToken<User>() {
                        })
                        .setCallback(new FutureCallback<User>() {
                            @Override
                            public void onCompleted(Exception e, User result) {
                                // do stuff with the result or error
                                Log.e(getTag(), "" + result);




                                if(result!=null){

                                    textViewName.setText(""+result.getNome()+", "+getString(R.string.tu_sei));

                                    //1
                                    Vector<KeyValue> params = new Vector<>();
                                    params.addElement(new KeyValue("IDSoluzione", result.getIDSoluzione()));
                                    String url = CommonMethods.getBackendServiceUrl("GetElementoFromSoluzione", params);
                                    if(url!=null) {
                                        Ion.with(getActivity())
                                                .load(url)
                                                .as(new TypeToken<List<Result>>() {
                                                })
                                                .setCallback(new FutureCallback<List<Result>>() {
                                                    @Override
                                                    public void onCompleted(Exception exp, List<Result> resultList) {
                                                        loadingDialog.dismiss();

                                                        if(resultList!=null){
                                                            Result result = resultList.get(0);

                                                            E e = result.getE();
                                                            if(e!=null) {
                                                                textViewElemento.setText(""+e.getElemento());
                                                                textViewElemento1.setText(""+e.getElemento());
                                                                textViewSimbolo.setText(""+e.getSimbolo());
                                                                textViewNumeroAtomico.setText(""+e.getNumeroAtomico());
                                                                textViewPesoAtomico.setText(""+e.getPesoAtomico());
                                                                textViewProgress1.setText(getString(R.string.Sono)+" "+e.getElemento());
                                                                textViewProgress2.setText(getString(R.string.Vanno_daccordo_con)+" "+e.getElemento());

                                                            }
                                                        }

                                                    }
                                                });
                                    }

                                    //2





                                    if(result.getPerc1()!=null)
                                        donutProgress1.setProgress(Integer.parseInt(result.getPerc1()));
                                    if(result.getPerc2()!=null)
                                        donutProgress2.setProgress(Integer.parseInt(result.getPerc2()));
                                    if(result.getPerc3()!=null)
                                        donutProgress3.setProgress(Integer.parseInt(result.getPerc3()));

                                    Log.e(getTag(), "" + result.getPerc1());
                                    Log.e(getTag(), "" + result.getPerc2());
                                    Log.e(getTag(), "" + result.getPerc3());
                                }else{
                                    Toast.makeText(getActivity(), getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }

        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {

                loadingDialog.show();

                Vector<KeyValue> params = new Vector<>();
                params.addElement(new KeyValue("idUtente", user_id));
                String url = CommonMethods.getBackendServiceUrl("MandaMailRisultato", params);
                if(url!=null) {
                    Ion.with(getActivity())
                            .load(url)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception exp, JsonObject result) {
                                    loadingDialog.dismiss();

                                    if (result != null) {
                                        JsonElement jsonElement = result.get("SendSuccessful");
                                        Log.e(getTag(),""+jsonElement.getAsBoolean());
                                        if(jsonElement.getAsBoolean()){
                                            Toast.makeText(getActivity(), getString(R.string.Inviata_con_successo), Toast.LENGTH_LONG).show();
                                        }
                                    }

                                }
                            });
                }

            }
        };


        LinearLayout resultSentMail = (LinearLayout)getView().findViewById(R.id.resultSentMail);
        resultSentMail.setOnClickListener(clickListener);

        LinearLayout linearLayoutMail = (LinearLayout)getView().findViewById(R.id.linearLayoutMail);
        linearLayoutMail.setOnClickListener(clickListener);


        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


        textViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("KIMIK")
                            .setContentDescription(
                                    "Scava dentro te!\nIndaga su te stesso!\nE trova CHI SEI.\nSe con sincerità e purezza seguirai la tua strada,\nincontrerai il TUO ELEMENTO.\nVieni anche tu su http://www.chimic.it/")
                            .setContentUrl(Uri.parse("http://www.chimic.it/"))
                            .build();

                    shareDialog.show(linkContent);
                }

            }
        });



        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //Toast.makeText(getActivity(), "Back Pressed", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });



    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
