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
    partial class Form1 : Form
    {
        private LoginService serv;
        private ConcerteService concerteServ;
        public Form1(LoginService serv,ConcerteService concerteServ)
        {
            this.serv = serv;
            this.concerteServ = concerteServ;
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String username = textBox1.Text.ToString();
            String parola = textBox2.Text.ToString();
            if(serv.E_valid(username,parola) == true)
            {
                this.Hide();
                Form2 form = new Form2(serv,concerteServ);
                form.Show();

            }
            else
                MessageBox.Show("Introduceti un username si o parola valida", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
