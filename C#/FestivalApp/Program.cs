using Autofac;
using FestivalApp.Model;
using FestivalApp.Repository;
using FestivalApp.Service;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FestivalApp
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {

            SQLiteConnection m_dbConnection = new SQLiteConnection("Data Source=/Users/Alex/IdeaProjects/Databases/FestivaluriDB.db;Version=3;");
            AngajatRepository repoAngajat = new AngajatRepository(m_dbConnection);
            BiletRepository repoBilet = new BiletRepository(m_dbConnection);
            SpectacolRepository repoSpectacol = new SpectacolRepository(m_dbConnection);
            ArtistiRepository repoArtist = new ArtistiRepository(m_dbConnection);
            /*
            var container = Config.Configure();

            using (var scope = container.BeginLifetimeScope())
            {
                Angajat a = new Angajat("aaa", "hhh", "lll");
                var app = scope.Resolve<IAngajatRepository>();
                foreach(var x in app.findAll())
                {
                    Console.WriteLine(x);
                }
                Console.ReadKey();
            }*/

            LoginService servLogin = new LoginService(repoAngajat);
            ConcerteService servConcerte = new ConcerteService(repoBilet, repoSpectacol, repoArtist);
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1(servLogin,servConcerte));




        }
    }
}
