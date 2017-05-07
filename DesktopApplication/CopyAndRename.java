

/**
 * Creator Martin Chukaleski 04/2017
 */

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.FileSystemException;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class CopyAndRename {
	private int counter;

	public String copyRename(String origin, String destination)// method to copy
																// a
																// file from a
																// directory and
																// rename it or
																// change the
																// extension
			throws IOException {
		Path FROM = Paths.get(origin);
		String cnt = Integer.toString(counter);
		counter++;
		String res = destination.concat(cnt.concat(".txt"));
		Path TO = Paths.get(res);
		// overwrite the destination file if it exists, and copy
		// the file attributes, including the rwx permissions
		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES };
		Files.copy(FROM, TO, options);
		return res; // return a string of the path of the new txt file

	}

	public String copyTo(String origin, String destination) throws IOException { // catching
																					// the
		// errors in this
		// method

		return copyRename(origin, destination);

	}

	public void deleteFIle(String path) { // deleting a file in a specified path
											// on the exit of the program
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		} else {
			System.out.println("Cannot find file");
		}
	}
}