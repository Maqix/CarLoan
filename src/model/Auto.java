package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auto 
{	
	private final StringProperty 	targa;
	private final IntegerProperty 	fascia;
	private final StringProperty 	modello;
	private final StringProperty 	agenzia;
	private final IntegerProperty 	stato;
	private final IntegerProperty 	chilometraggio;
	
	public Auto()
	{
		this.targa = new SimpleStringProperty("");
		this.modello = new SimpleStringProperty("");
		this.agenzia = new SimpleStringProperty("");
		this.fascia = new SimpleIntegerProperty(0);
		this.stato = new SimpleIntegerProperty(0);
		this.chilometraggio = new SimpleIntegerProperty(0);
		
	}

	public StringProperty getTargaProperty() {
		return targa;
	}

	public IntegerProperty getFasciaProperty() {
		return fascia;
	}

	public StringProperty getModelloProperty() {
		return modello;
	}

	public StringProperty getAgenziaProperty() {
		return agenzia;
	}

	public IntegerProperty getStatoProperty() {
		return stato;
	}

	public IntegerProperty getChilometraggioProperty() {
		return chilometraggio;
	}

	public void setTarga(String targa) 
	{
	        this.targa.set(targa);
	}
	
	public void setFascia(int fascia) 
	{
	        this.fascia.set(fascia);
	}
	
	public void setModello(String modello) 
	{
	        this.modello.set(modello);
	}
	
	public void setAgenzia(String agenzia) 
	{
	        this.agenzia.set(agenzia);
	}
	
	public void setStato(int stato) 
	{
	        this.stato.set(stato);
	}
	
	public void setChilometraggio(int km) 
	{
	        this.chilometraggio.set(km);
	}
	
	public String getTarga()
	{
		return targa.get();
	}

	public int getFascia()
	{
		return fascia.get();
	}
	
	public String getModello()
	{
		return modello.get();
	}
	
	public String getAgenzia()
	{
		return agenzia.get();
	}
	
	public int getChilometraggio()
	{
		return chilometraggio.get();
	}
	
	public int getStato()
	{
		return stato.get();
	}
	
}
