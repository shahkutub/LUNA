package com.deveyesgroup.luna.view;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deveyesgroup.luna.manager.CommonMethods;
import com.luna.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView textViewTuSeiKimika = (TextView)getView ().findViewById(R.id.textViewTuSeiKimika);
        textViewTuSeiKimika.setText(Html.fromHtml(getString(R.string.Tu_sei_Kimika_Il_tuo_corpo)));

        getView().findViewById(R.id.imageViewDiscoverYourElement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if user already logged in then only profile image visible
                if(CommonMethods.getStringFromDefaults(getActivity(), "user_id")!=null){
                    //TODO: show popup to start quiz
                    mListener.setContentFragment(new DiscoverElementFragment(), true);
                }else{
                    ((MainActivity)getActivity()).loginRegister();
                }


            }
        });

        getView().findViewById(R.id.textViewGasRari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setContentFragment(new GasRariFragment(), true);
            }
        });

        getView().findViewById(R.id.textViewMetali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setContentFragment(new MetaliFragment(), true);
            }
        });

        getView().findViewById(R.id.textViewNonMetali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setContentFragment(new NonMetaliFragment(), true);
            }
        });

        getView().findViewById(R.id.textViewSemimetalli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setContentFragment(new SemiMetaliFragment(), true);
            }
        });

    }
}
