package getak.app.com.getak.Presenters;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

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
    public static void getContracts(Context context, HashMap id, final ContractsView view){
        Call<Result<ContactsModel>> getContracts = ServiceBuilder.getRouter(context).getContracts(id);
        view.loading(true);
        getContracts.enqueue(new Callback<Result<ContactsModel>>() {
            @Override
            public void onResponse(Call<Result<ContactsModel>> call, Response<Result<ContactsModel>> response) {
                view.loading(false);
                if(response.isSuccessful()){
                    view.onSuccess(response.body().getData());
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
            public void onFailure(Call<Result<ContactsModel>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
}
