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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController  implements Initializable {
	
	@FXML
	
	public ComboBox<String> combo_box;
	@FXML
	Button button1 = new Button();
	public TextField username;
	public PasswordField pwd;
	String userlogin;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		ObservableList<String>options=FXCollections.observableArrayList("Student","Library Staff");
		
		combo_box.setItems(options);
	} 
	
public void clicknew(ActionEvent event) throws IOException {
		
		
		//label1.setText(user + " Login");
		userlogin=combo_box.getValue();
		Stage primaryStage=new Stage();
		
		Parent root=FXMLLoader.load(getClass().getResource("/application/newuser.fxml"));
		if(userlogin=="Student") {
			((Node)event.getSource()).getScene().getWindow().hide();
			root=FXMLLoader.load(getClass().getResource("/application/newuser.fxml"));
			primaryStage.setTitle("Create Account");
		}
		if(userlogin=="Faculty") {
			((Node)event.getSource()).getScene().getWindow().hide();
			root=FXMLLoader.load(getClass().getResource("/application/newfacultyaccount.fxml"));
			primaryStage.setTitle("Create Account");
		}
		if(userlogin=="Library Staff") {
			root=FXMLLoader.load(getClass().getResource("/application/alert.fxml"));
			primaryStage.setTitle("ALERT!!!");
		}
		else if(userlogin==null){
			root=FXMLLoader.load(getClass().getResource("/application/selectalert.fxml"));
			primaryStage.setTitle("ALERT!!!");
		}
		Scene scene =new Scene(root);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

public void clicklogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
	
	
	//label1.setText(user + " Login");
	
	//System.out.println(user);
	userlogin=combo_box.getValue();
	String id=username.getText();
	String password=pwd.getText();
	
	
	MainControllerDashboard student =new MainControllerDashboard();
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/library";
	String user="root";
	String pass="2001";
	Connection con=DriverManager.getConnection(url,user,pass);

	String query1;
	
	Parent root = FXMLLoader.load(getClass().getResource("/application/selectalert.fxml"));
	Stage primaryStage=new Stage();
	primaryStage.setTitle("Dashboard");
	
	//System.out.println(userlogin);
	
	if(userlogin=="Student") {
		
		query1="Select name from students_btech where email_id =? and Password=?";
		
		PreparedStatement st1=con.prepareStatement(query1);
		
		st1.setString(1,id);
		st1.setString(2,password);
		
		//to fetch
		ResultSet rs1=st1.executeQuery();
	
		String ans=null;
		if(rs1.next()) {
			((Node)event.getSource()).getScene().getWindow().hide();
			MainControllerDashboard.Id=id;
			root=FXMLLoader.load(getClass().getResource("/application/Account.fxml"));
			ans=rs1.getString(1);
			//System.out.println(ans);
			
		}
		
		else {
			root=FXMLLoader.load(getClass().getResource("/application/invaliduser.fxml"));
			primaryStage.setTitle("ALERT!!!");
		}
		
		st1.close();
		
	}
	else if(userlogin=="Faculty") {
		
		query1="Select name from faculty where faculty_id =? and password=?";
		PreparedStatement st1=con.prepareStatement(query1);
		st1.setString(1,id);
		st1.setString(2,password);
		MainControllerFacultyLogin.id=id;
//		System.out.println(id+password);
//		System.out.println(st1);
//		
		
		//to fetch
		String ans=null;
		ResultSet rs1=st1.executeQuery();
		if(rs1.next()) {
			ans=rs1.getString(1);
		}
		
			
		//System.out.println(ans);
		
		if(ans==null)
		{
			root=FXMLLoader.load(getClass().getResource("/application/invaliduser.fxml"));
			primaryStage.setTitle("ALERT!!!");
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			root=FXMLLoader.load(getClass().getResource("/application/facultylogin.fxml"));
			rs1.next();
			
		}
	    st1.close();
		
	}
	else if(userlogin=="Library Staff") {
		query1="Select name from libstaff where email =? and password=?";
		PreparedStatement st1=con.prepareStatement(query1);
		st1.setString(1,id);
		st1.setString(2,password);
		MainControllerLibraryStaff.id=id;
		
//		System.out.println(id+password);
//		System.out.println(st1);
//		
		
		//to fetch
		String ans=null;
		ResultSet rs1=st1.executeQuery();
		if(rs1.next()) {
			ans=rs1.getString(1);
		}
		
			
		//System.out.println(ans);
		
		if(ans==null)
		{
			root=FXMLLoader.load(getClass().getResource("/application/invaliduser.fxml"));
			primaryStage.setTitle("ALERT!!!");
		}
		else
		{
			st1.close();
			((Node)event.getSource()).getScene().getWindow().hide();
			root=FXMLLoader.load(getClass().getResource("/application/librarystafflogin.fxml"));
			
		}
	    
	}
	
	else if(userlogin==null){
		root=FXMLLoader.load(getClass().getResource("/application/selectalert.fxml"));
		primaryStage.setTitle("ALERT!!!");
	}
	
	Scene scene =new Scene(root);
	

	primaryStage.setScene(scene);
	primaryStage.show();
	
	con.close();
}

	}

