package getak.app.com.getak.Presenters;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import getak.app.com.getak.Model.NotificationModels.Trip;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Model.Trips;
import getak.app.com.getak.Service.ServiceBuilder;
import getak.app.com.getak.Views.EndTripInteraction;
import getak.app.com.getak.Views.IncomingTripInteraction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomingTripsPresenter {
    public static KProgressHUD dialog;
    public static void acceptTrip(Context context, HashMap request , final IncomingTripInteraction incomingTripInteraction){
        dialog=new KProgressHUD(context);
        dialog.show();
        Call<Result<Object>> acceptCall = ServiceBuilder.getRouter(context).acceptTrip(request);
        acceptCall.enqueue(new Callback<Result<Object>>() {
            @Override
            public void onResponse(Call<Result<Object>> call, Response<Result<Object>> response) {
                if(dialog.isShowing())
                    dialog.dismiss();
                if(response.isSuccessful()){
                    incomingTripInteraction.onAccepted(response.message());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage = "";
                        if (!jsonObject.getBoolean("status"))
                            ErrorMessage = jsonObject.getString("message");
                        incomingTripInteraction.onError(ErrorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<Object>> call, Throwable t) {
                if(dialog.isShowing())
                    dialog.dismiss();
                incomingTripInteraction.onError(t.getMessage());
            }
        });

    }
    public static void endTrip(Context context, HashMap request , final EndTripInteraction endTripInteraction){
        dialog=new KProgressHUD(context);
        dialog.show();
        Call<Result<Trip>> finishTripCall = ServiceBuilder.getRouter(context).finishTrip(request);
        finishTripCall.enqueue(new Callback<Result<Trip>>() {
            @Override
            public void onResponse(Call<Result<Trip>> call, Response<Result<Trip>> response) {
                if(dialog.isShowing())
                    dialog.dismiss();
                if(response.isSuccessful()){
                    endTripInteraction.onTripEnded(response.body().getData());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage = "";
                        if (!jsonObject.getBoolean("status"))
                            ErrorMessage = jsonObject.getString("message");
                        endTripInteraction.onError(ErrorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<Trip>> call, Throwable t) {
                if(dialog.isShowing())
                    dialog.dismiss();
                endTripInteraction.onError(t.getMessage());
            }
        });
    }
    public static void cancelTrip(Context context, HashMap request , final IncomingTripInteraction incomingTripInteraction){
        dialog=new KProgressHUD(context);
        dialog.show();
        Call<Result<Object>> acceptCall = ServiceBuilder.getRouter(context).acceptTrip(request);
        acceptCall.enqueue(new Callback<Result<Object>>() {
            @Override
            public void onResponse(Call<Result<Object>> call, Response<Result<Object>> response) {
                if(dialog.isShowing())
                    dialog.dismiss();
                if(response.isSuccessful()){
                    incomingTripInteraction.onCanceled(response.body().getMessage());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage = "";
                        if (!jsonObject.getBoolean("status"))
                            ErrorMessage = jsonObject.getString("message");
                        incomingTripInteraction.onError(ErrorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<Object>> call, Throwable t) {
                if(dialog.isShowing())
                    dialog.dismiss();
                incomingTripInteraction.onError(t.getMessage());
            }
        });

    }
}
