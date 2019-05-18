package getak.app.com.getak.Dialogs;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.GpsUtils.GPSTracker;
import getak.app.com.getak.R;

import static android.content.Context.LOCATION_SERVICE;

public class CustomPlacePicker extends Dialog implements  OnMapReadyCallback {

    private GoogleMap googleMap;
    private LatLng currentLatLng;
    PlacePickerInteraction placePickerInteraction;
    GPSTracker gpsTracker;
    MapView mapView;
    public CustomPlacePicker(Context context, PlacePickerInteraction placePickerInteraction) {
        super(context);
        this.placePickerInteraction = placePickerInteraction;
        setContentView(R.layout.place_picker_layout);
        // Gets the MapView from the XML layout and creates it
        mapView = findViewById(R.id.mapview);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        gpsTracker=new GPSTracker(context);
        if(!gpsTracker.canGetLocation()){
            gpsTracker.showSettingsAlert();
        }
        ButterKnife.bind(this);

    }





    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.googleMap = googleMap;
        // Enabling MyLocation Layer of Google Map
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        // Getting the name of the best provider



            // Add a marker in Sydney and move the camera
            currentLatLng = new LatLng(gpsTracker.getLatitude(),
                    gpsTracker.getLongitude());
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                    15);
            this.googleMap.moveCamera(update);
            this.googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {
                    //get latlng at the center by calling
                    currentLatLng = googleMap.getCameraPosition().target;
                }
            });
        }



    @OnClick(R.id.pick_location_btn)
    void onConfirm(){
        String name=getLocationName(currentLatLng);
        placePickerInteraction.onPlaceSelected(currentLatLng,name);
        dismiss();
    }
    public interface PlacePickerInteraction{
        void onPlaceSelected (LatLng location,String locationName);
    }


    private String getLocationName(LatLng location) {
            final Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            String strAdd = "";
            try {
                List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
                if (addresses != null) {
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder("");

                    for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
                    }
                    strAdd = strReturnedAddress.toString();
                } else {
                   strAdd="لا يوجد أسم لهذا الموقع";
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("Current loction", "Canont get Address!");
            }
            return strAdd;
    }

}
