package getak.app.com.getak.Service;

import getak.app.com.getak.Model.Responses.LoginResponse.ClientLoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Router {

    //Register
    @POST("registerClient")
    Call<Result<ClientRegisterationData>> register(@Body RequestBody requestBody);

    //Login
    @POST("login")
    Call<Result<ClientLoginResponse>> login(@Body RequestBody requestBody);

    //Get Profile for client
    @POST("clientProfile/{id}")
    Call<Result<ClientRegisterationData>> getProfile(@Path("id") int id);

}