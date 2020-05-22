package application;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//import com.mysql.cj.xdevapi.PreparableStatement;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
public class Main extends Application implements Initializable {
    public static void main(String[] args) throws Exception{
    	launch(args);	
    }
	
	@FXML
	public ComboBox<String> combo_box;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String>options=FXCollections.observableArrayList("Student","Faculty","Library Staff");
		
		combo_box.setItems(options);
		
	}
public void start(Stage primaryStage) throws Exception {
	
	    Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene =new Scene(root);
		primaryStage.setTitle("NITJ Library");
		primaryStage.setScene(scene);
		primaryStage.show();
		
        //Parent root2=FXMLLoader.load(getClass().getResource("/home/ams/eclipse-workspace/library_management/bin/application/accounts.fxml"));
        //Scene scene2=new Scene(root2);
		
		
		//button1.setOnAction(e->primaryStage.setScene(scene2));
      
	}


}
	
