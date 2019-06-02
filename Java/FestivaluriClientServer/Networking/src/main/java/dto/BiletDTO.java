package dto;

import Model.Spectacol;

import java.io.Serializable;

public class BiletDTO implements Serializable {
    private Integer id;
    private String numeClient;
    private Integer numarLocuri;
    private SpectacolDTO spectacol;

    public BiletDTO(String numeClient, Integer numarLocuri,SpectacolDTO spectacol) {
        this.id = id;
        this.numeClient = numeClient;
        this.numarLocuri = numarLocuri;
        this.spectacol = spectacol;
    }

    public SpectacolDTO getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(SpectacolDTO spectacol) {
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

    public Integer getId() {
        return this.id;
    }

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
