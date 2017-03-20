package com.deveyesgroup.luna.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luna.R;


/**
 * Created by User on 6/3/2016.
 */
public class WorkingDetailsFragement extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.working_details, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView tvCategory = (TextView)getView ().findViewById(R.id.tvCategory);
        tvCategory.setText("Category: "+"");
        TextView tvSubCategory = (TextView)getView ().findViewById(R.id.tvSubCategory);
        tvSubCategory.setText("Sub-Category: "+"");
        TextView tvDte = (TextView)getView ().findViewById(R.id.tvDte);
        tvDte.setText("3 May 2016"+"");


        TextView tvTime = (TextView)getView ().findViewById(R.id.tvTime);
        tvTime.setText("Time: "+"");
        TextView tvRate = (TextView)getView ().findViewById(R.id.tvRate);
        tvRate.setText("Rate: "+"");
        TextView tvWorkDetails = (TextView)getView ().findViewById(R.id.tvWorkDetails);
        tvWorkDetails.setText("I want cleane my room");

        TextView tvWorkLocation = (TextView)getView ().findViewById(R.id.tvWorkLocation);
        tvWorkLocation.setText("Location"+"");
        TextView tvWorkProTitle = (TextView)getView ().findViewById(R.id.tvWorkProTitle);
        tvWorkProTitle.setText("House clean:"+"");
        TextView tvWorkProName = (TextView)getView ().findViewById(R.id.tvWorkProName);
        tvWorkProName.setText("Jon Doe"+"");

        TextView tvWorkProDate = (TextView)getView ().findViewById(R.id.tvWorkProDate);
        tvWorkProDate.setText("3 May 2016"+"");

        TextView tvOrderCancel = (TextView)getView ().findViewById(R.id.tvOrderCancel);



    }
}
