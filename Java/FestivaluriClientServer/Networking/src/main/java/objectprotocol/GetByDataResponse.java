package objectprotocol;

import Model.DataArtistLocatie;
import Model.Spectacol;
import dto.DataArtistLocatieDTO;
import dto.SpectacolDTO;

import javax.xml.crypto.Data;
public class GetByDataResponse implements Response {
    private SpectacolDTO tryout;
    public GetByDataResponse(SpectacolDTO p) {
        this.tryout=p;
    }
    public SpectacolDTO getTryout() {
        return tryout;
    }
}
