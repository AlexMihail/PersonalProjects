using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FestivalApp.Model;

namespace FestivalApp.Repository
{
    class AngajatRepository : IAngajatRepository
    {
        SQLiteConnection m_dbConnection;

        public AngajatRepository(SQLiteConnection m_dbConnection)
        {
            this.m_dbConnection = m_dbConnection;
        }

        public void Delete(int id)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("delete from Angajati where id ="+id.ToString(), m_dbConnection);
            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public List<Angajat> findAll()
        {
            List<Angajat> ImportedFiles = new List<Angajat>();
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Angajati", m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                Angajat a = new Angajat(reader["username"].ToString(), reader["parola"].ToString(),reader["email"].ToString());
                a.Id = int.Parse(reader["id"].ToString());
                ImportedFiles.Add(a);
            }

            m_dbConnection.Close();
            return ImportedFiles;
        }

        public Angajat FindByName(string nume)
        {
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Angajati where username= @username", m_dbConnection);
            command.Parameters.AddWithValue("@username", nume);
            SQLiteDataReader reader = command.ExecuteReader();
            Angajat a = null;
            while (reader.Read())
            {
                Console.WriteLine(reader["username"]);
                a = new Angajat(reader["username"].ToString(), reader["parola"].ToString(), "");
                a.Id = int.Parse(reader["id"].ToString());
                
            }

            m_dbConnection.Close();
            return a;
        }

        public Angajat findOne(int id)
        {
            m_dbConnection.Open();

            SQLiteCommand command = new SQLiteCommand("Select * from Angajati where id= " + id, m_dbConnection);
            SQLiteDataReader reader = command.ExecuteReader();
            Angajat a = null;
            while (reader.Read())
            {
                a = new Angajat(reader["username"].ToString(), reader["parola"].ToString(), reader["email"].ToString());
                a.Id = int.Parse(reader["id"].ToString());

            }

            m_dbConnection.Close();
            return a;
        }

        public void Save(Angajat elem)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("insert into Angajati(username,parola,email) values (@username,@parola,@email)", m_dbConnection);
            command.Parameters.AddWithValue("@username", elem.Username);
            command.Parameters.AddWithValue("@parola", elem.Password);
            command.Parameters.AddWithValue("@email", elem.Email);
        
            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }

        public void Update(int id, Angajat entity)
        {
            m_dbConnection.Open();
            SQLiteCommand command = new SQLiteCommand("update Angajati set username= @username, parola = @parola, email = @email where id = @id ", m_dbConnection);
            command.Parameters.AddWithValue("@username",entity.Username);
            command.Parameters.AddWithValue("@parola",entity.Password);
            command.Parameters.AddWithValue("@email",entity.Email);
            command.Parameters.AddWithValue("@id",id);



            command.ExecuteNonQuery();
            m_dbConnection.Close();
        }
    }
}
