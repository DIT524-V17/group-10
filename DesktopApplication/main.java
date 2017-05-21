import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.bluetooth.BluetoothConnectionException;
import javax.bluetooth.BluetoothStateException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//Author: Ludvig Hygrell

public class main extends JFrame {

	private JPanel contentPane;
	public static Bluetooth bluetooth;
	
	private JLabel lblSearching = new JLabel("");
	private JLabel lblConnecting = new JLabel("");
	private final JLabel lblController = new JLabel("");
	private final JLabel lblTwitch = new JLabel("");
	private JLabel lblTryAgain = new JLabel("");
	private final JLabel lblNoCar = new JLabel("");
	private final JLabel lblConnFail = new JLabel("");

	private Object syncEvent = new Object();
	private Sound clickSound;
	private Sound mouseoverSound;
	private Sound tryAgain;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		clickSound = new Sound("/resource/clickSound.wav");
		mouseoverSound = new Sound("/resource/mouseoverSound.wav");
		tryAgain = new Sound("/resource/tryAgain.wav");
		
		setTitle("SMLR");
		setFocusable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				if(bluetooth.getCarConnected()){
				try {
					bluetooth.getOs().close();
					bluetooth.getCon().close();
					dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
				dispose();
			} else {
				dispose();
			}
			}
			
		});
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblSearching.setIcon(new ImageIcon(main.class.getResource("/resource/searching.png")));
		lblSearching.setBounds(249, 146, 584, 373);
		lblSearching.setVisible(false);
		contentPane.add(lblSearching);
		
		
		lblConnecting.setVisible(false);
		lblConnecting.setBounds(191, 96, 673, 528);
		lblConnecting.setIcon(new ImageIcon(main.class.getResource("/resource/connecting.png")));
		contentPane.add(lblConnecting);
		
		lblController.setBounds(285, 74, 476, 225);
		lblController.setVisible(false);
		lblController.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblController.setIcon(new ImageIcon(main.class.getResource("/resource/controller2.png")));
				mouseoverSound.play();
				lblController.setBounds(260, 29, 521, 319);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblController.setIcon(new ImageIcon(main.class.getResource("/resource/controller.png")));
				lblController.setBounds(285, 74, 476, 225);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				clickSound = new Sound("/resource/clickSound.wav");
				clickSound.play();
				controller control = new controller();
                             //   jSTPlay jplayer = new jSTPlay();
//				control.setFocusable(true);
//				control.setVisible(true);
			}
		});
		lblController.setIcon(new ImageIcon(main.class.getResource("/resource/controller.png")));
		contentPane.add(lblController);
		
		//CONNECT TO TWITCH BUTTON
		lblTwitch.setVisible(false);
		lblTwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTwitch.setIcon(new ImageIcon(main.class.getResource("/resource/twitch2.png")));
				mouseoverSound.play();
				lblTwitch.setBounds(285, 378, 459, 300);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblTwitch.setBounds(300, 396, 425, 268);
				lblTwitch.setIcon(new ImageIcon(main.class.getResource("/resource/twitch.png")));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickSound.play();
				TwitchCommandSend test = new TwitchCommandSend();
				test.sendCommands("C:/Users/Ludvig/Desktop/t/twitch.txt");
			}
		});
		lblTwitch.setBounds(300, 396, 425, 268);
		lblTwitch.setIcon(new ImageIcon(main.class.getResource("/resource/twitch.png")));
		contentPane.add(lblTwitch);
		
		//TRY AGAIN BUTTON
		lblTryAgain.setVisible(false);
		lblTryAgain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNoCar.setVisible(false);
				lblConnFail.setVisible(false);
				lblTryAgain.setVisible(false);
				tryAgain.play();
				try {
					SearchAndConnect();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseoverSound.play();
				lblTryAgain.setBounds(257, 560, 430, 144);
				lblTryAgain.setIcon(new ImageIcon(main.class.getResource("/resource/tryAgain2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblTryAgain.setBounds(266, 563, 419, 134);
				lblTryAgain.setIcon(new ImageIcon(main.class.getResource("/resource/tryAgain.png")));
			}
		});
		lblTryAgain.setBounds(266, 563, 419, 134);
		contentPane.add(lblTryAgain);
		lblTryAgain.setIcon(new ImageIcon(main.class.getResource("/resource/tryAgain.png")));
		
		lblNoCar.setVisible(false);
		lblNoCar.setBounds(210, 96, 647, 481);
		lblNoCar.setIcon(new ImageIcon(main.class.getResource("/resource/NoFind.png")));
		contentPane.add(lblNoCar);
		
		lblConnFail.setVisible(false);
		lblConnFail.setBounds(172, 98, 622, 512);
		lblConnFail.setIcon(new ImageIcon(main.class.getResource("/resource/ConnFail.png")));
		contentPane.add(lblConnFail);
		
		bluetooth = new Bluetooth();
		try {
			SearchAndConnect();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	public void SearchAndConnect() throws InterruptedException{
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				try {
					bluetooth.findCar();
					lblSearching.setVisible(false);
					if(bluetooth.getCarFound()) {
						lblConnecting.setVisible(true);
						try{
						bluetooth.connect();
						lblConnecting.setVisible(false);
						lblTwitch.setVisible(true);
						lblController.setVisible(true);;
						} catch (BluetoothConnectionException e){
							lblConnecting.setVisible(false);
							lblTryAgain.setVisible(true);
							lblConnFail.setVisible(true);
						}

					}
					else{
						lblTryAgain.setVisible(true);
						lblNoCar.setVisible(true);
					}
				} catch (BluetoothStateException | InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		lblSearching.setVisible(true);
		t1.start();
	}
	public void tryAgain(){
		try {
			SearchAndConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
