package getak.app.com.getak.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.andexert.library.RippleView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Activites.IntroActivity;
import getak.app.com.getak.Activites.PriceActivity;
import getak.app.com.getak.Dialogs.CustomPlacePicker;
import getak.app.com.getak.Events.AcceptTripEvent;
import getak.app.com.getak.Events.EndTripForCustomers;
import getak.app.com.getak.GpsUtils.GPSTracker;
import getak.app.com.getak.Model.NotificationModels.DriverNotification;
import getak.app.com.getak.Model.NotificationModels.Trip;
import getak.app.com.getak.Model.Requests.FastTripRequest;
import getak.app.com.getak.Model.Responses.CheckStatus.Status;
import getak.app.com.getak.Model.Responses.FastTripResponse.FastTripResModel;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.Presenters.IncomingTripsPresenter;
import getak.app.com.getak.Presenters.TripsRequestsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.AccountView;
import getak.app.com.getak.Views.ChangeDriverStatusView;
import getak.app.com.getak.Views.EndTripInteraction;
import getak.app.com.getak.Views.TripsRequestsView;

public class FragmentHome extends Fragment implements OnMapReadyCallback, EndTripInteraction,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, CustomPlacePicker.PlacePickerInteraction, TripsRequestsView, AccountView , ChangeDriverStatusView {

    View v;
    public static KProgressHUD dialog;
    private final static int TARGET_LOCATION=0;
    private final static int CURRENT_LOCATION=1;
    private static int selection=5;
    CustomPlacePicker customPlacePicker;
    public static GoogleMap map;
    GoogleApiClient mpiclients;
    Location mlastLocation;
    public FusedLocationProviderClient mFusedLocationProviderClient;
    LocationRequest mLocationrequest;
    GPSTracker gpsTracker;
    static FastTripRequest fastTripRequest;
    @BindView(R.id.current_location)
    TextView currentLocation;
    @BindView(R.id.target)
    TextView target;
    @BindView(R.id.user_main_layout)
    RelativeLayout userMainLayout;
    @BindView(R.id.set_active_btn)
    Button setActiveBtn;
    @BindView(R.id.set_dis_active_btn)
    Button setDisActiveBtn;
    @BindView(R.id.driver_layout)
    FrameLayout driverLayout;
    @BindView(R.id.end_trip_btn)
    Button endTripBtn;
    @BindView(R.id.customer_info_dialog_layout)
    RelativeLayout customer_info_dialog;
    @BindView(R.id.customer_name_tv)
    TextView customerName;
    @BindView(R.id.customer_pic)
    RoundedImageView customerPic;
    @BindView(R.id.call_customer_btn)
    RippleView callCustomer;
    @BindView(R.id.driver_name_tv)
    TextView driverName;
    @BindView(R.id.car_model)
    TextView carModel;
    @BindView(R.id.driver_rate_bar)
    RatingBar driverRate;
    @BindView(R.id.driver_pic)
    RoundedImageView driver_pic;
    @BindView(R.id.call_driver_btn)
    RippleView callDriver;
    @BindView(R.id.driver_info_layout_dialog)
    RelativeLayout driver_info_dialog;
    @BindView(R.id.map)
    MapView mapView;
    @Override
    public void onStart() {
        super.onStart();
        userTypeConfig(getContext());
        if(SessionHelper.isLogin(getContext())&&SessionHelper.isDriver(getContext())){
          checkDriverStatus();
        }else {
            endTripBtn.setVisibility(View.GONE);
        }

        //Check status for client dialog
        checkCustomerStatus();
    }

