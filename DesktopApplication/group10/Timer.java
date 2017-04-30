package group10;
/**
 * Creator Martin Chukaleski 03/2017
 */

import java.util.ArrayList;

public class Timer {
	public void timedTask(long s) {
		long start = System.currentTimeMillis() + s;
		while (start > System.currentTimeMillis()) {

		}
	}

	public void wait10sec() {
		long start = System.currentTimeMillis() + 10000;
		while (start > System.currentTimeMillis()) {
		}

	}

	public void timedExecutor(Executor s) {
		for (int i = 0; i < s.size(); i++) {

		}
	}
}