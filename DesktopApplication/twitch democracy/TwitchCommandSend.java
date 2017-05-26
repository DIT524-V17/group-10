
import java.io.IOException;

import javax.swing.SwingWorker;

//public class TwitchCommandSend extends SwingWorker<Integer, String>{
public class TwitchCommandSend {


	
	private String path; 
	
	public void sendCommands(String path) {
		// "C:/Users/Fujitsu/Desktop/t/martin.txt";
		
		this.path = path;
		String file = path;

		StringTrim trim = new StringTrim();
		String folder = trim.getFolder(file) + "\\";
		folder = folder.replaceAll("\\\\", "/");

		TwitchExecutor t = new TwitchExecutor();

		try {
			t.iterator(file, folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	protected Integer doInBackground() throws Exception {
//		// TODO Auto-generated method stub
//		
//		String file = path;
//
//		StringTrim trim = new StringTrim();
//		String folder = trim.getFolder(file) + "\\";
//		folder = folder.replaceAll("\\\\", "/");
//
//		TwitchExecutor t = new TwitchExecutor();
//
//		try {
//			t.iterator(file, folder);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}

}
