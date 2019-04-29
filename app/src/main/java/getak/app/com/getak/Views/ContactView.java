package getak.app.com.getak.Views;

public interface ContactView {
    void onSuccess(Object obj);
    void onFailed(String err);
    void loading(boolean status);
}
