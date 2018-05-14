/**
 * Author: David Sampson
 * Date: 2018-05-14
 * Description: Wav Player
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//end imports


public class AudioPlayer extends JPanel implements ActionListener {

	//end field declaration
	private static final long serialVersionUID = 1L;
	final String newline = "\n";
	static String filePath;
	boolean paused = false;
	Long currentFrame;
    Clip clip;
    JTextArea log;
    JButton play, stop, resume, pause;

    AudioInputStream audioInputStream;
    
    // constructor for audioplayer includes gui and button setup
	AudioPlayer(String a)throws UnsupportedAudioFileException,
    IOException, LineUnavailableException {
		
		filePath = a;
		System.out.println("contr"+filePath);
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	         
	        // create clip reference
		clip = AudioSystem.getClip();
	         
	        // open audioInputStream to the clip
	    
	         
	    
	    
	    JPanel player = new JPanel();
	    log = new JTextArea(5,50);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
	    
	    play = new JButton("Play");
	    play.addActionListener(this);
		pause = new JButton("Pause");
		pause.addActionListener(this);
		stop = new JButton("Stop");
		resume = new JButton("Resume");
		stop.addActionListener(this);
		resume.addActionListener(this);
		player.setForeground(Color.blue);
		player.setBackground(Color.black);
		log.setForeground(Color.white);
		log.setBackground(Color.black);
		
		player.add(play);
		player.add(stop);
		player.add(pause);
		player.add(resume);
		add(player, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
		
		
	}//end constructor
	
	public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		if (paused == false)
			resetAudioStream();
		clip.start();
		System.out.println("Is playing");
		
	} // play method 
	
	public void pause() {
		currentFrame = clip.getMicrosecondPosition();
		clip.stop();
		paused = true;
	}
	public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        play();
	}
	public void stop() throws UnsupportedAudioFileException,IOException, LineUnavailableException {
		 currentFrame = 0L;
	     clip.stop();
	     clip.close();
	}
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
    LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void show(String a) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		JFrame frame = new JFrame("Audio Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String filePath = a.replace("\\", "\\\\");
        System.out.println(filePath);
        //Add content to the window.
        frame.add(new AudioPlayer(filePath));
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	// displaying frame and window ^
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==play) {
			log.append("Playing File: "+filePath+newline);
			try {
				play();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				
				e1.printStackTrace();
			}
		}
		
		if (e.getSource()==pause) {
			log.append("Pausing File: "+filePath+newline);
			pause();
		}
		
		
		if (e.getSource()==resume) {
			
			try {
				log.append("Resuming File: "+filePath+newline);
				resumeAudio();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource()==stop) {
			
			try {
				log.append("Stopping File: "+filePath+newline);
				stop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	//button set up and implenting new methods

}//end audio player object

