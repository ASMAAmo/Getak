package getak.app.com.getak.Views;

import java.util.List;

import getak.app.com.getak.Model.Datum_trip;
import getak.app.com.getak.Model.MyTrips;
import getak.app.com.getak.Model.Trips;

public interface MyTripsview {
    void onSuccess(Trips mytrips);
    void onFailed(String err);
    void getTrips();
}
