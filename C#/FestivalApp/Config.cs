using Autofac;
using FestivalApp.Repository;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FestivalApp
{
    public class Config
    {
        public static IContainer Configure()
        {
            var builder = new ContainerBuilder();

            //adauga repos
            builder.RegisterType<SQLiteConnection>()
                .WithParameter(new TypedParameter(typeof(string), "Data Source=/Users/Alex/IdeaProjects/Databases/FestivaluriDB.db;Version=3;"))
                .UsingConstructor(typeof(string));
            builder.RegisterType<AngajatRepository>().As<IAngajatRepository>();

            //add services

            return builder.Build();
        }
    }
}
