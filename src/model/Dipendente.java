package model;

import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utility.Verificatore;

public class Dipendente {

	private final StringProperty 	username;
	private final StringProperty 	agenzia;
	private final StringProperty 	nome;
	private final StringProperty 	cognome;
	private final StringProperty 	telefono;
	private final StringProperty 	password;
	private final StringProperty 	nomeAgenzia;
	
	public String verificaDipendente()
	{
		String risposta = "Errore!";
		if (nome.get().length() > 0 && nome.get().length() < 46)
		{
			if (cognome.get().length() > 0 && cognome.get().length() < 46)
			{
				if (telefono.get().length() > 5 && telefono.get().length() < 11)
				{
					if (Verificatore.controllaTel(telefono.get()))
					{
						if (!isUsernameEsistente())
						{
							risposta = "";
						}else
						{
							risposta = "Un dipendente con questo Username è già presente";
						}
					}else
					{
						risposta = "Il telefono deve essere numerico";
					}
				}else
				{
					risposta = "Il telefono deve essere compreso tra 6 e 10 caratteri";
				}
			}else
			{
				risposta = "Il cognome deve essere compreso tra 1 e 45 caratteri";
			}
		}else
		{
			risposta = "Il nome deve essere compreso tra 1 e 45 caratteri";
		}
		return risposta;
	}
	
	private boolean isUsernameEsistente()
	{
		String comando = String.format("SELECT Username FROM dipendente WHERE Username = '%s'", this.getUsername());
		try {
			return DAO.cerca(comando);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Dipendente()
	{
		this.username = new SimpleStringProperty("");
		this.agenzia = new SimpleStringProperty("");
		this.nome = new SimpleStringProperty("");
		this.cognome = new SimpleStringProperty("");
		this.telefono = new SimpleStringProperty("");
		this.password = new SimpleStringProperty("");
		this.nomeAgenzia = new SimpleStringProperty("");
	}
	
	public StringProperty getAgenziaNomeProperty()
	{
		return nomeAgenzia;
	}

	public StringProperty getUsernameProperty() {
		return username;
	}
	
	public StringProperty getAgenziaProperty() {
		return agenzia;
	}
	
	public StringProperty getNomeProperty() {
		return nome;
	}
	
	public StringProperty getCognomeProperty() {
		return cognome;
	}
	
	public StringProperty getTelefonoProperty() {
		return telefono;
	}
	
	public String getUsername()
	{
		return username.get();
	}
	
	public String getAgenzia()
	{
		return agenzia.get();
	}
	
	public String getNome()
	{
		return nome.get();
	}
	
	public String getCognome()
	{
		return cognome.get();
	}
	
	public String getTelefono()
	{
		return telefono.get();
	}

	public void setUsername(String username) 
	{
	        this.username.set(username);
	}
	
	public void setAgenzia(String agenzia) 
	{
	        this.agenzia.set(agenzia);
			try {
				String nomeAgenzia = DAO.cercaS("SELECT Nome FROM agenzia WHERE PartitaIVA = '" + this.agenzia.get() + "'");
				this.nomeAgenzia.set(nomeAgenzia);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void setNome(String nome) 
	{
	        this.nome.set(nome);
	}
	
	public void setCognome(String cognome) 
	{
	        this.cognome.set(cognome);
	}
	
	public void setTelefono(String telefono) 
	{
	        this.telefono.set(telefono);
	}

	public  StringProperty getPasswordProperty() {
		return this.password;
	}
	
	public String getPassword()
	{
		return this.password.get();
	}
	
	public void setPassword(String password)
	{
		this.password.set(password);
	}
	
}
	
	
	
	