/**
 * Author: David Sampson
 * Date: 2018-05-14
 * Description: Runner
 */
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MusicPlayer {

	 static String filePath;
	 static AudioPlayer a;
	public static void main (String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		
		//start the thread
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            	
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                try {
					Music.createAndShowGUI(); // sets up file selection
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					
					e.printStackTrace();
				}
                
        	} // run end
        }); // thread end
	}//end main 
} // end class runner
