package objectprotocol;

import dto.BiletDTO;

public class RezervareRequest implements Request {
    private BiletDTO tryout;
    public RezervareRequest(BiletDTO p) {
        this.tryout=p;
    }
    public BiletDTO getBilet() {
        return tryout;
    }
}
