package com.deveyesgroup.luna.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luna.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class ActiveWorkHistoryFragment extends BaseFragment {

    public ActiveWorkHistoryFragment() {
    }
    TextView tvActiveWorkBtn,tvHistoryBtn;
    ListView listWorkHistory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_user, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        ImageView imgProPicPro = (ImageView) getView ().findViewById(R.id.imgProPicPro);
        TextView tvPoNmae = (TextView)getView ().findViewById(R.id.tvPoNmae);
        TextView tvWorkTitle = (TextView)getView ().findViewById(R.id.tvWorkTitle);
        TextView tvProLocation = (TextView)getView ().findViewById(R.id.tvProLocation);
        TextView tvProRate = (TextView)getView ().findViewById(R.id.tvProRate);
        final TextView tvActiveWorkBtn = (TextView)getView ().findViewById(R.id.tvActiveWorkBtn);
        final TextView tvHistoryBtn = (TextView)getView ().findViewById(R.id.tvHistoryBtn);
        ListView listWorkHistory = (ListView)getView().findViewById(R.id.listWorkHistory);

        tvHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHistoryBtn.setBackgroundResource(R.drawable.luna_work_history_barr);
                tvActiveWorkBtn.setBackgroundResource(R.drawable.luna_work_history_bar);

            }
        });

        tvActiveWorkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvHistoryBtn.setBackgroundResource(R.drawable.luna_work_history_bar);
                tvActiveWorkBtn.setBackgroundResource(R.drawable.luna_work_history_barr);
            }
        });

    }

    private class WorkHistoryAdapter extends ArrayAdapter<String>{


        public WorkHistoryAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }
}
