
public class listMain {

	public static void main(String[] args) {
		democracy test = new democracy();
		String[] list = new String[10];
		list[0] = "@right";
		list[1] = "@forward";
		list[2] = "@left";
		list[3] = "@forward";
		list[4] = "@left";
		System.out.println(test.getResult(list));
	}

}
