import java.util.ArrayList;
import java.util.Random;

public class democracy {
	String st = "@stop";
	String bw = "@backward";
	String fw = "@forward";
	String ri = "@right";
	String le = "@left";
	
	private Random ran = new Random();
	private DriveLog forward = new DriveLog(fw,"TO",4000);
	private DriveLog right = new DriveLog(ri,"RI",500);
	private DriveLog left = new DriveLog(le,"LE",600);
	private DriveLog backward = new DriveLog(bw,"BO",3000);
	private DriveLog stop = new DriveLog(st,"TF",500);

	private counter forwardCounter = new counter("@forward", forward);
	private counter rightCounter = new counter("@right", right);
	private counter leftCounter = new counter("@left", left);
	private counter backwardCounter = new counter("@backward", backward);
	private counter[] counters = new counter[4];
	private ArrayList<counter> biggestCounters = new ArrayList<counter>();
	
	public ArrayList<counter> getCounters(){
		return this.biggestCounters;
	}

	public democracy() {
		counters[0] = forwardCounter;
		counters[1] = leftCounter;
		counters[2] = rightCounter;
		counters[3] = backwardCounter;

	}

	public DriveLog getResult(Executor list) {
		clear();
	    System.out.println("index is " + main.indexCounter);
		for (int i = main.indexCounter ; i < list.size(); i++) {
			if (list.getArray().get(i) != null) {
				if (list.getArray().get(i).getCmdtype().equals("@forward")) {
					forwardCounter.increment();
				} else if (list.getArray().get(i).getCmdtype().equals("@left")) {
					leftCounter.increment();
				} else if (list.getArray().get(i).getCmdtype().equals("@right")) {
					rightCounter.increment();
				} else if (list.getArray().get(i).getCmdtype().equals("@backward")) {
					backwardCounter.increment();
				}
			}
		}
		int highest = -1;
		for (int i = 0; i < counters.length; i++) {
			if (counters[i].getCounter() > highest) {
				highest = counters[i].getCounter();
			}
		}
		for (int i = 0; i < counters.length; i++) {
			if (counters[i].getCounter() == highest) {
				biggestCounters.add(counters[i]);
			}
		}
		//TESTING PRINTS
		for (int i = 0; i < counters.length; i++) {
			System.out.println("          " + counters[i].getCommand() + ": " + counters[i].getCounter());
			
		}
		for (int i = 0; i < biggestCounters.size(); i++) {
			System.out.println("--------" + biggestCounters.get(i).getDriveLog().getCmdtype());
		}
		if (highest == 0) {
			System.out.println("Something is being returned");
			return stop;
		} else if (biggestCounters.size() == 1) {
			System.out.println("Something is being returned");
			System.out.println("size: " + biggestCounters.size());
			return biggestCounters.get(0).getDriveLog();
			

		} else {
			System.out.println("Something is being returned");
			return biggestCounters.get(ran.nextInt(biggestCounters.size())).getDriveLog();
		}
	}

	public void clear() {
		for (int i = 0; i < counters.length; i++) {
			counters[i].reset();
			System.out.println("          " + counters[i].getCommand() + ": " + counters[i].getCounter());
			
		}
		biggestCounters.clear();
//		for (int i = 0; i < biggestCounters.size(); i++) {
//			biggestCounters.remove(0);
//		}
		biggestCounters.trimToSize();
		for (int i = 0; i < counters.length; i++) {

		}
		System.out.println("Size = " + biggestCounters.size());

	}

}
