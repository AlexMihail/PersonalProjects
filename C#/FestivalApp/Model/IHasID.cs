using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Model
{
    public interface IHasID<T>
    {
        T Id { get; set; }
    }
}
