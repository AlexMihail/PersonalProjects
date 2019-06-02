package Model;

import java.io.Serializable;

public class Spectacol implements IHasId<Integer>, Serializable {

    private Integer id;
    private String nume;
    private String locatie;
    private String data;
    private Integer numarTotalLocuri;
    private Integer numarLocuriDisponibile;
    private Integer numarLocuriOcupate;
    private Artist artist;
    private String artistNume;

    public Spectacol(String data, String locatie, Integer numarTotalLocuri, String nume, Artist artist) {
        this.data = data;
        this.locatie = locatie;
        this.numarTotalLocuri = numarTotalLocuri;
        this.nume = nume;
        this.numarLocuriOcupate = 0;
        this.artist = artist;
        this.artistNume = artist.getNume();
    }

    public Integer getNumarLocuriDisponibile() {
        return (numarTotalLocuri - numarLocuriOcupate);
    }

    public String getData() {
        return data;
    }

    public void setData(String date) {
        this.data = date;
    }

    public String getNume() {
        return nume;
    }

    public String getArtistNume()
    {
        return artist.getNume();
    }

    public void setNume(String name) {
        this.nume = name;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String location) {
        this.locatie = location;
    }

    public Integer getNumarTotalLocuri() {
        return numarTotalLocuri;
    }

    public void setNumarTotalLocuri(Integer totalPlaces) {
        this.numarTotalLocuri = totalPlaces;
    }



    public Integer getNumarLocuriOcupate() {
        return numarLocuriOcupate;
    }

    public void setNumarLocuriOcupate(Integer occupiedPlaces) {
        this.numarLocuriOcupate = occupiedPlaces;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public void setArtistNume(String nume) {
        this.artistNume = nume;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", locatie='" + locatie + '\'' +
                ", data=" + data +
                ", numarTotalLocuri=" + numarTotalLocuri +
                ", numarLocuriOcupate=" + numarLocuriOcupate +
                ", artist=" + artist +
                '}';
    }
}
