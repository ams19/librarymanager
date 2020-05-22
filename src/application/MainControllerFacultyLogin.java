package application;
import javafx.application.Application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainControllerFacultyLogin  implements Initializable {
	
	@FXML
	public Label sname,sbranch,label1;
	static String id;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//System.out.println(Id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/library";
			String user="root";
			String pass="2001";
			Connection con=DriverManager.getConnection(url,user,pass);
			String query="Select name,department from faculty where faculty_id=?";
			PreparedStatement st1=con.prepareStatement(query);
			st1.setString(1,id);
			//System.out.println(st1);
			String sqname,sqbranch;
			ResultSet rs1=st1.executeQuery();
			rs1.next();
			sqname=rs1.getString(1);
			
			sqbranch=rs1.getString(2);
			
			//System.out.println(sqdegree);
			sname.setText(sqname);
			
			sbranch.setText(sqbranch);
			
			st1.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} 
	public void clicktologout(ActionEvent event) throws IOException {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Parent root=FXMLLoader.load(getClass().getResource("/application/login.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
public void searchbook(ActionEvent event) throws IOException {
		
		
		Parent root=FXMLLoader.load(getClass().getResource("/application/booksearch.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Book Search");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	}

