package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainControllerBookRemove {

	@FXML
	TextField bookid,quantity;
	

	
@FXML
public void click(javafx.event.ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
	
	((Node)event.getSource()).getScene().getWindow().hide();
Parent root=FXMLLoader.load(getClass().getResource("/application/successremove.fxml"));
Scene scene =new Scene(root);
Stage primaryStage=new Stage();
primaryStage.setTitle("Success");
primaryStage.setScene(scene);
primaryStage.show();

	String bid,qty;
	bid=bookid.getText();
	qty=quantity.getText();

	Class.forName("com.mysql.cj.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/library";
	String user="root";
	String pass="2001";
	Connection con=DriverManager.getConnection(url,user,pass);
	String query="Select quantity from books where book_id=?";
	PreparedStatement st1=con.prepareStatement(query);
	st1.setInt(1,Integer.parseInt(bid));
	
	ResultSet rs1=st1.executeQuery();
	rs1.next();
	int q=rs1.getInt(1);
	int fq=q-Integer.parseInt(qty);
	String query2;
	if(fq>0) {
		query2="UPDATE books SET quantity=? WHERE book_id=?";
		PreparedStatement st2=con.prepareStatement(query2);
		st2.setInt(1,fq);
		st2.setInt(2,Integer.parseInt(bid));
		st2.executeUpdate();
		st2.close();
	}
	else {
		query2="DELETE FROM books WHERE book_id=?";
		PreparedStatement st2=con.prepareStatement(query2);
		st2.setInt(1,Integer.parseInt(bid));
		st2.executeUpdate();
		st2.close();
	}
	
	//System.out.println(q);
	st1.close();
	con.close();
	
}
}
