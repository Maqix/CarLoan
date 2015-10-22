package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verificatore {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String TARGA_PATTERN = 
			"^[a-zA-Z]{2}[0-9]{3,4}[a-zA-Z]{2}$";


	public static boolean controllaeMail(final String hex) 
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	
	public static boolean controllaTarga(final String hex) 
	{
		pattern = Pattern.compile(TARGA_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}