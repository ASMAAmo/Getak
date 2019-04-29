package getak.app.com.getak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contract {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("from_place_lat")
    @Expose
    private String fromPlaceLat;
    @SerializedName("from_place_long")
    @Expose
    private String fromPlaceLong;
    @SerializedName("to_place_long")
    @Expose
    private String toPlaceLong;
    @SerializedName("to_place_lat")
    @Expose
    private String toPlaceLat;
    @SerializedName("from_place_name")
    @Expose
    private String fromPlaceName;
    @SerializedName("to_place_name")
    @Expose
    private String toPlaceName;
    @SerializedName("go_time")
    @Expose
    private String goTime;
    @SerializedName("back_time")
    @Expose
    private String backTime;
    @SerializedName("days")
    @Expose
    private Days days;
    @SerializedName("passengers_number")
    @Expose
    private Integer passengersNumber;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("contracts_status")
    @Expose
    private Integer contractsStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("client_name")
    @Expose
    private Object clientName;
    @SerializedName("client_avatar")
    @Expose
    private Object clientAvatar;
    @SerializedName("driver_name")
    @Expose
    private Object driverName;
    @SerializedName("driver_avatar")
    @Expose
    private Object driverAvatar;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFromPlaceLat() {
        return fromPlaceLat;
    }

    public void setFromPlaceLat(String fromPlaceLat) {
        this.fromPlaceLat = fromPlaceLat;
    }

    public String getFromPlaceLong() {
        return fromPlaceLong;
    }

    public void setFromPlaceLong(String fromPlaceLong) {
        this.fromPlaceLong = fromPlaceLong;
    }

    public String getToPlaceLong() {
        return toPlaceLong;
    }

    public void setToPlaceLong(String toPlaceLong) {
        this.toPlaceLong = toPlaceLong;
    }

    public String getToPlaceLat() {
        return toPlaceLat;
    }

    public void setToPlaceLat(String toPlaceLat) {
        this.toPlaceLat = toPlaceLat;
    }

    public String getFromPlaceName() {
        return fromPlaceName;
    }

    public void setFromPlaceName(String fromPlaceName) {
        this.fromPlaceName = fromPlaceName;
    }

    public String getToPlaceName() {
        return toPlaceName;
    }

    public void setToPlaceName(String toPlaceName) {
        this.toPlaceName = toPlaceName;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public Integer getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(Integer passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getContractsStatus() {
        return contractsStatus;
    }

    public void setContractsStatus(Integer contractsStatus) {
        this.contractsStatus = contractsStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getClientName() {
        return clientName;
    }

    public void setClientName(Object clientName) {
        this.clientName = clientName;
    }

    public Object getClientAvatar() {
        return clientAvatar;
    }

    public void setClientAvatar(Object clientAvatar) {
        this.clientAvatar = clientAvatar;
    }

    public Object getDriverName() {
        return driverName;
    }

    public void setDriverName(Object driverName) {
        this.driverName = driverName;
    }

    public Object getDriverAvatar() {
        return driverAvatar;
    }

    public void setDriverAvatar(Object driverAvatar) {
        this.driverAvatar = driverAvatar;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
