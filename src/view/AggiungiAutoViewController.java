package view;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Auto;
import model.DAO;

public class AggiungiAutoViewController {
	
	private Stage dialogStage;
	
	@FXML
	private TextField targaTF;
	@FXML
	private TextField modelloTF;
	@FXML
	private TextField chilometraggioTF;
	@FXML
	private ChoiceBox<String> fasciaCB;
	@FXML
	private ChoiceBox<String> agenziaCB;
	@FXML
	private ChoiceBox<String> statoCB;
	@FXML
	private Button aggiungiAutoButton;
	
	private ObservableList<String> fasce = FXCollections.observableArrayList();
	private ObservableList<String> agenzie = FXCollections.observableArrayList();
	private ObservableList<String> stati = FXCollections.observableArrayList();
	
	private ObservableList<Auto> listaAuto;
	
	@FXML
	private void aggiungiAuto()
	{
		if (verificaForm())
		{
			 String targa = targaTF.getText();
			 int fascia = Integer.parseInt(fasce.get(fasciaCB.getSelectionModel().getSelectedIndex()));
			 String modello = modelloTF.getText();
			 String agenzia = agenzie.get(agenziaCB.getSelectionModel().getSelectedIndex());
			 int stato = Integer.parseInt(stati.get(statoCB.getSelectionModel().getSelectedIndex()));
			 int km = Integer.parseInt(chilometraggioTF.getText());
			 
			 String comando = String.format("INSERT INTO `auto` (`Targa`, `Fascia`, `Modello`, `Agenzia`, `Stato`, `Chilometraggio`) VALUES ('%s', %d, '%s', '%s', %d, %d)", targa,fascia,modello,agenzia,stato,km);
			 if (DAO.esegui(comando))
			 {
				 Main.lanciaInfo("Nuova Auto", "Auto aggiunta!");
				 Auto tempAuto = new Auto();
				 tempAuto.setTarga(targa);
				 tempAuto.setFascia(fascia);
				 tempAuto.setModello(modello);
				 tempAuto.setAgenzia(agenzia);
				 tempAuto.setStato(stato);
				 tempAuto.setChilometraggio(km);
				 listaAuto.add(tempAuto);
				 dialogStage.close();
			 }else
			 {
				 Main.lanciaWarning("Nuova Auto", "Auto NON aggiunta!");
				 dialogStage.close();
			 }
		}
	}
	
	@FXML
	private void initialize() 
	{
		configuraPicker();
    }
	
	private boolean verificaForm()
	{
		return true;
	}
	
	private void configuraPicker()
	{
		
		fasce.add("1");
		fasce.add("2");
		fasce.add("3");
		fasce.add("4");
		fasciaCB.setItems(fasce);	
		
		
		agenzie.add("1");
		agenzie.add("2");
		agenzie.add("3");
		agenzie.add("4");
		agenziaCB.setItems(agenzie);
		
		
		stati.add("1");
		stati.add("2");
		stati.add("3");
		statoCB.setItems(stati);
	}
	
    public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }

	public ObservableList<Auto> getListaAuto() {
		return listaAuto;
	}

	public void setListaAuto(ObservableList<Auto> listaAuto) {
		this.listaAuto = listaAuto;
	}

}
