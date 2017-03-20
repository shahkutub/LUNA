package com.deveyesgroup.luna.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.deveyesgroup.luna.manager.CommonMethods;
import com.deveyesgroup.luna.manager.KeyValue;
import com.deveyesgroup.luna.manager.LoadingDialog;
import com.deveyesgroup.luna.models.User;
import com.facebook.login.LoginManager;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Arrays;
import java.util.Vector;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.luna.R;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    Toolbar toolbar;
    FragmentDrawer drawerFragment;
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView tvToolBarTitle;
    ImageView imageViewProfile;
    TextView textViewLogin;

    Dialog dialog;
    Dialog dialogForgetPassword;

    private CallbackManager mCallbackManager;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        con=this;
        //FacebookSdk.sdkInitialize(MainActivity.this);
        //callbackManager = CallbackManager.Factory.create();

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Success", "Login");

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        // Application code
                                        Log.v("LoginActivity", response.toString());
                                        Log.e("Fb email", ">>" + user.optString("email"));
                                        Log.e("Fb email", ">>" + user.optString("id"));
                                        Log.e("Fb email", ">>" + user.optString("name"));

                                        //todo:

                                        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this, true, false);
                                        loadingDialog.show();

                                        Vector<KeyValue> params = new Vector<>();
                                        params.addElement(new KeyValue("Email", user.optString("email")));
                                        params.addElement(new KeyValue("SocialID", user.optString("id")));
                                        //params.addElement(new KeyValue("campi", "Nome"));
                                        //params.addElement(new KeyValue("valori", user.optString("name")));
                                        String url =  CommonMethods.getBackendServiceUrl("SocialLoginUtente",params);

                                        Log.e(getLocalClassName(), "" + url);

                                        if(url!=null) {
                                            Ion.with(MainActivity.this)
                                                    .load(url)
                                                    .as(new TypeToken<User>() {
                                                    })
                                                    .setCallback(new FutureCallback<User>() {
                                                        @Override
                                                        public void onCompleted(Exception e, User result) {
                                                            // do stuff with the result or error
                                                            //Log.e(getLocalClassName(), "" + result);
                                                            loadingDialog.dismiss();

                                                            if(result!=null){
                                                                if(result.getIDUtente()!=null){
                                                                    CommonMethods.writeToDefaults(MainActivity.this, "user_id", "" + result.getIDUtente());

                                                                    imageViewProfile.setVisibility(View.VISIBLE);
                                                                    textViewLogin.setVisibility(View.GONE);
                                                                    navigationView.findViewById(R.id.textViewLogout).setVisibility(View.VISIBLE);


                                                                }else{

                                                                    if(dialog!=null)
                                                                        dialog.dismiss();

                                                                    Toast.makeText(MainActivity.this,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                                                                }
                                                            }else{
                                                                if(dialog!=null)
                                                                    dialog.dismiss();

                                                                Toast.makeText(MainActivity.this,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();

                                                                //Toast.makeText(MainActivity.this,"User exist, do login", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }


                                        //PersistData.setStringData(con, AppConstant.userEmail, user.optString("email"));
                                        //requestFBAccount(AllURL.getRegisterUrl(PersistData.getStringData(con,AppConstant.userEmail)));
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        //Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, ""+exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
         tvToolBarTitle = (TextView)toolbar.findViewById(R.id.tvToolBarTitle);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();



        //menu item
        LinearLayout navifationViewtProfile = (LinearLayout)navigationView.findViewById(R.id.navifationViewtProfile);
        navifationViewtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getLocalClassName(), "Profile");

                tvToolBarTitle.setText("Profile");
                drawerLayout.closeDrawers();
                setContentFragment(new EditProfileFragement(), true);
            }
        });

        LinearLayout linearActiveOrder = (LinearLayout)navigationView.findViewById(R.id.linearActiveOrder);
        linearActiveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getLocalClassName(), "Contatti");
                drawerLayout.closeDrawers();


//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                sendIntent.setType("plain/text");
//                //sendIntent.setData(Uri.parse("info@bekimik.com"));
//                //sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
//                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@bekimik.com"});
//                sendIntent.putExtra(Intent.EXTRA_SUBJECT, ""+getString(R.string.Contatti));
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "\n-KIMIK");
//                startActivity(sendIntent);
                tvToolBarTitle.setText("Active Work");
                setContentFragment(new ActiveWorkHistoryFragment(), true);
            }
        });



        LinearLayout linearhistory = (LinearLayout)navigationView.findViewById(R.id.linearhistory);
        linearhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e(getLocalClassName(), "");
                drawerLayout.closeDrawers();
                tvToolBarTitle.setText("Booking");
                setContentFragment(new BookingFragement(), true);
