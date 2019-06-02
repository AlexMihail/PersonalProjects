package objectprotocol;

import Model.DataArtistLocatie;
import Model.Spectacol;
import dto.ArtistDTO;
import dto.DataArtistLocatieDTO;
import dto.SpectacolDTO;

public class GetByDataRequest implements Request {

    private DataArtistLocatieDTO tryout;
    public GetByDataRequest(DataArtistLocatieDTO p) {
        this.tryout=p;
    }
    public DataArtistLocatieDTO getTryout() {
        return tryout;
    }
}
