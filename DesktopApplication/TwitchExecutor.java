

/**
 * Creator Martin Chukaleski 05/2017
 */

import java.io.IOException;

public class TwitchExecutor {
	private FileCommandSender r = new FileCommandSender();
	private CopyAndRename copy = new CopyAndRename();
	private Timer t = new Timer();
	
	
	public void iterator(String file ,String folder) throws IOException{
		while (true) {
			String path = copy.copyTo(file, folder);
			r.readAndExecuteFromFile(path);
			t.timedTask(3000);
		}
	}
}

