package getak.app.com.getak.Model.Responses.FastTripResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {
    @SerializedName("driver_id")
    @Expose
    private Integer driverId;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("destination_time")
    @Expose
    private String destinationTime;
    @SerializedName("start_place_lat")
    @Expose
    private String startPlaceLat;
    @SerializedName("start_place_lng")
    @Expose
    private String startPlaceLng;
    @SerializedName("destination_place_lat")
    @Expose
    private String destinationPlaceLat;
    @SerializedName("destination_place_lng")
    @Expose
    private String destinationPlaceLng;
    @SerializedName("traveler_number")
    @Expose
    private String travelerNumber;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("distance")
    @Expose
    private float distance;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("driver")
    @Expose
    private Driver driver;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getStartPlaceLat() {
        return startPlaceLat;
    }

    public void setStartPlaceLat(String startPlaceLat) {
        this.startPlaceLat = startPlaceLat;
    }

    public String getStartPlaceLng() {
        return startPlaceLng;
    }

    public void setStartPlaceLng(String startPlaceLng) {
        this.startPlaceLng = startPlaceLng;
    }

    public String getDestinationPlaceLat() {
        return destinationPlaceLat;
    }

    public void setDestinationPlaceLat(String destinationPlaceLat) {
        this.destinationPlaceLat = destinationPlaceLat;
    }

    public String getDestinationPlaceLng() {
        return destinationPlaceLng;
    }

    public void setDestinationPlaceLng(String destinationPlaceLng) {
        this.destinationPlaceLng = destinationPlaceLng;
    }

    public String getTravelerNumber() {
        return travelerNumber;
    }

    public void setTravelerNumber(String travelerNumber) {
        this.travelerNumber = travelerNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
