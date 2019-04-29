package getak.app.com.getak.Service;

import getak.app.com.getak.Model.Contract;
import getak.app.com.getak.Model.Contractmodel;
import getak.app.com.getak.Model.ContactsModel;
import getak.app.com.getak.Model.FavModel;
import getak.app.com.getak.Model.MyTrips;
import getak.app.com.getak.Model.Responses.LoginResponse.ClientLoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    // get trips
    @POST("clientTrips")
    Call<MyTrips> getTrips(@Body Object data,@Query("page") String page);
    // contact us
    @POST("contactUs")
    Call<Result<Contractmodel>> contactUs(@Body RequestBody requestBody);
// get fav
@POST("favoritePlaces")
Call<FavModel> getfav(@Body Object data);
// get contracts
@POST("clientContracts")
Call<Result<ContactsModel>> getContracts(@Body Object data);
}