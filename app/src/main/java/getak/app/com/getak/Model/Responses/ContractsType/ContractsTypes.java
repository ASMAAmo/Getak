package getak.app.com.getak.Model.Responses.ContractsType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContractsTypes {
    @SerializedName("types")
    @Expose
    private List<Type> types = null;

    public List<Type> getTypes() {
        return types;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

}
