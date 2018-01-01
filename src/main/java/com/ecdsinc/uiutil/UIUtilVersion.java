package com.ecdsinc.uiutil;

public class UIUtilVersion {

    public UIUtilVersion() {
    }

	static public String getLibraryName() {
		return "ECDS UIUtil Library";
	}

	static public String getVersionString() {
		return String.valueOf(m_MajorVersion) + "." +
			   String.valueOf(m_MinorVersion) + "." +
			   String.valueOf(m_BuildNumber);
	}

	static public int getMajorVersion() {
		return m_MajorVersion;
	}

	static public int getMinorVersion() {
		return m_MinorVersion;
	}

	static public int getBuildNumber() {
		return m_BuildNumber;
	}

	static public String getCopyright() {
		return m_Copyright;
	}

	static public boolean versionEqualOrGreater(String version)
		throws UIUtilException {

		int         dotIndex;
		int         majorVersion;
		int         minorVersion;
		int         buildNumber;
		boolean     done = false;
		String      parseVersion = "";

		//  If minor version and build number are not specified then assume
		//  a match
		minorVersion = m_MinorVersion;
		buildNumber = m_BuildNumber;

		try {
			dotIndex = version.indexOf(".");
			if (dotIndex < 0) {
				majorVersion = Integer.parseInt(version);
				done = true;
			}
			else {
				majorVersion = Integer.parseInt(version.substring(0, dotIndex));
				parseVersion = version.substring(dotIndex+1);
			}
		}
		catch (NumberFormatException except) {
			throw new UIUtilException("Invalid Version String - Major Version Invalid");
		}

		if (!done) {

			try {
				dotIndex = parseVersion.indexOf(".");
				if (dotIndex < 0) {
					minorVersion = Integer.parseInt(parseVersion);
					done = true;
				}
				else {
					minorVersion = Integer.parseInt(parseVersion.substring(0, dotIndex));
					parseVersion = parseVersion.substring(dotIndex+1);
				}
			}
			catch (NumberFormatException except) {
				throw new UIUtilException("Invalid Version String - Minor Version invalid");
			}
		}

		if (!done) {
			try {
				buildNumber = Integer.parseInt(parseVersion);
			}
			catch (NumberFormatException except) {
				throw new UIUtilException ("Invalid Version String - Build Number invalid");
			}
		}

		if (m_MajorVersion > majorVersion) {
			return true;
		}

		if ((m_MajorVersion == majorVersion) &&
			(m_MinorVersion > minorVersion)) {
			return true;
		}

		if ((m_MajorVersion == majorVersion) &&
			(m_MinorVersion == minorVersion) &&
			(m_BuildNumber >= buildNumber)) {
			return true;
		}
		return false;
	}

	public static void main (String[] args) {
		
		System.out.println(UIUtilVersion.getLibraryName());
		System.out.println("Version: " + UIUtilVersion.getVersionString());
		System.out.println(UIUtilVersion.getCopyright());
	}
	
	//	Change version in build.xml also
	private static int      m_MajorVersion = 1;
	private static int      m_MinorVersion = 4;
	private static int      m_BuildNumber = 0;
	private static String   m_Copyright = "Copyright 2000-2010 " +
										  "East Coast Dealer Services.  " +
										  "All rights reserved.";

}