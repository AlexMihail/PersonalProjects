package Service;


import Model.Bilet;
import Model.Spectacol;
import Repository.ArtistDBRepository;
import Repository.BiletDBRepository;
import Repository.SpectacolDBRepository;

import java.util.List;

public class ConcerteServices {

    ArtistDBRepository artistiRepo;
    BiletDBRepository biletRepo;
    SpectacolDBRepository spectacolRepo;

    public ConcerteServices(ArtistDBRepository artistiRepo, BiletDBRepository biletRepo, SpectacolDBRepository spectacolRepo) {
        this.artistiRepo = artistiRepo;
        this.biletRepo = biletRepo;
        this.spectacolRepo = spectacolRepo;
    }

    public List<Spectacol> listaArtisti()
    {
        List<Spectacol> listaFin  = spectacolRepo.findAll();
        return listaFin;
    }

    public List<Spectacol> findByDate(String date)
    {
        List<Spectacol> listaFin  = spectacolRepo.findByDate(date);
        return listaFin;
    }

    public Spectacol findByDataArtistLocatie(String data, String Artist,String locatie)
    {
        List<Spectacol> listaFin  = spectacolRepo.findByDate(data);
        for (Spectacol spect: listaFin)
            if(spect.getLocatie().equals(locatie) && spect.getArtist().getNume().equals(Artist))
                return spect;
            return null;
    }

    public void adaugaBilet(Bilet b)
    {
        biletRepo.save(b);
    }


    public void rezervaLoc(Spectacol spect,Integer locuri)
    {
        Spectacol s = spectacolRepo.findOne(spect.getId());
        s.setNumarLocuriOcupate(s.getNumarLocuriOcupate() + locuri);
        spectacolRepo.update(spect.getId(),s);
    }

}
