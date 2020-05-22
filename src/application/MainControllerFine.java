package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainControllerFine implements Initializable {

	@FXML
	Label fine;
	static String txt;

@FXML
public void click (ActionEvent event) throws IOException{
	
	((Node)event.getSource()).getScene().getWindow().hide();
	Parent root=FXMLLoader.load(getClass().getResource("/application/successreturn.fxml"));
	Scene scene =new Scene(root);
	Stage primaryStage=new Stage();
	primaryStage.setTitle("Success");
	primaryStage.setScene(scene);
	primaryStage.show();
    
	}

public static void setvalue(float charges){
	// TODO Auto-generated method stub
	txt=String.valueOf(charges);
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	fine.setText(txt);
}
}