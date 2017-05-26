import java.util.ArrayList;

public class listMain {

	public static void main(String[] args) {
		democracy test = new democracy();
		ArrayList<DriveLog> list = new ArrayList<DriveLog>(10);
		Executor exec = new Executor();
		
		exec.add(new DriveLog(12,"@forward"));
		exec.add(new DriveLog(12,"@forward"));
		exec.add(new DriveLog(12,"@forward"));
		exec.add(new DriveLog(12,"@forward"));
		exec.add(new DriveLog(12,"@left"));
		exec.add(new DriveLog(12,"@left"));
		exec.add(new DriveLog(12,"@left"));
		exec.add(new DriveLog(12,"@left"));

		
		System.out.println(test.getResult(exec).getCmdtype());
		exec.indexCount = exec.getArray().size();
		
		exec.add(new DriveLog(12,"@forward"));
//		exec.add(new DriveLog(12,"@forward"));
//		exec.add(new DriveLog(12,"@right"));
//		exec.add(new DriveLog(12,"@right"));
//		exec.add(new DriveLog(12,"@right"));
		
		System.out.println(test.getResult(exec).getCmdtype());
	}

}
