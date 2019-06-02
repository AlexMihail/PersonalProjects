package spectacole.services.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spectacole.domain.Artist;
import spectacole.repository.IRepository;
import spectacole.repository.SpectacoleException;


@CrossOrigin
@RestController
@RequestMapping("/spectacole.domain/artist")
public class ArtistController {
    private static final String template = "Hello, %s!";

    @Autowired
    private IRepository artistRepository;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Artist> getAll(){ return artistRepository.findAll();}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int id){
        Artist artist = (Artist) artistRepository.findOne(id);
        if(artist == null)
            return new ResponseEntity<String>("Artist not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Artist>(artist, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Artist create(@RequestBody Artist artist){
        artistRepository.save(artist);
        return artist;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            artistRepository.delete(id);
            return new ResponseEntity<Artist>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public Artist update(@RequestBody Artist artist){
        artistRepository.update(artist.getId(), artist);
        return artist;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String probaError(ServiceException e) {
        return e.getMessage();
    }

}