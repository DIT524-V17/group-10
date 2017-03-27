package group10;

import java.io.File;
import java.io.IOException;

/**
 * Creator Martin Chukaleski 03/2017
 */

public class Tester {
	public static void main(String[] args) {

		String f = "C:/Users/hp/Desktop/test.txt"; // User your own path to the
													// txt file
		String m = "C:/Users/hp/Desktop/#gtare22.txt";
		String c = "C:/Users/hp/AppData/Roaming/mIRC/logs/1.txt";
	

		FileCommandSender r = new FileCommandSender();
		r.readAndExecuteFromFile(c);
	
	}
}
