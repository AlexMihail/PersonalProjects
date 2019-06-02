using FestivalApp.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Repository
{
    interface IArtistiRepository : IRepository<Artist, int>
    {
        Artist FindByName(string nume);
    }
}
