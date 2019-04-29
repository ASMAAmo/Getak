package getak.app.com.getak.Views;

import java.util.List;

import getak.app.com.getak.Model.Data_fav;
import getak.app.com.getak.Model.FavModel;
import getak.app.com.getak.Model.Favorite;
import getak.app.com.getak.Model.Trips;

public interface FavView {
    void onSuccess(List<Favorite> favorites);
}
