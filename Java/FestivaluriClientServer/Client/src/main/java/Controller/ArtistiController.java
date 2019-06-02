package Controller;

import Model.Angajat;
import Model.Spectacol;
import Utils.IServer;
import Utils.MyException;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ArtistiController implements Observer {


    IServer server;
    Parent parent;
    private Stage stage;
    Angajat angajat;

    @FXML
    TableView<Spectacol> tabelData;
    @FXML
    TableColumn<Spectacol,String> columnArtist;
    @FXML
    TableColumn<Spectacol,String> columnData;
    @FXML
    TableColumn<Spectacol,String> columnLocul;
    @FXML
    TableColumn<Spectacol,String> columnDisponibile;
    @FXML
    TableColumn<Spectacol,String> columnOcupate;

    @FXML
    Text cautareText;

    @FXML
    DatePicker selectareData;
    @FXML
    TableView<Spectacol> tableAll;

    @FXML
    TableColumn<Spectacol,String> columnArtistData;
    @FXML
    TableColumn<Spectacol,String> columnLocatieData;
    @FXML
    TableColumn<Spectacol,String> columnDisponibileData;


    private ObservableList<Spectacol> model= FXCollections.observableArrayList();

    Stage loginStage;

    public void setStage(Stage s, Angajat a, Stage stageL) {
        stage =s;
        angajat = a;
        loginStage = stageL;
    }

    public  LoginController loginCtrl;
    public void setLoginController(LoginController loginObj)
    {
        this.loginCtrl = loginObj;
    }

    public void setServer(IServer server) {
        this.server = server;
    }
    public void setParent(Parent p) {
        parent=p;
    }

    public void initModel() {
        List<Spectacol> allArtists = new ArrayList<>(Arrays.asList(server.getArtisti()));
        model.setAll(new ArrayList<>(allArtists));

    }

    @FXML
    public void initialize(){

        columnArtist.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("artistNume"));
        columnData.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("data"));
        columnLocul.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("locatie"));

        columnDisponibile.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("numarLocuriDisponibile"));

        columnOcupate.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("numarLocuriOcupate"));

        tableAll.setItems(model);

    }


    public void cautareHandler()
    {
        if(selectareData.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("Selecteaza o data");
            alert.showAndWait();
        }
        else {
            String data = selectareData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ObservableList<Spectacol> listaData= FXCollections.observableArrayList();

            List<Spectacol> allArtists = new ArrayList<>(Arrays.asList(server.findByDate(data)));
            listaData.setAll(new ArrayList<>(allArtists));

            columnArtistData.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("artistNume"));
            columnLocatieData.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("locatie"));
            columnDisponibileData.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("numarLocuriDisponibile"));

            tabelData.setItems(listaData);

        }
    }

    static void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
    public void rezervareHandler()
    {

        if( tabelData.getSelectionModel().getSelectedItem()==null) {
            showErrorMessage("Selectati un spectacol");
        }
        else
        {
            String data = selectareData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String numeArtist = tabelData.getSelectionModel().getSelectedItem().getArtistNume();
            String locatia = tabelData.getSelectionModel().getSelectedItem().getLocatie();

            Spectacol spect = server.findByDataArtistLocatie(data,numeArtist,locatia);
            if(spect != null)
            {
                try {
                    // create a new stage for the popup dialog.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/RezervareWindow.fxml"));

                    AnchorPane root = (AnchorPane) loader.load();

                    // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    //dialogStage.initOwner(primaryStage);
                    Scene scene = new Scene(root);
                    dialogStage.setScene(scene);

                    RezervareController controll = loader.getController();
                    controll.setServer(server);
                    controll.setStage(dialogStage,spect,stage);
                    controll.initializare();
                    dialogStage.show();
                    this.stage.hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void logoutHandler()
    {

        try {
            server.logout(angajat, loginCtrl);
            try {
                loginStage.show();
                stage.close();
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }

        } catch (MyException ex) {

            showErrorMessage("Authentication failed!");
        }



    }


    @Override
    public void update(List<Spectacol> spect) {
        model.setAll(StreamSupport.stream(spect.spliterator(),false)
                .collect(Collectors.toList()));
        tableAll.setItems(model);
    }
}
