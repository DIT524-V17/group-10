import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.bluetooth.BluetoothStateException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main extends JFrame {

	private JPanel contentPane;
	private JButton btnCont = new JButton("Connect as controller");
	private JLabel connectionPic = new JLabel("");

	private boolean menu1 = true;
	private boolean menuCont = false;
	private boolean menuTwitch = false;

	private boolean carForward = false;
	private boolean carLeft = false;
	private boolean carRight = false;
	private boolean carBack = false;

	private final JButton btnMakeDummyConnection = new JButton("Make dummy connection");
	private final JButton btnBack = new JButton("Back");
	private final JButton btnDisconnect = new JButton("Disconnect");
	private final JButton btnStart = new JButton("Start");
	private final JButton btnTwitch = new JButton("Connect to Twitch.tv");

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setTitle("SMLR");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setFocusable(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCont.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu1();
				menuCont();
			}
		});
		btnCont.setBounds(70, 341, 229, 96);
		btnCont.setFocusable(false);
		contentPane.add(btnCont);

		connectionPic.setBounds(294, 16, 239, 53);
		connectionPic.setIcon(new ImageIcon(main.class.getResource("/resource/notcon.png")));
		connectionPic.setVisible(false);
		connectionPic.setFocusable(false);
		contentPane.add(connectionPic);

		btnMakeDummyConnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				Bluetooth bluetooth = new Bluetooth();
				try {
					bluetooth.findCar();
				} catch (BluetoothStateException | InterruptedException e1) {
					e1.printStackTrace();
				}
				if (bluetooth.getCarFound()) {
					try {
						bluetooth.connect();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					connectionPic.setIcon(new ImageIcon(main.class.getResource("/resource/conn.png")));
					btnStart.setVisible(true);
				} else {
					System.out.println("Car not found");
				}
			}
		});
		btnMakeDummyConnection.setBounds(294, 78, 244, 39);
		btnMakeDummyConnection.setFocusable(false);
		btnMakeDummyConnection.setVisible(false);
		contentPane.add(btnMakeDummyConnection);

		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu1();
				menuCont();
				disconnectController();
			}
		});
		btnBack.setBounds(15, 16, 140, 53);
		btnBack.setFocusable(false);
		btnBack.setVisible(false);
		contentPane.add(btnBack);
		btnDisconnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disconnectController();
				btnStart.setVisible(false);
			}
		});
		btnDisconnect.setBounds(548, 16, 121, 53);
		btnDisconnect.setFocusable(false);
		btnDisconnect.setVisible(false);
		contentPane.add(btnDisconnect);

		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control control = new control();
				control.setFocusable(true);
				control.setVisible(true);
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnStart.setVisible(false);
		btnStart.setBackground(Color.GREEN);
		btnStart.setBounds(703, 216, 260, 145);

		contentPane.add(btnStart);
		btnTwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		

		btnTwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menu1();
				menuTwitch();
			}
		});
		btnTwitch.setBounds(70, 478, 229, 96);
		contentPane.add(btnTwitch);
	}

	public void menu1() {
		if (menu1)
			menu1 = false;
		else
			menu1 = true;
		btnCont.setVisible(menu1);
		btnTwitch.setVisible(menu1);
	}

	public void menuCont() {
		if (menuCont)
			menuCont = false;
		else
			menuCont = true;
		connectionPic.setVisible(menuCont);
		btnMakeDummyConnection.setVisible(menuCont);
		btnBack.setVisible(menuCont);
		btnDisconnect.setVisible(menuCont);
	}
	public void menuTwitch(){
		if (menuTwitch)
			menuTwitch = false;
		else
			menuTwitch = true;
	}
	public void disconnectController() {
		connectionPic.setIcon(new ImageIcon(main.class.getResource("/resource/notcon.png")));
		btnStart.setVisible(false);
	}
}
