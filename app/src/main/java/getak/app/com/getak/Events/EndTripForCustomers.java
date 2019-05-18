package getak.app.com.getak.Events;

public class EndTripForCustomers {
    String price;

    public EndTripForCustomers(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

}
