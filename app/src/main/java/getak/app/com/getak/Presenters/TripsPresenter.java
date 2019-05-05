package getak.app.com.getak.Presenters;

import android.content.Context;
import android.graphics.Color;

import java.util.HashMap;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import getak.app.com.getak.Model.MyTrips;
import getak.app.com.getak.Service.Router;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.MyTripsview;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsPresenter {
    Context context;
     MyTripsview view;
    ACProgressFlower dialog;


    public TripsPresenter(Context context, MyTripsview view) {
        this.view = view;
        this.context = context;

    }
    public  void getTrips(String id) {

        dialog =  new ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();

        dialog.show();
        if(SessionHelper.isDriver(context)){
            HashMap input = new HashMap();
            input.put("driver_id",id);
            Constanturl.createService(Router.class).getDriverTrips(input).enqueue(new Callback<MyTrips >() {
                @Override
                public void onResponse(Call<MyTrips> call, Response<MyTrips > response) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if (response.isSuccessful()) {
                        MyTrips  model = response.body();
                        view.onSuccess(model.getData().getTrips());

                    } else {

                    }

                }

                @Override
                public void onFailure(Call<MyTrips > call, Throwable t) {
                    if (dialog.isShowing())
                        dialog.dismiss();

                }
            });
        }else {
            HashMap input = new HashMap();
            input.put("client_id",id);
            Constanturl.createService(Router.class).getTrips(input).enqueue(new Callback<MyTrips >() {
                @Override
                public void onResponse(Call<MyTrips> call, Response<MyTrips > response) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                    if (response.isSuccessful()) {
                        MyTrips  model = response.body();
                        view.onSuccess(model.getData().getTrips());

                    } else {

                    }

                }

                @Override
                public void onFailure(Call<MyTrips > call, Throwable t) {
                    if (dialog.isShowing())
                        dialog.dismiss();

                }
            });        }
    }
}
