package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dipendente {

	private final StringProperty 	username;
	private final StringProperty 	agenzia;
	private final StringProperty 	nome;
	private final StringProperty 	cognome;
	private final StringProperty 	telefono;
	
	public Dipendente()
	{
		this.username = new SimpleStringProperty("");
		this.agenzia = new SimpleStringProperty("");
		this.nome = new SimpleStringProperty("");
		this.cognome = new SimpleStringProperty("");
		this.telefono = new SimpleStringProperty("");
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
}
	
	
	
	