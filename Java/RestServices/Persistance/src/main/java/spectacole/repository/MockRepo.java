package spectacole.repository;

import org.springframework.stereotype.Component;
import spectacole.domain.Artist;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class MockRepo implements IRepository<Integer, Artist> {
    public Map<Integer, Artist> allProbes;

    public MockRepo() {
        allProbes = new HashMap<>();
        populateMap();
    }

    Artist artist1 = new Artist(1,"Andre Rieu");
    Artist artist2 = new Artist(2,"Bon Jovi");
    Artist artist3 = new Artist(3,"Rihanna");
    Artist artist4 = new Artist(4,"ACDC");
    Artist artist5 = new Artist(5,"Queen");
    Artist artist6 = new Artist(6,"EdSheeran");


    private void populateMap() {


        allProbes.put(artist1.getId(), artist1);
        allProbes.put(artist2.getId(), artist2);
        allProbes.put(artist3.getId(), artist3);
        allProbes.put(artist4.getId(), artist4);
        allProbes.put(artist5.getId(), artist5);
        allProbes.put(artist6.getId(), artist6);

    }

    @Override
    public int size() {
        return allProbes.size();
    }

    @Override
    public void save(Artist entity) {
        if(allProbes.containsKey(entity))
            throw new SpectacoleException("Artist existent");
        allProbes.put(entity.getId(),entity);
    }

    @Override
    public void delete(Integer integer) {
        if(allProbes.containsKey(integer))
            allProbes.remove(integer);
        else
            throw new SpectacoleException("Artist inexistent");
    }

    @Override
    public void update(Integer integer, Artist entity) {
        if(allProbes.containsKey(integer))
            if(integer.equals(entity.getId())) {
                allProbes.put(integer, entity);
                return;
            }
    }

    @Override
    public Artist findOne(Integer integer) {
        return allProbes.get(integer);
    }

    @Override
    public Iterable findAll() {
        return allProbes.values();
    }
}