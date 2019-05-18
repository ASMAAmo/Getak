package getak.app.com.getak.Model.NotificationModels;

public class DriverNotification {
    Client client;
    Trip trip;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
