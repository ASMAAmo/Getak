package getak.app.com.getak.Activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Dialogs.NewTripDialog;
import getak.app.com.getak.Events.AcceptTripEvent;
import getak.app.com.getak.Events.LoginEvent;
import getak.app.com.getak.Events.NotificationsEvent;
import getak.app.com.getak.Fragments.DriverAccount;
import getak.app.com.getak.Fragments.FragmentAccount;
import getak.app.com.getak.Fragments.FragmentContracts;
import getak.app.com.getak.Fragments.FragmentFavPlaces;
import getak.app.com.getak.Fragments.FragmentHome;
import getak.app.com.getak.Fragments.FragmentMessages;
import getak.app.com.getak.Fragments.FragmentMyTrips;
import getak.app.com.getak.Fragments.FragmentSettings;
import getak.app.com.getak.Fragments.FragmentSupport;
import getak.app.com.getak.Model.NotificationModels.Client;
import getak.app.com.getak.Model.NotificationModels.DriverNotification;
import getak.app.com.getak.Model.NotificationModels.Trip;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //Drawer int
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    @BindView(R.id.toolbar)
    //Toolbar int
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView titleTv;

    //Side menu
    @BindView(R.id.rel_home)
    RippleView relHometBtn;
    @BindView(R.id.rel_my_account)
    RippleView relMyAccountBtn;
    @BindView(R.id.rel_my_trips)
    RippleView relMyTripsBtn;
    @BindView(R.id.rel_fav_places)
    RippleView relFavPlacesBtn;
    @BindView(R.id.rel_messages)
    RippleView relMyMessagesBtn;
    @BindView(R.id.rel_support)
    RippleView relSupportBtn;
    @BindView(R.id.rel_settings)
    RippleView relSettingsBtn;
    @BindView(R.id.rel_contracts)
    RippleView relContracts;
    @BindView(R.id.rel_logout)
    RippleView relLogOut;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.user_name)
    TextView userName;

    public static NewTripDialog newTripDialog;
    MenuItem itemAddContract;

    //Pages
    public static final int HOME = 0;
    public static final int MY_ACCOUNT = 1;
    public static final int MY_TRIPS = 2;
    public static final int MY_FAV_PLACES = 3;
    public static final int MESSAGES = 4;
    public static final int SUPPORT = 5;
    public static final int SETTINGS = 6;
    public static final int CONTRACTS=7;
    public static final int DRIVER_ACCOUNT=8;
    public static int pageIndex=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpSideMenuDrawer(toolbar);
        if(SessionHelper.isLogin(this)){
            relLogOut.setVisibility(View.VISIBLE);
        }else {
            relLogOut.setVisibility(View.GONE);
        }
        relMyAccountBtn.setOnClickListener(this);
        relMyTripsBtn.setOnClickListener(this);
        relMyMessagesBtn.setOnClickListener(this);
        relSupportBtn.setOnClickListener(this);
        relSettingsBtn.setOnClickListener(this);
        relFavPlacesBtn.setOnClickListener(this);
        relHometBtn.setOnClickListener(this);
        relContracts.setOnClickListener(this);
        relLogOut.setOnClickListener(this);
        switchToPage(HOME,null,getString(R.string.getk));
        userTypeConfig(this);
        checkLoginStatus();
        handlEventMessage(this,getApplicationContext());
    }

    //

    private void checkLoginStatus() {
        if(SessionHelper.isLogin(this)) {
            if(SessionHelper.isDriver(this)){
                Picasso.get()
                        .load(SessionHelper.getUserSession(this).getDriverAvatar()+"")
                        .placeholder(R.drawable.ic_person_black_24dp)
                        .into(avatar);
                userName.setVisibility(View.VISIBLE);
                userName.setText(SessionHelper.getUserSession(this).getDriverName());
            }else {
                Picasso.get()
                        .load(SessionHelper.getUserSession(this).getClientAvatar())
                        .placeholder(R.drawable.ic_person_black_24dp)
                        .into(avatar);
                userName.setVisibility(View.VISIBLE);
                userName.setText(SessionHelper.getUserSession(this).getClientName());
            }
        }else {
            userName.setVisibility(View.GONE);
        }
    }

    //Check user type and perform configurations
    private void userTypeConfig(Context context) {
        if(SessionHelper.isDriver(context)){
            relFavPlacesBtn.setVisibility(View.GONE);
        }else {
            relFavPlacesBtn.setVisibility(View.VISIBLE);
        }
    }



    @Subscribe
    public void onEvent(LoginEvent event){
        if (event.isSucess()){
            relMyAccountBtn.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.VISIBLE);
            relLogOut.setVisibility(View.VISIBLE);
            if(SessionHelper.isDriver(this)){
                Picasso.get().load(SessionHelper.getUserSession(this).getDriverAvatar()+"")
                        .fit()
                        .into(avatar);
            }else {
                Picasso.get().load(SessionHelper.getUserSession(this).getClientAvatar())
                        .fit()
                        .into(avatar);
            }
            checkLoginStatus();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NotificationsEvent event) {
        try {
            JSONObject object = new JSONObject(event.getMessage());
            if(object.optString("trip")!=null&&object.optString("client")!=null) {
                String trip = object.optString("trip") ;
                String client=object.getString("client");
                Gson gson =new Gson();
                Trip tripModel= gson.fromJson(trip,Trip.class);
                Client clientModel =gson.fromJson(client,Client.class);
                DriverNotification driverNotification =new DriverNotification();
                driverNotification.setClient(clientModel);
                driverNotification.setTrip(tripModel);
                if(SessionHelper.isDriver(this)){
                  new NewTripDialog(MainActivity.this,driverNotification).show();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AcceptTripEvent event) {
        Gson gson =new Gson();
        SessionHelper.setNotificationPayload(this,gson.toJson(event.getDriverNotification()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        itemAddContract = menu.findItem(R.id.addcontract_action);
        itemAddContract.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addcontract_action){
           startActivity(new Intent(this,AddContractActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    private void setUpSideMenuDrawer(Toolbar toolbar) {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }




    //Action side menu Buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_home : {
                switchToPage(HOME,null,getResources().getString(R.string.getk));
                itemAddContract.setVisible(false);
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_my_account : {
                if(SessionHelper.isLogin(this)) {
                    if(SessionHelper.isDriver(this)){
                        switchToPage(DRIVER_ACCOUNT, null, getResources().getString(R.string.my_account));
                    }else {
                        switchToPage(MY_ACCOUNT, null, getResources().getString(R.string.my_account));
                    }
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,IntroActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_fav_places : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MY_FAV_PLACES,null,getResources().getString(R.string.favorite_places));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,IntroActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_my_trips : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MY_TRIPS,null,getResources().getString(R.string.my_trips));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,IntroActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_messages : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MESSAGES,null,getResources().getString(R.string.messages));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,IntroActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_support : {
                switchToPage(SUPPORT,null,getResources().getString(R.string.support));
                itemAddContract.setVisible(false);
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_settings : {
                switchToPage(SETTINGS,null,getResources().getString(R.string.settings));
                itemAddContract.setVisible(false);
                drawer.closeDrawers();
                break;
            }

            case R.id.rel_contracts : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(CONTRACTS,null,getResources().getString(R.string.contracts));
                    //Check if user is driver or clint
                    if(!SessionHelper.isDriver(this)) {
                        itemAddContract.setVisible(true);
                    }
                }else {
                    startActivity(new Intent(this,IntroActivity.class));
                }
                drawer.closeDrawers();
                break;
            }

            case R.id.rel_logout : {
                SessionHelper.logout(this, new SessionHelper.SessionCallBack() {
                    @Override
                    public void setOnLogout() {
                        restartApp(MainActivity.this);
                    }
                });
                break;
            }
        }
    }

    //Switch fragments
    public void switchToPage(int page, Bundle bundle, String name) {
        FragmentTransaction transaction = getTransaction();
        switch (page) {
            case HOME: {
                FragmentHome home =new FragmentHome();
                transaction.replace(R.id.main_container, home);
                pageIndex = HOME;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case MY_ACCOUNT: {
                FragmentAccount fragmentAccount =new FragmentAccount();
                transaction.replace(R.id.main_container, fragmentAccount);
                pageIndex = MY_ACCOUNT;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case MY_FAV_PLACES: {
                FragmentFavPlaces fragmentFavPlaces = new FragmentFavPlaces();
                transaction.replace(R.id.main_container, fragmentFavPlaces);
                pageIndex = MY_FAV_PLACES;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case MY_TRIPS: {
                FragmentMyTrips fragmentMyTrips =new FragmentMyTrips();
                transaction.replace(R.id.main_container, fragmentMyTrips);
                pageIndex = MY_TRIPS;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case MESSAGES: {
                FragmentMessages fragmentMessages =new FragmentMessages();
                transaction.replace(R.id.main_container, fragmentMessages);
                pageIndex = MESSAGES;
                transaction.commit();
                titleTv.setText(name);

                break;
            }

            case SUPPORT: {
                FragmentSupport fragmentSupport =new FragmentSupport();
                transaction.replace(R.id.main_container, fragmentSupport);
                pageIndex = SUPPORT;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case SETTINGS: {
                FragmentSettings fragmentSettings =new FragmentSettings();
                transaction.replace(R.id.main_container, fragmentSettings);
                pageIndex = SETTINGS;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case CONTRACTS : {
                FragmentContracts fragmentContracts =new FragmentContracts();
                transaction.replace(R.id.main_container, fragmentContracts);
                pageIndex = CONTRACTS;
                transaction.commit();
                titleTv.setText(name);
                break;
            }

            case DRIVER_ACCOUNT : {
                DriverAccount driverAccount =new DriverAccount();
                transaction.replace(R.id.main_container, driverAccount);
                pageIndex = DRIVER_ACCOUNT;
                transaction.commit();
                titleTv.setText(name);
                break;
            }
        }
    }



    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()==0) {
            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.app_name));
            builder.setMessage(getString(R.string.exit));
            builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
            return;
        }
    }


    public  void handlEventMessage(final Context context,Context activity){
        Bundle bundle = ((Activity)context).getIntent().getExtras();
        if (bundle != null) {
            String payload = bundle.getString("payload");
            try {
                JSONObject object = new JSONObject(payload);
                if(object.optString("trip")!=null&&object.optString("client")!=null) {
                    String trip = object.optString("trip") ;
                    String client=object.getString("client");
                    Gson gson =new Gson();
                    Trip tripModel= gson.fromJson(trip,Trip.class);
                    Client clientModel =gson.fromJson(client,Client.class);
                    final DriverNotification driverNotification =new DriverNotification();
                    driverNotification.setClient(clientModel);
                    driverNotification.setTrip(tripModel);
                    if(SessionHelper.isDriver(context)){
                        newTripDialog = new NewTripDialog(context,driverNotification);
                        newTripDialog.show();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
