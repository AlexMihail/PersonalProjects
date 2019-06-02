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
    partial class Form3 : Form
    {
        private LoginService loginServ;
        private ConcerteService concerteServ;
        private Spectacol spect;
        public Form3(LoginService loginServ, ConcerteService concerteServ,Spectacol spect)
        {
            this.loginServ = loginServ;
            this.concerteServ = concerteServ;
            this.spect = spect;
            InitializeComponent();
        }

        private void Form3_Load(object sender, EventArgs e)
        {
            label2.Text = "     LocuriDisponibile:" + (spect.locuriTotale - spect.locuriOcupate);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form2 form = new Form2(loginServ, concerteServ);
            
            form.Show();
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {


            if (textBox1.Text.Length == 0 || textBox2.Text.Length == 0)
                MessageBox.Show("Completati toate campurile", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            else
            {
                String numeClient = textBox1.Text.ToString();
                String numar = textBox2.Text.ToString();

                int numarLocuri = 0;
                try
                {
                    numarLocuri = int.Parse(textBox2.Text.ToString());
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Introduceti un numar valid", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }

                if (numarLocuri != 0)
                {
                    if (numarLocuri > (spect.locuriTotale - spect.locuriOcupate))
                        MessageBox.Show("Nu puteti cumpara atat de multe locuri", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);

                    else {
                        Bilet b = new Bilet(numeClient, numarLocuri, this.spect);
                        concerteServ.adaugaBilet(b);
                        concerteServ.rezervaLoc(this.spect, numarLocuri);
                        Form2 form = new Form2(loginServ, concerteServ);

                        form.Show();
                        this.Close();
                    }
                }


            }
        }
    }
}
