package getak.app.com.getak.Views;

public interface IncomingTripInteraction {
    void onAccepted(String msg);
    void onCanceled(String msg);
    void onError(String err);
}
