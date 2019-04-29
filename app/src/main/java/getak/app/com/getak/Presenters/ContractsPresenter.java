package getak.app.com.getak.Presenters;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import getak.app.com.getak.Model.ContactsModel;
import getak.app.com.getak.Model.Contract;
import getak.app.com.getak.Model.Contractmodel;
import getak.app.com.getak.Model.Responses.LoginResponse.ClientLoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Service.ServiceBuilder;
import getak.app.com.getak.Views.AccountView;
import getak.app.com.getak.Views.ContractsView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractsPresenter {
    public static void getContracts(Context context, int id, final ContractsView view){
        Call<Result<Contractmodel>> getContracts = ServiceBuilder.getRouter(context).getContracts(id);
        view.loading(true);
        getContracts.enqueue(new Callback<Result<Contractmodel>>() {
            @Override
            public void onResponse(Call<Result<Contractmodel>> call, Response<Result<Contractmodel>> response) {
                view.loading(false);
                if(response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage="";
                        if (!jsonObject.getBoolean("status"))
                            ErrorMessage = jsonObject.getString("message");
                        view.onFailed(ErrorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<Contractmodel>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
}
