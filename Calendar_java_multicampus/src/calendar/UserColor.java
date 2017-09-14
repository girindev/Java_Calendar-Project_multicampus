package calendar;

import java.awt.Color;
import java.util.Random;

public class UserColor {
	public static Color getCalcColor(int color) {
		Color returnColor = new Color(Math.abs(255 - color*1530)%255, 
				Math.abs(255 - color*121) %255, 
				Math.abs(255 - color*3217)%255);
		return returnColor;
	}
}
