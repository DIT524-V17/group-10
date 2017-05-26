

/**
 * Creator Martin Chukaleski 05/2017
 */

import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		
//		String file = "C:/Users/Fujitsu/Desktop/t/martin.txt";
//	
//		StringTrim trim = new StringTrim();
//		String folder = trim.getFolder(file) + "\\";
//		folder = folder.replaceAll("\\\\", "/");
//	
//		
//		TwitchExecutor t = new TwitchExecutor();
//
//		try {
//			t.iterator(file,folder);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		TwitchCommandSend test = new TwitchCommandSend();
		test.sendCommands("C:/Users/Ludvig/Desktop/twitch.txt");
		

	}

}
