package Repository;

import Model.Artist;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistDBRepository implements IArtistRepository {


    private JdbcUtils dbUtils;

    //private static final Logger logger= LogManager.getLogger();

    public ArtistDBRepository() {

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
    }


    @Override
    public Artist findByName(String nume) {
        Connection con=dbUtils.getConnection();
        Artist ar = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Artist where nume = ?")) {
            preStmt.setString(1,nume);
            try(ResultSet result=preStmt.executeQuery()) {

                if (result.next()) {
                    int id = result.getInt("id");
                    String name= result.getString("nume");

                    ar = new Artist(name);
                    ar.setId(id);

                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return ar;
    }

    @Override
    public Artist findOne(Integer integer) {
        Connection con=dbUtils.getConnection();
        Artist ar = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Artist where id = ?")) {
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    String name= result.getString("nume");
                    int id = result.getInt("id");
                    ar = new Artist(name);
                    ar.setId(id);

                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return ar;
    }

    @Override
    public List<Artist> findAll() {

        Connection con=dbUtils.getConnection();
        List<Artist> artisti=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Artist")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String nume= result.getString("nume");

                    Artist an = new Artist(nume);
                    an.setId(id);
                    artisti.add(an);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return artisti;
    }

    @Override
    public void save(Artist entity) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Artist(nume) values (?)")){
            //preStmt.setInt(1,entity.getId());
            preStmt.setString(1,entity.getNume());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void delete(Integer integer) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Artist where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void update(Integer integer, Artist entity) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Artist set nume= ? where id = ? ")){
            preStmt.setString(1,entity.getNume());
            preStmt.setInt(2,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }
}
