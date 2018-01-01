package com.ecdsinc.uiutiltest;

import java.awt.*;
import java.awt.event.*;
import java.beans.BeanInfo;
import java.io.IOException;
import javax.swing.*;


import com.ecdsinc.uiutil.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    JPanel contentPane;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileExit = new JMenuItem();
    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();
    JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();
    TestPane outCtrl = new TestPane();
    JMenu menuErrorDlg = new JMenu();
    JMenuItem errorShowException = new JMenuItem();
    JMenuItem errorShowError = new JMenuItem();
    JMenuItem errorShowWarning = new JMenuItem();
    JMenu menuView = new JMenu();
    JMenuItem viewClear = new JMenuItem();
    JMenu viewLookandFeel = new JMenu();
    JRadioButtonMenuItem viewLFWindows = new JRadioButtonMenuItem();
    JRadioButtonMenuItem viewLFMotif = new JRadioButtonMenuItem();
    JRadioButtonMenuItem viewLFMetal = new JRadioButtonMenuItem();
	ButtonGroup          viewLFGroup = new ButtonGroup();
    JMenuItem testsImageGetImages = new JMenuItem();
    JMenu menuControls = new JMenu();
    JMenuItem controlNumberField = new JMenuItem();
    JMenuItem controlXMLTreeDom = new JMenuItem();
    JMenuItem controlXMLTreeJDom = new JMenuItem();

    /**Construct the frame*/
    public MainFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
			initLookandFeelMenu();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**Component initialization*/
    private void jbInit() throws Exception  {
        setIconImage(ImageUtil.getStandardIcon(ImageUtil.ECDS_ICON).getImage());
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(400, 300));
        this.setTitle("UIUtil Test");
        statusBar.setText(" ");
        jMenuFile.setText("File");
        jMenuFileExit.setText("Exit");
        jMenuFileExit.addActionListener(new MainFrame_jMenuFileExit_ActionAdapter(this));
        jMenuHelp.setText("Help");
        jMenuHelpAbout.setText("About");
        jMenuHelpAbout.addActionListener(new MainFrame_jMenuHelpAbout_ActionAdapter(this));
        outCtrl.setBorder(null);
        outCtrl.setToolTipText("");
        menuErrorDlg.setText("ErrorDialog");
        errorShowException.setText("Show Exception ...");
        errorShowException.addActionListener(new MainFrame_errorShowException_actionAdapter(this));
        errorShowError.setText("Show Error ...");
        errorShowError.addActionListener(new MainFrame_errorShowError_actionAdapter(this));
        errorShowWarning.setText("Show Warning ...");
        errorShowWarning.addActionListener(new MainFrame_errorShowWarning_actionAdapter(this));
        menuView.setText("View");
        viewClear.setText("Clear");
        viewClear.addActionListener(new MainFrame_viewClear_actionAdapter(this));
        viewLookandFeel.setText("LookandFeel");
        viewLFWindows.setText("Windows");
        viewLFWindows.addActionListener(new MainFrame_viewLFWindows_actionAdapter(this));
        viewLFMotif.setText("Motif");
        viewLFMotif.addActionListener(new MainFrame_viewLFMotif_actionAdapter(this));
        viewLFMetal.setText("Metal");
        viewLFMetal.addActionListener(new MainFrame_viewLFMetal_actionAdapter(this));
        testsImageGetImages.setText("Get Images");
        testsImageGetImages.addActionListener(new MainFrame_testsImageGetImages_actionAdapter(this));
        menuControls.setText("Controls");
        controlNumberField.setText("NumberField");
        controlNumberField.addActionListener(new MainFrame_controlNumberField_actionAdapter(this));
        controlXMLTreeDom.setText("XML Tree (DOM)");
        controlXMLTreeDom.addActionListener(new MainFrame_controlXMLTreeDom_actionAdapter(this));
        controlXMLTreeJDom.setText("XML Tree (JDOM)");
        controlXMLTreeJDom.addActionListener(new MainFrame_controlXMLTreeJDom_actionAdapter(this));
        testMenu.setText("Tests");
        testsImage.setText("Image");
        testsVersion.setText("Version");
        testVersionCompareVersion.setText("Compare Version");
        testVersionCompareVersion.addActionListener(new MainFrame_testVersionCompareVersion_actionAdapter(this));
        testsVersionVersionInfo.setText("Display Version Info");
        testsVersionVersionInfo.addActionListener(new MainFrame_testsVersionVersionInfo_actionAdapter(this));
        controlClassBrowser.setText("ClassBrowser");
        controlClassBrowser.addActionListener(new MainFrame_controlClassBrowser_actionAdapter(this));
        jMenuFile.add(jMenuFileExit);
        jMenuHelp.add(jMenuHelpAbout);
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(testMenu);
        jMenuBar1.add(menuErrorDlg);
        jMenuBar1.add(menuControls);
        jMenuBar1.add(menuView);
        jMenuBar1.add(jMenuHelp);
        this.setJMenuBar(jMenuBar1);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        contentPane.add(outCtrl, BorderLayout.CENTER);
        menuErrorDlg.add(errorShowException);
        menuErrorDlg.add(errorShowError);
        menuErrorDlg.add(errorShowWarning);
        menuView.add(viewLookandFeel);
        menuView.addSeparator();
        menuView.add(viewClear);
        viewLookandFeel.add(viewLFWindows);
        viewLookandFeel.add(viewLFMotif);
        viewLookandFeel.add(viewLFMetal);
		viewLFGroup.add(viewLFWindows);
		viewLFGroup.add(viewLFMotif);
		viewLFGroup.add(viewLFMetal);
        menuControls.add(controlNumberField);
        menuControls.add(controlXMLTreeDom);
        menuControls.add(controlClassBrowser);
        menuControls.add(controlXMLTreeJDom);
        testMenu.add(testsImage);
        testMenu.add(testsVersion);
        testsImage.add(testsImageGetImages);
        testsVersion.add(testsVersionVersionInfo);
        testsVersion.add(testVersionCompareVersion);
    }

	private void initLookandFeelMenu() {
		String  currentLookClassname;

		currentLookClassname = UIManager.getLookAndFeel().getClass().getName();
		if (currentLookClassname.compareTo(LOOKANDFEEL_CLASS_WINDOWS) == 0) {
			viewLFWindows.setSelected(true);
		}
		else if (currentLookClassname.compareTo(LOOKANDFEEL_CLASS_MOTIF) == 0) {
			viewLFMotif.setSelected(true);
		}
		else if (currentLookClassname.compareTo(LOOKANDFEEL_CLASS_METAL) == 0) {
			viewLFMetal.setSelected(true);
		}
	}

	private void switchLookandFeel(String lookAndFeelClassname) {

		try {

		    UIManager.setLookAndFeel(lookAndFeelClassname);

		}
		catch(ClassNotFoundException except) {
		    ErrorDialog.showException(this,
				    "LookandFeel class not found: " + lookAndFeelClassname,
					except);
		}
		catch(InstantiationException except) {
			ErrorDialog.showException(this,
				    "Instanciation error while switching Look and Feel",
					except);
		}
		catch(IllegalAccessException except) {
			ErrorDialog.showException(this,
				    "Illegal Access error while switching Look and Feel",
					except);
		}
		catch(javax.swing.UnsupportedLookAndFeelException except) {
			ErrorDialog.showException(this,
				    "Unsupported Look and Feel: " + lookAndFeelClassname,
					except);
		}

		SwingUtilities.updateComponentTreeUI(this);
		validate();
	}

    /**File | Exit action performed*/
    public void jMenuFileExit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    /**Help | About action performed*/
    public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
		ECDSAboutDialog dlg = new ECDSAboutDialog(this,
			"EcdsUtil Test",
			"ECDSUtil Test version 1.0",
			"Copyright (c) 2001 East Coast Dealer Services");

        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setVisible(true);
    }

    /**Overridden so we can exit when window is closed*/
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            jMenuFileExit_actionPerformed(null);
        }
    }

    void errorShowException_actionPerformed(ActionEvent e) {
		try {
			throw new Exception("Test Exception");
		}
		catch (Exception except) {
			ErrorDialog.showException(this, "Caught the test exception", except);
		}
    }

    void errorShowError_actionPerformed(ActionEvent e) {
		ErrorDialog.showError(this, "This is an example of an error message");
    }

    void errorShowWarning_actionPerformed(ActionEvent e) {
		ErrorDialog.showWarning(this, "This is an example of a warning message");
    }

    void testsImageGetImages_actionPerformed(ActionEvent e) {

		Image               tmpImage;
		TestPaneBeanInfo    beanInfo = new TestPaneBeanInfo();

		try {
		    tmpImage = ImageUtil.getImage("TestPane32C.GIF");
//		    tmpImage = ImageUtil.getImage("/com/ecdsinc/uiutil/TestPane32C.GIF");
			if (tmpImage == null) {
				System.out.println("Error fetching image");
			}

			ImageIcon icon = ImageUtil.getStandardIcon(ImageUtil.BLANK_ICON);
			if (icon == null) {
				System.out.println("Error fetching icon");
			}
		}
		catch (IOException except) {
		}

		tmpImage = beanInfo.getIcon(BeanInfo.ICON_COLOR_16x16);
		tmpImage = beanInfo.getIcon(BeanInfo.ICON_COLOR_32x32);
		tmpImage = beanInfo.getIcon(BeanInfo.ICON_MONO_16x16);
		tmpImage = beanInfo.getIcon(BeanInfo.ICON_MONO_32x32);
    }

	void viewLFWindows_actionPerformed(ActionEvent e) {
		switchLookandFeel(LOOKANDFEEL_CLASS_WINDOWS);
    }

    void viewLFMotif_actionPerformed(ActionEvent e) {
		switchLookandFeel(LOOKANDFEEL_CLASS_MOTIF);
    }

    void viewLFMetal_actionPerformed(ActionEvent e) {
		switchLookandFeel(LOOKANDFEEL_CLASS_METAL);
    }

    void viewClear_actionPerformed(ActionEvent e) {
		outCtrl.clear();
    }

    void controlXMLTreeDom_actionPerformed(ActionEvent e) {

		JFileChooser    fileDlg = new JFileChooser();
		int             retVal;

		retVal = fileDlg.showOpenDialog(this);
		if (retVal != JFileChooser.APPROVE_OPTION) {
			return;
		}

		String  filename = "file:" + fileDlg.getSelectedFile().getAbsolutePath();

		org.apache.xerces.parsers.DOMParser     parser;
		parser = new org.apache.xerces.parsers.DOMParser();

		try {
			parser.setFeature("http://xml.org/sax/features/validation", false);
            parser.setFeature("http://xml.org/sax/features/namespaces", true);
            parser.setFeature("http://apache.org/xml/features/validation/schema",
                              false);

		    parser.parse(filename);
		}
		catch (Exception except) {
			String message = "Error parsing XML Document\n " +
						     except.getMessage();
			JOptionPane.showMessageDialog(this, message, "Error",
										  JOptionPane.ERROR_MESSAGE);
			return;
		}

		XMLTreeDlg  treeDlg = new XMLTreeDlg(this, parser.getDocument());
		treeDlg.showDialog();

    }

    void controlXMLTreeJDom_actionPerformed(ActionEvent e) {

		JFileChooser        fileDlg = new JFileChooser();
		int                 retVal;
		org.jdom.Document   doc = null;

		retVal = fileDlg.showOpenDialog(this);
		if (retVal != JFileChooser.APPROVE_OPTION) {
			return;
		}

		String  filename = "file:" + fileDlg.getSelectedFile().getAbsolutePath();

		org.jdom.input.SAXBuilder   builder;
		builder = new org.jdom.input.SAXBuilder();

		try {
		    doc = builder.build(filename);
		}
		catch (Exception except) {
			String message = "Error parsing XML Document\n " +
						     except.getMessage();
			JOptionPane.showMessageDialog(this, message, "Error",
										  JOptionPane.ERROR_MESSAGE);
			return;
		}

		XMLTreeDlg  treeDlg = new XMLTreeDlg(this, doc);
		treeDlg.showDialog();

    }


    void controlNumberField_actionPerformed(ActionEvent e) {

		TextFieldsDlg   dlg = new TextFieldsDlg(this);
		int             retval;

		dlg.setNumber(12345678);
		dlg.setConstrainedNumber(12345);
		retval = dlg.showDialog();
		if (retval == ModalDialog.DIALOG_ACCEPT) {
			outCtrl.displayMessage("Changes Accepted");
			outCtrl.displayMessage("Unconstrained Number: ", false);
			outCtrl.displayMessage(String.valueOf(dlg.getNumber()));
			outCtrl.displayMessage("Constrained Number: ", false);
			outCtrl.displayMessage(String.valueOf(dlg.getConstrainedNumber()));
		}

    }

    void controlClassBrowser_actionPerformed(ActionEvent e) {

		ClassBrowser    classBrowserDlg = new ClassBrowser(this, "Select Class");

		if (classBrowserDlg.showDialog() == ModalDialog.DIALOG_ACCEPT) {

			outCtrl.displayMessage("The selected class is: " +
								   classBrowserDlg.getSelectedClass());
		}

    }

    void testsVersionVersionInfo_actionPerformed(ActionEvent e) {

		outCtrl.displayMessage("Library Name: " +
							   UIUtilVersion.getLibraryName());
		outCtrl.displayMessage("Version: " +
							   UIUtilVersion.getVersionString());
		outCtrl.displayMessage(UIUtilVersion.getCopyright());
		outCtrl.displayMessage("Major Version: " +
							   String.valueOf(UIUtilVersion.getMajorVersion()));
		outCtrl.displayMessage("Minor Version: " +
							   String.valueOf(UIUtilVersion.getMinorVersion()));
		outCtrl.displayMessage("Build Number: " +
							   String.valueOf(UIUtilVersion.getBuildNumber()));
    }

    void testVersionCompareVersion_actionPerformed(ActionEvent e) {

		boolean             greaterOrEqual;
		String []           testVersion = {
			"0.1.2",
			"1.0.4",
			"1.1.1",
			"1.1.3",
			"1.1.4",
			"1.2.1",
			"2.3.1",
			"a.b.c"
		};

		outCtrl.displayMessage("Current Version: " + UIUtilVersion.getVersionString());

		try {
			for (int index = 0; index < testVersion.length; index++) {

				greaterOrEqual =
					UIUtilVersion.versionEqualOrGreater(testVersion[index]);

				if (greaterOrEqual) {
					outCtrl.displayMessage("Current Version is greater than " +
										   testVersion[index]);
				}
				else {
					outCtrl.displayMessage("Current Version is less than " +
										   testVersion[index]);
				}
			}
		}
		catch (UIUtilException except) {
			outCtrl.displayMessage(except.getMessage());
		}


    }

	//  Misc constants
	static final String LOOKANDFEEL_CLASS_WINDOWS =	"com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	static final String LOOKANDFEEL_CLASS_MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	static final String LOOKANDFEEL_CLASS_METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
    JMenu testMenu = new JMenu();
    JMenu testsImage = new JMenu();
    JMenu testsVersion = new JMenu();
    JMenuItem testsVersionVersionInfo = new JMenuItem();
    JMenuItem testVersionCompareVersion = new JMenuItem();
    JMenuItem controlClassBrowser = new JMenuItem();


}

