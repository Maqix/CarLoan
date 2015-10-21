package view;

import java.io.IOException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Auto;
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
	TableView tblAgenzie, tblDipendenti;
	
	@FXML
	TableColumn<Auto, String> colTarga, colModello, colAgenzia;
	
	@FXML
	TableColumn<Auto, Integer> colKm, colFascia, colStato;
	
	//La lista di auto
	private ObservableList<Auto> listaAuto = FXCollections.observableArrayList();

	@FXML
    private void initialize() 
	{
		configuraTabellaAuto();
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
			colAgenzia.setCellValueFactory(cellData -> cellData.getValue().getAgenziaProperty());
			colKm.setCellValueFactory(cellData -> cellData.getValue().getChilometraggioProperty().asObject());
			colStato.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty().asObject());
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
