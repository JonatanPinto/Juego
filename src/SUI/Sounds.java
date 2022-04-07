package SUI;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
	private String soundPath;
	private Clip clip;

	public Sounds(String soundPath) {
		this.soundPath = soundPath;
		init();
	}

	public void init() {
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playSound() {
		try {
			File fileSong = new File("sounds/" + soundPath);
			clip.open(AudioSystem.getAudioInputStream(fileSong));
			clip.setFramePosition(0);  // Must always rewind!
            clip.loop(0);
            clip.start();
//			clip.close();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public String getSoundPath() {
		return soundPath;
	}

	public void setSoundPath(String soundPath) {
		this.soundPath = soundPath;
		init();
	}
}
