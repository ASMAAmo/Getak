package getak.app.com.getak.Views;

public interface ChangeDriverStatusView {
    void onStatusChanged(Object object);
    void onStatusChangedError(String err);
    void changingProgress(boolean status);
}
