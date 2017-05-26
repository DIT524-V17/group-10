

/**
 * Creator Martin Chukaleski 03/2017
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCommandSender {
	
	democracy democracy = new democracy();

	public void readAndExecuteFromFile(String path) {
		try {
			readCommands(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readCommands(String path) throws FileNotFoundException { // method
																			// to
																			// read
																			// txt
																			// file
																			// and
																			// execute
																			// commands
		InputStream is = new FileInputStream(path);
		Scanner scanner = new Scanner(is);// Get File Input stream here
		StringBuilder builder = new StringBuilder();
		while (scanner.hasNextLine()) {
			builder.append(scanner.nextLine());
			builder.append(" ");// appending empty strings to builder
		}
		String strings[] = builder.toString().split(" "); // splitting every
															// line to several
															// words in seperate
															// array index

		String[] res = clearEmpty(strings); // removing empty strings or null
											// values
		// naming of twitch commands to make the rover move
		String st = "@stop";
		String bw = "@backward";
		String fw = "@forward";
		String ri = "@right";
		String le = "@left";

		Executor x = new Executor();// executor object that we need to store the
									// movement commands and send them to the
									// rover
		// going thru the string array and checking for commands and adding them
		// accordingly to their order
//		Executor m = new Executor();

		for (int i = 0; i < res.length; i++) {
			if (res[i].equals(st)) {
				DriveLog a = new DriveLog(st,"TF",500);
				x.add(a);
			} else if (res[i].equals(bw)) {
				DriveLog a = new DriveLog(bw,"BO",3000);
				x.add(a);
			} else if (res[i].equals(fw)) {
				DriveLog a = new DriveLog(fw,"TO",4000);
				x.add(a);
			} else if (res[i].equals(ri)) {
				DriveLog a = new DriveLog(ri,"RI",500);
				x.add(a);
			} else if (res[i].equals(le)) {
				DriveLog a = new DriveLog(le,"LE",600);
				x.add(a);
			}
		}
		
//		m.add(democracy.getResult(x));
////		System.out.println(m.size());
//		System.out.println(democracy.getCounters().size());
//		
		if (x.size() > 0) {
			x.autoExecution();// where the execution of the commands is being
								// made
			x.clearAll();
		}else {
			System.out.println("Nothing was found");
		}
	}

	public String[] clearEmpty(String[] r) { // method to clear all the
												// empty strings in a String
												// array
		List<String> list = new ArrayList<String>();

		for (String s : r) {
			if (s != null && s.length() > 0) {
				list.add(s);
			}
		}

		r = list.toArray(new String[list.size()]);
		return r;
	}

}
