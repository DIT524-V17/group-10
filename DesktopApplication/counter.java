
public class counter {
	private String command;
	private int counter;

	public counter(String command){
		this.command = command;
		counter = 0;
	}
	public void reset(){
		counter = 0;
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
}
