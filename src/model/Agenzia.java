package model;

import java.sql.SQLException;

import utility.Verificatore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Agenzia {

	private final StringProperty 	partitaIva;
	private final StringProperty 	nome;
	private final StringProperty 	citta;
	private final StringProperty 	provincia;
	private final StringProperty 	via;
	private final StringProperty 	civico;
	
	public Agenzia()
	{
		this.partitaIva = new SimpleStringProperty("");
		this.nome = new SimpleStringProperty("");
		this.citta = new SimpleStringProperty("");
		this.provincia = new SimpleStringProperty("");
		this.via = new SimpleStringProperty("");
		this.civico = new SimpleStringProperty("");
	}
	
	public StringProperty getPartitaIvaProperty() {
		return partitaIva;
	}
	
	public StringProperty getNomeProperty() {
		return nome;
	}
	
	public StringProperty getCittaProperty() {
		return citta;
	}
	
	public StringProperty getProvinciaProperty() {
		return provincia;
	}
	
	public StringProperty getViaProperty() {
		return via;
	}
	
	public StringProperty getCivicoProperty() {
		return civico;
	}
	
	public void setPartitaIva(String partitaIva) 
	{
	        this.partitaIva.set(partitaIva);
	}
	
	public void setNome(String nome) 
	{
	        this.nome.set(nome);
	}
	
	public void setCitta(String citta) 
	{
	        this.citta.set(citta);
	}

	public void setProvincia(String provincia) 
	{
	        this.provincia.set(provincia);
	}
	
	public void setVia(String via) 
	{
	        this.via.set(via);
	}
	
	public void setCivico(String civico) 
	{
	        this.civico.set(civico);
	}
	
	public String getPartitaIva()
	{
		return partitaIva.get();
	}
	
	public String getNome()
	{
		return nome.get();
	}

	public String getCitta()
	{
		return citta.get();
	}
	
	public String getProvincia()
	{
		return provincia.get();
	}
	public String getVia()
	{
		return via.get();
	}

	public String getCivico()
	{
		return civico.get();
	}
	
	public String verificaAgenzia() {
		String risposta = "Errore!";

		if (this.getPartitaIva().length() == 11) {
			if (Verificatore.controllaPartitaIva(this.getPartitaIva())) {
				if (this.getNome().length() > 0 && this.getNome().length() < 30) {
					if (this.getCitta().length() > 0 && this.getCitta().length() < 20) {
						if (Verificatore.controllaProvincia(this.getProvincia())) {
							if (this.getVia().length() > 0 && this.getVia().length() < 30) {
								if (!partitaIvaEsistente()) {

									risposta = "";
								} else {
									risposta = "Un'agenzia con questa Partita IVA e' gia' presente";
								}
							} else {
								risposta = "La Via non puo' essere vuota";
							}
						} else {
							risposta = "La Provincia deve essere composta da 2 sole lettere";
						}

					} else

					{
						risposta = "La Citta non puo' essere vuota";
					}
				} else {
					risposta = "Il Nome non puo' essere vuoto";
				}
			} else {
				risposta = "La Partita IVA deve contenere solo cifre";
			}
		} else {
			risposta = "La Partita IVA deve essere di 11 caratteri";
		}

		return risposta;
	}
	

				
private boolean partitaIvaEsistente()
	{
	String comando = String.format("SELECT PartitaIva FROM agenzia WHERE PartitaIva = '%s'", this.getPartitaIva());
		try {
			return DAO.cerca(comando);
			} catch (SQLException e) {
			e.printStackTrace();
		return false;
			}
	}			
}

	

