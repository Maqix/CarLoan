package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Auto;
import model.Cliente;
import model.Contratto;
import application.AutoController;
import application.ClienteController;
import application.ContrattoController;
import application.Main;

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
	//La lista di agenzie
	private ObservableList<Contratto> listaContratti = FXCollections.observableArrayList();
	//La lista di dipendenti
	private ObservableList<Cliente> listaClienti = FXCollections.observableArrayList();

	@FXML
    private void initialize() 
	{
		configuraTabellaAuto();
		//configuraTabellaContratti();
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
	
	
	
	/*
	private void configuraTabellaContratti()
	{
		listaContratti = ContrattoController;
		
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
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
