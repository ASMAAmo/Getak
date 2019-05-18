package getak.app.com.getak.Service;

import java.util.HashMap;

import getak.app.com.getak.Model.Contractmodel;
import getak.app.com.getak.Model.ContactsModel;
import getak.app.com.getak.Model.FavModel;
import getak.app.com.getak.Model.MyTrips;
import getak.app.com.getak.Model.NotificationModels.Trip;
import getak.app.com.getak.Model.Requests.CreateContractRequest;
import getak.app.com.getak.Model.Requests.FastTripRequest;
import getak.app.com.getak.Model.Responses.AllDriversResponse.AllDrivers;
import getak.app.com.getak.Model.Responses.CheckStatus.Status;
import getak.app.com.getak.Model.Responses.ContractsType.ContractsTypes;
import getak.app.com.getak.Model.Responses.FastTripResponse.FastTripResModel;
import getak.app.com.getak.Model.Responses.LoginResponse.LoginResponse;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.RegisterationResponse.DriverRegisterationData;
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
    Call<Result<LoginResponse>> login(@Body RequestBody requestBody);
    //Get Profile for client
    @POST("clientProfile/{id}")
    Call<Result<ClientRegisterationData>> getProfile(@Path("id") int id);
    // get client trips
    @POST("clientTrips")
    Call<MyTrips> getTrips(@Body Object data);
    //Get Drivers trips
    @POST("driverTrips")
    Call<MyTrips> getDriverTrips(@Body Object data);
    // contact us
    @POST("contactUs")
    Call<Result<ContactsModel>> contactUs(@Body RequestBody requestBody);
    // get fav
    @POST("favoritePlaces")
    Call<FavModel> getfav(@Body Object data);
    // get contracts
    @POST("clientContracts")
    Call<Result<Contractmodel>> getContracts(@Body Object data);
    //Get Driver Contracts
    @POST("driverContracts")
    Call<Result<Contractmodel>> getDriverContracts(@Body Object data);
    // Fast Trip Request
    @POST("makeTrip")
    Call<Result<FastTripResModel>> requestFastTrip (@Body FastTripRequest fastTripRequest);
    //Update Profile
    @POST("clientUpdateProfile/{id}")
    Call<Result<ClientRegisterationData>> updateClientProfile (@Path("id") int id,@Body RequestBody requestBody);
    //Get Contracts Type
    @POST("contracts")
    Call<Result<ContractsTypes>> getContractsTypes ();
    //Get All Drivers
    @POST("allDrivers?")
    Call<Result<AllDrivers>> getAllDrivers (@Query("page") int pageNum);
    //Create contract
    @POST("contract")
    Call<Result<Object>> createContract (@Body CreateContractRequest request);
    //Get Driver profile
    @POST("driverProfile/{id}")
    Call<Result<DriverRegisterationData>> getDriverProfile(@Path("id") int id);
    //Register Driver
    @POST("registerDriver")
    Call<Result<DriverRegisterationData>> registerDriver(@Body RequestBody requestBody);
    //Check status
    @POST("check_status")
    Call<Result<Status>> checkStatus(@Body HashMap request);
    //Change Driver location
    @POST("updateLocation")
    Call<Result<Object>> changeStatus(@Body HashMap request);
    //Update Driver
    @POST("driverUpdateProfile/{id}")
    Call<Result<DriverRegisterationData>> updateDriver (@Path("id") int id,@Body RequestBody requestBody);
    //Accept Trip
    @POST("tripAccept")
    Call<Result<Object>> acceptTrip (@Body Object requestBody);
    //Trip Done
    @POST("tripDone")
    Call<Result<Trip>> finishTrip (@Body Object requestBody);

}