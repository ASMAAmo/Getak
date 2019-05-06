package getak.app.com.getak.Model.Responses.RegisterationResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import getak.app.com.getak.Model.Responses.Driver;

public class DriverRegisterationData {
    @SerializedName("driver")
    @Expose
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
