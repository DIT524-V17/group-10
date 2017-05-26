import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

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

import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.RenderCallback;
import uk.co.caprica.vlcj.player.direct.RenderCallbackAdapter;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;


public class controller {
    
	private final JPanel mediaPanel;
    private final BufferedImage img;
    private static final int width = 352;
    private static final int height = 288;
    private final JFrame frame;
    private final DirectMediaPlayerComponent mediaPlayerComponent;
	
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
            
		 boolean found = new NativeDiscovery().discover();
         System.out.println(found);
         System.out.println(LibVlc.INSTANCE.libvlc_get_version());
        
        frame = new JFrame("Controller");
        frame.setBounds(50, 100, 850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new BorderLayout());
        mediaPanel = new mediaSurface();
        mediaPanel.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());;
        
        basePanel.add(mediaPanel,BorderLayout.CENTER);
        img = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(width, height);
            BufferFormatCallback bufferFormatCallback = new BufferFormatCallback() {
                @Override
                public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
                    return new RV32BufferFormat(width, height);
                }
            };
            
            mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback)  {
                @Override
                protected RenderCallback onGetRenderCallback() {
                    return new jPlayRenderCallback();
                }
            };

        JPanel speedPanel = new JPanel();
        JPanel directionPanel = new JPanel();
        
        speedPanel.setLayout(new BorderLayout());
        directionPanel.setLayout(new BorderLayout());
        directionPanel.setPreferredSize(new Dimension(50, 50));
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
        forward.setHorizontalAlignment(JLabel.CENTER);
        directionPanel.add(forward,BorderLayout.NORTH);

       // back.setBounds(44, 264, 50, 50);
        back.setIcon(new ImageIcon(main.class.getResource("/resource/back0.png")));
        back.setHorizontalAlignment(JLabel.CENTER);
        directionPanel.add(back,BorderLayout.SOUTH);
        
        panel.add(speedPanel,BorderLayout.NORTH);
        panel.add(directionPanel,BorderLayout.CENTER);
        
       basePanel.add(panel,BorderLayout.WEST);
  
        frame.setContentPane(basePanel);
        frame.pack();
        frame.setVisible(true);
        // mediaPlayerComponent.getMediaPlayer().playMedia("http://192.168.0.1:8000/picam.webm");
        mediaPlayerComponent.getMediaPlayer().playMedia("http://clips.vorwaerts-gmbh.de/big_buck_bunny.webm");    
                
		
	
	//	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
			  main.bluetooth.btnPress("OF");
                          mediaPlayerComponent.release();
				frame.dispose();
			}
		});
		
	}
    
    private class mediaSurface extends JPanel {

        private mediaSurface() {
            setBackground(Color.black);
            setOpaque(true);
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(width, height));
            setMaximumSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(img, null, 0, 0);
        }
    }
    private class jPlayRenderCallback extends RenderCallbackAdapter {

        private jPlayRenderCallback() {
            super(new int[width * height]);
        }

        @Override
        protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
            // Simply copy buffer to the image and repaint
            img.setRGB(0, 0, width, height, rgbBuffer, 0, width);
            mediaPanel.repaint();
        }
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
