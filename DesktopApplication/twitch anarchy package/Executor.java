
/**
 * Creator Martin Chukaleski 03/2017
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.bluetooth.BluetoothConnectionException;
import javax.bluetooth.BluetoothStateException;

public class Executor {
	
	private DriveLog forward = new DriveLog("@forward","TO",4000);
	private DriveLog right = new DriveLog("@right","RI",500);
	private DriveLog left = new DriveLog("@left","LE",600);
	private DriveLog backward = new DriveLog("@backward","BO",3000);
	private DriveLog stop = new DriveLog("@stop","TF",500);
	
	int subExeCounter = 0;
	
	ArrayList<DriveLog> arr = new ArrayList<DriveLog>();
	static int indexCount = 0;

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

		ArrayList<String> immediately = new ArrayList<String>();
		for (int i = indexCount; i < this.size(); i++) { // for loop to iterate
															// thru every
															// element of the
															// array list

			// long endTime = System.currentTimeMillis() + this.getTime(i); //
			// when
			// do
			// we
			// want
			// the
			// while
			// loop
			// to
			// stop

			// sending
			// commands

			// System.out.println(this.size());
			// System.out.println(this.getCommand(i));

			// System.out.println(this.getCommand(i));

			System.out.println("line: " + i + " single command: " + this.getCommand(i));

			// implementing democracy
			//if (subExeCounter < 5) {
			if((i%10)!=0 || i == 0){
				immediately.add(this.getCommand(i));
				subExeCounter++;
				// System.out.println("jj = " + jj + " " + immediately);
			} else {
				//i --;
				immediately.add(this.getCommand(i));
				//subExeCounter = 0;
				BestKoreanDemocracy kimJongBest = new BestKoreanDemocracy();
				String bestDemocracy = kimJongBest.returnDemocracy(immediately);
				// main.bluetooth.btnPress(this.getCommand(i));

				System.out.println("Driving: " + bestDemocracy);
				
				if(bestDemocracy.equals("TO")){
					main.bluetooth.btnPress(bestDemocracy);
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (bestDemocracy.equals("BO")){
					main.bluetooth.btnPress(bestDemocracy);
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(bestDemocracy.equals("LE")){
					try {
						main.bluetooth.btnPress(bestDemocracy);
						TimeUnit.MILLISECONDS.sleep(400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(bestDemocracy.equals("RI")){
					main.bluetooth.btnPress(bestDemocracy);
					try {
						TimeUnit.MILLISECONDS.sleep(400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					main.bluetooth.btnPress(bestDemocracy);
				}
				// Timer t = new Timer();
				// t.timedTask(this.getTime(i));
				
//				try {
//					TimeUnit.MILLISECONDS.sleep(400);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				main.bluetooth.btnPress("TF");
				
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				immediately = new ArrayList<String>();

			}

			indexCount = this.size(); // having the indexCount as a stored index
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
