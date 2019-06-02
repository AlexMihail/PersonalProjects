package objectprotocol;

import dto.SpectacolDTO;

public class GetDateResponse implements Response {
    private SpectacolDTO[] artisti;
    public GetDateResponse(SpectacolDTO[] artisti) {
        this.artisti=artisti;
    }
    public SpectacolDTO[] getArtisti() {
        return artisti;
    }
}
