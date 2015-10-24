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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Agenzia;
import model.Auto;
import model.DAO;
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
			colCitta.setCellValueFactory(cellData -> cellData.getValue().getCittaProperty());
			colProvincia.setCellValueFactory(cellData -> cellData.getValue().getProvinciaProperty());
			colVia.setCellValueFactory(cellData -> cellData.getValue().getViaProperty());
			colCivico.setCellValueFactory(cellData -> cellData.getValue().getCivicoProperty());
		}
	}
	
	private void configuraTabellaDipendenti()
	{
		listaDipendenti = DAO.getListaDipendenti();
		
		if (!listaDipendenti.isEmpty())
		{
			tblDipendenti.setItems(listaDipendenti);
			colUsername.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
			colAgenziaDip.setCellValueFactory(cellData -> cellData.getValue().getAgenziaNomeStringProperty());
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
			if (autoSelezionata.getStato() != 2)
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
				Main.lanciaWarning("Elimina Auto", "Impossibile eliminare un'Auto in uso");
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

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne){
				    settaManutenzioneAuto(autoSelezionata.getTarga(), 1);
				} else if (result.get() == buttonTypeTwo) {
					settaManutenzioneAuto(autoSelezionata.getTarga(), 3);
				} else if (result.get() == buttonTypeThree) {
					settaManutenzioneAuto(autoSelezionata.getTarga(), 4);
				}
			}else
			{
				Main.lanciaWarning("Manutenzione Auto", "L'Auto è attualmente in uso");
			}
		}else
		{
			Main.lanciaWarning("Manutenzione Auto", "Seleziona una auto per la manutenzione");
		}
	}
	
	private void settaManutenzioneAuto(String targa, int stato)
	{
		//Aggiorno l'auto nel DB
		String comando = String.format("UPDATE `auto` SET `Stato` = '%d' WHERE `Targa` = '%s';", stato,targa);
		if (DAO.esegui(comando))
		{
			//Cambio lo stato nella lista in ram
			for (Auto autoCorrente: listaAuto)
			{
				if (autoCorrente.getTarga().equals(targa))
				{
					autoCorrente.setStato(stato);
				}
			}
			//Aggiorno la tabella
			tblAuto.refresh();
		}else
		{
			Main.lanciaWarning("Impossibile aggiornare l'auto", "Problemi col database");
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
				//Elimino l'agenzia dal DB
				String comando = String.format("DELETE FROM `agenzia` WHERE `PartitaIVA` IN ('%s')", agenziaSelezionata.getPartitaIva());
				
				//Se l'operazione sul DB va a buon fine
				if (DAO.esegui(comando))
				{
					//Elimino l'agenzia dalla lista
					listaAgenzie.remove(agenziaSelezionata);
					//Avviso l'utente
					Alert alert3 = new Alert(AlertType.INFORMATION);
					alert3.setTitle("Elimina Agenzia");
					alert3.setHeaderText("Agenzia eliminata");
					alert3.setContentText(null);
					alert3.showAndWait();
				}else
				{
					//Avviso l'utente che l'operazione non è andata a buon fine
					Alert alert4 = new Alert(AlertType.WARNING);
					alert4.setTitle("Elimina Agenzia");
					alert4.setHeaderText("Nessuna agenzia eliminata");
					alert4.setContentText("C'� stato un problema col Database, contattare l'amministratore");
					alert4.showAndWait();
				}
			}
			
		}else
		{
			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Elimina Agenzia");
			alert2.setHeaderText("Nessuna agenzia selezionata");
			alert2.setContentText("Seleziona un'agenzia nell'elenco per eliminarla");
			alert2.showAndWait();
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
		
	}
	
	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
