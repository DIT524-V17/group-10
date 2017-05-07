
import java.io.IOException;
/**
 * Creator Martin Chukaleski 05/2017
 */

public class TwitchCommandSend {

	public void sendCommands(String path) {
		// "C:/Users/Fujitsu/Desktop/t/martin.txt";
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

}
