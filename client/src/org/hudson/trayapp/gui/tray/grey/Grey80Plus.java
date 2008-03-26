package org.hudson.trayapp.gui.tray.grey;

import java.awt.TrayIcon;

import org.hudson.trayapp.gui.tray.HudsonTrayIconHelper;
import org.hudson.trayapp.gui.tray.Images;
import org.hudson.trayapp.gui.tray.HudsonTrayIconHelper.StaticImage;

public class Grey80Plus extends StaticImage{

	public Grey80Plus(TrayIcon trayIcon) {
		super(Images.GREY, Images.H80PL, trayIcon);
	}
}
