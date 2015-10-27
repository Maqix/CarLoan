package view;

import java.util.ArrayList;

import application.AgenziaController;
import application.ClienteController;
import application.ContrattoController;
import application.FasciaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	
	@FXML
	private void initialize()
	{
		configuraPicker();
	}
	
	@FXML
	private void premutoGeneraContratto()
	{
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
		accontoTF.setText(acconto.toString() + "€");
		//Faccio generare un numero di contratto al ContrattoController
		Integer numContratto = contrattoController.generaNumContratto();
		//Mostro nella TextField il numero di contratto
		contrattoTF.setText(numContratto.toString());
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
		//TODO: Bisogna scrivere Cliente e ClienteController
		
		
		ArrayList<String> listaClientiPresenti = ClienteController.getDatiCliente();
		for (String cliente: listaClientiPresenti)
		{
			clienti.add(cliente);
		}
		clienteCB.setItems(clienti);
		clienteCB.getSelectionModel().selectFirst();
	}
	
	/*
    private int ottieniFasciaIntFromString(String fascia)
    {
		String regex = "\\d+";	  
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fascia);	
		int fasciaInt = 0;
		while (matcher.find())
		{
			String match = matcher.group();
			fasciaInt = Integer.parseInt(match);
		}
		return fasciaInt;
    }
	*/
	
	public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }
}
