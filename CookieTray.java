import java.util.ArrayList;
import java.util.HashMap;

public class CookieTray {
	private int maxCount;
	private HashMap<Integer, ArrayList<String>> countToCookiesMap; 
	private HashMap<String, Integer> cookieToCountMap;
	
	public CookieTray() {
		this.maxCount = 0;
		this.countToCookiesMap = new HashMap<Integer, ArrayList<String>>();
		this.cookieToCountMap = new HashMap<String,Integer>();
	}
	
	public void hashCookie(String cookie) {
		Integer localCount = cookieToCountMap.get(cookie);
		if(localCount == null) {
			// empty
			this.cookieToCountMap.put(cookie, 1);
			localCount = 1;
		} else {
			localCount = localCount.intValue() + 1; 
			this.cookieToCountMap.put(cookie, localCount);
		}
		this.hashCount(localCount, cookie);
	}
	
	private void hashCount(int localCount, String cookie) {
		ArrayList<String> localList = this.countToCookiesMap.get(localCount);
		if(localList == null) {
			localList = new ArrayList<String>();
			incrementMaxCount(); // need new highest value because we have empty arrayList
		}
		localList.add(cookie);
		this.countToCookiesMap.put(localCount, localList);
	}
	
	public ArrayList<String> getList(int localCount) {
		return this.countToCookiesMap.get(localCount);
	}
	
	public int getMaxCount() {
		return this.maxCount;
	}
	
	public void incrementMaxCount() {
		this.maxCount = this.maxCount + 1;
	}
	
	public void printMostActiveCookie() {
		ArrayList<String> localList = this.countToCookiesMap.get(this.maxCount);
		if(localList == null) {
			System.out.println("There is no most active cookie");
			return;
		}
		for(int i = 0; i < localList.size(); i++) {
			if(localList.get(i) != null) {
				System.out.println(localList.get(i));
			} else {
				System.out.println("There is no most active cookie");
				return;
			}
			
		}
	}

}
