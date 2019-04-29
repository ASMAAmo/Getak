package getak.app.com.getak.Presenters;

import android.content.Context;
import android.graphics.Color;

import java.util.HashMap;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import getak.app.com.getak.Model.FavModel;
import getak.app.com.getak.Model.MyTrips;
import getak.app.com.getak.Service.Router;
import getak.app.com.getak.Views.FavView;
import getak.app.com.getak.Views.MyTripsview;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavPresenter {
    Context context;
    FavView view;
    ACProgressFlower dialog;

    public FavPresenter(Context context, FavView view) {
        this.view = view;
        this.context = context;

    }
    public  void getFav(String id) {

        dialog =  new ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();

        dialog.show();
        HashMap input = new HashMap();
        input.put("client_id",id);
        // Log.e("inpp", input + "");
        Constanturl.createService(Router.class).getfav(input).enqueue(new Callback<FavModel >() {
            @Override
            public void onResponse(Call<FavModel> call, Response<FavModel> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {

                    FavModel  model = response.body();
                    view.onSuccess(model.getData().getFavorites());
                    // view.affFav(model.getStatus());
                    ;
                    // contactview.getContacts(model.getAbout());
                    // view.getProductdetails(model.getService());


                } else {

                }

            }

            @Override
            public void onFailure(Call<FavModel > call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }
}
