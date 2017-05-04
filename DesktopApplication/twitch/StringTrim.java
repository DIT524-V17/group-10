package twitch;

/**
 * Creator Martin Chukaleski 05/2017
 */

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class StringTrim {
	
	public String getFolder(String path){
		File file = new File (path);
		return file.getParent();
	}
	
	
	
}
