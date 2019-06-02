package Controller;


import Model.Bilet;
import Model.Spectacol;
import Utils.IServer;
import Utils.MyException;
import Utils.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RezervareController  {


    Stage stage;
    IServer server;
    Spectacol spectacol;
    Stage stageA;




    @FXML
    Text textSpectacol;

    @FXML
    TextField textClient;

    @FXML
    TextField textLocuri;

    static void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }

    public void initializare(){
        String mesaj = spectacol.getArtistNume() + " / Data: " + spectacol.getData() + " / Locatie: " + spectacol.getLocatie() + " / Locuri disponibile: "+ spectacol.getNumarLocuriDisponibile();
        textSpectacol.setText(mesaj);

    }
    public void setServer(IServer server) {
        this.server = server;
    }
    public void setStage(Stage s, Spectacol a, Stage stageA) {
        stage =s;
        spectacol = a;
        this.stageA = stageA;


    }
    public void openWindow()
    {
        try {

            try {
                stageA.show();
                stage.close();
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }

        } catch (MyException ex) {

            showErrorMessage("Error");
        }
    }

    public void cancelHandler()
    {
     openWindow();
    }

    public void bookHandler()
    {

        if(textClient.getText().isEmpty() || textLocuri.getText().isEmpty())
            showErrorMessage("Campurile nu pot sa fie vide");
        else
        {
            String client = textClient.getText();
            String numarBilete = textLocuri.getText();

            Integer locuri =0;
            int ok=0;
            try{
                locuri = Integer.parseInt(numarBilete);

                if(locuri > spectacol.getNumarLocuriDisponibile())
                    showErrorMessage("Nu mai sunt disponibile atat de multe locuri");
                else{
                    Bilet b = new Bilet(client,locuri,spectacol);
                    Bilet b1 = server.rezervaLoc(b);
                    ok=1;}
            }
            catch (NumberFormatException e)
            {
                showErrorMessage("Introduceti un numar");
            }


            if(ok==1)
                openWindow();

        }


    }

}
