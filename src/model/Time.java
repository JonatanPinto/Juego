package model;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
	private int seconds;
	private int minutes;
	private boolean pause = false;
	private boolean stop;
	private Timer timer;
	private TimerTask timerTask;

	public Time(int minutes) {
		this.minutes = minutes/2;
		if(minutes%2 != 0) {
			seconds = 30; 
		}else {
			seconds = 0;
		}
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			synchronized public void run() {
				if(!pause)
					runClock();
			}
		};
		timer.schedule(timerTask, 0, 1000);
	}

	public void runClock() {
		seconds--;
		if (seconds <= 0) {
			minutes--;
			seconds = 59;
		}
	}

	public String getChron() {
		return "0" + minutes + " : " + (seconds <= 9 ? "0" : "") + seconds;
	}

	public boolean isZero() {
		if (minutes < 0) {
			return true;
		}
		return false;
	}

	public void reInit(int minutes) {
		this.minutes = minutes;
		timer.schedule(timerTask, 0, 1000);
	}

	public void pauseTime() {
		stop = false;
		pause = true;
	}

	public void resumeTime() {
		stop = false;
		pause = false;
	}

}
