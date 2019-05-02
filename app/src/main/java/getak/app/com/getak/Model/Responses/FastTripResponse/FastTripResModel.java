package getak.app.com.getak.Model.Responses.FastTripResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FastTripResModel {
    @SerializedName("trip")
    @Expose
    private Trip trip;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
