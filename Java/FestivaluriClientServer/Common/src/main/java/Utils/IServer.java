package Utils;

import Model.Angajat;
import Model.Artist;
import Model.Bilet;
import Model.Spectacol;

import java.rmi.Remote;
import java.util.List;



public interface IServer  {
    void login(Angajat user, Observer client) ;
    void logout(Angajat user, Observer client);
    Spectacol[] getArtisti();
    Spectacol findByDataArtistLocatie(String data, String Artist,String locatie);
    Spectacol[] findByDate(String data);
    Bilet rezervaLoc(Bilet b);
}
