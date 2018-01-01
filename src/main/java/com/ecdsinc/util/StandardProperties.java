package com.ecdsinc.util;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

public class StandardProperties {

    public StandardProperties(String propertiesFilename,
						      String propertiesHeader) {
		m_PropertiesFilename = propertiesFilename;
		m_PropertiesHeader = propertiesHeader;
    }

	public void load() throws IOException {

		//  Set up the default values
		m_DefaultProperties = new Properties();
		initDefaults (m_DefaultProperties);

		//  Load the persisted properties
		m_Properties = new Properties(m_DefaultProperties);
	    m_Properties.load(new FileInputStream(m_PropertiesFilename));
	}

	public void save()
		throws IOException {

		m_Properties.store(new FileOutputStream(m_PropertiesFilename),
					       m_PropertiesHeader);
	}

	public String getLastDir() {
		return getProperty(LAST_DIR);
	}

	public void setLastDir(String dir) {
		setProperty(LAST_DIR, dir);
	}

	public Rectangle getLastWindowPosition() {

		int                 x, y, width, height;
		Rectangle           windowRect = new Rectangle();
		String              positionStr;
		StringTokenizer     tokens;

		positionStr = getProperty(WINDOW_POS);
		tokens = new StringTokenizer(positionStr);

		if (tokens.countTokens() != 4) {
			//  position string is invalid, return the empty rectangle
			return windowRect;
		}

		try {
		    x = Integer.parseInt(tokens.nextToken());
		    y = Integer.parseInt(tokens.nextToken());
		    width = Integer.parseInt(tokens.nextToken());
		    height = Integer.parseInt(tokens.nextToken());
		}
		catch (Exception except) {
			//  Error parsing the position string, return the empty rectangle
			return windowRect;
		}

		windowRect.setBounds(x, y, width, height);
		return windowRect;

	}

	public void setLastWindowPosition(Rectangle windowRect) {

		String  positionStr = String.valueOf(windowRect.x) + " " +
							  String.valueOf(windowRect.y) + " " +
							  String.valueOf(windowRect.width) + " " +
							  String.valueOf(windowRect.height);
		setProperty(WINDOW_POS, positionStr);
	}

	protected void setProperty (String key, String value) {
		m_Properties.setProperty(key, value);
	}

	protected String getProperty (String key) {
		return m_Properties.getProperty(key);
	}

	protected void initDefaults(Properties defaultProperties) {

		defaultProperties.setProperty(LAST_DIR, LAST_DIR_DEF);
		defaultProperties.setProperty(WINDOW_POS, WINDOW_POS_DEF);
	}

	//  Data members
	private Properties      m_Properties;
	private Properties      m_DefaultProperties;
	private String          m_PropertiesFilename;
	private String          m_PropertiesHeader;

	private static final String     LAST_DIR = "LastDir";
	private static final String     LAST_DIR_DEF = "";
	private static final String     WINDOW_POS = "WindowPosition";
	private static final String     WINDOW_POS_DEF = "0 0 0 0";


}