package com.deveyesgroup.luna.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luna.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MetaliFragment extends BaseFragment {

    public MetaliFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_metali, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView textViewDescription = (TextView)getView ().findViewById(R.id.textViewDescription);
        textViewDescription.setText(Html.fromHtml(getString(R.string.text_metali)));


        getView().findViewById(R.id.textViewGasRari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setContentFragment(new GasRariFragment(), true);
            }
        });

        getView().findViewById(R.id.textViewMetali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mListener.setContentFragment(new MetaliFragment(), true);
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
