package getak.app.com.getak.Fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Activites.IntroActivity;
import getak.app.com.getak.Dialogs.CustomPlacePicker;
import getak.app.com.getak.Dialogs.DriverButtomDialog;
import getak.app.com.getak.GpsUtils.GPSTracker;
import getak.app.com.getak.Model.Requests.FastTripRequest;
import getak.app.com.getak.Model.Responses.FastTripResponse.FastTripResModel;
import getak.app.com.getak.Model.Trips;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.Presenters.TripsRequestsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.TripsRequestsView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FragmentHome extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, CustomPlacePicker.PlacePickerInteraction, TripsRequestsView {

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        ButterKnife.bind(this,v);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
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
        userTypeConfig(getContext());
        gpsTracker = new GPSTracker(getContext());
        if(customPlacePicker==null) {
            try {
                customPlacePicker = new CustomPlacePicker(getActivity(), this);
            }catch (Exception e){}
        }
        dialog=new KProgressHUD(getContext());
        fastTripRequest=new FastTripRequest();



    }


    //Check user type ind perform configurations
    public void userTypeConfig(Context context) {
        if(SessionHelper.isDriver(getContext())){
            userMainLayout.setVisibility(View.GONE);
        }else {
            userMainLayout.setVisibility(View.VISIBLE);
        }
    }

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
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        buildGoogleapiClient();
        if(gpsTracker.canGetLocation()){
            LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(),
                    gpsTracker.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                    15);
            map.moveCamera(update);
        }
    }









    protected synchronized void buildGoogleapiClient() {
        mpiclients = new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).addApi(Places.GEO_DATA_API).build();
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

        Toast.makeText(getContext(),location.latitude+" "+location.longitude,Toast.LENGTH_LONG).show();
    }




    @Override
    public void onTripRequestSuccess(FastTripResModel fastTripResModel) {
        new DriverButtomDialog(getContext(),fastTripResModel).show();
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
    public void onDestroy() {
        super.onDestroy();
        customPlacePicker.removeMapFragment(getContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        customPlacePicker.removeMapFragment(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        customPlacePicker.removeMapFragment(getContext());
    }
}
