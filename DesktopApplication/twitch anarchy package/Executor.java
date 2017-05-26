

/**
 * Creator Martin Chukaleski 03/2017
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.bluetooth.BluetoothConnectionException;
import javax.bluetooth.BluetoothStateException;

public class Executor {
	
	ArrayList<DriveLog> arr = new ArrayList<DriveLog>(); // containing all the drivelog objects that need to be executed
	private int indexCount = main.indexCounter; // a way to track down where we left of in the execution so we dont execute old commands

	public int size() { // checks the size of the array list
		return this.arr.size();
	}

	public boolean isEmpty() { // checking if the array is empty
		return this.size() == 0 ? true : false;
	}

	public void add(DriveLog a) { // adds DriveLog objects to the array
		this.arr.add(a);
	}

	public void clearAll() { // removes every object of the array list
		this.arr.clear();
	}

	public long getTime(int i) { // get the time of the DriveLog object at index
									// i
		return this.arr.get(i).getTime();
	}

	public String getCmdtype(int i) { // get the cmdType of the DriveLog object
										// at index i
		return this.arr.get(i).getCmdtype();
	}

	public String getCommand(int i) { // get the command in a form of a string
										// that is sent to the smart car
		return this.arr.get(i).getCommand();
	}

	public ArrayList<DriveLog> getArray() { // return the ArrayList of Execution
											// object
		return this.arr;
	}

	public void autoExecution() {
		
		main.sizecount = this.size();
		

		for (int i = indexCount; i < this.size(); i++) { // for loop to iterate
															// thru every
															// element of the
															// array list
				// calling the method to execute the command from the executor object on index i
				main.bluetooth.btnPress(this.getCommand(i));
				
				Timer t = new Timer();
				t.timedTask(this.getTime(i));// waiting for the time to pass and after that sending a stop command to the car
				
				main.bluetooth.btnPress("TF");
		
				

			
			
			main.indexCounter = this.size(); // having the indexCount as a stored index
										// from the object executor so that when
										// the txt file is updated the program
			// doesn't execute the old commands only the new ones
		}
	}
	

	public void printF() { // for testing purposes print all commands from
							// DriveLog objects stored in Execution obj
		for (DriveLog x : this.arr) {
			System.out.print(x.getCmdtype() + " ");
		}
	}
	
}
