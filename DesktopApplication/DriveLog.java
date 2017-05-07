

/**
 * Creator Martin Chukaleski 03/2017
 */

public class DriveLog {
	private long time;
	private String cmdtype;
	private String command;

	public DriveLog(long time, String cmdtype) {
		this.time = time;
		this.cmdtype = cmdtype;
	}

	public DriveLog(String cmdtype , String command) {
		this.time = 1000;
		this.cmdtype = cmdtype;
		this.command = command;
	}

	public long getTime() {
		return time;
	}

	public String getCmdtype() {
		return cmdtype;
	}
	public String getCommand() {
		return command;
	}
}
