package org.hudson.trayapp.gui.tray;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Timer;


public class AnimatedTrayIcon {
	
	private ImageAndDelay[] imageAndDelays;
	
	private int frame;
	
	private TrayIcon trayIcon;
	
	private Timer timer;
	
	public AnimatedTrayIcon(ImageAndDelay[] imageAndDelays, TrayIcon trayIcon) {
		this.imageAndDelays = imageAndDelays;
		frame = imageAndDelays.length != 0 ? 0 : -1;
		this.trayIcon = trayIcon;
		timer = new Timer(100, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ImageAndDelay iad = AnimatedTrayIcon.this.imageAndDelays[frame];
				int length = AnimatedTrayIcon.this.imageAndDelays.length;
				AnimatedTrayIcon.this.trayIcon.setImage(iad.image.getImage());
				frame++;
				if (frame == length) {
					frame = 0;
				}
				if (length != 1) {
					timer.setDelay(iad.delay);
					timer.restart();
				}
			}
			
		});
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public static class ImageAndDelay {
		private LayeredImage image;
		private int delay;
		
		public ImageAndDelay(LayeredImage image, int delay) {
			this.image = image;
			this.delay = delay;
		}
	}
	
	public static class LayeredImage {
		private BufferedImage image = null;
		
		public LayeredImage(BufferedImage[] images, float[] alpha) {
			if (images != null && images.length > 0 && alpha != null && alpha.length > 0 && alpha.length == images.length) {
				image = new BufferedImage(images[0].getWidth(), images[0].getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D g = (Graphics2D) image.getGraphics();
				for (int i = 0; i < images.length; i++) {
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha[i]));
					g.drawRenderedImage(images[i], new AffineTransform());
				}
			}
		}
		
		public Image getImage() {
			return image;
		}
	}
}
