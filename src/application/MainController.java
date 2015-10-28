package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DAO;
import utility.Crittografia;
import view.DialogVerificaViewController;
import view.HomeAdminViewController;
import view.HomeImpiegatoViewController;
import view.RegistraAdminViewController;

public class MainController {
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Button registratiButton;
	 
	@FXML
	private TextField usernameTF;
	
	@FXML
	private PasswordField pswdTF;
	
	
	//@SuppressWarnings("unused")
	private Main  mainApp;
	
	public MainController(){}
	
	@FXML
    private void initialize() 
	{
		
    }
	
	@FXML
	private void premutoLogin()
	{
		if (isAdmin(usernameTF.getText(),pswdTF.getText()))
		{
			//Lancia HomeAdmin
			mainApp.utenteLoggato = usernameTF.getText();
			lanciaHomeAdmin();
		}else if (isDipendente(usernameTF.getText(),pswdTF.getText()))
		{
			//Lancia HomeDipendente
			mainApp.utenteLoggato = usernameTF.getText();
			lanciaHomeImpiegato();
		}else
		{
			Main.lanciaWarning("Username o Password errati", "");
		}
		
	}
	
	@FXML
	private void premutoRegistrati()
	{
		if (isAuthorized())
		{
			lanciaRegistraAdmin();
		}
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
	private boolean isAuthorized()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(DialogVerificaViewController.class.getResource("DialogVerifica.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Verifica Admin");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainApp.primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        DialogVerificaViewController controller =  loader.getController();
	        controller.setDialogStage(dialogStage);
	        
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isAuth();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private void lanciaRegistraAdmin()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(DialogVerificaViewController.class.getResource("ViewRegistraAdmin.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Scene scene = new Scene(page);
	        mainApp.primaryStage.setTitle("Registra Admin");
	        mainApp.primaryStage.setScene(scene);

	        
	        RegistraAdminViewController controller =  loader.getController();
	        controller.setMainApp(mainApp);
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}

	private void lanciaHomeAdmin()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(DialogVerificaViewController.class.getResource("ViewHomeAdmin.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Scene scene = new Scene(page);
	        mainApp.primaryStage.setTitle("Home Admin");
	        mainApp.primaryStage.setScene(scene);
	        mainApp.primaryStage.centerOnScreen();

	        
	        HomeAdminViewController controller =  loader.getController();
	        controller.setMainApp(mainApp);
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}
	
	private void lanciaHomeImpiegato()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(DialogVerificaViewController.class.getResource("ViewHomeImpiegato.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Scene scene = new Scene(page);
	        mainApp.primaryStage.setTitle("Home Impiegato");
	        mainApp.primaryStage.setScene(scene);
	        mainApp.primaryStage.centerOnScreen();
	     


	        
	        HomeImpiegatoViewController controller =  loader.getController();
	        controller.setMainApp(mainApp);
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}
	
	private boolean isAdmin(String nome, String pswd)
	{
		boolean risposta = false;
		String enPswd = Crittografia.encrypt(pswd);
		//Cerco se nel DB è presente già un admin con questo nome
		String comando = String.format("SELECT Username FROM admin WHERE Username = '%s' AND Password = '%s'", nome,enPswd);
		try {
			//DAO.connetti();
			risposta = DAO.cerca(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return risposta;
	}
	
	private boolean isDipendente(String nome, String pswd)
	{
		boolean risposta = false;
		String enPswd = Crittografia.encrypt(pswd);
		//Cerco se nel DB è presente già un dipendente con questo nome
		String comando = String.format("SELECT Username FROM dipendente WHERE Username = '%s' AND Password = '%s'", nome,enPswd);
		try {
			//DAO.connetti();
			risposta = DAO.cerca(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return risposta;
	}

	
}
