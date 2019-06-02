package objectprotocol;

import dto.ArtistDTO;
import dto.SpectacolDTO;

public class GetArtistiResponse implements Response {
    private SpectacolDTO[] artisti;
    public GetArtistiResponse(SpectacolDTO[] artisti) {
        this.artisti=artisti;
    }
    public SpectacolDTO[] getArtisti() {
        return artisti;
    }
}