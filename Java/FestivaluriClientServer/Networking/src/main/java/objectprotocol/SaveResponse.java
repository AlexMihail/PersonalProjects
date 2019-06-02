package objectprotocol;

public class SaveResponse implements UpdateResponse {
    private Integer[] tryouts;
    public SaveResponse(Integer[] tryouts) {
        this.tryouts=tryouts;
    }
    public Integer[] getTryouts() {
        return tryouts;
    }

}