package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cliente;
import model.DAO;

public class ClienteController {

	public ClienteController(){}
	public static ObservableList<Cliente> getListaCliente()
    {
		return caricaListaCliente();
    }
	
	
	
	
	
	public static ObservableList<Cliente> caricaListaCliente()
	{
		ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
   	 ResultSet rs = null;
   	 String comando = "SELECT * FROM cliente";
   	 rs = DAO.getResultSet(comando);
   	 try {
			while (rs.next())
			 {
				 Cliente tempCliente = new Cliente();
				
				 String cf = rs.getString("CF");
				 String nome = rs.getString("Nome");
				 String cognome = rs.getString("Cognome");
				 String telefono = rs.getString("Telefono");
				// int contratto = rs.getInt("Contratto");
				 
				 tempCliente.setCF(cf);
				 tempCliente.setNome(nome);
				 tempCliente.setCognome(cognome);
				 tempCliente.setTelefono(telefono);
				 //tempCliente.setContratto(contratto);
				 
				 listaCliente.add(tempCliente);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
   	 	
		return listaCliente;
		
	}
	
	
	public static ArrayList<String> getDatiCliente()
	{
		return DAO.getListaString("cliente", "Nome,Cognome,Telefono");
	}
	
	
	
}
