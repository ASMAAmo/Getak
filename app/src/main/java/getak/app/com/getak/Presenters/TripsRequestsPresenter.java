package getak.app.com.getak.Presenters;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import getak.app.com.getak.Model.Requests.FastTripRequest;
import getak.app.com.getak.Model.Responses.FastTripResponse.FastTripResModel;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Service.ServiceBuilder;
import getak.app.com.getak.Views.TripsRequestsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsRequestsPresenter {
    public static void requstFastTrip(Context context, FastTripRequest request, final TripsRequestsView view){
        view.progress(true);
        Call<Result<FastTripResModel>> requestFastTripCall = ServiceBuilder.getRouter(context).requestFastTrip(request);
        requestFastTripCall.enqueue(new Callback<Result<FastTripResModel>>() {
            @Override
            public void onResponse(Call<Result<FastTripResModel>> call, Response<Result<FastTripResModel>> response) {
                view.progress(false);
                if(response.isSuccessful()){
                    view.onTripRequestSuccess(response.body().getData());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage="";
                        if (!jsonObject.getBoolean("status"))
                            ErrorMessage = jsonObject.getString("message");
                        view.onTripRequestFailure(ErrorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                  }
                }
            @Override
            public void onFailure(Call<Result<FastTripResModel>> call, Throwable t) {
                view.progress(false);
                view.onTripRequestFailure(t.getMessage());
            }
        });
    }
}
