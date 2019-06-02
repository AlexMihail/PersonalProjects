using FestivalApp.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace FestivalApp.Repository
{
    public interface IAngajatRepository : IRepository<Angajat, int>
    {
       Angajat FindByName(string nume);
    }
}
