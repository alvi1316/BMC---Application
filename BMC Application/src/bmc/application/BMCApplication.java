
package bmc.application;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BMCApplication extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        File file = new File("DataBase");
        if(!file.exists()){
            file.mkdir();
        }
        file = new File("Save");
        if(!file.exists()){
            file.mkdir();
        }
        stage=primaryStage;
        startPage();
    }
    public void startPage() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StartPageFXML.fxml"));
        Parent root = loader.load();
        StartPageFXMLController controller = loader.getController();
        controller.setBmc(this);
        stage.setTitle("BMC Application");
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
