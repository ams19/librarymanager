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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainControllerDashboard  implements Initializable {
	
	@FXML
	public Label sname,sroll,sbranch,syear,label1;
	static String Id;
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
		// TODO Auto-generated method stub
		//System.out.println(Id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/library";
			String user="root";
			String pass="2001";
			Connection con=DriverManager.getConnection(url,user,pass);
			String query="Select name,roll_no,branch,year,degree from students_btech where email_id=?";
			PreparedStatement st1=con.prepareStatement(query);
			st1.setString(1,Id);
			//System.out.println(st1);
			String sqname,sqroll,sqbranch,sqyr,sqdegree;
			ResultSet rs1=st1.executeQuery();
			rs1.next();
			sqname=rs1.getString(1);
			sqroll=rs1.getString(2);
			sqbranch=rs1.getString(3);
			sqyr=rs1.getString(4);
			sqdegree=rs1.getString(5);
			//System.out.println(sqdegree);
			sqname=sqname.toUpperCase();
			sqbranch=sqbranch.toUpperCase();
			sqdegree=sqdegree.toUpperCase();
			sqyr=sqyr.toUpperCase();
			//System.out.println(sqname);
			sname.setText(sqname);
			sroll.setText(sqroll);
			sbranch.setText(sqbranch);
			syear.setText(sqyr+" YEAR, "+sqdegree);
			st1.close();
			
		    //String query3="Select books.book_id,books.book_name,books.author_name,books.publisher_name,books.department,issued.issued_on,issued.due_return from books INNER JOIN issued ON issued.";
		
			String query3="Select book_id,issued_on,due_return from issued where roll_no=?";
			PreparedStatement st3=con.prepareStatement(query3);
			st3.setString(1,sqroll);
			//System.out.println(st1);
			//String sqname,sqroll,sqbranch,sqyr,sqdegree;
			ResultSet rs3=st3.executeQuery();
			int bid1;
			java.sql.Date issuedate,retdate;
			String str1="",str2=""; 
			if(rs3.next()) {
				
				bid1=rs3.getInt(1);
				issuedate=rs3.getDate(2);
				retdate=rs3.getDate(3);
				String query4="Select book_id,book_name,author_name,publisher_name,department from books where book_id=?";
				PreparedStatement st4=con.prepareStatement(query4);
				st4.setInt(1,bid1);
				//System.out.println(st1);
				//String sqname,sqroll,sqbranch,sqyr,sqdegree;
				ResultSet rs4=st4.executeQuery();
				rs4.next();
				int resid=rs4.getInt(1);
				String resbook=rs4.getString(2);
				String resauthor=rs4.getString(3);
				String respub=rs4.getString(4);
				String resdept=rs4.getString(5);
				
//				String query5="Select issued_on,due_return from issued where book_id=? and roll_no=?";
//				PreparedStatement st5=con.prepareStatement(query5);
//				st5.setInt(1,bid1);st5.setInt(1,bid1);
//				//System.out.println(st1);
//				//String sqname,sqroll,sqbranch,sqyr,sqdegree;
//				ResultSet rs4=st4.executeQuery();
//				rs4.next();
//				int resid=rs4.getInt(1);
//				
				String idate,rdate;
				idate=issuedate.toString();
				rdate=retdate.toString();
				st4.close();
				str1+=resbook+ " by "+resauthor +", Book Id: "+resid +", Published by " +respub +", Department- "+resdept+"\n"+"Issued on: "+idate+"  Due return date: "+rdate+"\n"+"\n";
			    
				if(rs3.next()) {
					int bid2=rs3.getInt(1);
					issuedate=rs3.getDate(2);
					retdate=rs3.getDate(3);
				    //System.out.println("bid1"+bid1+" bid2"+bid2);
					
					String query5="Select book_id,book_name,author_name,publisher_name,department from books where book_id=?";
					PreparedStatement st5=con.prepareStatement(query5);
					st5.setInt(1,bid2);
					//System.out.println(st1);
					//String sqname,sqroll,sqbranch,sqyr,sqdegree;
					ResultSet rs5=st5.executeQuery();
					rs5.next();
					int resid2=rs5.getInt(1);
					String resbook2=rs5.getString(2);
					String resauthor2=rs5.getString(3);
					String respub2=rs5.getString(4);
					String resdept2=rs5.getString(5);
					idate=issuedate.toString();
					rdate=retdate.toString();
					st5.close();
					str2+=resbook2+ " by "+resauthor2 +", Published by " +respub2 +", Department- "+resdept2+"\n"+"Issued on: "+idate+"  Due return date: "+rdate+"\n"+"\n" ;
				}
			}
			
			String str3=str1+str2;
			label1.setText(str3);
		    st3.close();
		    
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

