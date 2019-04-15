package getak.app.com.getak.Events;

public class ContractCreationStepsEvent {
    private boolean isSucess;
    private String payload;
    private int pageIndex;

    public ContractCreationStepsEvent(boolean isSucess, String payload,int target) {
        this.isSucess = isSucess;
        this.payload = payload;
        this.pageIndex=target;
    }

    public boolean isSucess() {
        return isSucess;
    }

    public String getPayload() {
        return payload;
    }

    public int getPageIndex() {
        return pageIndex;
    }
}
