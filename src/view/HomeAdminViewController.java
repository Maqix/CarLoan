package view;

import java.io.IOException;
import java.util.Optional;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Agenzia;
import model.Auto;
import model.Dipendente;
import model.DAO;

public class HomeAdminViewController 
{
	private Main mainApp;
	
	@FXML
	Button btnAggiungiAgenzia, btnRimuoviAgenzia, btnAggiungiDipendente, btnRimuoviDipendente, btnAggiungiAuto, btnRimuoviAuto;
	
	@FXML
	Button btnManutenzioneAuto, btnApriContratto, btnChiudiContratto;
	
	@FXML
	TableView<Auto>  tblAuto;
	
	@FXML
	TableView<Dipendente> tblDipendenti;
	
	@FXML
	TableView<Agenzia> tblAgenzie;
	
	@FXML
	TableColumn<Agenzia, String> colPartitaIva, colNome, colCitta, colProvincia, colVia;
	
	@FXML
	TableColumn<Agenzia, Integer> colCivico;
	
	@FXML
	TableColumn<Dipendente, String> colUsername, colAgenziaDip, colNomeDip, colCognome, colTelefono;
	
	@FXML
	TableColumn<Auto, String> colTarga, colModello, colAgenzia, colStato;
	
	@FXML
	TableColumn<Auto, Integer> colKm, colFascia;
	
	//La lista di auto
	private ObservableList<Auto> listaAuto = FXCollections.observableArrayList();
	
	//La lista di agenzie
	private ObservableList<Agenzia> listaAgenzie = FXCollections.observableArrayList();
	
	//La lista di dipendenti
	private ObservableList<Dipendente> listaDipendenti = FXCollections.observableArrayList();
	


	@FXML
    private void initialize() 
	{
		configuraTabellaAuto();
		configuraTabellaAgenzie();
		configuraTabellaDipendenti();

    }
	
	private void configuraTabellaAuto()
	{
		listaAuto = DAO.getListaAuto();
		
		if (!listaAuto.isEmpty())
		{
			tblAuto.setItems(listaAuto);
			colTarga.setCellValueFactory(cellData -> cellData.getValue().getTargaProperty());
			colFascia.setCellValueFactory(cellData -> cellData.getValue().getFasciaProperty().asObject());
			colModello.setCellValueFactory(cellData -> cellData.getValue().getModelloProperty());
			colAgenzia.setCellValueFactory(cellData -> cellData.getValue().getAgenziaNomeStringProperty());
			colKm.setCellValueFactory(cellData -> cellData.getValue().getChilometraggioProperty().asObject());
			colStato.setCellValueFactory(cellData -> cellData.getValue().getStatoStringProperty());
		}
	}
	
	private void configuraTabellaAgenzie()
	{
		listaAgenzie = DAO.getListaAgenzie();
		
		if (!listaAgenzie.isEmpty())
		{
			tblAgenzie.setItems(listaAgenzie);
			colPartitaIva.setCellValueFactory(cellData -> cellData.getValue().getPartitaIvaProperty());
			colNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			//colCitta.setCellValueFactory(cellData -> cellData.getValue().getCittaProperty());
			colProvincia.setCellValueFactory(cellData -> cellData.getValue().getProvinciaProperty());
			colVia.setCellValueFactory(cellData -> cellData.getValue().getViaProperty());
			colCivico.setCellValueFactory(cellData -> cellData.getValue().getCivicoProperty().asObject());
		}
	}
	
	private void configuraTabellaDipendenti()
	{
		listaDipendenti = DAO.getListaDipendenti();
		
		if (!listaDipendenti.isEmpty())
		{
			tblDipendenti.setItems(listaDipendenti);
			colUsername.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
			colAgenziaDip.setCellValueFactory(cellData -> cellData.getValue().getAgenziaProperty());
			colNomeDip.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			colCognome.setCellValueFactory(cellData -> cellData.getValue().getCognomeProperty());
			colTelefono.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());
		}
	}
	

	
	@FXML
	private void aggiungiAuto()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(HomeAdminViewController.class.getResource("ViewAggiungiAuto.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Aggiungi Auto");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainApp.primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        AggiungiAutoViewController controller =  loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setListaAuto(listaAuto);
	        
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	      
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}
	
	@FXML
	private void eliminaAuto()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Elimina Auto");
		alert.setHeaderText("Attenzione!");
		alert.setContentText("Sei sicuro di voler eliminare quest'auto?");

		//Ottengo l'auto selezionata
		Auto autoSelezionata = tblAuto.getSelectionModel().getSelectedItem();
		
		//Se ho selezionato veramente una Auto
		if (autoSelezionata != null)
		{
			//Se l'utente conferma di voler eliminare l'auto
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				//Elimino l'auto dal DB
				String comando = String.format("DELETE FROM `auto` WHERE `Targa` IN ('%s')", autoSelezionata.getTarga());
				
				//Se l'operazione sul DB va a buon fine
				if (DAO.esegui(comando))
				{
					//Elimino l'auto dalla lista
					listaAuto.remove(autoSelezionata);
					//Avviso l'utente
					Alert alert3 = new Alert(AlertType.INFORMATION);
					alert3.setTitle("Elimina Auto");
					alert3.setHeaderText("Auto eliminata");
					alert3.setContentText(null);
					alert3.showAndWait();
				}else
				{
					//Avviso l'utente che l'operazione non è andata a buon fine
					Alert alert4 = new Alert(AlertType.WARNING);
					alert4.setTitle("Elimina Auto");
					alert4.setHeaderText("Nessuna auto eliminata");
					alert4.setContentText("C'è stato un problema col Database, contattare l'amministratore");
					alert4.showAndWait();
				}
			}
			
		}else
		{
			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Elimina Auto");
			alert2.setHeaderText("Nessuna auto selezionata");
			alert2.setContentText("Seleziona una auto nell'elenco per eliminarla");
			alert2.showAndWait();
		}
	}
	
	@FXML
	private void manutenzioneAuto()
	{
		
	}
	
	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
