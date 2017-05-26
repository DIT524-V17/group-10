

/**
 * Creator Martin Chukaleski 03/2017
 */

// Class used to store the driving information in a object time is the time that we want the bluetooth to send to command to the smartcar
//cmdtype is the type of string that we send to the smart car serial and command is the command itself like right forward back to 
//distungiush them easier
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
	public DriveLog(String cmdtype , String command , long time) {
		this.time = time;
		this.cmdtype = cmdtype;
		this.command = command;
	}
	// returns the time in milliseconds
	public long getTime() {
		return time;
	}
	// returns the string type like "TO" "TF"
	public String getCmdtype() {
		return cmdtype;
	}
	// returns the command like right left forward
	public String getCommand() {
		return command;
	}
}