//                if(CommonMethods.getStringFromDefaults(MainActivity.this, "user_id")!=null){
//                    //update by S.Rana
//                    if(PersistData.getBooleanData(con, AppConstant.isResultFragmentView)){
//                        setContentFragment(new ResultFragment(), true);
//                    }else{
//                        setContentFragment(new DiscoverElementFragment(), true);
//                    }
//
//                }else{
//                    loginRegister();
//                }

               // setContentFragment(new ActiveWorkHistoryFragment(), true);
            }
        });
        LinearLayout linearTellSomething = (LinearLayout)navigationView.findViewById(R.id.linearTellSomething);
        linearTellSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.e(getLocalClassName(), "Contatti");
                drawerLayout.closeDrawers();


//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                sendIntent.setType("plain/text");
//                //sendIntent.setData(Uri.parse("info@bekimik.com"));
//                //sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
//                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@bekimik.com"});
//                sendIntent.putExtra(Intent.EXTRA_SUBJECT, ""+getString(R.string.Contatti));
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "\n-KIMIK");
//                startActivity(sendIntent);
                tvToolBarTitle.setText("TELL US SOMETHING");
                setContentFragment(new TellSomeThingFragment(), true);
            }
        });
//
        LinearLayout linearReferral = (LinearLayout)navigationView.findViewById(R.id.linearReferral);
        linearReferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                tvToolBarTitle.setText("Referral");
                setContentFragment(new NotificationFragement(), true);
//                Bundle bundle = new Bundle();
//                bundle.putString("url", "https://www.facebook.com/profile.php?id=100009571399553&fref=ts");
//                SeguiSuFragment fragment = new SeguiSuFragment();
//                fragment.setArguments(bundle);
//                setContentFragment(fragment, true);
            }
        });

        LinearLayout linearNotification = (LinearLayout)navigationView.findViewById(R.id.linearNotification);
        linearNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                tvToolBarTitle.setText("Notification");
                setContentFragment(new NotificationFragement(), true);

//                Bundle bundle = new Bundle();
//                bundle.putString("url", "https://twitter.com/bekimik");
//                SeguiSuFragment1 fragment = new SeguiSuFragment1();
//                fragment.setArguments(bundle);
//                setContentFragment(fragment, true);

            }
        });

        LinearLayout linearTermsUse = (LinearLayout) navigationView.findViewById(R.id.linearTermsUse);
        linearTermsUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawers();
                tvToolBarTitle.setText("Term of use");
                setContentFragment(new NotificationFragement(), true);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("url", "https://plus.google.com/114820358422245979157/about?hl=it");
//                SeguiSuFragment2 fragment = new SeguiSuFragment2();
//                fragment.setArguments(bundle);
//                setContentFragment(fragment, true);

                //setContentFragment(new ResultFragment(), true);

            }
        });

        LinearLayout linearPrivacyPolicy = (LinearLayout) navigationView.findViewById(R.id.linearPrivacyPolicy);
        linearPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawers();
                tvToolBarTitle.setText("Privacy Policy");
                setContentFragment(new NotificationFragement(), true);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("url", "https://plus.google.com/114820358422245979157/about?hl=it");
//                SeguiSuFragment2 fragment = new SeguiSuFragment2();
//                fragment.setArguments(bundle);
//                setContentFragment(fragment, true);

                //setContentFragment(new ResultFragment(), true);

            }
        });

        //logout
        LinearLayout linearLogOut = (LinearLayout)navigationView.findViewById(R.id.linearLogOut);
        if(CommonMethods.getStringFromDefaults(MainActivity.this, "user_id")!=null){
           // linearLogOut.setVisibility(View.VISIBLE);;
        }else{
           // linearLogOut.setVisibility(View.GONE);
        }
        linearLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                LoginManager.getInstance().logOut();
//
//                Log.e(getLocalClassName(), "Logout");
//                drawerLayout.closeDrawers();
//                CommonMethods.writeToDefaults(MainActivity.this, "user_id", null);
//                setContentFragment(new ActiveWorkHistoryFragment(), true);
//                imageViewProfile.setVisibility(View.GONE);
//                textViewLogin.setVisibility(View.VISIBLE);
//                v.setVisibility(View.GONE);
//                //update by S.Rana
//                PersistData.setBooleanData(con,AppConstant.isResultFragmentView,false);
//                PersistData.setIntDataPos(con, AppConstant.questionPos, 0);
//                PersistData.setIntData(con, AppConstant.questionStep, 1);
//                PersistData.setIntData(con, AppConstant.questionNo, 1);
//                //v.setVisibility(View.GONE);


            }
        });




        //action bar item
        imageViewProfile = (ImageView)toolbar.findViewById(R.id.imageViewProfile);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(getLocalClassName(), "Profile");
                //v.setVisibility(View.GONE);
            }
        });

