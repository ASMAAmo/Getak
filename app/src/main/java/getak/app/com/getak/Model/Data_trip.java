package getak.app.com.getak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_trip {
    @SerializedName("trips")
    @Expose
    private Trips trips;

    public Trips getTrips() {
        return trips;
    }

    public void setTrips(Trips trips) {
        this.trips = trips;
    }
}
