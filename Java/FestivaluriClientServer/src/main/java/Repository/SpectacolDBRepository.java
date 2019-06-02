package Repository;

import Model.Artist;
import Model.Spectacol;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SpectacolDBRepository implements ISpectacolRepository {

    private JdbcUtils dbUtils;
    ArtistDBRepository repo;



    public SpectacolDBRepository() {

        Properties serverProps=new Properties();
        try{
            serverProps.load(ArtistDBRepository.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set.");
            serverProps.list(System.out);
        }
        catch (IOException e) {
            System.err.println("Cannot find sever properties" + e);
            return;
        }
        dbUtils= new JdbcUtils(serverProps);

        repo = new ArtistDBRepository();
    }

    @Override
    public List<Spectacol> findByDate(String date) {

        Connection con=dbUtils.getConnection();
        List<Spectacol> spectacole=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Spectacol where data=?")) {
            preStmt.setString(1,date);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String locatie= result.getString("locatie");
                    String data = result.getString("data");
                    String name= result.getString("nume");
                    Integer locuriTotale = result.getInt("locuriTotale");
                    Integer idArtist = result.getInt("idArtist");
                    Artist a = repo.findOne(idArtist);

                    Spectacol spect = new Spectacol(data,locatie,locuriTotale,name,a);
                    spect.setId(id);
                    Integer locuriOcupate = result.getInt("locuriOcupate");
                    spect.setNumarLocuriOcupate(locuriOcupate);
                    spectacole.add(spect);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return spectacole;
    }

    @Override
    public Spectacol findOne(Integer integer) {
        Connection con=dbUtils.getConnection();
        Spectacol spect = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Spectacol where id = ?")) {
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String locatie= result.getString("locatie");
                    String name= result.getString("nume");
                    String data = result.getString("data");
                    Integer locuriTotale = result.getInt("locuriTotale");
                    Integer idArtist = result.getInt("idArtist");
                    Artist a = repo.findOne(idArtist);

                    spect = new Spectacol(data,locatie,locuriTotale,name,a);
                    spect.setId(id);
                    Integer locuriOcupate = result.getInt("locuriOcupate");
                    spect.setNumarLocuriOcupate(locuriOcupate);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return spect;
    }

    @Override
    public List<Spectacol> findAll() {

        Connection con=dbUtils.getConnection();
        List<Spectacol> spectacole=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Spectacol")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String locatie= result.getString("locatie");
                    String name= result.getString("nume");
                    String data = result.getString("data");
                    Integer locuriTotale = result.getInt("locuriTotale");
                    Integer idArtist = result.getInt("idArtist");
                    Artist a = repo.findOne(idArtist);

                    Spectacol spect = new Spectacol(data,locatie,locuriTotale,name,a);
                    spect.setId(id);
                    Integer locuriOcupate = result.getInt("locuriOcupate");
                    spect.setNumarLocuriOcupate(locuriOcupate);
                    spectacole.add(spect);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return spectacole;
    }

    @Override
    public void save(Spectacol entity) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Spectacol(nume,locatie,data,locuriTotale,idArtist) values (?,?,?,?,?)")){
            //preStmt.setInt(1,entity.getId());
            preStmt.setString(1,entity.getNume());
            preStmt.setString(2,entity.getLocatie());
            preStmt.setString(3,entity.getData());
            preStmt.setInt(4,entity.getNumarTotalLocuri());

            if(repo.findByName(entity.getArtist().getNume()) != null){
                int id = repo.findByName(entity.getArtist().getNume()).getId();
            preStmt.setInt(5,id);
            int result=preStmt.executeUpdate();}

        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }


    public Spectacol findByName(String name1) {
        Connection con=dbUtils.getConnection();
        Spectacol spect = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Spectacol where nume = ?")) {
            preStmt.setString(1,name1);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("id");
                    String locatie= result.getString("locatie");
                    String name= result.getString("nume");
                    String data = result.getString("data");
                    Integer locuriTotale = result.getInt("locuriTotale");
                    Integer idArtist = result.getInt("idArtist");
                    Artist a = repo.findOne(idArtist);

                    spect = new Spectacol(data,locatie,locuriTotale,name,a);
                    spect.setId(id);
                    Integer locuriOcupate = result.getInt("locuriOcupate");
                    spect.setNumarLocuriOcupate(locuriOcupate);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return spect;
    }


    @Override
    public void delete(Integer integer) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Spectacol where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void update(Integer integer, Spectacol entity) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Spectacol set nume= ?,locuriOcupate = ? where id = ? ")){
            preStmt.setString(1,entity.getNume());
            preStmt.setInt(2,entity.getNumarLocuriOcupate());
            preStmt.setInt(3,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }
}
