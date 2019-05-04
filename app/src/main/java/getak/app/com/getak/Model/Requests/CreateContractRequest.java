package getak.app.com.getak.Model.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import getak.app.com.getak.Model.Days;

public class CreateContractRequest {
    @SerializedName("client_id")
    @Expose
    int client_id;
    @SerializedName("driver_id")
    @Expose
    int driver_id;
    @SerializedName("start_date")
    @Expose
    String start_date;
    @SerializedName("end_date")
    @Expose
    String end_date;
    @SerializedName("go_time")
    @Expose
    String go_time;
    @SerializedName("back_time")
    @Expose
    String back_time;
    @SerializedName("passengers_number")
    @Expose
    int passengers_number;
    @SerializedName("days")
    @Expose
    private String days = null;
    @SerializedName("from_place_lat")
    @Expose
    Double from_place_lat;
    @SerializedName("from_place_long")
    @Expose
    Double from_place_long;
    @SerializedName("from_place_name")
    @Expose
    String from_place_name;
    @SerializedName("to_place_lat")
    @Expose
    Double to_place_lat;
    @SerializedName("to_place_long")
    @Expose
    Double to_place_long;
    @SerializedName("to_place_name")
    @Expose
    String to_place_name;
    @SerializedName("period")
    @Expose
    int period;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getGo_time() {
        return go_time;
    }

    public void setGo_time(String go_time) {
        this.go_time = go_time;
    }

    public String getBack_time() {
        return back_time;
    }

    public void setBack_time(String back_time) {
        this.back_time = back_time;
    }

    public int getPassengers_number() {
        return passengers_number;
    }

    public void setPassengers_number(int passengers_number) {
        this.passengers_number = passengers_number;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Double getFrom_place_lat() {
        return from_place_lat;
    }

    public void setFrom_place_lat(Double from_place_lat) {
        this.from_place_lat = from_place_lat;
    }

    public Double getFrom_place_long() {
        return from_place_long;
    }

    public void setFrom_place_long(Double from_place_long) {
        this.from_place_long = from_place_long;
    }

    public String getFrom_place_name() {
        return from_place_name;
    }

    public void setFrom_place_name(String from_place_name) {
        this.from_place_name = from_place_name;
    }

    public Double getTo_place_lat() {
        return to_place_lat;
    }

    public void setTo_place_lat(Double to_place_lat) {
        this.to_place_lat = to_place_lat;
    }

    public Double getTo_place_long() {
        return to_place_long;
    }

    public void setTo_place_long(Double to_place_long) {
        this.to_place_long = to_place_long;
    }

    public String getTo_place_name() {
        return to_place_name;
    }

    public void setTo_place_name(String to_place_name) {
        this.to_place_name = to_place_name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
