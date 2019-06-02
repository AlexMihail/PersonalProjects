package objectprotocol;

import dto.BiletDTO;

public class RezervareResponse implements Response {
    private BiletDTO tryout;
    public RezervareResponse(BiletDTO p) {
        this.tryout=p;
    }
    public BiletDTO getBilet() {
        return tryout;
    }
}
