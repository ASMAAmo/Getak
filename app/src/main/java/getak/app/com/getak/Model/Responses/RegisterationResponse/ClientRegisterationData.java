package getak.app.com.getak.Model.Responses.RegisterationResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import getak.app.com.getak.Model.Responses.UserModel;

public class ClientRegisterationData {
    @SerializedName("client")
    @Expose
    private UserModel client;

    public UserModel getClient() {
        return client;
    }

    public void setClient(UserModel client) {
        this.client = client;
    }
}
