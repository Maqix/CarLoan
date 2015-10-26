package view;

import java.io.IOException;
import java.util.Optional;

import application.AgenziaController;
import application.AutoController;
import application.DipendenteController;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Agenzia;
import model.Auto;
import model.Dipendente;

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
	TableColumn<Agenzia, String> colPartitaIva, colNome, colCitta, colProvincia, colVia, colCivico;
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
	
	private void configuraTabellaAgenzie()
	{
		listaAgenzie = AgenziaController.getListaAgenzia();
		
		if (!listaAgenzie.isEmpty())
		{
			tblAgenzie.setItems(listaAgenzie);
			colPartitaIva.setCellValueFactory(cellData -> cellData.getValue().getPartitaIvaProperty());
			colNome.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			colCitta.setCellValueFactory(cellData -> cellData.getValue().getCittaProperty());
			colProvincia.setCellValueFactory(cellData -> cellData.getValue().getProvinciaProperty());
			colVia.setCellValueFactory(cellData -> cellData.getValue().getViaProperty());
			colCivico.setCellValueFactory(cellData -> cellData.getValue().getCivicoProperty());
		}
	}
	
	private void configuraTabellaDipendenti()
	{
		listaDipendenti = DipendenteController.getListaDipendente();
		
		if (!listaDipendenti.isEmpty())
		{
			tblDipendenti.setItems(listaDipendenti);
			colUsername.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
			colAgenziaDip.setCellValueFactory(cellData -> cellData.getValue().getAgenziaNomeProperty());
			colNomeDip.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
			colCognome.setCellValueFactory(cellData -> cellData.getValue().getCognomeProperty());
			colTelefono.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());
		}
	}
	
	@FXML
	private void apriContratto()
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
	private void chiudiContratto()
	{
		
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
			if (autoSelezionata.getStato() != 2)
			{
				//Se l'utente conferma di voler eliminare l'auto
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK)
				{
					//Elimino l'auto dal DB
					//Se l'operazione sul DB va a buon fine
					if (AutoController.eliminaAuto(autoSelezionata))
					{
						//Elimino l'auto dalla lista
						listaAuto.remove(autoSelezionata);
						//Avviso l'utente
						Main.lanciaInfo("Elimina Auto", "Auto eliminata");
					}else
					{
						//Avviso l'utente che l'operazione non è andata a buon fine
						Main.lanciaWarning("Nessuna Auto eliminata", "C'è stato un problema col Database, contattare l'amministratore");
					}
				}
			}else
			{
				Main.lanciaWarning("Elimina Auto", "Impossibile eliminare un'Auto in uso");
			}
			
		}else
		{
			Main.lanciaWarning("Nessuna Auto selezionata", "Seleziona un'Auto dall'elenco per eliminarla");
		}
	}
	
	@FXML
	private void manutenzioneAuto()
	{
		//Ottengo l'auto selezionata
		Auto autoSelezionata = tblAuto.getSelectionModel().getSelectedItem();
		//Se ho selezionato veramente un auto
		if (autoSelezionata != null)
		{
			//Se l'auto non è in uso
			if (autoSelezionata.getStato() != 2)
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Manutenzione Auto");
				alert.setHeaderText("Seleziona un nuovo stato per l'Auto");
				alert.setContentText("Choose your option.");

				ButtonType buttonTypeOne = new ButtonType("Libera");
				ButtonType buttonTypeTwo = new ButtonType("Ordinaria");
				ButtonType buttonTypeThree = new ButtonType("Straordinaria");
				ButtonType buttonTypeCancel = new ButtonType("Annulla", ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
				
				int statoScelto = 0;
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne){
				    if(AutoController.settaManutenzioneAuto(autoSelezionata, 1))
				    {
				    	statoScelto = 1;
				    }else {Main.lanciaWarning("Impossibile aggiornare l'auto", "Problemi col database");}
				} else if (result.get() == buttonTypeTwo) {
				    if(AutoController.settaManutenzioneAuto(autoSelezionata, 3))
				    {
				    	statoScelto = 3;
				    }else {Main.lanciaWarning("Impossibile aggiornare l'auto", "Problemi col database");}
				} else if (result.get() == buttonTypeThree) {
				    if(AutoController.settaManutenzioneAuto(autoSelezionata, 4))
				    {
				    	statoScelto = 4;
				    }else {Main.lanciaWarning("Impossibile aggiornare l'auto", "Problemi col database");}
				}
				//Cambio lo stato nella lista in ram
				for (Auto autoCorrente: listaAuto)
				{
					if (autoCorrente.getTarga().equals(autoSelezionata.getTarga()))
					{
						autoCorrente.setStato(statoScelto);
					}
				}
				//Aggiorno la tabella
				tblAuto.refresh();
			}else
			{
				Main.lanciaWarning("Manutenzione Auto", "L'Auto è attualmente in uso");
			}
		}else
		{
			Main.lanciaWarning("Manutenzione Auto", "Seleziona una auto per la manutenzione");
		}
	}

	
	@FXML
	private void aggiungiAgenzia()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(HomeAdminViewController.class.getResource("ViewAggiungiAgenzia.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Aggiungi Agenzia");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainApp.primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        AggiungiAgenziaViewController controller =  loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setListaAgenzie(listaAgenzie);
	        
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	      
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}
	
	
	@FXML
	private void eliminaAgenzia()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Elimina Agenzia");
		alert.setHeaderText("Attenzione!");
		alert.setContentText("Sei sicuro di voler eliminare quest'agenzia?");

		//Ottengo l'agenzia selezionata
		Agenzia agenziaSelezionata = tblAgenzie.getSelectionModel().getSelectedItem();
		
		//Se ho selezionato veramente un'agenzia
		if (agenziaSelezionata != null)
		{
			//Se l'utente conferma di voler eliminare l'auto
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				
				//Se l'operazione sul DB va a buon fine
				if (AgenziaController.eliminaAgenzia(agenziaSelezionata))
				{
					//Elimino l'agenzia dalla lista
					listaAgenzie.remove(agenziaSelezionata);
					//Avviso l'utente
					Main.lanciaInfo("Elimina Agenzia", "Agenzia eliminata");
				}else
				{
					//Avviso l'utente che l'operazione non è andata a buon fine
					Main.lanciaWarning("Nessuna agenzia eliminata", "C'� stato un problema col Database, contattare l'amministratore");
				}
			}
			
		}else
		{
			Main.lanciaWarning("Nessuna agenzia selezionata", "Seleziona un'agenzia nell'elenco per eliminarla");
		}
	}

	@FXML
	private void aggiungiDipendente()
	{
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(HomeAdminViewController.class.getResource("ViewAggiungiDipendente.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Aggiungi Dipendente");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.initOwner(mainApp.primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        AggiungiDipendenteViewController controller =  loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setListaDipendenti(listaDipendenti);
	        
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	      
	        
	        return;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }
	}
	
	@FXML
	private void eliminaDipendente()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Elimina Dipendente");
		alert.setHeaderText("Attenzione!");
		alert.setContentText("Sei sicuro di voler eliminare questo dipendente?");

		//Ottengo l'auto selezionata
		Dipendente dipendenteSelezionato = tblDipendenti.getSelectionModel().getSelectedItem();
		
		//Se ho selezionato veramente una Auto
		if (dipendenteSelezionato != null)
		{
				//Se l'utente conferma di voler eliminare il dipendente
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK)
				{
					
					//Se l'operazione sul DB va a buon fine
					if (DipendenteController.eliminaDipendente(dipendenteSelezionato))
					{
						//Elimino il dipendente dalla lista
						listaDipendenti.remove(dipendenteSelezionato);
						//Avviso l'utente
						Main.lanciaInfo("Elimina Dipendente", "Dipendente eliminato");
					}else
					{
						//Avviso l'utente che l'operazione non è andata a buon fine
					    Main.lanciaWarning("Nessun dipendente eliminato", "C'� stato un problema col Database, contattare l'amministratore");
					}
				}
			
		}else
		{
			Main.lanciaWarning("Nessun dipendente eliminato", "Seleziona un dipendente nell'elenco per eliminarlo");
		}
	}
	
	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
