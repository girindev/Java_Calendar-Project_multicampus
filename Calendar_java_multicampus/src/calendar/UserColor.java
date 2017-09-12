package calendar;

import java.awt.Color;

public class UserColor {
	public static Color getColor(String color) {
		Color returnColor = null;
		if (color.equals("GREEN")) {
			returnColor = Color.green;
		} else if (color.equals("RED")) {
			returnColor = Color.RED;
		} else if (color.equals("BLUE")) {
			returnColor = Color.BLUE;
		} else if (color.equals("BLACK")) {
			returnColor = Color.BLACK;
		} else if (color.equals("GRAY")) {
			returnColor = Color.GRAY;
		} else if (color.equals("YELLOW")) {
			returnColor = Color.YELLOW;
		} else {
			returnColor = Color.ORANGE; // 색상이 없을경우
		}
		
		return returnColor;
	}
}
