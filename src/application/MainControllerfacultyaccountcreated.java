package application;
import javafx.application.Application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainControllerfacultyaccountcreated  implements Initializable {
	
	
	Button button1 = new Button();
	public TextField name,emailid,dept;
	public PasswordField pwd;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	} 
	public void click(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
    	String url="jdbc:mysql://localhost:3306/library";
    	String user="root";
    	String pass="2001";
    	Connection con=DriverManager.getConnection(url,user,pass);
//   
//    	
//    	MysqlDataSource dataSource = new MysqlDataSource();
//    	dataSource.setUser("root");
//    	dataSource.setPassword("2001");
//    	dataSource.setServerName("localhost");
//    	dataSource.setDatabaseName("library");
//    	Connection con=dataSource.getConnection();
    	
    	
    	
    	
		String facname,facemail;
		String facpass,facdept;
		facemail=emailid.getText();
		facname=name.getText();
		facdept=dept.getText();
//		sturoll=Integer.parseInt(rollno.getText());
		facpass=pwd.getText();
    	//System.out.println(stuemail+" "+stuname+" "+stubranch+" "+stubranch+" "+sturoll+" "+selyr+" "+selco);
    	
    	//prepared statement
		String query;
		query="insert into faculty values(?,?,?,?)";
    	
    	PreparedStatement st2=con.prepareStatement(query);
    	st2.setString(1,facemail);
    	st2.setString(2,facname);
    	st2.setString(3,facdept);
    	st2.setString(4,facpass);
    	
    	st2.executeUpdate();
    	st2.close();
    	con.close();

		
		//label1.setText(user + " Login");
		Parent root=FXMLLoader.load(getClass().getResource("/application/accountcreated.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Account Created");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	}

