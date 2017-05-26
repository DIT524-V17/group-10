

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
//			  #1 Open mIRC and type: //run $mircdir/scripts
//			  #2 Unzip the files (twitchat.mrc & viewers2txt.exe) inside that folder.
//			  #3 At mIRC type: /load -rs scripts/twitchat.mrc
//			  #4 Click YES at the 'run initialization commands' warning popup and configure to your needs.

		String o = "C:/Users/hp/Desktop/yo/#leh_tv.log";
		String d = "C:/Users/hp/Desktop/yo/";
	
		String c = "C:/Users/hp/AppData/Roaming/mIRC/logs/#nvidiageforcefr.log";
		String m = "C:/Users/hp/AppData/Roaming/mIRC/logs/";
		
		// Use your own path to the txt file

		FileCommandSender r = new FileCommandSender();
		CopyAndRename copy = new CopyAndRename();
		Timer t = new Timer();
		
		while (true) {
			String path = copy.copyTo(c, m);
			r.readAndExecuteFromFile(path);
			t.timedTask(3000);
		}
	}
}
