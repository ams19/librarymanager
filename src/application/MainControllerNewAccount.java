package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.MysqlDataSource;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class MainControllerNewAccount  implements Initializable {
	
	@FXML
	public ComboBox<String> year;
	public ComboBox<String> course;
	@FXML
	Button button1 = new Button();
	public TextField name,rollno,emailid,branch;
	public PasswordField pwd;
	
	
	
	public static void main(String[] args) throws Exception{
    	
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		ObservableList<String>yearop=FXCollections.observableArrayList("First","Second","Third","Fourth");
		ObservableList<String>courseop=FXCollections.observableArrayList("B.Tech","M.Tech","Research Scholar");
		
		course.setItems(courseop);
		year.setItems(yearop);
	} 
	public void click(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
		
		
		
		
		//setting up connection
				Class.forName("com.mysql.cj.jdbc.Driver");
		    	String url="jdbc:mysql://localhost:3306/library";
		    	String user="root";
		    	String pass="2001";
		    	Connection con=DriverManager.getConnection(url,user,pass);
//		   
//		    	
//		    	MysqlDataSource dataSource = new MysqlDataSource();
//		    	dataSource.setUser("root");
//		    	dataSource.setPassword("2001");
//		    	dataSource.setServerName("localhost");
//		    	dataSource.setDatabaseName("library");
//		    	Connection con=dataSource.getConnection();
		    	Parent root;
		    	Scene scene ;
		    	Stage primaryStage;

		    	
		    	
		    	String selyr,selco;
		    	selyr=year.getValue();
				selco=course.getValue();
				String stuname,stuemail;
				int sturoll;
				String stupass,stubranch;
				stuemail=emailid.getText();
				stuname=name.getText();
				stubranch=branch.getText();
				stupass=pwd.getText();
				//System.out.println(selyr);
				if(selyr==null||selco==null||stuemail.isEmpty()||stuname.isEmpty()||stubranch.isEmpty()||stupass.isEmpty()||rollno.getText().isEmpty()) {
					//System.out.println("inside");
					root=FXMLLoader.load(getClass().getResource("/application/fillalldetails.fxml"));
					scene =new Scene(root);
					primaryStage=new Stage();
					primaryStage.setTitle("Error");
					primaryStage.setScene(scene);
					primaryStage.show();
					
				}
		    	//System.out.println(stuemail+" "+stuname+" "+stubranch+" "+stubranch+" "+sturoll+" "+selyr+" "+selco);
		    	
		    	//prepared statement
				else {
					if(rollno.getText().isEmpty()) {
						root=FXMLLoader.load(getClass().getResource("/application/fillalldetails.fxml"));
						scene =new Scene(root);
						primaryStage=new Stage();
						primaryStage.setTitle("Error");
						primaryStage.setScene(scene);
						primaryStage.show();
					}
					else {	
				sturoll=Integer.parseInt(rollno.getText());
				if(sturoll<=0)
				{
					root=FXMLLoader.load(getClass().getResource("/application/fillalldetails.fxml"));
					scene =new Scene(root);
					primaryStage=new Stage();
					primaryStage.setTitle("Error");
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				else
				{
				String query;
		    	
				String query2="Select COUNT(roll_no) from students_btech where roll_no=?";
				PreparedStatement st4=con.prepareStatement(query2);
				st4.setInt(1,sturoll);
				ResultSet rs=st4.executeQuery();
				rs.next();
				int c=rs.getInt(1);
				if(c!=0)
				{
					root=FXMLLoader.load(getClass().getResource("/application/alreadyexists.fxml"));
					scene =new Scene(root);
					primaryStage=new Stage();
					primaryStage.setTitle("Error");
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				else
				{
		    	query="insert into students_btech values(?,?,?,?,?,?,?)";
		    	//System.out.println("else");
		    	PreparedStatement st2=con.prepareStatement(query);
		    	st2.setInt(1,sturoll);
		    	st2.setString(2,stuname);
		    	st2.setString(3,stubranch);
		    	st2.setString(4,selyr);
		    	st2.setString(5,stuemail);
		    	st2.setString(6,stupass);
		    	st2.setString(7, selco);
		    	st2.executeUpdate();
		    	st2.close();
		    	con.close();
		    	((Node)event.getSource()).getScene().getWindow().hide();
				root=FXMLLoader.load(getClass().getResource("/application/accountcreated.fxml"));
				scene =new Scene(root);
				primaryStage=new Stage();
				primaryStage.setTitle("Account Created");
				primaryStage.setScene(scene);
				primaryStage.show();
				}
				}
				}
				}
		
	}
}
	
	
	

