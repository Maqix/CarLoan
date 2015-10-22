
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAO {
	private static Connection connessione;

	//Inizializzatore
	/**
     * Connessione al database
     * @exception ClassNotFoundException Quando si cerca di accederead una classe che in realt� non esiste.
     */
	public static Connection connetti() throws ClassNotFoundException
	{

		try{
                Class.forName("com.mysql.jdbc.Driver");
                connessione = DriverManager.getConnection("jdbc:mysql://localhost:3306/carloan", "root", "");
                return connessione;
                }
                catch (ClassNotFoundException | SQLException e) {
                                System.out.println(e);
                return null;
                }       

	}


	/**
     * Esegue i predicati SQL
     * @param SQLString � la query da eseguire nel database.
     * @return true Quando non viene rilevata un' eccezione.
     * @return false Quando viene rilevata un'eccezione.
     */
	public static boolean esegui(String SQLString)
	{
		//Crea un oggetto per le operazioni sul database
		try {
			//Connessione
			Statement st = connessione.createStatement( );
			System.out.println("Database -> Esecuzione predicato SQL: "+ SQLString);		
			st.executeUpdate(SQLString);
			return true;
		} catch (SQLException e) {
			//ERRORE: restituisce falso
			e.printStackTrace();
			System.out.println("Errore: Impossibile aggiornare il database.");
			return false;
		}
	}
        
	
	/**
     * Effettua una ricerca dei predicati sql all'interno del database
     * @param SQLString � la query da eseguire nel database.
     * @exception SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori.
     * @return true Quando non viene rilevata un' eccezione.
     * @return false Quando viene rilevata un'eccezione.
     */
        public static boolean cerca(String SQLString) throws SQLException{
                    PreparedStatement pst=null;
                    ResultSet rs=null;
                    pst=connessione.prepareStatement(SQLString);
                    System.out.println("Database -> Esecuzione predicato SQL: "+ SQLString);
                    rs=pst.executeQuery(SQLString);
                        
                    if(rs.next()){
                        return true;
        
                    }else{
                        return false;
                }
        }
        
        
        /**
         * Effettua una ricerca dei predicati sql all'interno del database (cerca user)
         * @param SQLString Predicato sql
         * @exception SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori.
         * @return ris Restituisce 1 se ha trovato l'elemento oppure 0 altrimenti.
         */
        public static String cercaS(String SQLString) throws SQLException{
                    PreparedStatement pst=null;
                    ResultSet rs=null;
                    String ris = null;
                    
                    pst=connessione.prepareStatement(SQLString);
                    System.out.println("Database -> Esecuzione predicato SQL: "+ SQLString);
                    rs=pst.executeQuery(SQLString);
                        
                    if(rs.next()){
                         ris=rs.getString(1);
        
                    }else{
                    System.out.println("elemento non trovato");

                    }
                     System.out.println(ris);
                    return ris;
        }
        
        public static float cercaF(String SQLString) throws SQLException{
                    PreparedStatement pst=null;
                    ResultSet rs=null;
                    float ris = 0;
                    
                    pst=connessione.prepareStatement(SQLString);
                    System.out.println("Database -> Esecuzione predicato SQL: "+ SQLString);
                    rs=pst.executeQuery(SQLString);
                        
                    if(rs.next()){
                         ris=rs.getFloat(1);
        
                    }else{
                    System.out.println("elemento non trovato");

                    }
                     System.out.println(ris);
                    return ris;
        }
        
        /**
         * Preleva la mail-list dal database.
         * @param querytext Predicato sql.
         * @param colonna Contiene l'identificativo degli utenti a cui deve essere inviata la mail.
         * @exception SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori.
         * @return arr Contiene la lista degli indirizzi email.
         */
        
         public static ArrayList<String> caricaListaId(String queryText,String colonna) throws SQLException{
            PreparedStatement pst=null;
                    ResultSet rs=null;
                    //String ris = null;
                    ArrayList<String> arr=new ArrayList<String>();
                    

                    pst=connessione.prepareStatement(queryText);
                    System.out.println("Database -> Esecuzione predicato SQL: "+ queryText);
                    rs=pst.executeQuery(queryText);
                    System.out.println("Database -> Esecuzione predicato SQL: "+ rs);
                    while(rs.next()){
                        arr.add(rs.getString(colonna));
                        
        
                    }
                     System.out.println("Database -> Esecuzione predicato SQL: "+ arr);

                       return arr;
         }
         
         
        
         /**
          * Effettua una ricerca dei predicati sql all'interno del database (cerca id)
          * @param SQLString Predicato sql
          * @exception SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori.
          * @return ris Restituisce 1 se ha trovato l'elemento oppure 0 altrimenti.
          */
        
        
         public static int cercaI(String SQLString) throws SQLException{
                    PreparedStatement pst=null;
                    ResultSet rs=null;
                    int ris = 0 ;
                    
                    pst=connessione.prepareStatement(SQLString);
                    System.out.println("Database -> Esecuzione predicato SQL: "+ SQLString);
                    rs=pst.executeQuery(SQLString);
                        
                    if(rs.next()){
                         ris=rs.getInt(1);
        
                    }else{
                    System.out.println("elemento non trovato");

                    }
                    System.out.println(ris);

                    return ris;

        }
	
	
	/**
	 * Esegue una query sul database e restituisce il relativo tableModel da utilizzare in una JTable
	 * @param queryTXT
	 * @return AgroludosTableModel
	 * 
	 */
    /*
	public static AgroludosTableModel getTabella(String queryText)
	{
	
		Statement enunciato;
		try {
			
			//Crea uno statement per l'interrogazione del database
			enunciato = connessione.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//Trace
			System.out.println("Database -> Interrogazione SQL: " + queryText);		
			
			//Crea un ResultSet eseguendo l'interrogazione desiderata
			ResultSet tabellina = enunciato.executeQuery(queryText);
			
			return new AgroludosTableModel(tabellina);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}*/

         
     public static ObservableList<Auto> getListaAuto()

     {
    	 ObservableList<Auto> listaAuto = FXCollections.observableArrayList();
    	 ResultSet rs = null;
    	 String comando = "SELECT * FROM auto";
    	 rs = DAO.getResultSet(comando);
    	 try {
			while (rs.next())
			 {
				 Auto tempAuto = new Auto();
				
				 String targa = rs.getString("Targa");
				 int fascia = rs.getInt("Fascia");
				 String modello = rs.getString("Modello");
				 String agenzia = rs.getString("Agenzia");
				 int stato = rs.getInt("Stato");
				 int km = rs.getInt("Chilometraggio");
				 
				 tempAuto.setTarga(targa);
				 tempAuto.setFascia(fascia);
				 tempAuto.setModello(modello);
				 tempAuto.setAgenzia(agenzia);
				 tempAuto.setStato(stato);
				 tempAuto.setChilometraggio(km);
				 
				 listaAuto.add(tempAuto);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
    	 return listaAuto;
     }
     

     public static ObservableList<Agenzia> getListaAgenzie()
     {
    	 ObservableList<Agenzia> listaAgenzie = FXCollections.observableArrayList();
    	 ResultSet rs = null;
    	 String comando = "SELECT * FROM agenzia";
    	 rs = DAO.getResultSet(comando);
    	 try {
			while (rs.next())
			 {
				 Agenzia tempAgenzia = new Agenzia();
				
				 String partitaIva = rs.getString("PartitaIVA");
				 String nome = rs.getString("Nome");
				 String citta = rs.getString("Città");
				 String provincia = rs.getString("Provincia");
				 String via = rs.getString("Via");
				 int civico = rs.getInt("#Civico");
				 
				 tempAgenzia.setPartitaIva(partitaIva);
				 tempAgenzia.setNome(nome);
				 tempAgenzia.setCitta(citta);
				 tempAgenzia.setProvincia(provincia);
				 tempAgenzia.setVia(via);
				 tempAgenzia.setCivico(civico);
				 
				 listaAgenzie.add(tempAgenzia);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
    	 return listaAgenzie;
     }
     
     public static ObservableList<Dipendente> getListaDipendenti()
     {
    	 ObservableList<Dipendente> listaDipendenti = FXCollections.observableArrayList();
    	 ResultSet rs = null;
    	 String comando = "SELECT * FROM dipendente";
    	 rs = DAO.getResultSet(comando);
    	 try {
			while (rs.next())
			 {
				 Dipendente tempDipendente = new Dipendente();
				
				 String username = rs.getString("Username");
				 String agenzia = rs.getString("Agenzia");
				 String nome = rs.getString("Nome");
				 String cognome = rs.getString("Cognome");
				 String telefono = rs.getString("Telefono");
				 
				 tempDipendente.setUsername(username);
				 tempDipendente.setAgenzia(agenzia);
				 tempDipendente.setNome(nome);
				 tempDipendente.setCognome(cognome);
				 tempDipendente.setTelefono(telefono);
				
				 
				 listaDipendenti.add(tempDipendente);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
    	 return listaDipendenti;
     }


     
     public static ArrayList<Integer> getListaInteri(String tabella, String colonna)
     {
    	 ArrayList<Integer> lista = new ArrayList<Integer>();
    	 
    	 String comando = String.format("SELECT %s FROM %s", colonna,tabella);
    	 ResultSet rs = DAO.getResultSet(comando);
    	 if (rs != null)
    	 {
    		 try {
				while (rs.next())
				 {
					 lista.add(rs.getInt(1));
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	 }
    	 return lista;
     }
     
     public static ArrayList<String> getListaString(String tabella, String colonna)
     {
    	 ArrayList<String> lista = new ArrayList<String>();
    	 
    	 String comando = String.format("SELECT %s FROM %s", colonna,tabella);
    	 ResultSet rs = DAO.getResultSet(comando);
    	 if (rs != null)
    	 {
    		 try {
				while (rs.next())
				 {
					 lista.add(rs.getString(1));
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	 }
    	 return lista;
     }




	/**
	 * Esegue una query sul database e restituisce il relativo ResultSet
	 * @param queryTXT
	 * @return AgroludosTableModel
	 * 
	 */
	public static ResultSet getResultSet(String queryText)
	{
	
		Statement enunciato;
		try {
			
			//Crea uno statement per l'interrogazione del database
			enunciato = connessione.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
			//Trace
			System.out.println("Database -> Interrogazione SQL: " + queryText);		
			
			//Crea un ResultSet eseguendo l'interrogazione desiderata
			ResultSet tabellina = enunciato.executeQuery(queryText);
			
			return tabellina;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	/**Metodo da applicare ai valori di tipo stringa prima dell'inserimento nel database
	 * 
	 * @param stringaInput
	 */
	public static String string2sqlstring(String stringaInput)
	{
		String stringaOutput;
		
		//Rimedio bug apostrofo
		stringaOutput=stringaInput.replaceAll("'", "''");
		
		
		return stringaOutput;
	}

	public void finalize()
	{
		disconnetti();
	}

	/**Metodo per disconnettersi dal database.
	 * @exception SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori.
	 */

	public void disconnetti()
	{
		try {
			System.out.println("Database -> Disconnessione.");		
			connessione.close();
		} catch (SQLException e) {
			System.out.println("Impossibile terminare la connessione");
			e.printStackTrace();
		}	
		
	}

}
