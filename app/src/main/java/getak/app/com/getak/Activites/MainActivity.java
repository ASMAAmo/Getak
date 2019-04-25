package getak.app.com.getak.Activites;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
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
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Events.ContractCreationStepsEvent;
import getak.app.com.getak.Fragments.FragmentAccount;
import getak.app.com.getak.Fragments.FragmentContracts;
import getak.app.com.getak.Fragments.FragmentFavPlaces;
import getak.app.com.getak.Fragments.FragmentHome;
import getak.app.com.getak.Fragments.FragmentMessages;
import getak.app.com.getak.Fragments.FragmentMyTrips;
import getak.app.com.getak.Fragments.FragmentSettings;
import getak.app.com.getak.Fragments.FragmentSupport;
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
    public static int pageIndex=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        switchToPage(HOME,null,getResources().getString(R.string.home));
        EventBus.getDefault().register(this);
        if(SessionHelper.isLogin(this)) {
            Picasso.get()
                    .load("https://"+SessionHelper.getUserSession(this).getClientAvatar())
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .into(avatar);
        }

    }

    @Subscribe
    public void onEvent(ContractCreationStepsEvent event){
        if (event.isSucess()){
            relMyAccountBtn.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.VISIBLE);
            relLogOut.setVisibility(View.VISIBLE);
            Picasso.get().load(SessionHelper.getUserSession(this).getClientAvatar())
                    .fit()
                    .into(avatar);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
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
                switchToPage(HOME,null,getResources().getString(R.string.home));
                itemAddContract.setVisible(false);
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_my_account : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MY_ACCOUNT, null, getResources().getString(R.string.my_account));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,LoginActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_fav_places : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MY_TRIPS,null,getResources().getString(R.string.favorite_places));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,LoginActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_my_trips : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MY_TRIPS,null,getResources().getString(R.string.my_trips));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,LoginActivity.class));
                }
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_messages : {
                if(SessionHelper.isLogin(this)) {
                    switchToPage(MESSAGES,null,getResources().getString(R.string.messages));
                    itemAddContract.setVisible(false);
                }else {
                    startActivity(new Intent(this,LoginActivity.class));
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
                    if(true) {
                        itemAddContract.setVisible(true);
                    }
                }else {
                    startActivity(new Intent(this,LoginActivity.class));
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
}
