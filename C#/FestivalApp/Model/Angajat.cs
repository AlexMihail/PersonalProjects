using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp.Model
{
     public class Angajat : IHasID<int>
    {
        public int Id { get; set; }
        public String Username { get; set; }
        public String Password { get; set; }
        public String Email { get; set; }

        public Angajat(string username, string password, string email)
        {
            Username = username;
            this.Password = password;
            this.Email = email;
        }

        public override string ToString()
        {
            return Username + " " + Email;
        }
    }
}
