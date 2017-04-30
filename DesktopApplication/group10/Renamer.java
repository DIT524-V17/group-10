package group10;
/**
 * Creator Martin Chukaleski 03/2017
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Renamer {
	public void renameAndSave(String path){
		File original = new File(path);

		// Gets the File object for the directory that contains the file
		File dir = original.getParentFile();

		// Creates a File object for a file in the same directory with the name "cukalz.txt"
		File result = new File(dir, "cukalz.txt");
	}
	 public void copyDirectory(File sourceLocation , File targetLocation)
			    throws IOException {

			        if (sourceLocation.isDirectory()) {
			            if (!targetLocation.exists()) {
			                targetLocation.mkdir();
			            }

			            String[] children = sourceLocation.list();
			            for (int i=0; i<children.length; i++) {
			                copyDirectory(new File(sourceLocation, children[i]),
			                        new File(targetLocation, children[i]));
			            }
			        } else {

			            InputStream in = new FileInputStream(sourceLocation);
			            OutputStream out = new FileOutputStream(targetLocation);

			            // Copy the bits from instream to outstream
			            byte[] buf = new byte[1024];
			            int len;
			            while ((len = in.read(buf)) > 0) {
			                out.write(buf, 0, len);
			            }
			            in.close();
			            out.close();
			        }
			    }

}
