package view;

import java.io.IOException;
import java.util.Optional;

import application.AutoController;
import application.ClienteController;
import application.ContrattoController;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Auto;
import model.Cliente;
import model.Contratto;

public class HomeImpiegatoViewController {

	private Main mainApp;
	@FXML
	TableView<Auto>  tblAuto;
	@FXML
	TableView<Contratto> tblContratto;
	@FXML
	TableView<Cliente> tblCliente;
	@FXML
	TableColumn<Auto, String> colTarga, colModello, colAgenzia, colStato;
	@FXML
	TableColumn<Auto, Integer> colKm, colFascia;
	@FXML
	TableColumn<Contratto, String> colAuto, colAgenziaAp,colAgenziaCh,colDataInizio,colDataFine;
	@FXML
	TableColumn<Contratto, String> colCliente, colTipoNoleggio, colTipoChilometraggio;
	@FXML
	TableColumn<Contratto,Integer> colID, colAcconto, colKmIniziali;
	@FXML
	TableColumn<Cliente, String> colCF, colNome, colCognome, colTelefono;
	@FXML
	TableColumn<Cliente,Integer> colContratto;
	
	//La lista di auto
	private ObservableList<Auto> listaAuto = FXCollections.observableArrayList();
	//La lista di contratti
	private ObservableList<Contratto> listaContratti = FXCollections.observableArrayList();
	//La lista di dipendenti
	private ObservableList<Cliente> listaClienti = FXCollections.observableArrayList();

	@FXML
    private void initialize() 
	{
		configuraTabellaAuto();
		configuraTabellaContratti();
		configuraTabellaClienti();

    }
	
	private void configuraTabellaAuto()
	{
		listaAuto = AutoController.getListaAuto();
		
		if (!listaAuto.isEmpty())
		{
			tblAuto.setItems(listaAuto);
			colTarga.setCellValueFactory(cellData -> cellData.getValue().getTargaProperty());
			colFascia.setCellValueFactory(cellData -> cellData.getValue().getFasciaProperty().asObject());
			colModello.setCellValueFactory(cellData -> cellData.getValue().getModelloProperty());
			colAgenzia.setCellValueFactory(cellData -> cellData.getValue().getNomeAgenziaProperty());
			colKm.setCellValueFactory(cellData -> cellData.getValue().getChilometraggioProperty().asObject());
			colStato.setCellValueFactory(cellData -> cellData.getValue().getStatoStringProperty());
		}
	}
	
	private void configuraTabellaClienti()
	{
		listaClienti = ClienteController.getListaCliente();
		
		if (!listaClienti.isEmpty())
		{
			tblCliente.setItems(listaClienti);
			colCF.setCellValueFactory(cellData -> cellData.getValue().getCFProperty());
			colNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			colCognome.setCellValueFactory(cellData -> cellData.getValue().getCognomeProperty());
			colTelefono.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());
			colContratto.setCellValueFactory(cellData -> cellData.getValue().getContrattoProperty().asObject());
			
		}
	}
	
	
	
	
	private void configuraTabellaContratti()
	{
		listaContratti = ContrattoController.getListaContratti();
		
		if (!listaContratti.isEmpty())
		{
			tblContratto.setItems(listaContratti);
			colID.setCellValueFactory(cellData -> cellData.getValue().idContrattoProperty().asObject());
			colAuto.setCellValueFactory(cellData -> cellData.getValue().autoProperty());
			colAgenziaAp.setCellValueFactory(cellData -> cellData.getValue().getNomeAgenziaApProperty());
			colAgenziaCh.setCellValueFactory(cellData -> cellData.getValue().getNomeAgenziaChProperty());
			colDataInizio.setCellValueFactory(cellData -> cellData.getValue().dataInizioProperty());
			colDataFine.setCellValueFactory(cellData -> cellData.getValue().dataFineProperty());
			colCliente.setCellValueFactory(cellData -> cellData.getValue().clienteProperty());
			colTipoNoleggio.setCellValueFactory(cellData -> cellData.getValue().tipoNoleggioProperty());
			colTipoChilometraggio.setCellValueFactory(cellData -> cellData.getValue().tipoChilometraggioProperty());
		}
	}
	
	
	@FXML
	public void apriContratto()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(HomeAdminViewController.class.getResource("ViewApriContratto.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Apri Contratto");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainApp.primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        ApriContrattoViewController controller =  loader.getController();
	        controller.setDialogStage(dialogStage);
	        
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	      
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}
	
	@FXML
	public void chiudiContratto()
	{
		
	}
	
	@FXML
	private void premutoLogout()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Attenzione!");
		alert.setContentText("Sei sicuro di voler effettuare il logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			mainApp.lanciaLogin();
		}
	}
	
	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
