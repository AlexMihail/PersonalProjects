package spectacole.domain;

import java.io.Serializable;

public class Artist implements Serializable {

    private Integer id;
    private String nume;


    public Artist(String name) {
        this.nume = name;
    }

    public Artist(Integer id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Artist() {
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

