package gruppe63_dungeon_crawler;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
/**
 * 
 * Sounddatei: Alle Sounds werden von hier geladen 
 *
 */

public class Sound {
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream myInputStream= AudioSystem.getAudioInputStream(new File("res/Underwater.wav"));
	    AudioFormat myAudioFormat = myInputStream.getFormat();
	    int groesse = (int)(myAudioFormat.getFrameSize()* myInputStream.getFrameLength());
	    byte[] mySound = new byte[groesse];
	    DataLine.Info myInfo = new DataLine.Info(Clip.class, myAudioFormat, groesse);
	    myInputStream.read(mySound,0,groesse);
	    
	    Clip myClip = (Clip) AudioSystem.getLine(myInfo);
	    myClip.open(myAudioFormat, mySound, 0, groesse);
	    myClip.start();
	    
	}
	public static void main2(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream myInputStream= AudioSystem.getAudioInputStream(new File("res/Gitarre.wav"));
	    AudioFormat myAudioFormat = myInputStream.getFormat();
	    int groesse = (int)(myAudioFormat.getFrameSize()* myInputStream.getFrameLength());
	    byte[] mySound = new byte[groesse];
	    DataLine.Info myInfo = new DataLine.Info(Clip.class, myAudioFormat, groesse);
	    myInputStream.read(mySound,0,groesse);
	    
	    Clip myClip = (Clip) AudioSystem.getLine(myInfo);
	    myClip.open(myAudioFormat, mySound, 0, groesse);
	    myClip.start();
	    
	}
	public static void main3(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream myInputStream= AudioSystem.getAudioInputStream(new File("res/buch.wav"));
	    AudioFormat myAudioFormat = myInputStream.getFormat();
	    int groesse = (int)(myAudioFormat.getFrameSize()* myInputStream.getFrameLength());
	    byte[] mySound = new byte[groesse];
	    DataLine.Info myInfo = new DataLine.Info(Clip.class, myAudioFormat, groesse);
	    myInputStream.read(mySound,0,groesse);
	    
	    Clip myClip = (Clip) AudioSystem.getLine(myInfo);
	    myClip.open(myAudioFormat, mySound, 0, groesse);
	    myClip.start();
	    
	}
}
