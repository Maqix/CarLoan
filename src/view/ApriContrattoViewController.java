package view;

import java.time.LocalDate;
import java.util.ArrayList;

import application.AgenziaController;
import application.ClienteController;
import application.ContrattoController;
import application.FasciaController;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class ApriContrattoViewController 
{
	private Stage dialogStage;
	
	@FXML
	private ChoiceBox<String> fasciaCB;
	@FXML
	private ChoiceBox<String> noleggioCB;
	@FXML
	private ChoiceBox<String> tariffaCB;
	@FXML
	private ChoiceBox<String> rilascioCB;
	@FXML
	private ChoiceBox<String> ritornoCB;
	@FXML
	private DatePicker dataInizioDF;
	@FXML
	private ChoiceBox<String> clienteCB;
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
	
	private ObservableList<String> clienti = FXCollections.observableArrayList();
	private ContrattoController contrattoController = new ContrattoController();
	
	boolean generato = false;
	
	@FXML
	private void initialize()
	{
		configuraPicker();
		dataInizioDF.setValue(LocalDate.now());
	}
	
	@FXML
	private void premutoGeneraContratto()
	{
		generato = true;
		//Devo valorizzare i dati del riepilogo
		//Ottengo la fascia e l'agenzia scelti per assegnare una auto
		int fascia = fasciaCB.getSelectionModel().getSelectedIndex()+1;
		String agenzia = AgenziaController.getPivaFromNome(rilascioCB.getSelectionModel().getSelectedItem());
		//Faccio generare una auto valida al ContrattoController
		contrattoController.setAuto(fascia, agenzia);
		//Mostro nella TextField l'auto assegnata
		autoTF.setText(contrattoController.getAuto().getModello() + " - " + contrattoController.getAuto().getTarga());
		//Faccio generare un acconto adeguato al ContrattoController
		Integer acconto = contrattoController.generaAcconto();
		//Mostro nella TextField l'acconto
		accontoTF.setText(acconto.toString());
		//Faccio generare un numero di contratto al ContrattoController
		Integer numContratto = contrattoController.generaNumContratto();
		//Mostro nella TextField il numero di contratto
		contrattoTF.setText(numContratto.toString());
	}
	
	@FXML
	private void premutoApriContratto()
	{
		contrattoController.setClienteNome(nomeTF.getText());
		contrattoController.setClienteCognome(cognomeTF.getText());
		contrattoController.setClienteTelefono(telefonoTF.getText());
		if (generato)
		{
			if (formRiempito())
			{
				impostaAperturaContratto();
				if (contrattoController.verificaContratto().equals(""))
				{
					if (contrattoController.apriContratto()) 
					{
						Main.lanciaInfo("Nuovo Contratto", "Il contratto è stato aperto");
						dialogStage.close();
						//listaContratti.add(ContrattoController.getListaContratti().get(ContrattoController.getListaContratti().size()));
					}else
					{
						Main.lanciaWarning("Impossibile aprire il contratto", "Problemi con il database");
					}
				}else
				{
					Main.lanciaWarning("Impossibile aprire il contratto", contrattoController.verificaContratto());
				}
			}else
			{
				Main.lanciaWarning("Impossibile aprire il contratto", "Seleziona un cliente dall'elenco o riempi i campi");
			}
		}else
		{
			Main.lanciaWarning("Impossibile aprire il contratto", "Premi 'Genera Contratto' prima di aprire il contratto");
		}
		
	}
	
	private boolean formRiempito()
	{
		boolean risposta = !((nomeTF.getText().equals("")) && (cognomeTF.getText().equals("")));
		risposta = risposta && !(telefonoTF.getText().equals(""));
		return risposta;
	}
	
	
	@FXML
	private void premutoAnnulla()
	{
		dialogStage.close();
	}
	
	private void impostaAperturaContratto()
	{
		//Imposto l'agenzia di chiusura
		String agenziaChiusura = AgenziaController.getPivaFromNome(ritornoCB.getSelectionModel().getSelectedItem());
		contrattoController.setAgenziaChiusura(agenziaChiusura);
		//Assegno la data di di inizio
		String dataInizio = dataInizioDF.getValue().toString();
		contrattoController.setDataInizio(dataInizio);
		//Assegno la data di fine
		LocalDate inizio = dataInizioDF.getValue();
		if (noleggioCB.getSelectionModel().getSelectedItem().equals("Giornaliero"))
		{
			//Aggiungo un giorno alla data di inizio
			LocalDate fine = inizio.plusDays(1);
			contrattoController.setDataFine(fine.toString());
		}else
		{
			//Aggiungo sette giorni alla data di inizio
			LocalDate fine = inizio.plusDays(7);
			contrattoController.setDataFine(fine.toString());
		}
		//Assegno il totaleVersato pari all'acconto
		contrattoController.setTotVersato(Integer.parseInt(accontoTF.getText()));
		//Assegno i Km iniziali (il controller li prende dalla macchina assegnata)
		contrattoController.assegnaKmIniziali();
		//Assegno il cliente
		contrattoController.setCliente(codiceFiscaleTF.getText());
		//Imposto isAperto a true
		contrattoController.setAperto(true);
		//Imposto il tipo di noleggio
		contrattoController.setTipoNoleggio(noleggioCB.getSelectionModel().getSelectedItem());
		//Imposto il tipo di chilometraggio
		contrattoController.setTipoChilometraggio(tariffaCB.getSelectionModel().getSelectedItem());
	}
	
	private void configuraPicker()
	{
		//I picker delle agenzie di rilascio e ritorno
		ObservableList<String> agenzie = FXCollections.observableArrayList();
		ArrayList<String> listaAgenziePresenti = AgenziaController.getNomiAgenzie();
		for (String agenzia: listaAgenziePresenti)
		{
			agenzie.add(agenzia);
		}
		rilascioCB.setItems(agenzie);
		ritornoCB.setItems(agenzie);
		rilascioCB.getSelectionModel().selectFirst();
		ritornoCB.getSelectionModel().selectFirst();
		
		//Il picker della tariffa
		ObservableList<String> tariffa = FXCollections.observableArrayList();
		tariffa.add("Km Illimitati");
		tariffa.add("Km Limitati");
		tariffaCB.setItems(tariffa);
		tariffaCB.getSelectionModel().selectFirst();
		
		//Il picker del noleggio
		ObservableList<String> noleggio = FXCollections.observableArrayList();
		noleggio.add("Giornaliero");
		noleggio.add("Settimanale");
		noleggioCB.setItems(noleggio);
		noleggioCB.getSelectionModel().selectFirst();
		
		//Il picker della fascia
		ObservableList<String> fasce = FXCollections.observableArrayList();
		ArrayList<String> listaFascePresenti = FasciaController.getNomiFasce();
		for (String fascia: listaFascePresenti)
		{
			fasce.add(fascia);
		}
		fasciaCB.setItems(fasce);
		fasciaCB.getSelectionModel().selectFirst();
		
		//Il picker dei clienti
		ArrayList<String> listaClientiPresenti = ClienteController.getDatiCliente();
		for (String cliente: listaClientiPresenti)
		{
			clienti.add(cliente);
		}
		clienteCB.setItems(clienti);
		clienteCB.getSelectionModel().selectFirst();
		clienteCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		      @Override
		      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
		      {
		        String stringaClienteSelezionato = clienteCB.getItems().get((Integer) number2);
		        //Devo prendere i primi sedici caratteri (il CF)
		        String cf = stringaClienteSelezionato.substring(0, 16);
		        setClienteTF(cf);
		      }
		    });
		
	}
	
	private void setClienteTF(String cf)
	{
		Cliente clienteSelezionato = ClienteController.getClienteFromCF(cf);
		nomeTF.setText(clienteSelezionato.getNome());
		cognomeTF.setText(clienteSelezionato.getCognome());
		telefonoTF.setText(clienteSelezionato.getTelefono());
		codiceFiscaleTF.setText(clienteSelezionato.getCF());
	}

	
	public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }

}
