import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyScanner {
	public String fileString;
	public List<List<String>> records;
	public CookieTray cTray;
	public boolean exitFlag;
	
	public MyScanner(String fS) {
		this.fileString = fS;
		this.records = new ArrayList<>();
		this.cTray = new CookieTray();
		this.exitFlag = false;
	}
	
	
	public void doScan(String dayString) throws FileNotFoundException { // I may need to adjust the relative file pathing when I put this into Linux.
		// System.out.println("Testing file paths:");
		// System.out.println("" + System.getProperty("user.dir"));
		// "C:\\Users\\David Moore\\eclipse-workspace\\MostActiveCookie\\src\\mostActiveCookie" + "\\" + this.fileString
		String filePathString = "" + System.getProperty("user.dir") + "\\src\\mostActiveCookie" + "\\" + this.fileString;
		filePathString = "./" + this.fileString;
		try (Scanner scanner = new Scanner(new File(filePathString));) { // got this work while using absolute path to file, need relative path.
		    while (scanner.hasNextLine()) {
		        records.add(getRecordFromLine(scanner.nextLine(), dayString));
		        if(this.exitFlag) {
		        	break;
		        }
		    }
		    scanner.close();
		}
		this.printMostActiveCookie();
	}
	
	private List<String> getRecordFromLine(String line, String dayString) {
		// turn dayString into actual Date object:
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate inputDate = LocalDate.parse(dayString,formatter);
	    List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	        rowScanner.close();
	    }
	    // before returning, check if this is in the correct day:
	    String dateTimeString = values.get(values.size()-1); // assume datetime is always at end of row.
	    // parse the day out of it. 
	    String localDayString = this.getDayString(dateTimeString);
	    
	    
		LocalDate readDate = LocalDate.parse(localDayString,formatter);
	    
	    if(localDayString.equals(dayString)) {
	    	// we will put it into the cookie tray.
	    	cTray.hashCookie(values.get(0)); // assume cookie name is always first index of values.
	    } else if(readDate.isBefore(inputDate)) {
	    	// How do I make the program print the most active cookie and then be done.
	    	// Use a flag.
	    	this.exitFlag = true;
	    	
	    }
	    
	    return values;
	}
	private String getDayString(String dateTimeString) {
		String dayString = "";
		String[] strings = dateTimeString.split("T", 2);
		dayString = strings[0];
		return dayString;
		
	}
	
	public void printRecords() {
		System.out.println("Printing Directory:");
		System.out.println("" + new File("."));
		System.out.println("About to print records:");
		for(int i = 0; i < records.size(); i++) {
			for(int j = 0; j < records.get(i).size(); j++) {
				System.out.println(records.get(i).get(j));
			}
		}
	}
	
	public void printMostActiveCookie() {
		this.cTray.printMostActiveCookie();
	}

}
