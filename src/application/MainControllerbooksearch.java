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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainControllerbooksearch  implements Initializable {
	
	@FXML
	TextField bookname,authorname,publisher,department;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		
		
	} 
	
public void searchbook(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/library";
	String user="root";
	String pass="2001";
	Connection con=DriverManager.getConnection(url,user,pass);
	
	    
	 	String book=bookname.getText();
	 	String author=authorname.getText();
	 	String pub=publisher.getText();
	 	String dept=department.getText();
	 	//System.out.println(authorname.getText());
	 	
	 	String resbook="",resauthor="",resloc="",resdept="",respub="";
		int resid = 0,resqty=0;
	 	String query3;
	 	ResultSet rs3;
		PreparedStatement st3;
		String result="";
		//System.out.println(resid+resqty+resbook+resauthor+respub+resloc+resdept);
		
		if((!book.isEmpty())&&(!author.isEmpty())&&(!pub.isEmpty())&&(!dept.isEmpty()))
		{
			//System.out.println(4);
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=? and author_name=? and publisher_name=? and department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			st3.setString(2,author);
			st3.setString(3,pub);
			st3.setString(4,dept);
			rs3=st3.executeQuery();
			if(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			else {
				;
			}
			st3.close();
		}
		else if((!book.isEmpty())&&(!author.isEmpty())&&(!pub.isEmpty()))
		{
			//System.out.println(3);
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=? and author_name=? and publisher_name=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			st3.setString(2,author);
			st3.setString(3,pub);
			rs3=st3.executeQuery();
			if(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!book.isEmpty())&&(!author.isEmpty())&&(!dept.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=? and author_name=? and department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			st3.setString(2,author);
			st3.setString(3,dept);
			rs3=st3.executeQuery();
			if(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!author.isEmpty())&&(!pub.isEmpty())&&(!dept.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where author_name=? and publisher_name=? and department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,author);
			st3.setString(2,pub);
			st3.setString(3,dept);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!book.isEmpty())&&(!author.isEmpty()))
		{
			//System.out.println(2);
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=? and author_name=? ";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			st3.setString(2,author);
			rs3=st3.executeQuery();
			if(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!book.isEmpty())&&(!pub.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=? and publisher_name=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			st3.setString(2,pub);
			rs3=st3.executeQuery();
			if(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!book.isEmpty())&&(!dept.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=?  and department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			st3.setString(2,dept);
			rs3=st3.executeQuery();
			if(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!author.isEmpty())&&(!pub.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where author_name=? and publisher_name=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,author);
			st3.setString(2,pub);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!author.isEmpty())&&(!dept.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where  author_name=? and  department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,author);
			st3.setString(2,dept);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if((!pub.isEmpty())&&(!dept.isEmpty()))
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where publisher_name=? and department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,pub);
			st3.setString(2,dept);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if(!book.isEmpty())
		{
			//System.out.println(1);
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where book_name=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,book);
			rs3=st3.executeQuery();
			if(rs3.next()){
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if(!author.isEmpty())
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where author_name=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,author);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available."+"\n"+"\n";
				}
				else
				{
					result+=" Status: Currently Unvailable."+"\n"+"\n";
				}
			}
			st3.close();
		}
		else if(!pub.isEmpty())
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where publisher_name=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,pub);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		else if(!dept.isEmpty())
		{
			query3="Select book_id,book_name,author_name,publisher_name,quantity,Location,department from books where department=?";
			st3=con.prepareStatement(query3);
			st3.setString(1,dept);
			rs3=st3.executeQuery();
			while(rs3.next()) {
				resid=rs3.getInt(1);
				resqty=rs3.getInt(5);
				resbook=rs3.getString(2);
				resauthor=rs3.getString(3);
				respub=rs3.getString(4);
				resloc=rs3.getString(6);
				resdept=rs3.getString(7);
				result+=resbook+ " by "+resauthor +", Published by " +respub +", Placed in "+resloc+", Department- "+resdept ;
				if(resqty>0)
				{
					result+=" Status: Currently Available.\n ";
				}
				else
				{
					result+=" Status: Currently Unvailable.\n ";
				}
			}
			st3.close();
		}
		
		if(result.isEmpty())
		{
			result="                                No Result";
		}
		MainControllerSearchResult.str=result;
	 	con.close();
	 	((Node)event.getSource()).getScene().getWindow().hide();
		Parent root=FXMLLoader.load(getClass().getResource("/application/booksearchresult.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Book Search Result");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	}

