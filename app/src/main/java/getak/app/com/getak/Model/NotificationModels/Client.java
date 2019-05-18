package getak.app.com.getak.Model.NotificationModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("client_phone")
    @Expose
    private String clientPhone;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("client_email")
    @Expose
    private String clientEmail;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("client_age")
    @Expose
    private Integer clientAge;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("client_avatar")
    @Expose
    private String clientAvatar;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("client_address")
    @Expose
    private String clientAddress;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("notifications")
    @Expose
    private Integer notifications;
    @SerializedName("client_gender")
    @Expose
    private String clientGender;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getClientAge() {
        return clientAge;
    }

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClientAvatar() {
        return clientAvatar;
    }

    public void setClientAvatar(String clientAvatar) {
        this.clientAvatar = clientAvatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Integer getNotifications() {
        return notifications;
    }

    public void setNotifications(Integer notifications) {
        this.notifications = notifications;
    }

    public String getClientGender() {
        return clientGender;
    }

    public void setClientGender(String clientGender) {
        this.clientGender = clientGender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
