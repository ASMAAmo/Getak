package getak.app.com.getak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_trip {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("client_id")
    @Expose
    private Integer clientId;
    @SerializedName("driver_id")
    @Expose
    private Integer driverId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("destination_time")
    @Expose
    private Object destinationTime;
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
    private Object travelerNumber;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("driver_name")
    @Expose
    private String driverName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Object getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(Object destinationTime) {
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

    public Object getTravelerNumber() {
        return travelerNumber;
    }

    public void setTravelerNumber(Object travelerNumber) {
        this.travelerNumber = travelerNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
