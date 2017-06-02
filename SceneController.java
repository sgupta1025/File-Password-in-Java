package application;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController implements Initializable   {

	@FXML
	PasswordField passwordField = new PasswordField();
	@FXML
	BorderPane borderPane;
	BooleanProperty firstTime =new SimpleBooleanProperty(true); 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		passwordField.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	                try {
						passCheck();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    });
		passwordField.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            
			if(newValue && firstTime.get()){
                borderPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
	}
	public void passCheck() throws URISyntaxException{
		Stage stage = (Stage)borderPane.getScene().getWindow();
		String password = passwordField.getText();
		Desktop desktop = Desktop.getDesktop();
		if(password.equals(String.valueOf(20000))){
			File file = new File("sol2.png");
			try {
				desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.close();
		}
		else if(password.equals("Clot")){
			File file = new File("sol1.docx");
			try {
				desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.close();
		}
		else{
			passwordField.clear();
			borderPane.requestFocus();
			passwordField.setPromptText("Try again!");
		}
	}
}
