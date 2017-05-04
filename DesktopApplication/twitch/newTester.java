package twitch;

/**
 * Creator Martin Chukaleski 05/2017
 */

import java.io.IOException;

public class newTester {
	public static void main(String[] args) {
		
		String file = "C:/Users/Fujitsu/Desktop/t/martin.txt";
	
		StringTrim trim = new StringTrim();
		String folder = trim.getFolder(file) + "\\";
		folder = folder.replaceAll("\\\\", "/");
	
		
		TwitchExecutor t = new TwitchExecutor();

		try {
			t.iterator(file,folder);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
