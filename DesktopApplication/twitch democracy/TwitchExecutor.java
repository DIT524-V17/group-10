

/**
 * Creator Martin Chukaleski 05/2017
 */

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TwitchExecutor {
	private FileCommandSender r = new FileCommandSender();
	private CopyAndRename copy = new CopyAndRename();
	//private Timer t = new Timer();
	
	private String file;
	private String folder;
	
	private Runnable commandRunnable = new Runnable() {
	    public void run() {
	    	for(int i = 0; i <=1; i++){
				String path;
				try {
					path = copy.copyTo(file, folder);
					r.readAndExecuteFromFile(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//t.timedTask(3000);
			}
	    }
	};
	
	
	public void iterator(String file ,String folder) throws IOException{
//		while (true) {
//			String path = copy.copyTo(file, folder);
//			r.readAndExecuteFromFile(path);
//			t.timedTask(3000);
//		}
		
		this.file = file;
		this.folder = folder;
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(commandRunnable, 0, 3, TimeUnit.SECONDS);
		
		
	}
}

