package getak.app.com.getak.Views;

import getak.app.com.getak.Model.NotificationModels.Trip;

public interface EndTripInteraction {
    void onTripEnded(Trip trip);
    void onError(String err);
}
