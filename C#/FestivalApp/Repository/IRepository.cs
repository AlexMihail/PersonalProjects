using FestivalApp.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Repository
{
    public interface IRepository<E,ID> 
    {
        E findOne(ID id);
        List<E> findAll();
        void Save(E elem);
        void Delete(ID id);
        void Update(ID id, E entity);
    }
}
