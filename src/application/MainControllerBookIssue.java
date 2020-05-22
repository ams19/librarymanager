package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainControllerBookIssue {

	@FXML
	TextField bookid,studentid;

@FXML
public void click(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {
	
	
	((Node)event.getSource()).getScene().getWindow().hide();
	Parent root;
	Scene scene ;
	Stage primaryStage;

		String bid,id;
		bid=bookid.getText();
		id=studentid.getText();

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/library";
		String user="root";
		String pass="2001";
		Connection con=DriverManager.getConnection(url,user,pass);
	    
	    java.sql.Date issuedate= new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	    Calendar c = Calendar.getInstance(); 
   
	    c.add(Calendar.DATE, 14);
	    java.sql.Date retdate=new java.sql.Date(c.getTimeInMillis());
	    //System.out.println(issuedate); 
	    //System.out.println(retdate); 
	    
	    String query5="Select COUNT(book_id) from issued where roll_no=?";
		PreparedStatement st5=con.prepareStatement(query5);
		st5.setInt(1,Integer.parseInt(id));
		ResultSet rs5=st5.executeQuery();
		rs5.next();
		int i=rs5.getInt(1);
		//System.out.println("hi its i "+i);
		if(i>=2)
		{
			((Node)event.getSource()).getScene().getWindow().hide();
			root=FXMLLoader.load(getClass().getResource("/application/alert.fxml"));
			scene =new Scene(root);
			primaryStage=new Stage();
			primaryStage.setTitle("Alert");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}
		else {
			
			String q1="Select COUNT(book_id) from books where book_id=?";
			PreparedStatement s1=con.prepareStatement(q1);
			s1.setInt(1,Integer.parseInt(bid));
			ResultSet r1=s1.executeQuery();
			String q2="Select COUNT(roll_no) from students_btech where roll_no=?";
			PreparedStatement s2=con.prepareStatement(q2);
			s2.setInt(1,Integer.parseInt(id));
			ResultSet r2=s2.executeQuery();
			r1.next();r2.next();
			if(r1.getInt(1)!=0&&r2.getInt(1)!=0) {
			//System.out.println("IN"+r1.getInt(1)+r2.getInt(1));
			String query="Insert into issued values (?,?,?,?)";
			PreparedStatement st1=con.prepareStatement(query);
			st1.setInt(1,Integer.parseInt(bid));
			st1.setInt(2,Integer.parseInt(id));
			st1.setDate(3,issuedate);
			st1.setDate(4,retdate);
			st1.executeUpdate();
			st1.close();
			
			String query2="Select quantity from books where book_id=?";
			PreparedStatement st2=con.prepareStatement(query2);
			st2.setInt(1,Integer.parseInt(bid));
			ResultSet rs=st2.executeQuery();
			rs.next();
			int q=rs.getInt(1);
			q--;
			st2.close();
			String query3="UPDATE books\n" + 
					"SET  quantity= ?\n" + 
					"WHERE book_id=?";
			PreparedStatement st3=con.prepareStatement(query3);
			st3.setInt(1,q);
			st3.setInt(2,Integer.parseInt(bid));
			st3.executeUpdate();
			st3.close();

			((Node)event.getSource()).getScene().getWindow().hide();
			root=FXMLLoader.load(getClass().getResource("/application/successissue.fxml"));
			scene =new Scene(root);
			primaryStage=new Stage();
			primaryStage.setTitle("Issued");
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			else
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				root=FXMLLoader.load(getClass().getResource("/application/alert.fxml"));
				scene =new Scene(root);
				primaryStage=new Stage();
				primaryStage.setTitle("Alert");
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		}
		
		}
}