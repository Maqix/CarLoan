package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ApriContrattoViewController 
{
	private Stage dialogStage;
	
	@FXML
	private void initialize()
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
