package application;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class maincontrollerpendingreturns implements Initializable {
	@FXML
	Label label1;
	String[] recepients = new String[100];
	int i=0;
	@Override
	public void initialize(URL location, ResourceBundle resources){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/library";
			String user="root";
			String pass="2001";
			Connection con=DriverManager.getConnection(url,user,pass);
		
			String query="Select roll_no,book_id,issued_on,due_return from issued";
		
			PreparedStatement st1=con.prepareStatement(query);
			ResultSet rs1=st1.executeQuery();
			String result="";
			java.sql.Date issuedate,retdate;
			int rollno,bookid;
			java.sql.Date date= new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			
			while(rs1.next())
			{
				rollno=rs1.getInt(1);
				bookid=rs1.getInt(2);
				issuedate=rs1.getDate(3);
				retdate=rs1.getDate(4);
				if(date.compareTo(retdate)>=0) {
					result+="Roll no: "+rollno+" Book Id: "+bookid+" Issued On: "+issuedate.toString()
								+" Due Return Date: "+retdate.toString() +"\n";
					
					String query2="Select email_id from students_btech where roll_no=?";
					PreparedStatement st2=con.prepareStatement(query2);
					st2.setInt(1,rollno);
					ResultSet rs2=st2.executeQuery();
					rs2.next();
					recepients[i]=rs2.getString(1);
					i++;
					label1.setText(result);
					
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void click(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.setup.port","587");
		
		String myAccountEmail="anmolmaniksingh@gmail.com";
		String password="iphone6sams";
		
		Session session=Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail,password);
			}
		});
		
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			int j=0;
			while(j<i) {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(recepients[j]));
				j++;
			}
			message.setSubject("NITJ Library");
			message.setText("Alert!! You have a pending book to be returned to the library.Return it as soon as possible.");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root=FXMLLoader.load(getClass().getResource("/application/pendingreturnssuccess.fxml"));
		Scene scene =new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setTitle("Pending Returns");
		primaryStage.setScene(scene);
		primaryStage.show();

		
	}
};


