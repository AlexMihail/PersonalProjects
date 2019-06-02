using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FestivalApp.Model;

namespace FestivalApp.Repository
{
    class SpectacolRepository : ISpectacoleRepository
    {
        SQLiteConnection m_dbConnection;
        ArtistiRepository repoA;

        public SpectacolRepository(SQLiteConnection m_dbConnection)
        {
            this.m_dbConnection = m_dbConnection;
            repoA = new ArtistiRepository(m_dbConnection);
        }
        public void Delete(int id)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("delete from Spectacol where id =" + id.ToString(), m_dbConnection);
            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public List<Spectacol> findAll()
        {
            List<Spectacol> ImportedFiles = new List<Spectacol>();
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Spectacol", m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                Artist art = repoA.findOne(int.Parse(reader["idArtist"].ToString()));
                Spectacol a = new  Spectacol();
       
                a.locuriTotale = int.Parse(reader["locuriTotale"].ToString());
                a.Artist = art;
                a.data = reader["data"].ToString();
                a.locatie = reader["locatie"].ToString();
                a.nume = reader["nume"].ToString();
                a.id = int.Parse(reader["id"].ToString());
                a.locuriOcupate = int.Parse(reader["locuriOcupate"].ToString());
                ImportedFiles.Add(a);
            }

            m_dbConnection.Close();
            return ImportedFiles;
        }

       

        public List<Spectacol> FindByDate(string nume)
        {
            m_dbConnection.Open();
            List<Spectacol> ImportedFiles = new List<Spectacol>();

            SQLiteCommand command = new SQLiteCommand("Select * from Spectacol where data=@data ", m_dbConnection);
            command.Parameters.AddWithValue("@data", nume);
            SQLiteDataReader reader = command.ExecuteReader();
            Spectacol a = null;
            
            while (reader.Read())
            {
                
                Artist art = repoA.findOne(int.Parse(reader["idArtist"].ToString()));
                a = new Spectacol();

                a.Artist = art;
                a.locuriTotale = int.Parse(reader["locuriTotale"].ToString());
                a.data = reader["data"].ToString();
                a.locatie = reader["locatie"].ToString();
                a.nume = reader["nume"].ToString();
                a.id = int.Parse(reader["id"].ToString());
                a.locuriOcupate = int.Parse(reader["locuriOcupate"].ToString());
                ImportedFiles.Add(a);
            }

            m_dbConnection.Close();
            return ImportedFiles;
        }


        

        public Spectacol findOne(int id)
        {
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Spectacol where id= " + id, m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            Spectacol a = null;
            while (reader.Read())
            {
                Artist art = repoA.findOne(int.Parse(reader["idArtist"].ToString()));
                a = new Spectacol();
                a.Artist = art;
                a.locuriTotale = int.Parse(reader["locuriTotale"].ToString());
                a.data = reader["data"].ToString();
                a.locatie = reader["locatie"].ToString();
                a.nume = reader["nume"].ToString();
                a.locuriOcupate = int.Parse(reader["locuriOcupate"].ToString());
                a.id = int.Parse(reader["id"].ToString());
            }

            m_dbConnection.Close();
            return a;
        }

        public void Save(Spectacol elem)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("insert into Spectacol(nume,locatie,data,locuriTotale,locuriOcupate,idArtist) values (@nume,@locatie,@data,@locuriTotale,@locuriOcupate,@idArtist)", m_dbConnection);
            command.Parameters.AddWithValue("@nume", elem.nume);
            command.Parameters.AddWithValue("@locatie", elem.locatie);
            command.Parameters.AddWithValue("@data", elem.data);
            command.Parameters.AddWithValue("@locuriTotale", elem.locuriTotale);
            command.Parameters.AddWithValue("@locuriOcupate", elem.locuriOcupate);
            command.Parameters.AddWithValue("@idArtist", elem.Artist.id);

            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public void Update(int id, Spectacol elem)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("update Spectacol set nume= @nume, locatie=@locatie,data = @data, locuriTotale = @locuriTotale, locuriOcupate = @locuriOcupate, idArtist = @idArtist where id = @id ", m_dbConnection);
            command.Parameters.AddWithValue("@nume", elem.nume);
            command.Parameters.AddWithValue("@locatie", elem.locatie);
            command.Parameters.AddWithValue("@data", elem.data);
            command.Parameters.AddWithValue("@locuriTotale", elem.locuriTotale);
            command.Parameters.AddWithValue("@locuriOcupate", elem.locuriOcupate);
            command.Parameters.AddWithValue("@idArtist", elem.Artist.id);
            command.Parameters.AddWithValue("@id", id);


            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }
    }
}
