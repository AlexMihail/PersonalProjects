package Controller;

import Model.Angajat;
import Model.Artist;

import Model.Spectacol;
import Utils.IServer;
import Utils.MyException;
import Utils.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController implements Observer {

    IServer server;
    ArtistiController ctrl;
    Parent parent;

    private Stage stage;

    @FXML
    TextField textUsername;

    @FXML
    TextField textParola;

    @FXML
    Text textFeedback;


    @FXML
    private void loginHandler() {

        String username = textUsername.getText();
        String password = textParola.getText();
        Angajat u = new Angajat(username, password);
        try {
            server.login(u, ctrl);
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/ArtistiWindow.fxml"));

                AnchorPane root = (AnchorPane) loader.load();


                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);

                dialogStage.setTitle("User: " + textUsername.getText());
                ArtistiController controll = loader.getController();
                controll.setServer(server);
                controll.setStage(dialogStage,u,stage);
                controll.initModel();
                dialogStage.show();
                this.stage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (MyException ex) {

            showErrorMessage("Authentication failed!");
        }
    }


    public void setServer(IServer server) {
        this.server = server;
    }
    public void setParent(Parent p) {
        parent=p;
    }
    public void setStage(Stage s) {
        stage =s;


    }
    public void setArtistiController(ArtistiController ctrl) {
        this.ctrl=ctrl;
    }

    static void showErrorMessage(String text) {
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("ERROR!");
        message.setContentText(text);
        message.showAndWait();
    }

    @Override
    public void update(List<Spectacol> spect) {

    }
}
