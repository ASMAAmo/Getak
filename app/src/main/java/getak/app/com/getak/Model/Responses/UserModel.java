package getak.app.com.getak.Model.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("client_avatar")
    @Expose
    private String clientAvatar;
    @SerializedName("client_gender")
    @Expose
    private String clientGender;
    @SerializedName("client_age")
    @Expose
    private Integer clientAge;
    @SerializedName("client_email")
    @Expose
    private String clientEmail;
    @SerializedName("client_phone")
    @Expose
    private String clientPhone;
    @SerializedName("client_address")
    @Expose
    private String clientAddress;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("notifications")
    @Expose
    private Integer notifications;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_email")
    @Expose
    private String driverEmail;
    @SerializedName("driver_phone")
    @Expose
    private String driverPhone;
    @SerializedName("driver_address")
    @Expose
    private String driverAddress;
    @SerializedName("driver_gender")
    @Expose
    private String driverGender;
    @SerializedName("driver_age")
    @Expose
    private String driverAge;
    @SerializedName("driver_personal_id")
    @Expose
    private String driverPersonalId;
    @SerializedName("license_type")
    @Expose
    private String licenseType;
    @SerializedName("license_expire_date")
    @Expose
    private String licenseExpireDate;
    @SerializedName("car_type")
    @Expose
    private String carType;
    @SerializedName("car_model")
    @Expose
    private String carModel;
    @SerializedName("bank_account")
    @Expose
    private Object bankAccount;
    @SerializedName("car_color")
    @Expose
    private String carColor;
    @SerializedName("trip_numbers_per_day")
    @Expose
    private String tripNumbersPerDay;
    @SerializedName("driver_avatar")
    @Expose
    private Object driverAvatar;
    @SerializedName("driver_personal_id_pic")
    @Expose
    private Object driverPersonalIdPic;
    @SerializedName("driver_licence_id_pic")
    @Expose
    private Object driverLicenceIdPic;
    @SerializedName("car_pic")
    @Expose
    private Object carPic;



    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientGender() {
        return clientGender;
    }

    public void setClientGender(String clientGender) {
        this.clientGender = clientGender;
    }

    public Integer getClientAge() {
        return clientAge;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getNotifications() {
        return notifications;
    }

    public void setNotifications(Integer notifications) {
        this.notifications = notifications;
    }

    public String getClientAvatar() {
        return clientAvatar;
    }

    public void setClientAvatar(String clientAvatar) {
        this.clientAvatar = clientAvatar;
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

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
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

    public String getDriverGender() {
        return driverGender;
    }

    public void setDriverGender(String driverGender) {
        this.driverGender = driverGender;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(String driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverPersonalId() {
        return driverPersonalId;
    }

    public void setDriverPersonalId(String driverPersonalId) {
        this.driverPersonalId = driverPersonalId;
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

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Object getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Object bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getTripNumbersPerDay() {
        return tripNumbersPerDay;
    }

    public void setTripNumbersPerDay(String tripNumbersPerDay) {
        this.tripNumbersPerDay = tripNumbersPerDay;
    }


    public Object getDriverAvatar() {
        return driverAvatar;
    }

    public void setDriverAvatar(Object driverAvatar) {
        this.driverAvatar = driverAvatar;
    }

    public Object getDriverPersonalIdPic() {
        return driverPersonalIdPic;
    }

    public void setDriverPersonalIdPic(Object driverPersonalIdPic) {
        this.driverPersonalIdPic = driverPersonalIdPic;
    }

    public Object getDriverLicenceIdPic() {
        return driverLicenceIdPic;
    }

    public void setDriverLicenceIdPic(Object driverLicenceIdPic) {
        this.driverLicenceIdPic = driverLicenceIdPic;
    }

    public Object getCarPic() {
        return carPic;
    }

    public void setCarPic(Object carPic) {
        this.carPic = carPic;
    }
}
