
public class counter {
	private String command;
	private int counter;
	private DriveLog driveLog;

	public counter(String command, DriveLog driveLog){
		this.driveLog = driveLog;
		this.command = command;
		counter = 0;
	}
	public void reset(){
		this.counter = 0;
		System.out.println(command + " is now " + counter);
	}
	public void increment(){
		counter++;
	}
	public String getCommand(){
		return command;
	}
	public int getCounter(){
		return counter;
	}
	public DriveLog getDriveLog(){
		return driveLog;
		
	}
}
