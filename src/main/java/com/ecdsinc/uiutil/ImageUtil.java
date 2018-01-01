package com.ecdsinc.uiutil;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageUtil {

	//  Standard Icon Ids
	public static final int BLANK_ICON = 0;
	public static final int OPEN_FILE_ICON = 1;
	public static final int CLOSE_FILE_ICON = 2;
	public static final int COPY_ICON = 3;
	public static final int CUT_ICON = 4;
	public static final int PASTE_ICON = 5;
	public static final int ECDS_ICON = 6;
	public static final int ECDS_ICON_SM = 7;

	//  Standard Image Ids
	public static final int ECDS_LOGO_IMAGE = 1000;

    public ImageUtil() {
    }

	static public Image getImage(Class<?> rootClass, String filename)
		throws IOException {

		URL         url = rootClass.getResource(filename);

		if (url == null) {
			throw new IOException ("File " + filename + " not found");
		}
    	return Toolkit.getDefaultToolkit().getImage(url);
	}

	static public Image getImage(String filename)
		throws IOException {

		URL         url = ImageUtil.class.getResource(filename);
		if (url == null) {
			throw new IOException ("File " + filename + " not found");
		}
    	return Toolkit.getDefaultToolkit().getImage(url);
	}

	static public Image getStandardImage(int imageId)
		throws IOException {

		String  imageFilename;

		switch (imageId) {
			case ECDS_LOGO_IMAGE:
				imageFilename = "images/EcdsLogo.gif";
				break;

			default:
				throw new IllegalArgumentException("Invalid Image Id");
		}

		return getImage(imageFilename);

	}

	static public ImageIcon getIcon(Class<?> rootClass, String filename)
		throws IOException {

		URL     url = rootClass.getResource(filename);

		if (url == null) {
			throw new IOException ("File " + filename + " not found");
		}
		return new ImageIcon(url);

	}

	static public ImageIcon getIcon(String filename)
		throws IOException {

		URL     url = ImageUtil.class.getResource(filename);

		if (url == null) {
			throw new IOException ("File " + filename + " not found");
		}
		return new ImageIcon(url);

	}

	static public ImageIcon getStandardIcon(int iconId)
		throws IOException {

		String  iconFilename;

		switch (iconId) {

			case BLANK_ICON:
				iconFilename = "images/blankIcon.gif";
				break;

			case OPEN_FILE_ICON:
				iconFilename = "images/openFile.gif";
				break;

			case CLOSE_FILE_ICON:
				iconFilename = "images/closeFile.gif";
				break;

			case COPY_ICON:
				iconFilename = "images/copy.gif";
				break;

			case CUT_ICON:
				iconFilename = "images/cut.gif";
				break;

			case PASTE_ICON:
				iconFilename = "images/paste.gif";
				break;

			case ECDS_ICON:
				iconFilename = "images/EcdsIcon32.gif";
				break;

			case ECDS_ICON_SM:
				iconFilename = "images/EcdsIcon16.gif";
				break;

			default:
				throw new IllegalArgumentException("Invalid Icon Id");

		}

		return getIcon(iconFilename);
	}
}