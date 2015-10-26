package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ApriContrattoViewController 
{
	private Stage dialogStage;
	
	@FXML
	private ChoiceBox fasciaCB;
	@FXML
	private ChoiceBox noleggioCB;
	@FXML
	private ChoiceBox tariffaCB;
	@FXML
	private ChoiceBox rilascioCB;
	@FXML
	private ChoiceBox ritornoCB;
	@FXML
	private DatePicker dataInizioDF;
	@FXML
	private ChoiceBox clienteCB;
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cognomeTF;
	@FXML
	private TextField telefonoTF;
	@FXML
	private TextField codiceFiscaleTF;
	@FXML
	private TextField autoTF;
	@FXML
	private TextField accontoTF;
	@FXML
	private TextField contrattoTF;
	
	@FXML
	private void initialize()
	{
		
	}
	
	@FXML
	private void premutoGeneraContratto()
	{
		
	}
	
	@FXML
	private void premutoApriContratto()
	{
		
	}
	
	@FXML
	private void premutoAnnulla()
	{
		dialogStage.close();
	}
	
    public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }
}
