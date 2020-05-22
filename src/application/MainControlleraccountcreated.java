package application;
import javafx.application.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainControlleraccountcreated  implements Initializable {
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		
	} 
	public void clicktologin(ActionEvent event) throws IOException {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Parent root=FXMLLoader.load(getClass().getResource("/application/login.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	}

