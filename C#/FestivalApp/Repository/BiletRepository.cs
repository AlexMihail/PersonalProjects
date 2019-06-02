using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FestivalApp.Model;

namespace FestivalApp.Repository
{
    class BiletRepository : IBiletRepository
    {
        SQLiteConnection m_dbConnection;
        SpectacolRepository repo;
        

        public BiletRepository(SQLiteConnection m_dbConnection)
        {
            this.m_dbConnection = m_dbConnection;
            repo = new SpectacolRepository(m_dbConnection);
          
        }
        public void Delete(int id)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("delete from Bilete where id =" + id.ToString(), m_dbConnection);
            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public List<Bilet> findAll()
        {
            List<Bilet> ImportedFiles = new List<Bilet>();
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Bilete", m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                Spectacol spect = repo.findOne(int.Parse(reader["idSpectacol"].ToString()));
                Bilet a = new Bilet(reader["numeClient"].ToString(), int.Parse(reader["numarLocuri"].ToString()),spect);
                a.Id = int.Parse(reader["id"].ToString());
                ImportedFiles.Add(a);
            }

            m_dbConnection.Close();
            return ImportedFiles;
        }

        public List<Bilet> findByClient(string name)
        {
            List<Bilet> ImportedFiles = new List<Bilet>();
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Bilete where numeClient = " + name, m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                Spectacol spect = repo.findOne(int.Parse(reader["idSpectacol"].ToString()));
                Bilet a = new Bilet(reader["numeClient"].ToString(), int.Parse(reader["numarLocuri"].ToString()), spect);
                a.Id = int.Parse(reader["id"].ToString());
                ImportedFiles.Add(a);
            }

            m_dbConnection.Close();
            return ImportedFiles;
        }

        public Bilet findOne(int id)
        {
           
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Bilete where id = " + id, m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            Bilet a = null;
            while (reader.Read())
            {
                Spectacol spect = repo.findOne(int.Parse(reader["idSpectacol"].ToString()));
                a = new Bilet(reader["numeClient"].ToString(), int.Parse(reader["numarLocuri"].ToString()), spect);
                a.Id = int.Parse(reader["id"].ToString());
               
            }

            m_dbConnection.Close();
            return a;
        }

        public void Save(Bilet elem)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("insert into Bilete(numeClient,numarLocuri,idSpectacol) values (@nume,@numarLocuri,@idSpectacol)", m_dbConnection);
            command.Parameters.AddWithValue("@nume", elem.NumeClient);
            command.Parameters.AddWithValue("@numarLocuri", elem.NumarLocuri);
            command.Parameters.AddWithValue("@idSpectacol", elem.Spectacol.id);

            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public void Update(int id, Bilet elem)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("update Spectacol set numeClient= @nume, numarLocuri=@locuri,idSpectacol = @idSpectacol where id = @id ", m_dbConnection);
            command.Parameters.AddWithValue("@nume", elem.NumeClient);
            command.Parameters.AddWithValue("@numarLocuri", elem.NumarLocuri);
            command.Parameters.AddWithValue("@idSpectacol", elem.Spectacol.id);
            command.Parameters.AddWithValue("@id", id);

            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }
    }
}
