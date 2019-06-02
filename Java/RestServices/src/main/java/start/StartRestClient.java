package start;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import spectacole.domain.Artist;
import spectacole.services.rest.ServiceException;
import rest.client.ArtistClient;

import java.sql.Date;
import java.time.LocalDate;

public class StartRestClient {
    private static final ArtistClient meciClient = new ArtistClient();

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Artist artistTest = new Artist(9,"Adam Lambert");
        try{
            show(()->{
                System.out.println("add: ");
                System.out.println(meciClient.create(artistTest));
                Artist[] res = meciClient.getAll();
                for(Artist p : res)
                    System.out.println(p);
            });
            show(()->{
                System.out.println("update: ");
                Artist artistNou = new Artist(9, "NumeNou");
                meciClient.update(artistNou);
                Artist[] res = meciClient.getAll();
                for(Artist p : res)
                    System.out.println(p);

            });
            show(()->{
                System.out.println("delete: ");
                meciClient.delete(9);
                Artist[] res =meciClient.getAll();
                for(Artist p : res)
                    System.out.println(p);
            });
        }catch (RestClientException e){
            e.printStackTrace();
        }
    }

    private static void show(Runnable task) {
        try{
            task.run();
        }catch (ServiceException e){
            e.printStackTrace();
        }
    }
}
