package getak.app.com.getak.Model.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientRegisterRequest {
    @SerializedName("client_name")
    @Expose
    String client_name;
    @SerializedName("client_email")
    @Expose
    String client_email;
    @SerializedName("client_phone")
    @Expose
    String client_phone;
    @SerializedName("client_address")
    @Expose
    String client_address;
    @SerializedName("client_age")
    @Expose
    String client_age;
    @SerializedName("client_gender")
    @Expose
    String client_gender;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("password_confirmation")
    @Expose
    String password_confirmation;
    @SerializedName("deviceType")
    @Expose
    String deviceType;
    @SerializedName("token")
    @Expose
    String token;

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public void setClient_age(String client_age) {
        this.client_age = client_age;
    }

    public void setClient_gender(String client_gender) {
        this.client_gender = client_gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
