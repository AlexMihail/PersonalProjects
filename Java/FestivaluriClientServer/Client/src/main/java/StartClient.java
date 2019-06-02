
import Controller.ArtistiController;
import Controller.LoginController;
import Utils.IServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;

public class StartClient extends Application {
    private Stage primaryStage;


    public void start(Stage primaryStage) throws Exception {

        try {

            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IServer server=(IServer) factory.getBean("festivaluriService");
            System.out.println("Obtained a reference to remote chat server");

            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/MainWindow.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            LoginController controll = loader.getController();
            controll.setServer(server);
            controll.setStage(dialogStage);

            dialogStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println("Chat Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
