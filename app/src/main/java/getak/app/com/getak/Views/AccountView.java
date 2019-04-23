package getak.app.com.getak.Views;

import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
import getak.app.com.getak.Model.Responses.Result;

public interface AccountView {
    void onSuccess(Object obj);
    void onFailed(String err);
    void loading(boolean status);
}
