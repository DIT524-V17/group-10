package group10;

import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

/**
 * Creator Martin Chukaleski 03/2017
 */

public class Tester {
	public static void main(String[] args) throws IOException {

		String o = "C:/Users/hp/Desktop/yo/#gtare22.log";
		String d = "C:/Users/hp/Desktop/yo/";

		String c = "C:/Users/hp/AppData/Roaming/mIRC/logs/1.txt";
		// Use your own path to the txt file

		FileCommandSender r = new FileCommandSender();
		CopyAndRename copy = new CopyAndRename();
		Timer t = new Timer();

		while (true) {
			String path = copy.copyTo(o, d);
			r.readAndExecuteFromFile(path);
			t.timedTask(3000);
		}
	}
}
