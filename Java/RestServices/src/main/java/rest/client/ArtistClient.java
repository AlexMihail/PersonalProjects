package rest.client;

import org.springframework.web.client.RestTemplate;
import spectacole.domain.Artist;
import spectacole.services.rest.ServiceException;

import java.util.concurrent.Callable;

public class ArtistClient {
    public static final String URL = "http://localhost:8080/spectacole.domain/artist";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Artist[] getAll() { return execute(() -> restTemplate.getForObject(URL, Artist[].class)); }

    public Artist getID(String id){
        return execute(()-> restTemplate.getForObject(String.format("%s/%s", URL ,id), Artist.class));
    }

    public Artist create(Artist artist){
        return execute(()->restTemplate.postForObject(URL,artist, Artist.class));
    }

    public void update(Artist artist) {
        execute(() -> {restTemplate.put(String.format("%s/%s", URL, artist.getId()), artist);
            return null;
        });
    }

    public void delete(int id){
        execute(()->{
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }

}
