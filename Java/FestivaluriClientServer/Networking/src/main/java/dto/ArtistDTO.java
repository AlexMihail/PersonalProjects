package dto;

import java.io.Serializable;
import java.util.List;

public class ArtistDTO implements Serializable {

    private Integer id;
    private String nume;


    public ArtistDTO(String name) {
        this.nume = name;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String name) {
        this.nume = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + nume + '\'' +
                '}';
    }
}
