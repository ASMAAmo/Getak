package getak.app.com.getak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data_fav {
    @SerializedName("favorites")
    @Expose
    private List<Favorite> favorites = null;

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

}
