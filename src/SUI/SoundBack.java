package SUI;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class SoundBack extends Thread {
	private JFXPanel jfxPanel;
	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;

	private boolean pause;
	private boolean stop;

	public SoundBack(String string) {
		jfxPanel = new JFXPanel();
		file = new File(string);
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		startSoundBack();
	}

	public void playSoundBack() {
		mediaPlayer.play();
	}

	public void startSoundBack() {
		start();
	}

	public synchronized void pauseSoundBack() {
		pause = true;
		stop = false; 
	}
	
	public synchronized void resumeSoundBack() {
		pause = false;
		stop = false; 
	}

	public synchronized void stopSoundBack() {
		pause = false;
		stop = true;
		notify();
	}

	@Override
	public void run() {
		while (!stop) {
			while (!pause) {
				playSoundBack();
				if(!(mediaPlayer.getStatus().equals(Status.PLAYING))) {
					mediaPlayer.seek(Duration.ZERO);
					mediaPlayer.play();
				}
			}
			if(pause) {
				mediaPlayer.pause();
			}
			if(stop) {
				mediaPlayer.stop();
			}
		}
	}
	
	public void setSong(String pathSong) {
		file = new File(pathSong);
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);

	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
}
