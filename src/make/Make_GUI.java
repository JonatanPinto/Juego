/**
 * 
 */
package make;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * @author Jonatan
 *
 */
public class Make_GUI {

	public static void makeLabel() {
		
	}
	
	public static void makeTextField() {
		
	}
	
	
	/**
	 * 
	 * @param component
	 * @param button
	 * @param actionCommand
	 * @param ac
	 * @param image
	 * @param toolTipText
	 * @param stade
	 */
	public static void makeButton(JComponent component, JButton button, String actionCommand, ActionListener ac, ImageIcon image, String toolTipText, boolean stade) {
		button = new JButton();
		button.addActionListener(ac);
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setIcon(image);
		component.add(button);
	}
	/**
	 * 
	 * @param jMenu
	 * @param jMenuItem
	 * @param text
	 * @param image
	 * @param toolTipText
	 * @param ac
	 * @param actionCommand
	 */
	public static void makeJMenuItem(JMenu jMenu, JMenuItem jMenuItem, String text, ImageIcon image, String toolTipText, ActionListener ac, String actionCommand) {
		jMenuItem = new JMenuItem(image);
		jMenuItem.setText(text);
		jMenuItem.setToolTipText(toolTipText);
		jMenuItem.addActionListener(ac);
		jMenuItem.setActionCommand(actionCommand);
		jMenu.add(jMenuItem);
		
	}

}
