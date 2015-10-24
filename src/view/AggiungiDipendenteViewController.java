package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Dipendente;

public class AggiungiDipendenteViewController 
{
	@SuppressWarnings("unused")
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
	
	@FXML
	private void premutoAggiungi()
	{
		
	}
	
	@FXML
	private void premutoAnnulla()
	{
		
	}
	
	
	@FXML
	public void initialize()
	{
		
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
