package getak.app.com.getak.Service;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class CCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 401) {
            try {
                ErrorMessage errorJson = new Gson().fromJson(response.errorBody().string(), ErrorMessage.class);
                onUnauthenticated(errorJson.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            onResponse(call, response, response.isSuccessful());
        }
    }


    public abstract void onUnauthenticated(String errorMessage);

    public abstract void onResponse(Call<T> call, Response<T> response, boolean isSuccessful);

}
