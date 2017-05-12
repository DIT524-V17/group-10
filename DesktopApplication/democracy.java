import java.util.ArrayList;
import java.util.Random;

public class democracy {
	private Random ran = new Random();
	private String[] list;
	private counter forwardCounter = new counter("@forward");
	private counter rightCounter = new counter("@right");
	private counter leftCounter = new counter("@left");
	private counter backwardCounter = new counter("@backward");
	private counter[] counters = new counter[4];
	private ArrayList<counter> biggestCounters = new ArrayList<counter>(1);

	public democracy() {
		counters[0] = forwardCounter;
		counters[1] = leftCounter;
		counters[2] = rightCounter;
		counters[3] = backwardCounter;
	}

	public String getResult(String[] list) {
		this.list = list;
		for (int i = 0; i < list.length; i++) {
			if(list[i] != null){
			if (list[i].equals("@forward")) {
				forwardCounter.increment();
			} else if (list[i].equals("@left")) {
				leftCounter.increment();
			} else if (list[i].equals("@right")) {
				rightCounter.increment();
			} else if (list[i].equals("@backward")) {
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
		if(biggestCounters.size() == 1){
			return biggestCounters.get(0).getCommand();
		}
		else{
			return biggestCounters.get(ran.nextInt(biggestCounters.size())).getCommand();
		}
	}
}
