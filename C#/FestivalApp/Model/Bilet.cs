using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Model
{
    class Bilet:IHasID<int>
    {
        public int Id { get; set; }
        public String NumeClient { get; set; }
        public int NumarLocuri { get; set; }
        public Spectacol Spectacol { get; set; }

        public Bilet(string numeClient, int numarLocuri, Spectacol spectacol)
        {
            NumeClient = numeClient;
            NumarLocuri = numarLocuri;
            Spectacol = spectacol;
        }

        public override string ToString()
        {
            return NumeClient + " " + NumarLocuri + " " + Spectacol;
        }
    }
}
