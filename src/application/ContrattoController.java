package application;



import java.sql.ResultSet;
import java.sql.SQLException;

import model.Auto;
import model.DAO;

@SuppressWarnings("unused")
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
		this.auto = autoAssegnata;
	}
}
