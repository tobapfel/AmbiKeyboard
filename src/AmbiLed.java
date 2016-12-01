import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.logitech.gaming.LogiLED;

import net.coobird.thumbnailator.Thumbnails;

public class AmbiLed implements Runnable {
	public void run() {
		double per = 100.0 / 255.0;
		double r = 0;
		double b = 0;
		double g = 0;
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Rectangle screenRect = new
		// Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		Rectangle screenRect = new Rectangle(0, 980, 1920, 100);
		LogiLED.LogiLedInit();
		LogiLED.LogiLedSaveCurrentLighting();
		while (GUI.running) {
			BufferedImage Image = robot.createScreenCapture(screenRect);
			BufferedImage crop = null;
			try {
				crop = Thumbnails.of(Image).size(1, 1).asBufferedImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Color color = new Color(crop.getRGB(0, 0)).brighter().brighter().brighter();
			r = color.getRed() * per;
			g = color.getGreen() * per;
			b = color.getBlue() * per;
			LogiLED.LogiLedSetLighting((int) r, (int) g, (int) b);
			// LogiLED.LogiLedSetLighting(color[0], color[1], color[2]);
			try {
				Thread.sleep(33);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		LogiLED.LogiLedRestoreLighting();
		LogiLED.LogiLedShutdown();
	}

}
