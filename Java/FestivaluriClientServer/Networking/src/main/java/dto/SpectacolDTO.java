package dto;

import Model.Artist;

import java.io.Serializable;

public class SpectacolDTO implements Serializable {
    private Integer id;
    private String nume;
    private String locatie;
    private String data;
    private Integer numarTotalLocuri;
    private Integer numarLocuriDisponibile;
    private Integer numarLocuriOcupate;
    private ArtistDTO artist;
    private String artistNume;

    public SpectacolDTO(String data, String locatie, Integer numarTotalLocuri, String nume, ArtistDTO artist) {
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

    public ArtistDTO getArtist() {
        return artist;
    }
    public void setArtistNume(String nume) {
        this.artistNume = nume;
    }


    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public Integer getId() {
        return this.id;
    }

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
