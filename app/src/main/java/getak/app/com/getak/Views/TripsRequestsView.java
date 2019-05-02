package getak.app.com.getak.Views;

import getak.app.com.getak.Model.Responses.FastTripResponse.FastTripResModel;

public interface TripsRequestsView {
    void onTripRequestSuccess(FastTripResModel fastTripResModel);
    void onTripRequestFailure(String err);
    void progress(boolean status);
}
