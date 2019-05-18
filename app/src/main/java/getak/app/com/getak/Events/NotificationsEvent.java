package getak.app.com.getak.Events;

public class NotificationsEvent {
    String message;

    public NotificationsEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
