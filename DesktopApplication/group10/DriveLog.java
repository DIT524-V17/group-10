package group10;

/**
 * Creator Martin Chukaleski 03/2017
 */

public class DriveLog {
	private long time;
	private String cmdtype;

	public DriveLog(long time, String cmdtype) {
		this.time = time;
		this.cmdtype = cmdtype;
	}

	public DriveLog(String cmdtype) {
		this.time = 1000;
		this.cmdtype = cmdtype;
	}

	public long getTime() {
		return time;
	}

	public String getCmdtype() {
		return cmdtype;
	}
}
