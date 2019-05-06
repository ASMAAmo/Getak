package getak.app.com.getak.Model.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Driver {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_avatar")
    @Expose
    private String driverAvatar;
    @SerializedName("driver_gender")
    @Expose
    private String driverGender;
    @SerializedName("driver_age")
    @Expose
    private Integer driverAge;
    @SerializedName("driver_email")
    @Expose
    private String driverEmail;
    @SerializedName("driver_phone")
    @Expose
    private String driverPhone;
    @SerializedName("driver_address")
    @Expose
    private String driverAddress;
    @SerializedName("driver_personal_id")
    @Expose
    private String driverPersonalId;
    @SerializedName("driver_personal_id_pic")
    @Expose
    private String driverPersonalIdPic;
    @SerializedName("driver_licence_id_pic")
    @Expose
    private String driverLicenceIdPic;
    @SerializedName("license_type")
    @Expose
    private String licenseType;
    @SerializedName("license_expire_date")
    @Expose
    private String licenseExpireDate;
    @SerializedName("car_type")
    @Expose
    private String carType;
    @SerializedName("car_pic")
    @Expose
    private String carPic;
    @SerializedName("car_model")
    @Expose
    private String carModel;
    @SerializedName("bank_account")
    @Expose
    private String bankAccount;
    @SerializedName("car_color")
    @Expose
    private String carColor;
    @SerializedName("trip_numbers_per_day")
    @Expose
    private Integer tripNumbersPerDay;
    @SerializedName("work_days")
    @Expose
    private WorkDays workDays;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("token")
    @Expose
    private Object token;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("notifications")
    @Expose
    private Integer notifications;
    @SerializedName("star")
    @Expose
    private Integer star;
    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("rating")
    @Expose
    private Integer rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAvatar() {
        return driverAvatar;
    }

    public void setDriverAvatar(String driverAvatar) {
        this.driverAvatar = driverAvatar;
    }

    public String getDriverGender() {
        return driverGender;
    }

    public void setDriverGender(String driverGender) {
        this.driverGender = driverGender;
    }

    public Integer getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(Integer driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverPersonalId() {
        return driverPersonalId;
    }

    public void setDriverPersonalId(String driverPersonalId) {
        this.driverPersonalId = driverPersonalId;
    }

    public String getDriverPersonalIdPic() {
        return driverPersonalIdPic;
    }

    public void setDriverPersonalIdPic(String driverPersonalIdPic) {
        this.driverPersonalIdPic = driverPersonalIdPic;
    }

    public String getDriverLicenceIdPic() {
        return driverLicenceIdPic;
    }

    public void setDriverLicenceIdPic(String driverLicenceIdPic) {
        this.driverLicenceIdPic = driverLicenceIdPic;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseExpireDate() {
        return licenseExpireDate;
    }

    public void setLicenseExpireDate(String licenseExpireDate) {
        this.licenseExpireDate = licenseExpireDate;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarPic() {
        return carPic;
    }

    public void setCarPic(String carPic) {
        this.carPic = carPic;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getTripNumbersPerDay() {
        return tripNumbersPerDay;
    }

    public void setTripNumbersPerDay(Integer tripNumbersPerDay) {
        this.tripNumbersPerDay = tripNumbersPerDay;
    }

    public WorkDays getWorkDays() {
        return workDays;
    }

    public void setWorkDays(WorkDays workDays) {
        this.workDays = workDays;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getNotifications() {
        return notifications;
    }

    public void setNotifications(Integer notifications) {
        this.notifications = notifications;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
