package getak.app.com.getak.Presenters;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import getak.app.com.getak.Model.Responses.LoginResponse.LoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.RegisterationResponse.DriverRegisterationData;
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
        Call<Result<LoginResponse>> clientLoginCall = ServiceBuilder.getRouter(context).login(requestBody);
        clientLoginCall.enqueue(new Callback<Result<LoginResponse>>() {
            @Override
            public void onResponse(Call<Result<LoginResponse>> call, Response<Result<LoginResponse>> response) {
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
            public void onFailure(Call<Result<LoginResponse>> call, Throwable t) {
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
    public static void getDriverProfile(Context context, int id, final AccountView view){
        view.loading(true);
        Call<Result<DriverRegisterationData>> getDriverProfileCall = ServiceBuilder.getRouter(context).getDriverProfile(id);
        getDriverProfileCall.enqueue(new Callback<Result<DriverRegisterationData>>() {
            @Override
            public void onResponse(Call<Result<DriverRegisterationData>> call, Response<Result<DriverRegisterationData>> response) {
                view.loading(true);
                if(response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String ErrorMessage = "";
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
            public void onFailure(Call<Result<DriverRegisterationData>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
    public static void registerDriver(Context context ,RequestBody requestBody, final AccountView view){
        view.loading(true);
        Call<Result<DriverRegisterationData>> clientRegisterCall = ServiceBuilder.getRouter(context).registerDriver(requestBody);
        clientRegisterCall.enqueue(new Callback<Result<DriverRegisterationData>>() {
            @Override
            public void onResponse(Call<Result<DriverRegisterationData>> call, Response<Result<DriverRegisterationData>> response) {
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
            public void onFailure(Call<Result<DriverRegisterationData>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
}
