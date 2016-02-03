package Program3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 
 * @author Carson Wood
 *
 */
public class hours{
	public static void main(String args[]) throws ParseException{
		String startDate = formatDate(args[0]);
		String endDate = formatDate(args[1]);
		
		System.out.println(startDate + " to " + endDate);
	}

	/**
	 * Converts a mm/dd/yyy date format
	 * to a mmm-dd format. It converts the date
	 * from the input arguments format to
	 * the format in the hours.txt file. 
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws ParseException 
	 * @author Carson Wood
	 */
	private static String formatDate(String dateToFormat) throws ParseException {
		DateFormat fromFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat toFormat = new SimpleDateFormat("MMM-dd");
		
		Date date = fromFormat.parse(dateToFormat);
		String formattedDate = toFormat.format(date);
		//System.out.println(formattedDate);
		return formattedDate;
	}
}
