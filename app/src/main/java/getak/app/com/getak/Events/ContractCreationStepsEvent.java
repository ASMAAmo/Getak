package getak.app.com.getak.Events;

public class ContractCreationStepsEvent {
    private boolean isSucess;
    private Object payload;
    private int pageIndex;

    public ContractCreationStepsEvent(boolean isSucess, Object payload,int target) {
        this.isSucess = isSucess;
        this.payload = payload;
        this.pageIndex=target;
    }

    public boolean isSucess() {
        return isSucess;
    }

    public Object getPayload() {
        return payload;
    }

    public int getPageIndex() {
        return pageIndex;
    }
}
