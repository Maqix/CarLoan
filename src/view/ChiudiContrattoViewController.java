package view;

import java.time.LocalDate;

import application.ClienteController;
import application.ContrattoController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contratto;

public class ChiudiContrattoViewController 
{
	private Stage dialogStage;
	
	@FXML
	private ChoiceBox<String> idContrattoCB;
	
	@FXML
	private Label labelIdContratto;
	@FXML
	private Label labelAuto;
	@FXML
	private Label labelTipoNoleggio;
	@FXML
	private Label labelTipoTariffa;
	@FXML
	private Label labelScrittaKmPrevisti;
	@FXML
	private Label labelKmPrevisti;
	@FXML
	private TextField kmAttualiTF;
	@FXML
	private Label labelTotale;
	@FXML
	private DatePicker dataRientroDP;
	
	private Contratto contrattoSelezionato = new Contratto();
	private int kmAttuali = 0;
	private LocalDate dataSelezionata = LocalDate.now();
	
	ContrattoController contrattoController = new ContrattoController();
	
	@FXML
	private void initialize()
	{
		configuraPicker();
		configuraTextField();
		configuraDatePicker();
		labelScrittaKmPrevisti.setVisible(false);
		labelKmPrevisti.setVisible(false);
		labelTotale.setText("0");
	}

	@FXML
	private void premutoChiudiContratto()
	{
		contrattoController.chiudiContratto(ottieniNumContratto());
	}
	
	@FXML
	private void premutoAnnulla()
	{
		dialogStage.close();
	}
	
	private int ottieniNumContratto()
	{
		int risposta = 0;
		risposta = contrattoSelezionato.getIdContratto();
		return risposta;
	}
	
	private void configuraPicker()
	{
		ObservableList<Contratto> listaContratti = ContrattoController.getListaContrattiAperti();
		ObservableList<String> idNomeContratti = FXCollections.observableArrayList();
		for (Contratto contratto:listaContratti)
		{
			idNomeContratti.add(String.valueOf(contratto.getIdContratto())+" - "+ClienteController.getClienteFromCF(contratto.getCliente()).getNome()+" "+ClienteController.getClienteFromCF(contratto.getCliente()).getCognome());
		}
		idContrattoCB.setItems(idNomeContratti);
		idContrattoCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
		      {
		    	  int indice = (Integer) number2;
		    	  contrattoSelezionato = ContrattoController.getContrattoApertoFromIndice(indice);
		    	  configuraContrattoTF();
		      }
		    });
	}
	
	private void configuraTextField()
	{
		//TODO: Fanculo questa merda ci vuole un pulsante
		kmAttualiTF.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) 
            {
            	String testo = newValue;
            	int kmTF = -100;
            	try {
					kmTF = Integer.parseInt(testo);
	            	kmAttuali = kmTF;
	            	String totale = contrattoController.getTotaleContratto(contrattoSelezionato.getIdContratto(),kmAttuali,dataRientroDP.getValue());
	            	labelTotale.setText(totale);
	            	
				} catch (NumberFormatException e) {
					kmTF = -100;
	            	kmAttuali = kmTF;
	            	labelTotale.setText("Inserisci Km");
				}
            }
        });
	}
	
	/*
	private String validaTotale(String totaleStringa)
	{
		String risposta = "Errore!";
		Integer totale = Integer.parseInt(totaleStringa);
		if (totale < Integer.parseInt(contrattoController.getTotaleContratto(contrattoSelezionato.getIdContratto(),kmAttuali,dataRientroDP.getValue())))
		{
			risposta = "Km errati";
		}else
		{
			risposta = "";
		}
		return risposta;
	}
	*/
	
	private void configuraDatePicker()
	{
		dataRientroDP.setValue(LocalDate.now());
		dataRientroDP.setOnAction(event -> {
		    LocalDate dataScelta = dataRientroDP.getValue();
		    if (dataScelta.isBefore(LocalDate.now()))
		    {
		    	dataRientroDP.setValue(LocalDate.now());
		    	labelTotale.setText(contrattoController.getTotaleContratto(contrattoSelezionato.getIdContratto(),kmAttuali,dataRientroDP.getValue()));
		    }
		    setDataSelezionata(dataScelta);
		    labelTotale.setText(contrattoController.getTotaleContratto(contrattoSelezionato.getIdContratto(),kmAttuali,dataScelta));
		});
	}
	
	private void configuraContrattoTF()
	{
		labelIdContratto.setText(String.valueOf(contrattoSelezionato.getIdContratto()));
		labelAuto.setText(contrattoSelezionato.getAuto());
		labelTipoNoleggio.setText(contrattoSelezionato.getTipoNoleggio());
		labelTipoTariffa.setText(contrattoSelezionato.getTipoChilometraggio());
		if (contrattoSelezionato.getTipoChilometraggio().equals("Km Limitati"))
		{
			labelScrittaKmPrevisti.setVisible(true);
			labelKmPrevisti.setText(String.valueOf(contrattoSelezionato.getKmPrevisti()));
			labelKmPrevisti.setVisible(true);
		}else
		{
			labelScrittaKmPrevisti.setVisible(false);
			labelKmPrevisti.setVisible(false);
		}
		kmAttualiTF.setText(String.valueOf(contrattoSelezionato.getKmIniziali()));
		
	}

	public Stage getDialogStage() {
		return dialogStage;
	}


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public LocalDate getDataSelezionata() {
		return dataSelezionata;
	}

	public void setDataSelezionata(LocalDate dataSelezionata) {
		this.dataSelezionata = dataSelezionata;
	}
}
