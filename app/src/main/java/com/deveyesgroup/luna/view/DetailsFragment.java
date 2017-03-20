package com.deveyesgroup.luna.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luna.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends BaseFragment {

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView textViewTuSeiKimika = (TextView)getView ().findViewById(R.id.textViewTuSeiKimika);
        String str = "Tu sei Kimika,";
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new RelativeSizeSpan(20f), 0, str.length(), 0);
        //textViewTuSeiKimika.setText(str);
        textViewTuSeiKimika.setText(Html.fromHtml(getString(R.string.Tu_sei_Kimika_Il_tuo_corpo)));

        getView().findViewById(R.id.textViewGasRari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getView().findViewById(R.id.textViewMetali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getView().findViewById(R.id.textViewNonMetali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getView().findViewById(R.id.textViewSemimetalli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
