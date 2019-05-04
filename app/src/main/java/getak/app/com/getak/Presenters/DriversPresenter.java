package getak.app.com.getak.Presenters;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import getak.app.com.getak.Model.Responses.AllDriversResponse.AllDrivers;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Service.ServiceBuilder;
import getak.app.com.getak.Views.AllDriversView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriversPresenter {
    public static void getAllDrivers(Context context , final AllDriversView view , int page){
        view.progress(true);
        Call<Result<AllDrivers>> allDriversCall = ServiceBuilder.getRouter(context).getAllDrivers(page);
        allDriversCall.enqueue(new Callback<Result<AllDrivers>>() {
            @Override
            public void onResponse(Call<Result<AllDrivers>> call, Response<Result<AllDrivers>> response) {
                view.progress(false);
                if(response.isSuccessful()){
                    view.onGetAllDrivers(response.body().getData());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage="";
                        if (!jsonObject.getBoolean("status"))
                            ErrorMessage = jsonObject.getString("message");
                        view.onGetAllDriversError(ErrorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<AllDrivers>> call, Throwable t) {
                view.progress(false);
                view.onGetAllDriversError(t.getMessage());
            }
        });
    }
}