//        textViewLogin = (TextView)toolbar.findViewById(R.id.textViewLogin);
//        textViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(getLocalClassName(), "Login");
//                //v.setVisibility(View.GONE);
//                loginRegister();
//            }
//        });

        //if user already logged in then only profile image visible
        if(CommonMethods.getStringFromDefaults(MainActivity.this,"user_id")!=null){
            imageViewProfile.setVisibility(View.VISIBLE);
           // textViewLogin.setVisibility(View.GONE);
        }else{
            imageViewProfile.setVisibility(View.GONE);
           // textViewLogin.setVisibility(View.VISIBLE);
        }


        /*drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawerLayout, toolbar);

        textViewAccedi = (TextView)findViewById(R.id.textViewAccedi);
        imageViewProfile = (ImageView)findViewById(R.id.imageViewProfile);
        imageViewProfile.setVisibility(View.GONE);*/

        /*log_out=(TextView)findViewById(R.id.log_out);
        profileLayout=(LinearLayout)findViewById(R.id.profileLayout);
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PersistData.getBooleanData(con, AppConstant.isResultView)) {
                    setContentFragment(new ResultFragment(), false);
                } else {
                    setContentFragment(new QuestionFragment(), false);
                }

            }
        });*/


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        setContentFragment(new ActiveWorkHistoryFragment(), false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("Main activity result","Call");
    }


    @Override
    public void setContentFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment == null) {
            return;
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);

        //show only if current fragment is not same as given fragment
        /*if(currentFragment != null && fragment.getClass().equals("SeguiSuFragment")){

        }else{
            return;
        }*/
        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

        /*if (fragment == null) {
            finish();
            //Log.e(tag, "Content fragment cannot be null");
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.content_frame, fragment, ((Object) fragment).getClass().getName());

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(((Object) fragment).getClass().getName());
        }

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();*/
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }

        /*final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);
        Log.d("", "Current fragment before pop: ");
        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        }
        else {
            fragmentManager.popBackStack();
        }*/
    }

    //login/register
    public void loginRegister(){
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_dialoge);
        dialog.show();

        final EditText editTextEmail = (EditText)dialog.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = (EditText)dialog.findViewById(R.id.editTextPassword);
        final EditText editTextRegisterEmail = (EditText)dialog.findViewById(R.id.editTextRegisterEmail);


        editTextPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    dialog.dismiss();
                    /*if (TextUtils.isEmpty(registerEmail.getText().toString())) {
                        Toast.makeText(con, getString(R.string.email_missing), Toast.LENGTH_SHORT).show();
                    } else {
                        requestAccount(AllURL.getRegisterUrl(registerEmail.getText().toString()));
                    }*/
                    return true;
                }
                return false;
            }});

        editTextRegisterEmail.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    dialog.dismiss();

                    String email = editTextRegisterEmail.getText().toString();
                    registration(email);

                    return true;
                }
                return false;
            }});


        ImageView imageViewCancel = (ImageView)dialog.findViewById(R.id.imageViewCancel);
        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView textViewFacebook = (TextView)dialog.findViewById(R.id.textViewFacebook);
        textViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email","public_profile", "user_friends"));

            }
        });

        //registration
        TextView textViewOk = (TextView)dialog.findViewById(R.id.textViewOk);
        textViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextRegisterEmail.getText().toString();
                registration(email);
            }
        });

        TextView textViewAccedi = (TextView)dialog.findViewById(R.id.textViewAccedi);
        textViewAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if(email.equals("")){
                    Toast.makeText(MainActivity.this,getString(R.string.Email_required), Toast.LENGTH_LONG).show();
                }
                else if(password.equals("")){
                    Toast.makeText(MainActivity.this,getString(R.string.Password_required), Toast.LENGTH_LONG).show();
                }else{

                    final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this, true, false);
                    loadingDialog.show();

                    Vector<KeyValue> params = new Vector<>();
                    params.addElement(new KeyValue("Email", email));
                    params.addElement(new KeyValue("MD5password", CommonMethods.md5(password)));
                    String url =  CommonMethods.getBackendServiceUrl("LoginUtente",params);

                    Log.e(getLocalClassName(), "" + url);

                    if(url!=null) {
                        Ion.with(MainActivity.this)
                                .load(url)
                                .as(new TypeToken<User>() {
                                })
                                .setCallback(new FutureCallback<User>() {
                                    @Override
                                    public void onCompleted(Exception e, User result) {
                                        // do stuff with the result or error
                                        Log.e(getLocalClassName(), "" + result);
                                        loadingDialog.dismiss();

                                        if(result!=null){
                                            if(result.getIDUtente()!=null){
                                                CommonMethods.writeToDefaults(MainActivity.this,"user_id",""+result.getIDUtente());

                                                imageViewProfile.setVisibility(View.VISIBLE);
                                                textViewLogin.setVisibility(View.GONE);
                                                navigationView.findViewById(R.id.textViewLogout).setVisibility(View.VISIBLE);
                                                dialog.dismiss();
                                            }else{
                                                Toast.makeText(MainActivity.this,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(MainActivity.this,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }

                }





            }
        });

        TextView textViewForgetPassword = (TextView)dialog.findViewById(R.id.textViewForgetPassword);
        textViewForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassword();
            }
        });

    }

    //forget password
    public void forgetPassword(){
        dialogForgetPassword = new Dialog(MainActivity.this);
        dialogForgetPassword.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogForgetPassword.setContentView(R.layout.forget_password_dialoge);
        dialogForgetPassword.show();

        ImageView imageViewCancel = (ImageView)dialogForgetPassword.findViewById(R.id.imageViewCancel);
        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForgetPassword.dismiss();
            }
        });

        final EditText editTextEmail = (EditText)dialogForgetPassword.findViewById(R.id.editTextEmail);

        TextView textViewInvia = (TextView)dialogForgetPassword.findViewById(R.id.textViewInvia);
        textViewInvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextEmail.setText("oneenam@yahoo.com");

                String email = editTextEmail.getText().toString();

                if(email.equals("")){
                    Toast.makeText(MainActivity.this,getString(R.string.Email_required), Toast.LENGTH_LONG).show();
                }else{
                    final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this, true, false);
                    loadingDialog.show();

                    Vector<KeyValue> params = new Vector<>();
                    params.addElement(new KeyValue("Email", email));
                    String url =  CommonMethods.getBackendServiceUrl("RecuperaPassword",params);

                    Log.e(getLocalClassName(), "" + url);

                    if(url!=null) {
                        Ion.with(MainActivity.this)
                                .load(url)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        // do stuff with the result or error
                                        Log.e(getLocalClassName(), "" + result);
                                        loadingDialog.dismiss();
                                        dialogForgetPassword.dismiss();

                                    }
                                });
                    }
                }

            }
        });

    }


    //registration
    public void registration(String email){
        if(email!=null){

            final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this, true, false);
            loadingDialog.show();

            Vector<KeyValue> params = new Vector<>();
            params.addElement(new KeyValue("campi", "email"));
            params.addElement(new KeyValue("valori", email));
            String url =  CommonMethods.getBackendServiceUrl("RegistraUtente",params);

            Log.e(getLocalClassName(), "" + url);

            if(url!=null) {
                Ion.with(MainActivity.this)
                        .load(url)
                        .as(new TypeToken<User>() {
                        })
                        .setCallback(new FutureCallback<User>() {
                            @Override
                            public void onCompleted(Exception e, User result) {
                                // do stuff with the result or error
                                //Log.e(getLocalClassName(), "" + result);
                                loadingDialog.dismiss();

                                if(result!=null){
                                    if(result.getIDUtente()!=null){
                                        CommonMethods.writeToDefaults(MainActivity.this,"user_id",""+result.getIDUtente());

                                        imageViewProfile.setVisibility(View.VISIBLE);
                                        textViewLogin.setVisibility(View.GONE);
                                        navigationView.findViewById(R.id.textViewLogout).setVisibility(View.VISIBLE);
                                        dialog.dismiss();

                                    }else{
                                        Toast.makeText(MainActivity.this,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(MainActivity.this,getString(R.string.Something_wrong), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }

        }else{
            Toast.makeText(MainActivity.this,getString(R.string.Email_required), Toast.LENGTH_LONG).show();
        }
    }

    public void exitFromApp() {
        final CharSequence[] items = { "NO", "YES" };
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit from app?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        return;
                    case 1:
                        // onStopRecording();

                        LoginActivity.getInstance().finish();
                        finish();


                        break;
                    default:
                        return;
                }
            }
        });
        builder.show();
        builder.create();
    }


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitFromApp();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


}
