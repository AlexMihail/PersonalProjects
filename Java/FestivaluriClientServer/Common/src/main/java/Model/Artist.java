package Model;

import java.io.Serializable;
import java.util.List;

public class Artist implements IHasId<Integer> , Serializable {

    private Integer id;
    private String nume;


    public Artist(String name) {
        this.nume = name;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String name) {
        this.nume = name;
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
        return "Artist{" +
                "id=" + id +
                ", name='" + nume + '\'' +
                '}';
    }
}

