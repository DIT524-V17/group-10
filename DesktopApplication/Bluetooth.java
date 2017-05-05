import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class Bluetooth {
	private OutputStream os;
	private boolean carFound = false;
	private final RemoteDevice[] carDevice = new RemoteDevice[1];

	public void findCar() throws BluetoothStateException, InterruptedException {
		carFound = false;

		final Object inquiryCompletedEvent = new Object();
		DiscoveryListener listener = new DiscoveryListener() {

			@Override
			public void deviceDiscovered(RemoteDevice car, DeviceClass arg1) {
				System.out.println("Bluetooth device was found");
				if (car.getBluetoothAddress().equals("201511231637")) {
					System.out.println("The car was found");
					carDevice[0] = car;
					carFound = true;
				} else {
					System.out.println("The found device was not the car");
				}
			}

			@Override
			public void inquiryCompleted(int arg0) {
				System.out.println("Device Inquiry completed!");
				synchronized (inquiryCompletedEvent) {
					inquiryCompletedEvent.notifyAll();
				}
			}

			@Override
			public void serviceSearchCompleted(int arg0, int arg1) {
			}

			@Override
			public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
			}
		};
		synchronized (inquiryCompletedEvent) {
			boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC,
					listener);
			if (started) {
				System.out.println("wait for device inquiry to complete...");
				inquiryCompletedEvent.wait();
				System.out.println("Search complete");
			}
		}
	}

	public boolean getCarFound() {
		return carFound;
	}

	public void connect() throws IOException {
		System.out.println("attempting to connect");
		StreamConnection con = (StreamConnection) Connector
				.open("btspp://" + carDevice[0].getBluetoothAddress() + ":1");
		System.out.println("Connection made");
		os = con.openOutputStream();
		InputStream is = con.openInputStream();
		//autoExecution(os);
	}

	public void timedTask(long s) { // wait for s milliseconds than execute next
		// line of code
		long start = System.currentTimeMillis() + s;
		while (start > System.currentTimeMillis()) {

		}
	}
	public void autoExecution(OutputStream test) {
            long endTime = System.currentTimeMillis() + 2000; 
            while (System.currentTimeMillis() < endTime) {
                // execute commands here for a specific time
                try {
					test.write("GG".getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
	public void btnPress(String command){
		try {
			getOs().write(command.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public OutputStream getOs(){
		return os;
	}
}
