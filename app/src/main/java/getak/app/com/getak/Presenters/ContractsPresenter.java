package getak.app.com.getak.Presenters;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import getak.app.com.getak.Model.Contractmodel;
import getak.app.com.getak.Model.Requests.CreateContractRequest;
import getak.app.com.getak.Model.Responses.ContractsType.ContractsTypes;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Service.ServiceBuilder;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.ContractsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractsPresenter {
    public static void getContracts(Context context, HashMap id, final ContractsView view){
        if (SessionHelper.isDriver(context)) {
            Call<Result<Contractmodel>> getContracts = ServiceBuilder.getRouter(context).getDriverContracts(id);
            view.loading(true);
            getContracts.enqueue(new Callback<Result<Contractmodel>>() {
                @Override
                public void onResponse(Call<Result<Contractmodel>> call, Response<Result<Contractmodel>> response) {
                    view.loading(false);
                    if (response.isSuccessful()) {
                        view.onSuccess(response.body().getData());
                    } else {
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
                public void onFailure(Call<Result<Contractmodel>> call, Throwable t) {
                    view.loading(false);
                    view.onFailed(t.getMessage());
                }
            });
        }else {
            Call<Result<Contractmodel>> getContracts = ServiceBuilder.getRouter(context).getContracts(id);
            view.loading(true);
            getContracts.enqueue(new Callback<Result<Contractmodel>>() {
                @Override
                public void onResponse(Call<Result<Contractmodel>> call, Response<Result<Contractmodel>> response) {
                    view.loading(false);
                    if (response.isSuccessful()) {
                        view.onSuccess(response.body().getData());
                    } else {
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
                public void onFailure(Call<Result<Contractmodel>> call, Throwable t) {
                    view.loading(false);
                    view.onFailed(t.getMessage());
                }
            });
        }
    }
    public static void getContractsTypes(Context context, final ContractsView view){
        Call<Result<ContractsTypes>> getContractTypes = ServiceBuilder.getRouter(context).getContractsTypes();
        getContractTypes.enqueue(new Callback<Result<ContractsTypes>>() {
            @Override
            public void onResponse(Call<Result<ContractsTypes>> call, Response<Result<ContractsTypes>> response) {
                view.loading(false);
                if (response.isSuccessful()){
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
            public void onFailure(Call<Result<ContractsTypes>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
    public static void createContract(Context context, CreateContractRequest request, final ContractsView view){
        Call<Result<Object>> createContractCall = ServiceBuilder.getRouter(context).createContract(request);
        view.loading(true);
        createContractCall.enqueue(new Callback<Result<Object>>() {
            @Override
            public void onResponse(Call<Result<Object>> call, Response<Result<Object>> response) {
                view.loading(false);
                if(response.isSuccessful()) {
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
            public void onFailure(Call<Result<Object>> call, Throwable t) {
                view.loading(false);
                view.onFailed(t.getMessage());
            }
        });
    }
}
