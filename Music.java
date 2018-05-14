/**
 * Author: David Sampson
 * Date: 2018-05-14
 * Description: File Selector
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
// import end

public class Music extends JPanel implements ActionListener{ // file selector object
	
	//fields 
	
	private static final long serialVersionUID = 1L;
	final String newline = "\n";
	JButton openButton, saveButton ;
	JTextArea log;
	static String fileLocation;
	final JFileChooser fc = new JFileChooser();
	
	Music(){ // constructor  for gui and buttons
		
		
		log = new JTextArea(5,20);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		openButton = new JButton("Open a File");
		openButton.addActionListener(this);
		saveButton = new JButton("Save a File");
		saveButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        
 
        
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
		
	}//end constructor 

	public static void createAndShowGUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	        //Create and set up the window.
	        JFrame frame = new JFrame("File Selection");
	        
	 
	        //Add content to the window.
	        frame.add(new Music());
	        
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        
	        
	    }
	 
	public void actionPerformed(ActionEvent e) { // button commands
	
		if (e.getSource() == openButton) {
	       
			int returnVal = fc.showOpenDialog(Music.this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            log.append("Opening: " + file.getName() + "." + newline);
	            Music.fileLocation = file.getAbsolutePath();
	            System.out.println(fileLocation);
	           
	            //passing the file location to the music player objectg
	            try {
					AudioPlayer.show(fileLocation);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
	            
	        } 
	        
	        else {
	            log.append("Open command cancelled by user." + newline);
	        }
	   
		}
		
	}
	
	
	
}//end file selector object