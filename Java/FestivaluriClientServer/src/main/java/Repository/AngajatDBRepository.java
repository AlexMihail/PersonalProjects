package Repository;

import Model.Angajat;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AngajatDBRepository implements IAngajatRepository {


    private JdbcUtils dbUtils;



    public AngajatDBRepository() {

        Properties serverProps=new Properties();
        try{
            serverProps.load(AngajatDBRepository.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set.");
            serverProps.list(System.out);
        }
        catch (IOException e) {
            System.err.println("Cannot find sever properties" + e);
            return;
        }
        dbUtils= new JdbcUtils(serverProps);
    }

    @Override
    public Angajat findByUsername(String username) {
        Connection con=dbUtils.getConnection();
        Angajat an = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Angajati where username = ?")) {
            preStmt.setString(1,username);
            try(ResultSet result=preStmt.executeQuery()) {

                if (result.next()) {
                    int id = result.getInt("id");
                    String user= result.getString("username");

                    String parola = result.getString("parola");
                    an = new Angajat(user,parola);
                    an.setId(id);

                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return an;
    }

    @Override
    public Angajat findOne(Integer integer) {
        Connection con=dbUtils.getConnection();
        Angajat an = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Angajati where id = ?")) {
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {

                if (result.next()) {
                    int id = result.getInt("id");
                    String user= result.getString("username");
                    String parola = result.getString("parola");
                    an = new Angajat(user,parola);
                    an.setId(id);

                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return an;
    }

    @Override
    public List<Angajat> findAll() {

        Connection con=dbUtils.getConnection();
        List<Angajat> angajati=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Angajati")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String username= result.getString("username");
                    String parola = result.getString("parola");

                    Angajat an = new Angajat(username,parola);
                    an.setId(id);

                    angajati.add(an);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return angajati;
    }

    @Override
    public void save(Angajat entity) {

        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Angajati(username,parola) values (?,?)")){
            //preStmt.setInt(1,entity.getId());
            preStmt.setString(1,entity.getUsername());
            preStmt.setString(2,entity.getPassword());

            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Angajati where id=?")){
            preStmt.setInt(1,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void update(Integer id,Angajat an) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Angajati set username= ?, parola = ? where id = ? ")){
            preStmt.setString(1,an.getUsername());
            preStmt.setString(2,an.getPassword());
            preStmt.setInt(3,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }
}