class MainFrame_jMenuFileExit_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuFileExit_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuFileExit_actionPerformed(e);
    }
}

class MainFrame_jMenuHelpAbout_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuHelpAbout_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuHelpAbout_actionPerformed(e);
    }
}

class MainFrame_errorShowException_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_errorShowException_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.errorShowException_actionPerformed(e);
    }
}

class MainFrame_viewClear_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_viewClear_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.viewClear_actionPerformed(e);
    }
}

class MainFrame_errorShowError_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_errorShowError_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.errorShowError_actionPerformed(e);
    }
}

class MainFrame_errorShowWarning_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_errorShowWarning_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.errorShowWarning_actionPerformed(e);
    }
}

class MainFrame_viewLFWindows_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_viewLFWindows_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.viewLFWindows_actionPerformed(e);
    }
}

class MainFrame_viewLFMotif_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_viewLFMotif_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.viewLFMotif_actionPerformed(e);
    }
}

class MainFrame_viewLFMetal_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_viewLFMetal_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.viewLFMetal_actionPerformed(e);
    }
}

class MainFrame_testsImageGetImages_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_testsImageGetImages_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.testsImageGetImages_actionPerformed(e);
    }
}

class MainFrame_controlNumberField_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_controlNumberField_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.controlNumberField_actionPerformed(e);
    }
}

class MainFrame_controlXMLTreeDom_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_controlXMLTreeDom_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.controlXMLTreeDom_actionPerformed(e);
    }
}

class MainFrame_controlXMLTreeJDom_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_controlXMLTreeJDom_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.controlXMLTreeJDom_actionPerformed(e);
    }
}

class MainFrame_testsVersionVersionInfo_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_testsVersionVersionInfo_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.testsVersionVersionInfo_actionPerformed(e);
    }
}

class MainFrame_testVersionCompareVersion_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_testVersionCompareVersion_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.testVersionCompareVersion_actionPerformed(e);
    }
}

class MainFrame_controlClassBrowser_actionAdapter implements java.awt.event.ActionListener {
    MainFrame adaptee;

    MainFrame_controlClassBrowser_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.controlClassBrowser_actionPerformed(e);
    }
}