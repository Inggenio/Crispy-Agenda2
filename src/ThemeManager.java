// ThemeManager.java
import java.awt.*;

public class ThemeManager {
	public static boolean darkMode = true;

	public static final Color DARK_BACKGROUND = Color.DARK_GRAY;
	public static final Color DARK_FOREGROUND = Color.LIGHT_GRAY;
	public static final Color DARK_ACCENT = Color.ORANGE;

	public static final Color LIGHT_BACKGROUND = new Color(245,245,245);
	public static final Color LIGHT_FOREGROUND = new Color(50, 50, 50);
	public static final Color LIGHT_ACCENT = new Color(70, 70, 70);

	public static boolean isDarkMode() {
		return darkMode;
	}

	public static void toggleMode() {
		darkMode = !darkMode;
	}

	public static Color getBackground() {
		return darkMode ? DARK_BACKGROUND : LIGHT_BACKGROUND;
	}

	public static void setDarkMode(boolean dark) {
		darkMode = dark;
	}

	public static Color getForeground() {
		return darkMode ? DARK_FOREGROUND : LIGHT_FOREGROUND;
	}

	public static Color getAccent() {
		return darkMode ? DARK_ACCENT : LIGHT_ACCENT;
	}
}
