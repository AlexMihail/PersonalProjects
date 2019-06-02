using FestivalApp.Model;
using FestivalApp.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Service
{
    class LoginService
    {
        private IAngajatRepository repo;

        public LoginService(IAngajatRepository repo)
        {
            this.repo = repo;
        }

        public Angajat FindByUsername(String name)
        {
            return repo.FindByName(name);
        }

        public bool E_valid(String username, String password)
        {
            Angajat a = repo.FindByName(username);
            if (a == null)
                return false;

            if (a.Password.Equals(password))
                return true;
            return false;
        }


    }
}
