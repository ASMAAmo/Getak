package getak.app.com.getak.Activites;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.andexert.library.RippleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Fragments.FragmentFavPlaces;
import getak.app.com.getak.Fragments.FragmentHome;
import getak.app.com.getak.Fragments.FragmentMessages;
import getak.app.com.getak.Fragments.FragmentMyTrips;
import getak.app.com.getak.Fragments.FragmentSettings;
import getak.app.com.getak.Fragments.FragmentSupport;
import getak.app.com.getak.Fragments.Fragment_account;
import getak.app.com.getak.R;

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

    //Pages
    public static final int HOME = 0;
    public static final int MY_ACCOUNT = 1;
    public static final int MY_TRIPS = 2;
    public static final int MY_FAV_PLACES = 3;
    public static final int MESSAGES = 4;
    public static final int SUPPORT = 5;
    public static final int SETTINGS = 6;
    public static int pageIndex=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpSideMenuDrawer(toolbar);
        relMyAccountBtn.setOnClickListener(this);
        relMyTripsBtn.setOnClickListener(this);
        relMyMessagesBtn.setOnClickListener(this);
        relSupportBtn.setOnClickListener(this);
        relSettingsBtn.setOnClickListener(this);
        relFavPlacesBtn.setOnClickListener(this);
        relHometBtn.setOnClickListener(this);
        switchToPage(HOME,null,getResources().getString(R.string.home));

    }





    @SuppressLint("RestrictedApi")
    private void setUpSideMenuDrawer(Toolbar toolbar) {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
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
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_my_account : {
                switchToPage(HOME,null,getResources().getString(R.string.my_account));
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_fav_places : {
                switchToPage(MY_TRIPS,null,getResources().getString(R.string.favorite_places));
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_my_trips : {
                switchToPage(MY_TRIPS,null,getResources().getString(R.string.my_trips));
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_messages : {
                switchToPage(MESSAGES,null,getResources().getString(R.string.messages));
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_support : {
                switchToPage(SUPPORT,null,getResources().getString(R.string.support));
                drawer.closeDrawers();
                break;
            }
            case R.id.rel_settings : {
                switchToPage(SETTINGS,null,getResources().getString(R.string.settings));
                drawer.closeDrawers();
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
                Fragment_account fragmentAccount =new Fragment_account();
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
        }
    }


}
