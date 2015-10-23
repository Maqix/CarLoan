package view;

import java.sql.SQLException;

import application.Main;
import model.Agenzia;
import model.Auto;
import model.DAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AggiungiAgenziaViewController {
	
private Stage dialogStage;
	
	@FXML
	private TextField partitaIvaTF;
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cittaTF;
	@FXML
	private TextField provinciaTF;
	@FXML
	private TextField viaTF;
	@FXML
	private TextField civicoTF;
	@FXML
	private Button aggiungiAgenziaButton;
	@FXML
	private Button annullaButton;
	
	private ObservableList<Agenzia> listaAgenzie;
	
	
	@FXML
	private void aggiungiAgenzia()
	{
		
		
			//Ottengo i dati inseriti
			 String partitaIva= partitaIvaTF.getText();
			 String nome= nomeTF.getText();
			 String citta = cittaTF.getText();
			 String provincia = provinciaTF.getText(); 
			 String via = viaTF.getText();
			 String civico;
			 if((civicoTF.getText().length())==0)
			 { 
				 civico = "s.n.c.";
			 }else
			 {
					 civico = civicoTF.getText();
			 } 
					
			 //Creo l'agenzia da aggiungere
			 Agenzia tempAgenzia = new Agenzia();
			 tempAgenzia.setPartitaIva(partitaIva);
			 tempAgenzia.setNome(nome);
			 tempAgenzia.setCitta(citta);
			 tempAgenzia.setProvincia(provincia);
			 tempAgenzia.setVia(via);
			 tempAgenzia.setCivico(civico);
			 
			 //Se i dati sono corretti
			 if (tempAgenzia.verificaAgenzia().equals(""))
				{
					 String comando = String.format("INSERT INTO `agenzia` (`PartitaIVA`, `Nome`, `Citta`, `Provincia`, `Via`, `Civico`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", partitaIva,nome,citta,provincia,via,civico);
					 if (DAO.esegui(comando))
					 {
						 Main.lanciaInfo("Nuova Agenzia", "Agenzia aggiunta!");
						 listaAgenzie.add(tempAgenzia);
						 dialogStage.close();
					 }else
					 {
						 Main.lanciaWarning("Nuova Agenzia", "Agenzia NON aggiunta!");
					 }
				}else
				{
					Main.lanciaWarning("Impossibile aggiungere agenzia", tempAgenzia.verificaAgenzia());
				}
		}


	
	@FXML
	private void premutAnnulla()
	{
		 dialogStage.close();
	}
	
	  public void setDialogStage(Stage dialogStage) 
	    {
	        this.dialogStage = dialogStage;
	    }

		public ObservableList<Agenzia> getListaAgenzia() {
			return listaAgenzie;
		}

		public void setListaAgenzie(ObservableList<Agenzia> listaAgenzie) {
			this.listaAgenzie = listaAgenzie;
		}
}
