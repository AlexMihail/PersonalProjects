package Model;

import java.io.Serializable;

public class Bilet implements IHasId<Integer>, Serializable {

    private Integer id;
    private String numeClient;
    private Integer numarLocuri;
    private Spectacol spectacol;

    public Bilet(String numeClient, Integer numarLocuri,Spectacol spectacol) {
        this.id = id;
        this.numeClient = numeClient;
        this.numarLocuri = numarLocuri;
        this.spectacol = spectacol;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public Integer getNumarLocuri() {
        return numarLocuri;
    }

    public void setNumarLocuri(Integer numarLocuri) {
        this.numarLocuri = numarLocuri;
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
        return "Bilet{" +
                "id=" + id +
                ", numeClient='" + numeClient + '\'' +
                ", numarLocuri=" + numarLocuri + '\''+
                ", spectacol=" + spectacol.getNume()+
                '}';
    }
}