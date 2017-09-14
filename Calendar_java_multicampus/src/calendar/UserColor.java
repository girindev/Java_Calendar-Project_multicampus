package calendar;

import java.awt.Color;
import java.util.Random;

public class UserColor {
	public static Color getCalcColor(int color) {
		Random ran = new Random();
		Color returnColor = new Color(Math.abs(255 - color*450)%255, 
				Math.abs(255 - color*250) %255, 
				Math.abs(255 - color*150)%255);
		return returnColor;
	}
}
