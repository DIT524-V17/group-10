
import java.io.IOException;
//a class that handles every thing nedeed to execute commands every 3 seconds from a file path this was done for a easier not messy way of 
// calling this in main when the twitch button was pressed.
public class TwitchCommandSend {

	public void sendCommands(String path) {
		String file = path;

		StringTrim trim = new StringTrim();
		String folder = trim.getFolder(file) + "\\"; // replacing the slashesh from the windows explorer with the dashesh that java uses
		folder = folder.replaceAll("\\\\", "/");

		TwitchExecutor t = new TwitchExecutor();

		try {
			t.iterator(file, folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
