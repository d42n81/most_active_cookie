import java.io.FileNotFoundException;


public class ActiveCookie {
	public static void main(String[] args) {
		String csvFileString = args[0];
		String formatString = args[1];
		String dayString = args[2];
		// Here's my algorithm in a nutshell: scan, check the date, if dateParameter, increment cookieName in hashmap? Else, not dateParameter then skip. (If before our date parameter, done, stop scanning.)
		// Uses my Cookie Tray data structure which keeps track of two hashmaps. 
		// myScanner is a custom class that handles parsing my csv while also holding a reference to a Cookie Tray object.
		
		// I considered using the OpenCSV library here for CSV parsing, but I decided to use Java's built in Scanner in order to avoid relying on additional dependencies. 
		MyScanner myScanner = new MyScanner(csvFileString);
		try {
			myScanner.doScan(dayString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
