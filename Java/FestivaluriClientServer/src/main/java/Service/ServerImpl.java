package Service;

import Model.Angajat;
import Model.Artist;
import Model.Bilet;
import Model.Spectacol;
import Repository.AngajatDBRepository;
import Repository.ArtistDBRepository;
import Repository.BiletDBRepository;
import Repository.SpectacolDBRepository;
import Utils.*;
import Utils.Observable;
import Utils.Observer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerImpl implements IServer {
    private AngajatDBRepository angajatRepository;
    private BiletDBRepository biletRepository;
    private SpectacolDBRepository spectacolRepository;
    private ArtistDBRepository artistRepository;
    private Map<String, String> loggedIn;

    public ServerImpl(AngajatDBRepository angajatRepository, BiletDBRepository biletRepository, SpectacolDBRepository spectacolRepository, ArtistDBRepository artistRepository) {
        this.angajatRepository = angajatRepository;
        this.biletRepository = biletRepository;
        this.spectacolRepository = spectacolRepository;
        this.artistRepository = artistRepository;
        loggedIn=new ConcurrentHashMap<>();
    }

    public synchronized void login(Angajat user, Observer client) {
        Angajat userR = angajatRepository.findByUsername(user.getUsername());
        if(userR!=null)
            if(!userR.getPassword().equals(user.getPassword()))
                userR = null;

        if (userR != null) {
            System.out.println(userR);
            if (loggedIn.get(user.getUsername()) != null)
                throw new MyException("User already logged in.");
            loggedIn.put(userR.getUsername(), "");

        } else
            throw new MyException("Authentication failed.");
    }

    private final int defaultThreadsNo = 5;
    /*
    public void notifyLoggedIn(List<Integer> tryouts) {
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
        for (Observer o : loggedIn.values()) {
            executor.execute(()->{
                try{
                    System.out.println("Notify" + o.toString());
                    //o.update(tryouts.toArray(new Integer[tryouts.size()]));
                }
                catch (MyException e) {
                    System.err.println("Error notifying");
                }
            });
        }
        executor.shutdown();
    }
*/
    public synchronized void logout(Angajat user, Observer client) {
       // Observer localClient = loggedIn.remove(user.getUsername());
        //if (localClient == null)
          //  throw new MyException("User " + user.getUsername() + " is not logged in.");

    }

    public Spectacol[] getArtisti()
    {
        Set<Spectacol> all = new HashSet<>(spectacolRepository.findAll());
        return all.toArray(new Spectacol[all.size()]);
    }


    public Spectacol findByDataArtistLocatie(String data, String Artist, String locatie)
    {
        List<Spectacol> listaFin  = spectacolRepository.findByDate(data);

        for (Spectacol spect: listaFin)
            if(spect.getLocatie().equals(locatie) && spect.getArtist().getNume().equals(Artist)){
                System.out.println(spect);
                return spect;}
        return null;
    }


    public Spectacol[] findByDate(String date)
    {
        Set<Spectacol> all = new HashSet<>(spectacolRepository.findByDate(date));
        return all.toArray(new Spectacol[all.size()]);
    }


    public Bilet rezervaLoc(Bilet b)
    {


        System.out.println(b.getSpectacol());
        biletRepository.save(b);
        Spectacol spect = b.getSpectacol();
        Integer locuri = b.getNumarLocuri();


        Spectacol s = spectacolRepository.findOne(spect.getId());

        s.setNumarLocuriOcupate(s.getNumarLocuriOcupate() + locuri);
        spectacolRepository.update(spect.getId(),s);
        this.notifyUpdate();
        return b;
    }

    private final int nrThreads=5;
    private synchronized void notifyUpdate() {
        ExecutorService executor= Executors.newFixedThreadPool(nrThreads);
        /*
        for(Observer Client :loggedIn.values()){
            if (Client!=null)
                executor.execute(() -> {
                    System.out.println("Notifying [" + Client.toString());
                    Client.update(spectacolRepository.findAll());
                });
        }
        executor.shutdown();
        */
    }


}