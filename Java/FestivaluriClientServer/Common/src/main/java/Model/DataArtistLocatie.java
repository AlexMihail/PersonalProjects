package Model;

public class DataArtistLocatie {
    String data;
    String locatie;
    String artist;

    public DataArtistLocatie(String data, String locatie, String artist) {
        this.data = data;
        this.locatie = locatie;
        this.artist = artist;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "DataArtistLocatie{" +
                "data='" + data + '\'' +
                ", locatie='" + locatie + '\'' +
                ", artist=" + artist +
                '}';
    }



}
