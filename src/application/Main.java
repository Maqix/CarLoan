package application;
	
import java.io.IOException;
import java.net.URL;

import com.sun.javafx.css.StyleManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DAO;


public class Main extends Application {
	
	public Stage primaryStage;
    private AnchorPane rootLayout;
    private Scene scenaLogin;
    
    public String utenteLoggato = "";
	
	@Override
	public void start(Stage primaryStage) {
			
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("CarLoan");
			this.primaryStage.setResizable(false);
			
			try {
				DAO.connetti();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("Interfaccia.fxml"));
	            rootLayout = (AnchorPane) loader.load();

	            MainController controller = loader.getController();
	            controller.setMainApp(this);
	            
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            
	            //Salvo la scena con la schermata di login
	            scenaLogin = scene;
	            
	            primaryStage.setScene(scene);
	            
	            
	            primaryStage.show();
	            

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//Chiamata da altre classi per reimpostare la vista di Login
	//@SuppressWarnings("unused")
	public void lanciaLogin()
	{
		primaryStage.setTitle("CarLoan");
		primaryStage.setScene(scenaLogin);
		primaryStage.centerOnScreen();
	}
	
	public static void lanciaWarning(String titolo, String contenuto) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Attenzione");
		alert.setHeaderText(titolo);
		alert.setContentText(contenuto);
		alert.showAndWait();
	}
	
	public static void lanciaInfo(String titolo, String contenuto) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informazione");
		alert.setHeaderText(titolo);
		alert.setContentText(contenuto);
		alert.showAndWait();
	}
	
	public void cambiaTema(boolean nightmode)
	{
		if (nightmode)
		{
	        URL url = this.getClass().getResource("nightmode.css");
	        if (url == null) {
	            System.out.println("Resource not found. Aborting.");
	            System.exit(-1);
	        }
	        String css = url.toExternalForm(); 
	        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
	        StyleManager.getInstance().addUserAgentStylesheet(css);
		}else
		{
	        URL url = this.getClass().getResource("daymode.css");
	        if (url == null) {
	            System.out.println("Resource not found. Aborting.");
	            System.exit(-1);
	        }
	        String css = url.toExternalForm(); 
	        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
	        StyleManager.getInstance().addUserAgentStylesheet(css);
		}
	}
}
