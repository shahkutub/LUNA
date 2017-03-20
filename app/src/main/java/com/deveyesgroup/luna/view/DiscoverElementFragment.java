package com.deveyesgroup.luna.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deveyesgroup.luna.manager.AppConstant;
import com.deveyesgroup.luna.manager.CommonMethods;
import com.deveyesgroup.luna.manager.KeyValue;
import com.deveyesgroup.luna.manager.LoadingDialog;
import com.deveyesgroup.luna.manager.PersistData;
import com.deveyesgroup.luna.models.AllAnswer;
import com.deveyesgroup.luna.models.AllQuestion;
import com.deveyesgroup.luna.models.Answer;
import com.deveyesgroup.luna.models.GroupInfo;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.luna.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A placeholder fragment containing a simple view.
 */
public class DiscoverElementFragment extends BaseFragment {

    public DiscoverElementFragment() {
    }
    List<AllQuestion> allQuestions=null;
    TextView textViewQuestionNo,textViewQuestion,textViewPrevious,textViewNext;
    Context con;
    RadioButton radioButtonAnswer1,radioButtonAnswer2,radioButtonAnswer3,radioButtonAnswer4;
    LinearLayout questionMiddleLayout;
    RadioGroup radioQuestion;
    int buttonPos=0;
    List<AllAnswer>allAnswer=null;
    Dialog dd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover_element, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con=getActivity();
        radioQuestion=(RadioGroup)getView().findViewById(R.id.radioQuestion);
        textViewNext=(TextView)getView().findViewById(R.id.textViewNext);
        questionMiddleLayout=(LinearLayout)getView().findViewById(R.id.questionMiddleLayout);
        textViewPrevious=(TextView)getView().findViewById(R.id.textViewPrevious);
        radioButtonAnswer1=(RadioButton)getView().findViewById(R.id.radioButtonAnswer1);
        radioButtonAnswer2=(RadioButton)getView().findViewById(R.id.radioButtonAnswer2);
        radioButtonAnswer3=(RadioButton)getView().findViewById(R.id.radioButtonAnswer3);
        radioButtonAnswer4=(RadioButton)getView().findViewById(R.id.radioButtonAnswer4);
        textViewQuestion=(TextView)getView().findViewById(R.id.textViewQuestion);
        textViewQuestionNo=(TextView)getView().findViewById(R.id.textViewQuestionNo);
        int radioButtonID = radioQuestion.getCheckedRadioButtonId();
        View radioButton = radioQuestion.findViewById(radioButtonID);
        buttonPos = radioQuestion.indexOfChild(radioButton);

        if(PersistData.getIntData(con,AppConstant.questionNo)==1){
            textViewPrevious.setVisibility(View.GONE);
        }else{
            textViewPrevious.setVisibility(View.VISIBLE);
        }

