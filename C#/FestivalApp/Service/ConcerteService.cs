using FestivalApp.Model;
using FestivalApp.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Service
{
  
    class ConcerteService
    {
        private IBiletRepository biletRepo;
        private ISpectacoleRepository spectacolRepo;
        private IArtistiRepository artistiRepo;

        public ConcerteService(IBiletRepository biletRepo, ISpectacoleRepository spectacolRepo, IArtistiRepository artistiRepo)
        {
            this.biletRepo = biletRepo;
            this.spectacolRepo = spectacolRepo;
            this.artistiRepo = artistiRepo;
        }


        public List<Spectacol> listaArtisti()
        {
            return spectacolRepo.findAll();
        }

        public List<Spectacol> findSpectacolByDate(String data)
        {
            return spectacolRepo.FindByDate(data);
        }

        public Spectacol findByDataArtistLocatie(String data, String artist,String locatie)
        {
            List<Spectacol> lista = spectacolRepo.FindByDate(data);
            foreach(Spectacol spect in lista)
            {
                if (spect.Artist.nume == artist && spect.locatie == locatie)
                    return spect;
            }
            return null;
        }


        public void adaugaBilet(Bilet b)
        {
            biletRepo.Save(b);
        }

        public void rezervaLoc(Spectacol spect,int locuri)
        {
            Spectacol s = spectacolRepo.findOne((int)spect.id);
            s.locuriOcupate = (s.locuriOcupate + locuri);
            spectacolRepo.Update((int)spect.id, s);
        }
    }
}
