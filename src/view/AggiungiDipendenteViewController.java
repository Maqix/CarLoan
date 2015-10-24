package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO;
import model.Dipendente;

public class AggiungiDipendenteViewController 
{
	private Stage dialogStage;
	
	private ObservableList<Dipendente> listaDipendenti;
	
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cognomeTF;	
	@FXML
	private TextField telefonoTF;	
	@FXML
	private TextField usernameTF;
	@FXML
	private ChoiceBox<String> agenziaCB;
	@FXML
	private PasswordField passTF;
	@FXML 
	private PasswordField repPassTF;
	@FXML
	private Button aggiungiButton;
	@FXML
	private Button annullaButton;
	
	private ObservableList<String> agenzie = FXCollections.observableArrayList();
	
	@FXML
	private void premutoAggiungi()
	{
		
	}
	
	@FXML
	private void premutoAnnulla()
	{
		dialogStage.close();
	}
	
	
	@FXML
	public void initialize()
	{
		configuraPicker();
	}
	
	private void configuraPicker()
	{
		ArrayList<String> listaAgenziePresenti = DAO.getListaString("agenzia", "Nome");
		for (String agenzia: listaAgenziePresenti)
		{
			agenzie.add(agenzia);
		}
		agenziaCB.setItems(agenzie);
		agenziaCB.getSelectionModel().selectFirst();
	}
	
    public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }

	public ObservableList<Dipendente> getListaAuto() {
		return listaDipendenti;
	}

	public void setListaDipendenti(ObservableList<Dipendente> listaDipendenti) {
		this.listaDipendenti = listaDipendenti;
	}
}
