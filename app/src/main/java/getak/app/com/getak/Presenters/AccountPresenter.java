package getak.app.com.getak.Presenters;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import getak.app.com.getak.Model.Responses.LoginResponse.ClientLoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Service.ServiceBuilder;
import getak.app.com.getak.Views.AccountView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountPresenter {
    public static void registerClient(Context context ,RequestBody requestBody, final AccountView view){
        view.loading(true);
        Call<Result<ClientRegisterationData>> clientRegisterCall = ServiceBuilder.getRouter(context).register(requestBody);
        clientRegisterCall.enqueue(new Callback<Result<ClientRegisterationData>>() {
            @Override
            public void onResponse(Call<Result<ClientRegisterationData>> call, Response<Result<ClientRegisterationData>> response) {
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
            public void onFailure(Call<Result<ClientRegisterationData>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
    public static void clientLogin(Context context, RequestBody requestBody, final AccountView view){
        view.loading(true);
        Call<Result<ClientLoginResponse>> clientLoginCall = ServiceBuilder.getRouter(context).login(requestBody);
        clientLoginCall.enqueue(new Callback<Result<ClientLoginResponse>>() {
            @Override
            public void onResponse(Call<Result<ClientLoginResponse>> call, Response<Result<ClientLoginResponse>> response) {
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
            public void onFailure(Call<Result<ClientLoginResponse>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
    public static void getClientProfile(Context context, int id, final AccountView view){
        Call<Result<ClientRegisterationData>> getProfileCall = ServiceBuilder.getRouter(context).getProfile(id);
        view.loading(true);
        getProfileCall.enqueue(new Callback<Result<ClientRegisterationData>>() {
            @Override
            public void onResponse(Call<Result<ClientRegisterationData>> call, Response<Result<ClientRegisterationData>> response) {
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
            public void onFailure(Call<Result<ClientRegisterationData>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
    public static void updateClientProfile(Context context ,int id,RequestBody requestBody, final AccountView view){
        view.loading(true);
        Call<Result<ClientRegisterationData>> clientRegisterCall = ServiceBuilder.getRouter(context).updateClientProfile(id,requestBody);
        clientRegisterCall.enqueue(new Callback<Result<ClientRegisterationData>>() {
            @Override
            public void onResponse(Call<Result<ClientRegisterationData>> call, Response<Result<ClientRegisterationData>> response) {
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
            public void onFailure(Call<Result<ClientRegisterationData>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
}
