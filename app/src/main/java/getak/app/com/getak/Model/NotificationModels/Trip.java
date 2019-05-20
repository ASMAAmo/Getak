package getak.app.com.getak.Model.NotificationModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("start_place_lat")
    @Expose
    private String startPlaceLat;
    @SerializedName("driver_id")
    @Expose
    private Integer driverId;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("destination_time")
    @Expose
    private String destinationTime;
    @SerializedName("destination_place_lng")
    @Expose
    private String destinationPlaceLng;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("destination_place_lat")
    @Expose
    private String destinationPlaceLat;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("start_place_lng")
    @Expose
    private String startPlaceLng;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("traveler_number")
    @Expose
    private Object travelerNumber;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getStartPlaceLat() {
        return startPlaceLat;
    }

    public void setStartPlaceLat(String startPlaceLat) {
        this.startPlaceLat = startPlaceLat;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getDestinationPlaceLng() {
        return destinationPlaceLng;
    }

    public void setDestinationPlaceLng(String destinationPlaceLng) {
        this.destinationPlaceLng = destinationPlaceLng;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDestinationPlaceLat() {
        return destinationPlaceLat;
    }

    public void setDestinationPlaceLat(String destinationPlaceLat) {
        this.destinationPlaceLat = destinationPlaceLat;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartPlaceLng() {
        return startPlaceLng;
    }

    public void setStartPlaceLng(String startPlaceLng) {
        this.startPlaceLng = startPlaceLng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getTravelerNumber() {
        return travelerNumber;
    }

    public void setTravelerNumber(Object travelerNumber) {
        this.travelerNumber = travelerNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
