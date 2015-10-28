package application;



import java.sql.ResultSet;
import java.sql.SQLException;

import model.Auto;
import model.DAO;

public class ContrattoController 
{
	private int idContratto;
	private int totVersato;
	private int kmIniziali;
	private boolean isAperto;
	private Auto auto;
	private String agenziaApertura;
	private String agenziaChiusura;
	private String dataInizio;
	private String dataFine;
	private String cliente;
	private String tipoNoleggio;
	private String tipoChilometraggio;
	
	public boolean apriContratto()
	{
		//Inserisco il contratto nel DB
		String comando = String.format("INSERT INTO `contratto` (`idContratto`, `Auto`, `AgenziaApertura`, `AgenziaChiusura`, `Inizio`, `Fine`, `TotaleVersato`, `KmIniziali`, `Cliente`, `isAperto`, `TipoNoleggio`, `TipoChilometraggio`) VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%d', '%d', '%s', '%d', '%s', '%s')", this.idContratto,this.auto.getTarga(),this.agenziaApertura,this.agenziaChiusura,this.dataInizio,this.dataFine,this.totVersato,this.kmIniziali,this.cliente,(this.isAperto) ? 1 : 0,this.tipoNoleggio,this.tipoChilometraggio);
		if (DAO.esegui(comando))
		{
			//Aggiorno l'auto scelta come "In Uso"
			comando = String.format("UPDATE `auto` SET `Stato` = '2' WHERE `Targa` = '%s'",this.auto.getTarga());
			if (DAO.esegui(comando))
			{
				//Aggiorno il cliente col riferimento al contratto
				comando = String.format("UPDATE `cliente` SET `Contratto` = '%d' WHERE `CF` = '%s'",this.idContratto, this.cliente);
				if (DAO.esegui(comando))
				{
					return true;
					
				}else
				{
					return false;
				}
				
			}else
			{
				return false;
			}
		}else
		{
			return false;
		}
	}
	
	public void assegnaKmIniziali()
	{
		this.kmIniziali = auto.getChilometraggio();
	}
	
	/**
	 * Genera un numero di contratto contando i contratti presenti e aggiungendo 1
	 * @return Il primo idContratto libero
	 */
	public int generaNumContratto()
	{
		int numContratto = 0;
		String comando = "SELECT * FROM contratto";
		ResultSet rs = DAO.getResultSet(comando);
		try {
			while (rs.next())
			{
				numContratto++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.idContratto = numContratto + 1;
		return this.idContratto;
	}
	
	/**
	 * Genera un'acconto moltiplicando la fascia della macchina per 10
	 * @return L'acconto dovuto
	 */
	public int generaAcconto()
	{
		return (this.auto.getFascia() * 10);
	}
	
	public Auto getAuto()
	{
		return this.auto;
	}
	
	/**
	 * Genera una auto valida per il contratto
	 * @param fascia La fascia desiderata per l'auto
	 * @param agenzia L'agenzia in cui cercare l'auto
	 */
	public void setAuto(int fascia, String agenzia) 
	{
		Auto autoAssegnata = AutoController.getAuto(fascia, agenzia);
		this.agenziaApertura = agenzia;
		this.auto = autoAssegnata;
	}
	
	public String verificaContratto()
	{
		//TODO: Controllare veramente il contratto
		return "";
	}

	public int getIdContratto() {
		return idContratto;
	}

	public void setIdContratto(int idContratto) {
		this.idContratto = idContratto;
	}

	public int getTotVersato() {
		return totVersato;
	}

	public void setTotVersato(int totVersato) {
		this.totVersato = totVersato;
	}

	public int getKmIniziali() {
		return kmIniziali;
	}

	public void setKmIniziali(int kmIniziali) {
		this.kmIniziali = kmIniziali;
	}

	public boolean isAperto() {
		return isAperto;
	}

	public void setAperto(boolean isAperto) {
		this.isAperto = isAperto;
	}

	public String getAgenziaApertura() {
		return agenziaApertura;
	}

	public void setAgenziaApertura(String agenziaApertura) {
		this.agenziaApertura = agenziaApertura;
	}

	public String getAgenziaChiusura() {
		return agenziaChiusura;
	}

	public void setAgenziaChiusura(String agenziaChiusura) {
		this.agenziaChiusura = agenziaChiusura;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTipoNoleggio() {
		return tipoNoleggio;
	}

	public void setTipoNoleggio(String tipoNoleggio) {
		this.tipoNoleggio = tipoNoleggio;
	}

	public String getTipoChilometraggio() {
		return tipoChilometraggio;
	}

	public void setTipoChilometraggio(String tipoChilometraggio) {
		this.tipoChilometraggio = tipoChilometraggio;
	}
}
