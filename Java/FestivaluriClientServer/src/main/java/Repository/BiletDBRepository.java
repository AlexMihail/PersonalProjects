package Repository;

import Model.Bilet;
import Model.Spectacol;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BiletDBRepository implements IBiletRepository {

    private JdbcUtils dbUtils;

    private SpectacolDBRepository repo;


    public BiletDBRepository() {

        Properties serverProps=new Properties();
        try{
            serverProps.load(BiletDBRepository.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set.");
            serverProps.list(System.out);
        }
        catch (IOException e) {
            System.err.println("Cannot find sever properties" + e);
            return;
        }
        dbUtils= new JdbcUtils(serverProps);
        repo = new SpectacolDBRepository();
    }


    @Override
    public Bilet findByClient(String username) {
        Connection con=dbUtils.getConnection();
        Bilet an = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Bilete where numeClient = ?")) {
            preStmt.setString(1,username);
            try(ResultSet result=preStmt.executeQuery()) {

                if (result.next()) {
                    int id = result.getInt("id");
                    String client= result.getString("numeClient");
                    Integer locuri = result.getInt("numarLocuri");
                    Integer spect = result.getInt("idSpectacol");
                    Spectacol s = repo.findOne(spect);
                    an = new Bilet(client,locuri,s);
                    an.setId(id);

                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return an;
    }

    @Override
    public Bilet findOne(Integer integer) {
        Connection con=dbUtils.getConnection();
        Bilet an = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Bilete where id = ?")) {
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {

                if (result.next()) {
                    int id = result.getInt("id");
                    String client= result.getString("numeClient");
                    Integer spect = result.getInt("idSpectacol");
                    Integer locuri = result.getInt("numarLocuri");
                    Spectacol s = repo.findOne(spect);
                    an = new Bilet(client,locuri,s);
                    an.setId(id);

                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return an;
    }

    @Override
    public List<Bilet> findAll() {

        Connection con=dbUtils.getConnection();
        List<Bilet> bilete=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Bilete")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String client= result.getString("numeClient");
                    Integer spect = result.getInt("idSpectacol");
                    Integer locuri = result.getInt("numarLocuri");
                    Spectacol s = repo.findOne(spect);
                    Bilet b = null;
                    b = new Bilet(client,locuri,s);
                    b.setId(id);

                    bilete.add(b);
                }
            }
        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }


        return bilete;
    }

    @Override
    public void save(Bilet entity) {
        Connection con=dbUtils.getConnection();
       try(PreparedStatement preStmt=con.prepareStatement("insert into Bilete(numeClient,numarLocuri,idSpectacol) values (?,?,?)")){
            //preStmt.setInt(1,entity.getId());
            preStmt.setString(1,entity.getNumeClient());
            preStmt.setInt(2,entity.getNumarLocuri());

            if(repo.findByName(entity.getSpectacol().getNume()) != null){
                int id = entity.getSpectacol().getId();
                preStmt.setInt(3,id);
                int result=preStmt.executeUpdate();}

        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }

    }

    @Override
    public void delete(Integer integer) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Bilete where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }

    @Override
    public void update(Integer integer, Bilet entity) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Bilete set numeClient= ?, numarLocuri = ? where id = ? ")){
            preStmt.setString(1,entity.getNumeClient());
            preStmt.setInt(2,entity.getNumarLocuri());
            preStmt.setInt(3,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){

            System.out.println("Error DB "+ex);
        }
    }
}
