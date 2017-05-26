import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BestKoreanDemocracy {

	private String st = "TF";
	private String bw = "BO";
	private String le = "LE";
	private String ri = "RI";
	private String fw = "TO";

	private ArrayList<String> possibleOutcomes = new ArrayList<String>();

	private static final int STOP = 0;
	private static final int BACK = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private static final int FORWARD = 4;

	public BestKoreanDemocracy() {
		super();
	}

	// int max = Arrays.stream(array).max().getAsInt();
	void initializeOutcomes() {
		possibleOutcomes.add(st);
		possibleOutcomes.add(bw);
		possibleOutcomes.add(le);
		possibleOutcomes.add(ri);
		possibleOutcomes.add(fw);
	}

	public String returnDemocracy(ArrayList<String> votes) {

		int[] voteCount = { 0, 0, 0, 0, 0 };
		int kimJong = 0;

		initializeOutcomes();

		System.out.println("votes: " + votes);
		for (String vote : votes) {
			if (vote.equals(fw)) {
				voteCount[FORWARD]++;
			} else if (vote.equals(bw)) {
				voteCount[BACK]++;
			} else if (vote.equals(le)) {
				voteCount[LEFT]++;
			} else if (vote.equals(ri)) {
				voteCount[RIGHT]++;
			} else if (vote.equals(st)) {
				voteCount[STOP]++;
			}
		}
		System.out.println(voteCount);

		// kimJong = Arrays.stream(voteCount).max().getAsInt();

		int leader = voteCount[0];

		for (int i = 1; i < voteCount.length; i++) {
			if (voteCount[i] > leader) {
				leader = voteCount[i];
				kimJong = i;
			}
		}
		System.out.println("max " + kimJong);

		System.out.println("final command: " + possibleOutcomes.get(kimJong));
		return possibleOutcomes.get(kimJong);
	}
}