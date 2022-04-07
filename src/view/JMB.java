package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.Controller;
import make.Make_GUI;

public class JMB extends JMenuBar {

	private JMenu jmFile, jmAbout;
	private JMenuItem fileRefresh, fileNewLevel, fileNextLevel, jMenuItemPause, jMenuItemResume;

	public JMB() {
		jmFile = new JMenu("Archivo");
		Make_GUI.makeJMenuItem(jmFile, fileRefresh, "Info", null, null, Controller.getController(), "Hi");
		jmFile.addSeparator();
		JMenuItem jMenuItem = new JMenuItem("Controles");
		jMenuItem.setToolTipText("Revisa los controles");
		jMenuItem.setActionCommand("viewControls");
		jMenuItem.addActionListener(Controller.getController());
		jmFile.add(jMenuItem);
		this.add(jmFile);
		
		
		
		JMenu jMenuGame = new JMenu("Game");
		jMenuItemPause =  new JMenuItem("Pause");
		jMenuItemPause.setToolTipText("Pausa el juego");
		jMenuItemPause.setActionCommand("pauseGame");
		jMenuItemPause.addActionListener(Controller.getController());
		jMenuGame.add(jMenuItemPause);
		
		jMenuItemResume =  new JMenuItem("Resume");
		jMenuItemResume.setToolTipText("Reanuda el juego");
		jMenuItemResume.setActionCommand("resumeGame");
		jMenuItemResume.addActionListener(Controller.getController());
		jMenuItemResume.setEnabled(false);
		jMenuGame.add(jMenuItemResume);
		
		add(jMenuGame);
	}
	
	public void pause() {
		jMenuItemPause.setEnabled(false);
		jMenuItemResume.setEnabled(true);
	}
	
	public void resume() {
		jMenuItemPause.setEnabled(true);
		jMenuItemResume.setEnabled(false);
	}
	
}
