import java.awt.*;

public class Theme {
	public static Theme DARK = new Theme(
			Color.DARK_GRAY,     // Background
			Color.LIGHT_GRAY,    // Foreground
			Color.ORANGE         // Buchstaben
	);

	public static Theme LIGHT = new Theme(
			Color.WHITE,
			Color.BLACK,
			Color.BLUE
	);

	public Color background;
	public Color foreground;
	public Color accent;

	public Theme(Color bg, Color fg, Color acc) {
		this.background = bg;
		this.foreground = fg;
		this.accent = acc;
	}
}
