package getak.app.com.getak.Events;

import getak.app.com.getak.Model.NotificationModels.DriverNotification;
import getak.app.com.getak.Model.Responses.Driver;

public class AcceptTripEvent {
    DriverNotification driverNotification;

    public AcceptTripEvent(DriverNotification driverNotification) {
        this.driverNotification = driverNotification;
    }

    public DriverNotification getDriverNotification() {
        return driverNotification;
    }
}
