import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bluetooth = new Bluetooth();
		
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
				control control = new control();
				control.setFocusable(true);
				control.setVisible(true);
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
				SearchAndConnect();
				tryAgain.play();
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
		
		lblNoCar.setBounds(210, 96, 647, 481);
		lblNoCar.setIcon(new ImageIcon(main.class.getResource("/resource/NoFind.png")));
		contentPane.add(lblNoCar);
		
		lblConnFail.setVisible(false);
		lblConnFail.setBounds(172, 98, 622, 512);
		lblConnFail.setIcon(new ImageIcon(main.class.getResource("/resource/ConnFail.png")));
		contentPane.add(lblConnFail);
		
		
		SearchAndConnect();
	}
	public void SearchAndConnect(){
		try {
			lblSearching.setVisible(true);
			bluetooth.findCar();
			lblSearching.setVisible(false);
		} catch (BluetoothStateException | InterruptedException e) {
			lblNoCar.setVisible(true);
			lblSearching.setVisible(false);
			lblTryAgain.setVisible(true);
		}
		if(bluetooth.getCarFound()){
			try {
				lblConnecting.setVisible(true);
				bluetooth.connect();
				lblConnecting.setVisible(false);
				lblController.setVisible(true);
				lblTwitch.setVisible(true);
			} catch (IOException e) {
				lblConnFail.setVisible(true);
				lblSearching.setVisible(false);
				lblTryAgain.setVisible(true);
			}
		}
	}
}
