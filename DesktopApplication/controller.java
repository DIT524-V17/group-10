import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
//Author: Ludvig Hygrell

public class controller {
    
        private static final String NATIVE_LIBRARY_SEARCH_PATH = "C:\\Users\\Gautam\\Documents\\vlc";
        private static EmbeddedMediaPlayerComponent mediaPlayerComponent;
        private final JFrame frame;
	private JPanel contentPane;
	
	private boolean keyPress = false;
	private boolean carForward = false;
	private boolean carLeft = false;
	private boolean carRight = false;
	private boolean carBack = false;
	private boolean prevention = false;
	private boolean Fkey = false;
	
	private String[] speeds = new String[3];
	private int speed = 0;
	private JLabel lblSpeed = new JLabel();
	private JLabel lblPrevention = new JLabel("Prevention: " + prevention);
	private JLabel left = new JLabel("");
	private JLabel right = new JLabel("");
	private JLabel forward = new JLabel("");
	private final JLabel back = new JLabel("");
	private final JPanel panel = new JPanel();
	//private final JLabel lblVideoFeed = new JLabel("Video feed");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
            
            
            
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                                        new controller();
					//frame.setVisible(true);
                                        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public controller() {
            
            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);
        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
        
        frame = new JFrame("jStreamPlayer");
        frame.setBounds(50, 100, 850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
        
        
        JPanel mediaBase = new JPanel();
        mediaBase.setLayout(new BorderLayout());

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaBase.add(mediaPlayerComponent, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
 //      urlField = new JTextField("http://192.168.0.2:8008/test1.mpg",50);

        JPanel speedPanel = new JPanel();
        JPanel directionPanel = new JPanel();
        
        speedPanel.setLayout(new BorderLayout());
        directionPanel.setLayout(new BorderLayout());
        speeds[0] = "Low";
        speeds[1] = "Medium";
        speeds[2] = "High";
        lblSpeed.setText("Speed:  " + speeds[speed]);
        
     //   lblSpeed.setBounds(15, 54, 69, 20);
        speedPanel.add(lblSpeed,BorderLayout.NORTH);
               


      //  lblPrevention.setBounds(15, 89, 102, 20);
        speedPanel.add(lblPrevention,BorderLayout.SOUTH);


     //   left.setBounds(15, 203, 50, 50);
        left.setIcon(new ImageIcon(main.class.getResource("/resource/left0.png")));
        directionPanel.add(left,BorderLayout.WEST);


     //   right.setBounds(75, 203, 50, 50);
        right.setIcon(new ImageIcon(main.class.getResource("/resource/right0.png")));
        directionPanel.add(right,BorderLayout.EAST);

       // forward.setBounds(44, 142, 50, 50);
        forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward0.png")));
        directionPanel.add(forward,BorderLayout.NORTH);

       // back.setBounds(44, 264, 50, 50);
        back.setIcon(new ImageIcon(main.class.getResource("/resource/back0.png")));
        directionPanel.add(back,BorderLayout.SOUTH);
        
        panel.add(speedPanel,BorderLayout.NORTH);
        panel.add(directionPanel,BorderLayout.CENTER);
        
       mediaBase.add(panel,BorderLayout.WEST);
       frame.setBounds(50, 40, 640, 500);
        frame.setContentPane(mediaBase);
        frame.setVisible(true);
       mediaPlayerComponent.getMediaPlayer().playMedia("http://192.168.0.1:8000/picam.webm");
              
                
		
	
	//	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
			  main.bluetooth.btnPress("OF");
                          mediaPlayerComponent.release();
				frame.dispose();
			}
		});
		
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		
//		contentPane.setLayout(new BorderLayout() );
//                panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		//panel.setBounds(165, 54, 749, 497);
//		
//		contentPane.add(panel);
//		panel.setLayout(null);
//		//lblVideoFeed.setFont(new Font("Tahoma", Font.PLAIN, 43));
//		//lblVideoFeed.setBounds(281, 185, 348, 101);
//		panel.add(mediaPlayerComponent,BorderLayout.NORTH);
//             //   panel.setVisible(true);
//		
//		//lblSpeed.setBounds(15, 54, 69, 20);
//		contentPane.add(lblSpeed,BorderLayout.WEST);
//		
//
//		//lblPrevention.setBounds(15, 89, 102, 20);
//		contentPane.add(lblPrevention,BorderLayout.WEST);
//		
//
//		//left.setBounds(15, 203, 50, 50);
//		left.setIcon(new ImageIcon(main.class.getResource("/resource/left0.png")));
//		contentPane.add(left,BorderLayout.WEST);
//		
//
//		//right.setBounds(75, 203, 50, 50);
//		right.setIcon(new ImageIcon(main.class.getResource("/resource/right0.png")));
//		contentPane.add(right,BorderLayout.WEST);
//		
//		//forward.setBounds(44, 142, 50, 50);
//		forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward0.png")));
//		contentPane.add(forward,BorderLayout.WEST);
//		
//		//back.setBounds(44, 264, 50, 50);
//		back.setIcon(new ImageIcon(main.class.getResource("/resource/back0.png")));
//		contentPane.add(back,BorderLayout.WEST);
//		
//		//frame.add(panel);
//                frame.setContentPane(contentPane);
//                frame.setVisible(true);
//              mediaPlayerComponent.getMediaPlayer().playMedia("http://dl1.webmfiles.org/elephants-dream.webm");
	}
	public void processKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			
			if (e.getKeyCode() == KeyEvent.VK_UP && !keyPress) {
				main.bluetooth.btnPress("TO");
				System.out.println("Car is driving forward");
				forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward1.png")));
				carForward = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT && !keyPress){
				main.bluetooth.btnPress("LE");
				System.out.println("Car is turning left");
				left.setIcon(new ImageIcon(main.class.getResource("/resource/left1.png")));
				carLeft = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && !keyPress){
				System.out.println("Car is turning right");
				main.bluetooth.btnPress("RI");
				right.setIcon(new ImageIcon(main.class.getResource("/resource/right1.png")));
				carRight = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN && !keyPress){
				main.bluetooth.btnPress("BO");
				System.out.println("Car is going in reverse");
				back.setIcon(new ImageIcon(main.class.getResource("/resource/back1.png")));
				carBack = true;
				keyPress = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_Z && !keyPress){
				if(speed > 0) speed -= 1;
				changeSpeed(speed);
				lblSpeed.setText("Speed: " + speeds[speed]);
			}
			if (e.getKeyCode() == KeyEvent.VK_X && !keyPress){
				if (speed < 2) speed += 1;
				changeSpeed(speed);
				lblSpeed.setText("Speed: " + speeds[speed]);
			}
			if (e.getKeyCode() == KeyEvent.VK_F && !keyPress){
					if(prevention){
						prevention = false;
						main.bluetooth.btnPress("OF");
					}
					else{
						prevention = true;
						main.bluetooth.btnPress("ON");
					}
					lblPrevention.setText("Prevention: " + prevention);
					
			}

		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			if (e.getKeyCode() == KeyEvent.VK_UP && carForward == true) {
				main.bluetooth.btnPress("TF");
				System.out.println("Car stopped driving forward");
				forward.setIcon(new ImageIcon(main.class.getResource("/resource/forward0.png")));
				carForward = false;
				keyPress = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && carLeft == true) {
				main.bluetooth.btnPress("TF");
				System.out.println("Car stopped turning left");
				left.setIcon(new ImageIcon(main.class.getResource("/resource/left0.png")));
				carLeft = false;
				keyPress = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && carRight == true) {
				main.bluetooth.btnPress("TF");
				System.out.println("Car stopped turning right");
				right.setIcon(new ImageIcon(main.class.getResource("/resource/right0.png")));
				carRight = false;
				keyPress = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && carBack == true) {
				main.bluetooth.btnPress("TF");
				System.out.println("Car stopped going in reverse");
				back.setIcon(new ImageIcon(main.class.getResource("/resource/back0.png")));
				carBack = false;
				keyPress = false;
			}
			
		} else {
		}
	}
	public void changeSpeed(int speed){
		if(speed == 0){
			main.bluetooth.btnPress("S1");
		}
		else if(speed == 1){
			main.bluetooth.btnPress("S2");
		}
		else {
			main.bluetooth.btnPress("S3");
		}
	}
}