    private void checkCustomerStatus() {
        if(SessionHelper.isLogin(getContext())&&!SessionHelper.isDriver(getContext())){
            if(SessionHelper.getUserTripInfo(getContext())!=null) {
                setUpDriverDialog(getContext(), SessionHelper.getUserTripInfo(getContext()));
                driver_info_dialog.setVisibility(View.VISIBLE);
                userMainLayout.setVisibility(View.GONE);
            }else {
                driver_info_dialog.setVisibility(View.GONE);
                userMainLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setUpDriverDialog(final Context context, final getak.app.com.getak.Model.Responses.FastTripResponse.Trip trip) {
        driverName.setText(trip.getDriver().getDriverName()+"");
        carModel.setText("موديل السيارة : "+trip.getDriver().getCarType());
        Picasso.get().load("https://"+trip.getDriver().getDriverAvatar()).fit().into(driver_pic);
        callDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + trip.getDriver().getDriverPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity)context), new String[]{Manifest.permission.CALL_PHONE},003);
                }else {
                    context.startActivity(callIntent);
                }
            }
        });
    }


    private void checkDriverStatus() {
            if (!SessionHelper.getNotificationsPayload(getContext()).isEmpty()) {
                driverLayout.setVisibility(View.GONE);
                endTripBtn.setVisibility(View.VISIBLE);
                setUpCustomerInfoDialg(getContext());
                customer_info_dialog.setVisibility(View.VISIBLE);
            } else {
                driverLayout.setVisibility(View.VISIBLE);
                endTripBtn.setVisibility(View.GONE);
                customer_info_dialog.setVisibility(View.GONE);
            }

        HashMap request =new HashMap();
        request.clear();
        request.put("user_id",SessionHelper.getUserSession(getContext()).getId());
        request.put("type",SessionHelper.getUserType(getContext()));
        AccountPresenter.checkDriverStatus(getContext(),request,this);
    }





    private void setUpCustomerInfoDialg(final Context context) {
        Gson gson =new Gson();
        final DriverNotification driverNotification = gson.fromJson(SessionHelper.getNotificationsPayload(context),DriverNotification.class);
        customerName.setText(driverNotification.getClient().getClientName()+"");
        Picasso.get().load("https://"+driverNotification.getClient().getClientAvatar()).fit().into(customerPic);
        callCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + driverNotification.getClient().getClientPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity)context), new String[]{Manifest.permission.CALL_PHONE},003);
                }else {
                    context.startActivity(callIntent);
                }
            }
        });
    }

    private void changeDriverStatus(int i) {
        HashMap request=new HashMap();
        request.put("driver_id",SessionHelper.getUserSession(getContext()).getId());
        request.put("lat",gpsTracker.getLatitude());
        request.put("long",gpsTracker.getLongitude());
        request.put("status",i+"");
        AccountPresenter.changeDriverStatus(getContext(),request,this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(getActivity());
        EventBus.getDefault().register(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        ButterKnife.bind(this,v);

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        if(customPlacePicker==null) {
            try {
                customPlacePicker = new CustomPlacePicker(v.getContext(), this);
            }catch (Exception e){
                Log.e("ERR",e.getMessage());
            }
        }

        try {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gpsTracker = new GPSTracker(getContext());
        dialog=new KProgressHUD(getContext());
        fastTripRequest=new FastTripRequest();



    }


    //Check user type ind perform configurations
    public void userTypeConfig(Context context) {
        if(!SessionHelper.isLogin(context)){
            userMainLayout.setVisibility(View.VISIBLE);
            driverLayout.setVisibility(View.GONE);
        }

        if(SessionHelper.isDriver(context)){
            userMainLayout.setVisibility(View.GONE);
            driverLayout.setVisibility(View.VISIBLE);
        }else {
            userMainLayout.setVisibility(View.VISIBLE);
            driverLayout.setVisibility(View.GONE);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationrequest = new LocationRequest();
        mLocationrequest.setInterval(60000);
        mLocationrequest.setFastestInterval(60000);
        mLocationrequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mpiclients, mLocationrequest, FragmentHome.this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Location Error : ",connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location location) {
        mlastLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        buildGoogleapiClient();
    }



    protected synchronized void buildGoogleapiClient() {
        mpiclients = new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        mpiclients.connect();
    }




    @OnClick(R.id.fast_order_btn)
    void onClick(){
        if(!SessionHelper.isLogin(getContext())){
            startActivity(new Intent(getContext(), IntroActivity.class));
        }else {
            makeFastOrder();
        }
    }


    public void makeFastOrder(){
        target.setError(null);
        currentLocation.setError(null);
        String targetTv = target.getText().toString();
        String currentLoc = currentLocation.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(targetTv)) {
            target.setError("حدد مكان الذهاب !");
            focusView = target;
            cancel = true;
        }
        if (TextUtils.isEmpty(currentLoc)) {
            currentLocation.setError("حدد مكانك الحالي !");
            focusView = currentLocation;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt registration and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            fastTripRequest.setClient_id(SessionHelper.getUserSession(getContext()).getId()+"");
            TripsRequestsPresenter.requstFastTrip(getContext(),fastTripRequest,this);
        }
    }

    @OnClick(R.id.target)
    void onGetTarget(){
        fireLocationPicker(TARGET_LOCATION);
    }

    @OnClick(R.id.current_location)
    void onGetCurrent(){
        fireLocationPicker(CURRENT_LOCATION);
    }


    void fireLocationPicker(int requestType){
     if(customPlacePicker!=null){
         customPlacePicker.show();
         selection=requestType;
     }
    }

    @Override
    public void onPlaceSelected(LatLng location, String locationName) {
        switch (selection){
            case TARGET_LOCATION : {
                target.setText(locationName);
                fastTripRequest.setDestination_lat(location.latitude);
                fastTripRequest.setDestination_long(location.longitude);
                break;
            }
            case CURRENT_LOCATION : {
                currentLocation.setText(locationName);
                fastTripRequest.setCurrentLat(location.latitude);
                fastTripRequest.setCurrentLong(location.longitude);
                break;
            }
        }

        //Toast.makeText(getContext(),location.latitude+" "+location.longitude,Toast.LENGTH_LONG).show();
    }




    @Override
    public void onTripRequestSuccess(FastTripResModel fastTripResModel) {
        setUpDriverDialog(getContext(),fastTripResModel.getTrip());
        driver_info_dialog.setVisibility(View.VISIBLE);
        userMainLayout.setVisibility(View.GONE);
        SessionHelper.setUserTripInfo(getContext(),fastTripResModel.getTrip());
    }

    @Override
    public void onTripRequestFailure(String err) {
        Snackbar.make(getActivity().findViewById(android.R.id.content),err, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void progress(boolean status) {
        if(status){
            dialog.show();
        }else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }




    @Override
    public void onSuccess(Object obj) {
       switch (((Status)obj).getAvailable()){
           case 1 : {
               setDisActiveBtn.setVisibility(View.GONE);
               setActiveBtn.setVisibility(View.VISIBLE);
               break;
           }
           case 0 : {
               setDisActiveBtn.setVisibility(View.VISIBLE);
               setActiveBtn.setVisibility(View.GONE);
               break;
           }
       }
    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(getContext(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loading(boolean status) {

    }

    //Set Driver Active
    @OnClick(R.id.set_active_btn)
    void active(){
       changeDriverStatus(0);
    }



    //Set Driver Dis active
    @OnClick(R.id.set_dis_active_btn)
    void disActive(){
        changeDriverStatus(1);
    }





    @Override
    public void onStatusChanged(Object object) {
        checkDriverStatus();
        Toast.makeText(getContext(), ((Result<Object>)object).getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChangedError(String err) {
        Toast.makeText(getContext(), err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changingProgress(boolean status) {
        if(status){
            dialog.show();
        }else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AcceptTripEvent event) {
       driverLayout.setVisibility(View.GONE);
       endTripBtn.setVisibility(View.VISIBLE);
       Gson gson =new Gson();
       SessionHelper.setNotificationPayload(getContext(),gson.toJson(event.getDriverNotification()));
        setUpCustomerInfoDialg(getContext());
        customer_info_dialog.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EndTripForCustomers event) {
        checkCustomerStatus();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.end_trip_btn)
    void end(){
        Gson gson =new Gson();
        DriverNotification driverNotification = gson.fromJson(SessionHelper.getNotificationsPayload(getContext()),DriverNotification.class);
        HashMap request =new HashMap();
        request.put("trip_id",driverNotification.getTrip().getId());
        request.put("driver_id",SessionHelper.getUserSession(getContext()).getId());
        IncomingTripsPresenter.endTrip(getContext(),request,this);
    }

    @Override
    public void onTripEnded(Trip trip) {
        Gson gson =new Gson();
        DriverNotification driverNotification = gson.fromJson(SessionHelper.getNotificationsPayload(getContext()),DriverNotification.class);
        startActivity(new Intent(getActivity(), PriceActivity.class).putExtra("price",driverNotification.getTrip().getPrice()+"").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        SessionHelper.setNotificationPayload(getContext(),"");
        checkDriverStatus();
    }

    @Override
    public void onError(String err) {
        Toast.makeText(getContext(), err, Toast.LENGTH_SHORT).show();
    }

}
