

/**
 * Creator Martin Chukaleski 03/2017
 */

import java.util.ArrayList;

public class Timer {
	public void timedTask(long s) { // wait for s milliseconds than execute next
									// line of code
		long start = System.currentTimeMillis() + s;
		while (start > System.currentTimeMillis()) {

		}
	}

	public void wait10sec() { // wait for 10 milliseconds than execute next line
								// of code
		long start = System.currentTimeMillis() + 10000;
		while (start > System.currentTimeMillis()) {
		}

	}
}