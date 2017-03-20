package com.deveyesgroup.luna.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luna.R;


/**
 * Created by User on 6/3/2016.
 */
public class BookingFragement extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.booking, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        TextView textViewDescription = (TextView)getView ().findViewById(R.id.textViewDescription);
//        textViewDescription.setText(Html.fromHtml(getString(R.string.text_cose_kimik)));


    }
}
