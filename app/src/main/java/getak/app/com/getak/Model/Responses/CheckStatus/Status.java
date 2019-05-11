package getak.app.com.getak.Model.Responses.CheckStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("trip")
    @Expose
    private Object trip;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Object getTrip() {
        return trip;
    }

    public void setTrip(Object trip) {
        this.trip = trip;
    }
}