        questionMiddleLayout.setVisibility(View.GONE);
        getAllquestionfromStep("" + PersistData.getIntData(getActivity(), AppConstant.questionStep));
        textViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = radioQuestion.getCheckedRadioButtonId();
                View radioButton = radioQuestion.findViewById(radioButtonID);
                buttonPos = radioQuestion.indexOfChild(radioButton);
                Log.e("buttonPos", ">>" + buttonPos);
                if (buttonPos >= 0) {
                    if (TextUtils.isEmpty(CommonMethods.getStringFromDefaults(getActivity(), "user_id"))) {
                         mListener.setContentFragment(new MainFragment(), true);
                    } else {
                        requestSendAnswer(CommonMethods.getStringFromDefaults(getActivity(), "user_id"), "" + PersistData.getIntData(con, AppConstant.questionStep), "" + allAnswer.get(buttonPos).getIDRisposta());
                    }
                } else {
                    Toast.makeText(con, "Please select a Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PersistData.getIntData(con, AppConstant.questionNo) == 1) {
                    textViewPrevious.setVisibility(View.GONE);
                } else {
                    textViewPrevious.setVisibility(View.VISIBLE);
                }

                radioQuestion.clearCheck();
                int radioButtonID = radioQuestion.getCheckedRadioButtonId();
                View radioButton = radioQuestion.findViewById(radioButtonID);
                buttonPos = radioQuestion.indexOfChild(radioButton);
                Log.e("buttonPos", ">>" + buttonPos);
                PersistData.setIntData(con, AppConstant.questionNo, PersistData.getIntData(con, AppConstant.questionNo) - 1);
                PersistData.setIntDataPos(con, AppConstant.questionPos, PersistData.getIntDataPos(con, AppConstant.questionPos) - 1);
                Log.e("question pos", ">>" + PersistData.getIntDataPos(con, AppConstant.questionPos));
                if(PersistData.getIntData(con,AppConstant.questionNo)<10){
                    textViewQuestionNo.setText("DOMANDA 0"+PersistData.getIntData(con,AppConstant.questionNo));
                }else{
                    textViewQuestionNo.setText("DOMANDA "+PersistData.getIntData(con,AppConstant.questionNo));
                }
                if (PersistData.getIntDataPos(con, AppConstant.questionPos) >= 0) {
                    Log.e("Call", "above 0");
                    questionMiddleLayout.setVisibility(View.GONE);
                    textViewQuestion.setText(allQuestions.get(PersistData.getIntDataPos(con, AppConstant.questionPos)).getTestoDomanda());
                    getPossibleAnswer("" + allQuestions.get(PersistData.getIntDataPos(con, AppConstant.questionPos)).getIDDomanda());
                }else{
                    Log.e("Call", "below 0");
                    PersistData.setIntDataPos(con, AppConstant.questionPos, allQuestions.size()-1);
                    PersistData.setIntData(con, AppConstant.questionStep, PersistData.getIntData(con, AppConstant.questionStep) -1);
                    getAllquestionfromStep("" + PersistData.getIntData(getActivity(), AppConstant.questionStep));
                }

            }
        });

    }

    public void infoDialoge() {
        dd = new Dialog(con);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.first_dialoge);
        dd.show();

        TextView previousTv = (TextView) dd.findViewById(R.id.textViewIndietro);
        TextView nextTv = (TextView) dd.findViewById(R.id.textViewProsegui);
        ImageView infoCrosImg=(ImageView)dd.findViewById(R.id.infoCrosImg);
        TextView textviewInfoTitle=(TextView)dd.findViewById(R.id.textviewInfoTitle);
        setColorInfoDialoge(PersistData.getStringData(con, AppConstant.groupName), textviewInfoTitle);
        infoCrosImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dd.dismiss();
            }
        });

        previousTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PersistData.setIntDataPos(con, AppConstant.questionPos, 0);
                Log.e("question postion", ">>" + PersistData.getIntDataPos(con, AppConstant.questionPos));
                PersistData.setIntData(con, AppConstant.questionStep, 1);
                PersistData.setIntData(con, AppConstant.questionNo, 1);
                getAllquestionfromStep("" + PersistData.getIntData(getActivity(), AppConstant.questionStep));

                /*PersistData.setIntDataPos(con, AppConstant.questionPos, 0);
                Log.e("question postion",">>"+PersistData.getIntDataPos(con,AppConstant.questionPos));
                PersistData.setIntData(con, AppConstant.questionStep, 1);
                mListener.setContentFragment(new ResultFragment(), true);*/
                dd.dismiss();
            }
        });

        nextTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mListener.setContentFragment(new QuestionFragment(), true);
                PersistData.setIntDataPos(con, AppConstant.questionPos, 0);
                Log.e("question postion", ">>" + PersistData.getIntDataPos(con, AppConstant.questionPos));
                PersistData.setIntData(con, AppConstant.questionStep, PersistData.getIntData(con, AppConstant.questionStep) + 1);
                getAllquestionfromStep("" + PersistData.getIntData(getActivity(), AppConstant.questionStep));
                dd.dismiss();
            }
        });

    }


    public void finalDialoge() {
        dd = new Dialog(con);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.second_dialoge);
        dd.show();

        TextView textViewFinalProsegui = (TextView) dd.findViewById(R.id.textViewFinalProsegui);
        TextView textViewRecomincia = (TextView) dd.findViewById(R.id.textViewRecomincia);
        ImageView infoCrosImg=(ImageView)dd.findViewById(R.id.infoCrosImg);
        TextView textviewFinalTitle=(TextView)dd.findViewById(R.id.textviewFinalTitle);
        setColorFinalDialoge(PersistData.getStringData(con,AppConstant.groupName),textviewFinalTitle);
        infoCrosImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dd.dismiss();
            }
        });

        textViewRecomincia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PersistData.setIntDataPos(con, AppConstant.questionPos, 0);
                Log.e("question postion", ">>" + PersistData.getIntDataPos(con, AppConstant.questionPos));
                PersistData.setIntData(con, AppConstant.questionStep, 1);
                PersistData.setIntData(con, AppConstant.questionNo, 1);
                getAllquestionfromStep("" + PersistData.getIntData(getActivity(), AppConstant.questionStep));
                dd.dismiss();
            }
        });

        textViewFinalProsegui.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mListener.setContentFragment(new QuestionFragment(), true);
                mListener.setContentFragment(new ResultFragment(), true);
                dd.dismiss();
            }
        });

    }

    private void requestSendAnswer(String userId,String guestionStep,String answerId) {

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), true, false);
        loadingDialog.show();

        Vector<KeyValue> params = new Vector<>();
        params.addElement(new KeyValue("IdUtente", userId));
        params.addElement(new KeyValue("idDomanda", guestionStep));
        params.addElement(new KeyValue("idRisposta", answerId));
        String url =  CommonMethods.getBackendServiceUrl("SetRisposta",params);

        Log.e(getActivity().getLocalClassName(), "" + url);

        if(url!=null) {
            Ion.with(getActivity())
                    .load(url)
                    .as(new TypeToken<Answer>() {
                    })
                    .setCallback(new FutureCallback<Answer>() {
                        @Override
                        public void onCompleted(Exception e, Answer result) {
                            // do stuff with the result or error
                            Log.e(getActivity().getLocalClassName(), "" + result);
                            loadingDialog.dismiss();

                            if(result!=null){
                                radioQuestion.clearCheck();
                                PersistData.setIntData(con, AppConstant.questionNo, PersistData.getIntData(con, AppConstant.questionNo) + 1);
                                PersistData.setIntDataPos(con, AppConstant.questionPos, PersistData.getIntData(con, AppConstant.questionPos) + 1);
                                if(PersistData.getIntData(con,AppConstant.questionNo)==1){
                                    textViewPrevious.setVisibility(View.GONE);
                                }else{
                                    textViewPrevious.setVisibility(View.VISIBLE);
                                }
                                if(PersistData.getIntDataPos(con, AppConstant.questionPos)<allQuestions.size()){
                                    if(PersistData.getIntData(con,AppConstant.questionNo)<10){
                                        textViewQuestionNo.setText("DOMANDA 0"+PersistData.getIntData(con,AppConstant.questionNo));
                                    }else{
                                        textViewQuestionNo.setText("DOMANDA "+PersistData.getIntData(con,AppConstant.questionNo));
                                    }
                                    textViewQuestion.setText(allQuestions.get(PersistData.getIntDataPos(con,AppConstant.questionPos)).getTestoDomanda());
                                    getPossibleAnswer("" + allQuestions.get(PersistData.getIntDataPos(con, AppConstant.questionPos)).getIDDomanda());
                                }else{
                                    if(PersistData.getIntData(con,AppConstant.questionStep)>4){
                                        finalDialoge();

                                    }else{
                                        if(PersistData.getIntData(con,AppConstant.questionStep)==1){
                                            getGroupName();

                                        }else{
                                            infoDialoge();
                                        }

                                    }
                                }
                                loadingDialog.dismiss();

                            }else{
                                Toast.makeText(con,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void getGroupName() {

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), true, false);
        loadingDialog.show();

        Vector<KeyValue> params = new Vector<>();
        params.addElement(new KeyValue("IDUt", CommonMethods.getStringFromDefaults(getActivity(),"user_id")));
        params.addElement(new KeyValue("DStep", "1"));
        params.addElement(new KeyValue("lingua", "IT"));
        String url =  CommonMethods.getBackendServiceUrl("GetProssimoStep",params);

        Log.e(getActivity().getLocalClassName(), "" + url);

        if(url!=null) {
            Ion.with(getActivity())
                    .load(url)
                    .as(new TypeToken<ArrayList<GroupInfo>>() {
                    })
                    .setCallback(new FutureCallback<ArrayList<GroupInfo>>() {
                        @Override
                        public void onCompleted(Exception e, ArrayList<GroupInfo> result) {
                            // do stuff with the result or error
                            Log.e(getActivity().getLocalClassName(), "" + result);
                            loadingDialog.dismiss();

                            if(result!=null){
                                PersistData.setStringData(con, AppConstant.groupName, result.get(0).getNomeGruppo());
                                Log.e("Group Name",">>"+result.get(0).getNomeGruppo());
                                infoDialoge();
                            }else{
                                Toast.makeText(con,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void setColorInfoDialoge(String groupName,TextView textView) {
        Log.e("stepName",">>"+groupName);
        if(groupName.toLowerCase().contains("gas")){
            textView.setText(getString(R.string.AppartieniAlGruppoDei)+" "+groupName);
            textView.setTextColor(getContext().getResources().getColor(R.color.color_GasRari));
        }else if(groupName.equalsIgnoreCase("METALLI")){
            textView.setText(getString(R.string.AppartieniAlGruppoDei)+" "+groupName);
            textView.setTextColor(getContext().getResources().getColor(R.color.color_Metalli));

        }else if(groupName.equalsIgnoreCase("NON METALLI")){
            textView.setText(getString(R.string.AppartieniAlGruppoDei)+" "+groupName);
            textView.setTextColor(getContext().getResources().getColor(R.color.color_NonMetalli));

        }else if(groupName.equalsIgnoreCase("SEMI METALLI")){
            textView.setText(getString(R.string.AppartieniAlGruppoDei)+" "+groupName);
            textView.setTextColor(getContext().getResources().getColor(R.color.color_SemiMetalli));

        }
    }
    private void setColorFinalDialoge(String groupName,TextView textView) {
        if(groupName.toLowerCase().contains("gas")){
            textView.setTextColor(getContext().getResources().getColor(R.color.color_GasRari));
        }else if(groupName.equalsIgnoreCase("METALLI")){
            textView.setTextColor(getContext().getResources().getColor(R.color.color_Metalli));
        }else if(groupName.equalsIgnoreCase("NON METALLI")){
            textView.setTextColor(getContext().getResources().getColor(R.color.color_NonMetalli));
        }else if(groupName.equalsIgnoreCase("SEMI METALLI")){
            textView.setTextColor(getContext().getResources().getColor(R.color.color_SemiMetalli));
        }
    }


    private void getAllquestionfromStep(String questionStep) {

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), true, false);
        loadingDialog.show();

        Vector<KeyValue> params = new Vector<>();
        params.addElement(new KeyValue("DStep", questionStep));
        params.addElement(new KeyValue("lingua", "IT"));
        String url =  CommonMethods.getBackendServiceUrl("GetDomandeFromStep",params);

        Log.e(getActivity().getLocalClassName(), "" + url);

        if(url!=null) {
            Ion.with(getActivity())
                    .load(url)
                    .as(new TypeToken<ArrayList<AllQuestion>>() {
                    })
                    .setCallback(new FutureCallback<ArrayList<AllQuestion>>() {
                        @Override
                        public void onCompleted(Exception e, ArrayList<AllQuestion> result) {
                            // do stuff with the result or error
                            Log.e(getActivity().getLocalClassName(), "" + result);
                            loadingDialog.dismiss();

                            if(result!=null){
                                if(allQuestions!=null){
                                    allQuestions.clear();
                                }
                                    allQuestions=result;
                                Log.e("allQuestions.size",">>"+allQuestions.size());
                                if(PersistData.getIntData(getActivity(),AppConstant.questionNo)<10){
                                    textViewQuestionNo.setText("DOMANDA 0"+PersistData.getIntData(getActivity(),AppConstant.questionNo));
                                }else{
                                    textViewQuestionNo.setText("DOMANDA "+PersistData.getIntData(getActivity(),AppConstant.questionNo));
                                }
                                textViewQuestion.setText(""+allQuestions.get(PersistData.getIntDataPos(con, AppConstant.questionPos)).getTestoDomanda());
                                getPossibleAnswer("" + allQuestions.get(PersistData.getIntDataPos(con, AppConstant.questionPos)).getIDDomanda());
                                loadingDialog.dismiss();

                            }else{
                                Toast.makeText(con,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void getPossibleAnswer(String questionId) {

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), true, false);
        loadingDialog.show();

        Vector<KeyValue> params = new Vector<>();
        params.addElement(new KeyValue("idDomanda", questionId));
        params.addElement(new KeyValue("lingua", "IT"));
        String url =  CommonMethods.getBackendServiceUrl("GetRisposte",params);

        Log.e(getActivity().getLocalClassName(), "" + url);

        if(url!=null) {
            Ion.with(getActivity())
                    .load(url)
                    .as(new TypeToken<ArrayList<AllAnswer>>() {
                    })
                    .setCallback(new FutureCallback<ArrayList<AllAnswer>>() {
                        @Override
                        public void onCompleted(Exception e, ArrayList<AllAnswer> result) {
                            // do stuff with the result or error
                            Log.e(getActivity().getLocalClassName(), "" + result);
                            loadingDialog.dismiss();

                            if(result!=null){
                                if(allAnswer!=null){
                                    allAnswer.clear();
                                }
                                allAnswer=result;
                                if (PersistData.getIntData(con, AppConstant.questionNo) == 1) {
                                    textViewPrevious.setVisibility(View.GONE);
                                } else {
                                    textViewPrevious.setVisibility(View.VISIBLE);
                                }
                                questionMiddleLayout.setVisibility(View.VISIBLE);
                                radioButtonAnswer1.setText(result.get(0).getTestoRisposta());
                                radioButtonAnswer2.setText(result.get(1).getTestoRisposta());
                                radioButtonAnswer3.setText(result.get(2).getTestoRisposta());
                                radioButtonAnswer4.setText(result.get(3).getTestoRisposta());
                                loadingDialog.dismiss();

                            }else{
                                Toast.makeText(con,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
