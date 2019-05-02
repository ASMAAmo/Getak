package getak.app.com.getak.Model.Responses.FastTripResponse;

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
    @SerializedName("car_type")
    @Expose
    private String carType;
    @SerializedName("driver_phone")
    @Expose
    private String driverPhone;

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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
}
