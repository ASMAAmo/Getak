package getak.app.com.getak.Model.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FastTripRequest {
    @SerializedName("long")
    @Expose
    Double currentLong;
    @SerializedName("lat")
    @Expose
    Double currentLat;
    @SerializedName("client_id")
    @Expose
    String client_id;
    @SerializedName("destination_time")
    @Expose
    String destination_time;
    @SerializedName("destination_lat")
    @Expose
    Double destination_lat;
    @SerializedName("destination_long")
    @Expose
    Double destination_long;
    @SerializedName("traveler_number")
    @Expose
    Double traveler_number;

    public Double getCurrentLong() {
        return currentLong;
    }

    public void setCurrentLong(Double currentLong) {
        this.currentLong = currentLong;
    }

    public Double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(Double currentLat) {
        this.currentLat = currentLat;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDestination_time() {
        return destination_time;
    }

    public void setDestination_time(String destination_time) {
        this.destination_time = destination_time;
    }

    public Double getDestination_lat() {
        return destination_lat;
    }

    public void setDestination_lat(Double destination_lat) {
        this.destination_lat = destination_lat;
    }

    public Double getDestination_long() {
        return destination_long;
    }

    public void setDestination_long(Double destination_long) {
        this.destination_long = destination_long;
    }

    public Double getTraveler_number() {
        return traveler_number;
    }

    public void setTraveler_number(Double traveler_number) {
        this.traveler_number = traveler_number;
    }
}
