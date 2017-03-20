package com.deveyesgroup.luna.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.luna.R;


public class FragmentDrawer extends BaseFragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();
    protected OnFragmentInteractionListener mListener;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private static String[] titles = null;
    TextView log_out;

    public FragmentDrawer() {

    }
    View layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        /*log_out=(TextView)layout.findViewById(R.id.log_out);
        TextView cosKimikTv=(TextView)layout.findViewById(R.id.cosKimikTv);
        TextView contactTv=(TextView)layout.findViewById(R.id.contactTv);
        ImageView facebookIMg=(ImageView)layout.findViewById(R.id.facebookIMg);
        ImageView twitterImg=(ImageView)layout.findViewById(R.id.twitterImg);
        ImageView googlePlusImg=(ImageView)layout.findViewById(R.id.googlePlusImg);
        TextView tvDiscoverElement=(TextView)layout.findViewById(R.id.tvDiscoverElement);
        tvDiscoverElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(PersistData.getStringData(getActivity(),AppConstant.userId))){
                    AppConstant.isFromFragmentDrawer=true;
                    MainActivity.getInstanse().setContentFragment(new HomeFragment(), false);
                }else{
                    if(PersistData.getBooleanData(getActivity(),AppConstant.isResultView)){
                        MainActivity.getInstanse().setContentFragment(new ResultFragment(), false);
                    }else{
                        MainActivity.getInstanse().setContentFragment(new QuestionFragment(), false);
                    }
                }

                mDrawerLayout.closeDrawers();

            }
        });
        cosKimikTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.getInstanse().setContentFragment(new HomeFragment(), false);
                mDrawerLayout.closeDrawers();


            }
        });
        contactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getInstanse().setContentFragment(new ContactFragment(), false);
                mDrawerLayout.closeDrawers();
            }
        });

        facebookIMg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppConstant.webUrl="https://www.facebook.com/profile.php\n" +
                        "?id=100009571399553&fref=ts";
                MainActivity.getInstanse().setContentFragment(new FbFragment(), false);
                mDrawerLayout.closeDrawers();
            }
        });

        twitterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppConstant.webUrl="https://twitter.com/bekimik";
                MainActivity.getInstanse().setContentFragment(new TwitterFragment(), false);
                mDrawerLayout.closeDrawers();
            }
        });
        googlePlusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppConstant.webUrl="https://plus.google.com/114820358422\n" +
                        "245979157/about?hl=it";

                MainActivity.getInstanse().setContentFragment(new WebViewFragment(), false);
                mDrawerLayout.closeDrawers();
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersistData.setStringData(getActivity(),AppConstant.userEmail,"");
                PersistData.setStringData(getActivity(),AppConstant.userId,"");
                MainActivity.getInstanse().profileLayout.setVisibility(View.GONE);
                MainActivity.getInstanse().loginTopTv.setVisibility(View.VISIBLE);
                MainActivity.getInstanse().setContentFragment(new HomeFragment(), false);
                log_out.setVisibility(View.GONE);
                PersistData.setIntData(getActivity(), AppConstant.questionNo, 1);
                PersistData.setIntDataPos(getActivity(), AppConstant.questionPos, 0);
                PersistData.setIntData(getActivity(),AppConstant.questionNo,1);
                LoginManager.getInstance().logOut();
                mDrawerLayout.closeDrawers();
            }
        });*/
        return layout;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
                Log.e("open","Open");
                if (drawerView != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.e("Close", "Close");
                getActivity().invalidateOptionsMenu();
                if (drawerView != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }
}
