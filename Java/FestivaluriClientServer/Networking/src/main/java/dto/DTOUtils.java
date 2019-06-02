package dto;

import Model.*;

public class DTOUtils {

    public static Angajat getFromDTO(AngajatDTO usdto){
        String usernmae = usdto.getUsername();
        String parola = usdto.getPassword();

        return new Angajat(usernmae,parola);

    }
    public static AngajatDTO getDTO(Angajat user){
        String username=user.getUsername();
        String pass=user.getPassword();

        return new AngajatDTO(username,pass);
    }

    public static DataArtistLocatie getFromDTO(DataArtistLocatieDTO usdto){
        String data = usdto.getData();
        String locatie = usdto.getLocatie();
        String artist = usdto.getArtist();

        return new DataArtistLocatie(data,locatie,artist);

    }
    public static DataArtistLocatieDTO getDTO(DataArtistLocatie user){
       String data = user.getData();
       String locatie = user.getLocatie();
       String a = user.getArtist();
       return new DataArtistLocatieDTO(data,locatie,a);
    }




    public static Artist getFromDTO(ArtistDTO artist){

        String name = artist.getNume();
        return new Artist(name);

    }
    public static ArtistDTO getDTO(Artist artist){
        String name = artist.getNume();
        return new ArtistDTO(name);
    }

    public static Spectacol getFromDTO(SpectacolDTO spectacol){

        String data = spectacol.getData();
        String locatie = spectacol.getLocatie();
        Integer numarTotalLocuri = spectacol.getNumarTotalLocuri();
        String nume = spectacol.getNume();
        ArtistDTO artist1 = spectacol.getArtist();
        Artist artist = getFromDTO(artist1);
        Integer id = spectacol.getId();
        Integer ocupate = spectacol.getNumarLocuriOcupate();

        String numeArtist = spectacol.getArtistNume();

        Spectacol spect = new Spectacol(data,locatie,numarTotalLocuri,nume,artist);
        spect.setId(id);
        spect.setNumarLocuriOcupate(ocupate);
        spect.setArtistNume(numeArtist);
        return spect;

    }
    public static SpectacolDTO getDTO(Spectacol spectacol){

        String data = spectacol.getData();
        String locatie = spectacol.getLocatie();
        Integer numarTotalLocuri = spectacol.getNumarTotalLocuri();
        String nume = spectacol.getNume();
        Artist artist1 = spectacol.getArtist();
        ArtistDTO artist = getDTO(artist1);
        String numeArtist = spectacol.getArtistNume();
        Integer id = spectacol.getId();
        Integer ocupate = spectacol.getNumarLocuriOcupate();

        SpectacolDTO spect = new SpectacolDTO(data,locatie,numarTotalLocuri,nume,artist);
        spect.setId(id);
        spect.setNumarLocuriOcupate(ocupate);
        spect.setArtistNume(numeArtist);
        return spect;
    }


    public static Bilet getFromDTO(BiletDTO bilet)
    {
        String numeClient = bilet.getNumeClient();
        Integer numarLocuri = bilet.getNumarLocuri();
        SpectacolDTO spectacol1 = bilet.getSpectacol();
        Spectacol spectacol = getFromDTO(spectacol1);


         return new Bilet(numeClient,numarLocuri,spectacol);
    }

    public static BiletDTO getDTO(Bilet bilet)
    {
        String numeClient = bilet.getNumeClient();
        Integer numarLocuri = bilet.getNumarLocuri();
        Spectacol spectacol = bilet.getSpectacol();

        SpectacolDTO spectacol1 = getDTO(spectacol);

        return new BiletDTO(numeClient,numarLocuri,spectacol1);
    }

    public static AngajatDTO[] getDTO(Angajat[] users){
        AngajatDTO[] frDTO=new AngajatDTO[users.length];
        for(int i=0;i<users.length;i++)
            frDTO[i]=getDTO(users[i]);
        return frDTO;
    }

    public static SpectacolDTO[] getDTO(Spectacol[] users){
        SpectacolDTO[] frDTO=new SpectacolDTO[users.length];
        for(int i=0;i<users.length;i++)
            frDTO[i]=getDTO(users[i]);
        return frDTO;
    }

    public static ArtistDTO[] getDTO(Artist[] users){
        ArtistDTO[] frDTO=new ArtistDTO[users.length];
        for(int i=0;i<users.length;i++)
            frDTO[i]=getDTO(users[i]);
        return frDTO;
    }
    public static BiletDTO[] getDTO(Bilet[] users){
        BiletDTO[] frDTO=new BiletDTO[users.length];
        for(int i=0;i<users.length;i++)
            frDTO[i]=getDTO(users[i]);
        return frDTO;
    }


    public static Angajat[] getFromDTO(AngajatDTO[] tryouts) {
        Angajat[] t=new Angajat[tryouts.length];
        for (int i=0;i<tryouts.length;i++) {
            t[i]=getFromDTO(tryouts[i]);
        }
        return t;
    }

    public static Artist[] getFromDTO(ArtistDTO[] tryouts) {
        Artist[] t=new Artist[tryouts.length];
        for (int i=0;i<tryouts.length;i++) {
            t[i]=getFromDTO(tryouts[i]);
        }
        return t;
    }

    public static Spectacol[] getFromDTO(SpectacolDTO[] tryouts) {
        Spectacol[] t=new Spectacol[tryouts.length];
        for (int i=0;i<tryouts.length;i++) {
            t[i]=getFromDTO(tryouts[i]);
        }
        return t;
    }

    public static Bilet[] getFromDTO(BiletDTO[] tryouts) {
        Bilet[] t=new Bilet[tryouts.length];
        for (int i=0;i<tryouts.length;i++) {
            t[i]=getFromDTO(tryouts[i]);
        }
        return t;
    }

}
