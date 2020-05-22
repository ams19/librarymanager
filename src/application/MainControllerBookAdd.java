package application;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainControllerBookAdd {

	
	@FXML
	TextField bookname,authorname,publisher,department,id,qty,loc;
	
	@FXML
	public void add(javafx.event.ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
		
		((Node)event.getSource()).getScene().getWindow().hide();
	Parent root;
	Scene scene ;
	Stage primaryStage;

	
		String book,author,pub,dept,bid,quantity,loca;
		book=bookname.getText();
		author=authorname.getText();
		pub=publisher.getText();
		dept=department.getText();
		bid=id.getText();
		quantity=qty.getText();
		loca=loc.getText();
		
		if(book.isEmpty()||author.isEmpty()||pub.isEmpty()||dept.isEmpty()||bid.isEmpty()||quantity.isEmpty()||loca.isEmpty()) {
			
			root=FXMLLoader.load(getClass().getResource("/application/fillalldetails.fxml"));
			scene =new Scene(root);
			primaryStage=new Stage();
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/library";
		String user="root";
		String pass="2001";
		Connection con=DriverManager.getConnection(url,user,pass);
		String query="Insert into books values (?,?,?,?,?,?,?)";
		PreparedStatement st1=con.prepareStatement(query);
		st1.setInt(1,Integer.parseInt(bid));
		st1.setString(2,book);
		st1.setString(3,author);
		st1.setString(4,pub);
		st1.setInt(5,Integer.parseInt(quantity));
		st1.setString(6,loca);
		st1.setString(7,dept);
		st1.executeUpdate();
    	st1.close();
    	con.close();
    	root=FXMLLoader.load(getClass().getResource("/application/success.fxml"));
    	scene =new Scene(root);
    	primaryStage=new Stage();
    	primaryStage.setTitle("Success");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	
		}
	}
	

	}
