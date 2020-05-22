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

public class MainController1 implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
public void clicknew(ActionEvent event) throws IOException {
		
	
		((Node)event.getSource()).getScene().getWindow().hide();
		//label1.setText(user + " Login");
		Parent root=FXMLLoader.load(getClass().getResource("/application/newuser.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Create Account");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

public void clicklogin(ActionEvent event) throws IOException {
	
	((Node)event.getSource()).getScene().getWindow().hide();
	//label1.setText(user + " Login");
	
	Parent root=FXMLLoader.load(getClass().getResource("/application/Account.fxml"));
	Scene scene =new Scene(root);
	Stage primaryStage=new Stage();
	primaryStage.setTitle("Dashboard");
	primaryStage.setScene(scene);
	primaryStage.show();
}

}



//label1.setText();
	

