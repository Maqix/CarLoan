package application;

import model.Auto;

public class ContrattoController 
{
	public static Auto getAuto(int fascia, String agenzia)
	{
		return AutoController.getAuto(fascia, agenzia);
	}
}
