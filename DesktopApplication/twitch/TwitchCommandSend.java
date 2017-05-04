package twitch;

import java.io.IOException;

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
