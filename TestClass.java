import java.io.FileNotFoundException;

public class TestClass {
	// At first I tried to implement these tests using JUnit5, however i was having problems getting JUnit to be part of my build path in my Linux directory, so instead I wrote these tests in vanilla Java.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int countOfTestsFailed = 0;
		if(!testCompleteExample()) {
			System.out.println("Something went wrong with the complete example test. Did not get expected answer.");
			countOfTestsFailed++;
		}
		if(!testCookieTrayHash()) {
			System.out.println("Cookie Tray Hash failed unexpectantly");
			countOfTestsFailed++;
		}
		
		if(!testMyScannerScanning()) {
			System.out.println("Scanner either did not scan all rows of the sample csv or the sanner encountered a file not found exception");
			countOfTestsFailed++;
		}
		
		if(countOfTestsFailed == 0) {
			System.out.println("All test cases passed");
		}
		
		

	}
	
	public static boolean testCompleteExample() {
		String dateString = "2018-12-09"; // provided example, should return 1 cookie and it should be AtY0laUfhglK3lC7
		MyScanner scanner = new MyScanner("cookie_log.csv");
		try {
			scanner.doScan(dateString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(scanner.cTray.getList(scanner.cTray.getMaxCount()).get(0).equals("AtY0laUfhglK3lC7")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean testCookieTrayHash() {
		CookieTray cTray = new CookieTray();
		String cookieString = "cookie1";
		cTray.hashCookie(cookieString);
		
		if(cTray.getList(1).get(0).equals("cookie1")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean testMyScannerScanning() {
		MyScanner scanner = new MyScanner("cookie_log.csv");
		// use a day that is not in the file, because I want to check if the scanner successfully scans the whole csv file by default:
		// also tests for case where there are no most active cookies for a particular day
		String dayString = "2000-01-01";
		try {
			scanner.doScan(dayString);
			if(scanner.records.size() == 8) {
				return true; 
			} else {
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	

}
