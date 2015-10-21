package view;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	
	
	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
