package getak.app.com.getak.Model.Responses.AllDriversResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllDrivers {
    @SerializedName("drivers")
    @Expose
    private Drivers drivers;

    public Drivers getDrivers() {
        return drivers;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }
}
