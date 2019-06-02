using FestivalApp.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Repository
{
    interface ISpectacoleRepository : IRepository<Spectacol, int>
    {
       List<Spectacol> FindByDate(string nume);
     

    }
}
