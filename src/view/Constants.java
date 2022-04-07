package view;

import java.awt.Image;
import javax.swing.ImageIcon;

public enum Constants {

	LEVEL("Nivel: ", "castle.png"), LIVES("Vidas: ", "hearts.png"), TIME("Tiempo: ", "clock2.png"), POINTS("Puntos: ",
			"lingote.png");

	private String name, nameIcon, actionCommand;

	private Constants(String name, String nameIcon) {
		this.name = name;
		this.nameIcon = nameIcon;
	}

	public String getName() {
		return name;
	}

	public ImageIcon getIcon() {
		return new ImageIcon(new ImageIcon(ConstantsDefault.PATH_ICON + nameIcon).getImage()
				.getScaledInstance(ConstantsDefault.SIZE_ICON, ConstantsDefault.SIZE_ICON, Image.SCALE_DEFAULT));
	}
}
