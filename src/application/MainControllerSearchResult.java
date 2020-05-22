package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainControllerSearchResult implements Initializable{

	@FXML
	Label result;
	static String str;
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		
		result.setText(str);
	}
	
    
}

