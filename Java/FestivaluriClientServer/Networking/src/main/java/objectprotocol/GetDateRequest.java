package objectprotocol;

public class GetDateRequest implements Request {
    private String data;
    public GetDateRequest(String p) {
        this.data=p;
    }
    public String getTryout() {
        return data;
    }
}
