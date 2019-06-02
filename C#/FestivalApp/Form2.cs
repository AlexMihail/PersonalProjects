using FestivalApp.Model;
using FestivalApp.Service;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FestivalApp
{
    partial class Form2 : Form
    {
        private LoginService loginServ;
        private ConcerteService concerteServ;

        public Form2(LoginService loginServ,ConcerteService concertServ)
        {
            this.loginServ = loginServ;
            this.concerteServ = concertServ;
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            List<Spectacol> lista = concerteServ.listaArtisti();
            DataTable table = new DataTable();
            table.Columns.Add("NumeArtist", typeof(string));
            table.Columns.Add("DataSpectacolului", typeof(string));
            table.Columns.Add("Locul", typeof(string));
            table.Columns.Add("LocuriDisponibile", typeof(string));
            table.Columns.Add("LocuriOcupate", typeof(string));

            foreach (Spectacol spect in lista)
            {
             table.Rows.Add(spect.Artist.nume,spect.data,spect.locatie,(spect.locuriTotale-spect.locuriOcupate),spect.locuriOcupate);
            }
            dataGridView1.DataSource = table;
        }

        private void dateTimePicker1_ValueChanged(object sender, EventArgs e)
        {
            String s = dateTimePicker1.Value.ToString("dd/MM/yyyy");

            List<Spectacol> lista = concerteServ.findSpectacolByDate(s);
           
                DataTable table = new DataTable();
                table.Columns.Add("NumeArtist", typeof(string));
                table.Columns.Add("Locul", typeof(string));
                table.Columns.Add("LocuriDisponibile", typeof(string));


                foreach (Spectacol spect in lista)
                {
                    table.Rows.Add(spect.Artist.nume, spect.locatie, (spect.locuriTotale - spect.locuriOcupate));
                }
                dataGridView2.DataSource = table;
            



        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (dataGridView2.CurrentRow == null)
                MessageBox.Show("Selectati un rand", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            else
            {
                String numeArtist = dataGridView2.CurrentRow.Cells[0].Value.ToString();
                String locatie = dataGridView2.CurrentRow.Cells[1].Value.ToString();
                String data = dateTimePicker1.Value.ToString("dd/MM/yyyy");

                Spectacol s = concerteServ.findByDataArtistLocatie(data, numeArtist, locatie);
                Form3 form = new Form3(loginServ, concerteServ, s);
                this.Close();
                form.Show();

            }
        
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Form1 form = new Form1(loginServ, concerteServ);
            form.Show();
            this.Close();
        }
    }
}
