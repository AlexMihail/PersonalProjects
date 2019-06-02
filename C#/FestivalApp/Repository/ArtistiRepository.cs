using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FestivalApp.Model;

namespace FestivalApp.Repository
{
    class ArtistiRepository : IArtistiRepository
    {
        SQLiteConnection m_dbConnection;

        public ArtistiRepository(SQLiteConnection m_dbConnection)
        {
            this.m_dbConnection = m_dbConnection;
        }

        public void Delete(int id)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("delete from Artist where id =" + id.ToString(), m_dbConnection);
            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public List<Artist> findAll()
        {
            List<Artist> ImportedFiles = new List<Artist>();
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Artist", m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                Artist a = new Artist();
                a.nume = reader["nume"].ToString();
                a.id = int.Parse(reader["id"].ToString());
                ImportedFiles.Add(a);
            }

            m_dbConnection.Close();
            return ImportedFiles;
        }

        public Artist FindByName(string nume)
        {
            
            SQLiteCommand command = new SQLiteCommand("Select * from Artist where nume= @nume", m_dbConnection);
            command.Parameters.AddWithValue("@nume", nume);
            SQLiteDataReader reader = command.ExecuteReader();
            Artist a = null;
            while (reader.Read())
            {
                a = new Artist();
                a.nume = reader["nume"].ToString();
                a.id = int.Parse(reader["id"].ToString());
            }

           
            return a;
        }

        public Artist findOne(int id)
        {
           
            SQLiteCommand command = new SQLiteCommand("Select * from Artist where id= " + id, m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            Artist a = null;
            while (reader.Read())
            {
                a = new Artist();
                a.nume = reader["nume"].ToString();
                a.id = int.Parse(reader["id"].ToString());
            }

            return a;
            

        }

        public void Save(Artist elem)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("insert into Angajati(nume) values (@nume)", m_dbConnection);
            command.Parameters.AddWithValue("@nume", elem.nume);
          
            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public void Update(int id, Artist entity)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("update Angajati set nume= @nume where id = @id ", m_dbConnection);
            command.Parameters.AddWithValue("@nume", entity.nume);
            command.Parameters.AddWithValue("@id", id);


            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }
    }
}
