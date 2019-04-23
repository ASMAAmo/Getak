package getak.app.com.getak.Events;

public class LoginEvent {
    private boolean isSucess;
    private String payload;

    public LoginEvent(boolean isSucess, String payload) {
        this.isSucess = isSucess;
        this.payload = payload;
    }

    public boolean isSucess() {
        return isSucess;
    }

    public String getPayload() {
        return payload;
    }
}
