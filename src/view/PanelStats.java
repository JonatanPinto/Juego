package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelStats extends JPanel{

	private JLabel lblLevel,lblLives,lblTime,lblPoints;
	
	public PanelStats() {
		this.setLayout(new GridLayout(1, 4));
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		initComponents();
	}

	private void initComponents() {
		setBackground(new Color(191, 181, 181));
		this.lblLevel = new JLabel(Constants.LEVEL.getName());
		this.lblLevel.setIcon(Constants.LEVEL.getIcon());
		this.add(lblLevel);
		
		this.lblLives = new JLabel(Constants.LIVES.getName());
		this.lblLives.setIcon(Constants.LIVES.getIcon());
		this.add(lblLives);
		
		this.lblTime = new JLabel(Constants.TIME.getName());
		this.lblTime.setIcon(Constants.TIME.getIcon());
		this.add(lblTime);
		
		this.lblPoints = new JLabel(Constants.POINTS.getName());
		this.lblPoints.setIcon(Constants.POINTS.getIcon());
		this.add(lblPoints);
	}
	
	public void setLevel(String level) {
		this.lblLevel.setText(Constants.LEVEL + " " + level);
	}
	public void setLives(String lives) {
		this.lblLives.setText(Constants.LIVES + " " + lives);
	}
	public void setTime(String time) {
		this.lblTime.setText(Constants.TIME + " " + time);
	}
	public void setPoints(String points) {
		this.lblPoints.setText(Constants.POINTS + " " + points);
	}

	public JLabel getLblLevel() {
		return lblLevel;
	}

	public void setLblLevel(JLabel lblLevel) {
		this.lblLevel = lblLevel;
	}

	public JLabel getLblLives() {
		return lblLives;
	}

	public void setLblLives(JLabel lblLives) {
		this.lblLives = lblLives;
	}

	public JLabel getLblTime() {
		return lblTime;
	}

	public void setLblTime(JLabel lblTime) {
		this.lblTime = lblTime;
	}

	public JLabel getLblPoints() {
		return lblPoints;
	}

	public void setLblPoints(JLabel lblPoints) {
		this.lblPoints = lblPoints;
	}
}
