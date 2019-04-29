package getak.app.com.getak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contractmodel {
    @SerializedName("contracts")
    @Expose
    private List<Contract> contracts = null;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;

    }
}