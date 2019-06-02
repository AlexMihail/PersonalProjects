package Model;

import java.io.Serializable;

public class Angajat implements IHasId<Integer>, Serializable {

    private Integer id;
    private String username;
    private String password;


    public Angajat(String username, String password) {
        this.username = username;
        this.password = password;

    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
