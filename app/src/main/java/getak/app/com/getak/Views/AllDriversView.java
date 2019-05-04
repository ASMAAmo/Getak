package getak.app.com.getak.Views;

import getak.app.com.getak.Model.Responses.AllDriversResponse.AllDrivers;

public interface AllDriversView {
    void onGetAllDrivers(AllDrivers drivers);
    void onGetAllDriversError(String err);
    void progress(boolean status);

}
