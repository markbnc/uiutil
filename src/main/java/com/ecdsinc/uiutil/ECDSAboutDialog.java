package com.ecdsinc.uiutil;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class ECDSAboutDialog extends JDialog {

    public ECDSAboutDialog(Frame parent, String productName,
						   String versionInfo, String copyrightInfo) {

		super(parent, "About " + productName, true);

        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

		productNameCtrl.setText(productName);
		versionCtrl.setText(versionInfo);
		copyrightCtrl.setText(copyrightInfo);
		setResizable(false);

    }

    private void jbInit() throws Exception {

		//  Set the dialog size
		setSize(400,250);

		//  Load the ECDS logo image
		ImageIcon   ecdsLogo = ImageUtil.getIcon("images/EcdsLogo.gif");

		//  Image panel
        imagePanel.setLayout(imageFlowLayout);
        imageFlowLayout.setAlignment(FlowLayout.LEFT);
        imageFlowLayout.setHgap(0);
        imageFlowLayout.setVgap(0);
        imageCtrl.setBackground(Color.white);
        imageCtrl.setOpaque(true);
        imageCtrl.setIcon(ecdsLogo);
        spacer1 = Box.createHorizontalStrut(10);

        getContentPane().add(imagePanel, BorderLayout.CENTER);
        imagePanel.add(spacer1, null);
        imagePanel.add(imageCtrl, null);

		//  Info panel
        infoPanel.setLayout(infoGridBagLayout);
        productNameCtrl.setText("Product name goes here");
        versionCtrl.setText("Version info goes here");
        copyrightCtrl.setText("Copyright info goes here");
        ecdsVersionCtrl.setText("ECDS UIUtil Version: " + UIUtilVersion.getVersionString());

        getContentPane().add(infoPanel, BorderLayout.SOUTH);
		infoPanel.add(productNameCtrl, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
			,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 10, 5, 10), 0, 0));
        infoPanel.add(versionCtrl, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 10, 8, 10), 0, 0));
		infoPanel.add(copyrightCtrl, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
			,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 10, 8, 10), 0, 0));
		infoPanel.add(ecdsVersionCtrl, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0
			,GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 10, 8, 10), 0, 0));

		//  Top panel
        topPanel.setLayout(topFlowLayout);
        topFlowLayout.setVgap(0);
        spacer2 = Box.createVerticalStrut(8);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        topPanel.add(spacer2, null);
    }

   //   UI Components
    JPanel imagePanel = new JPanel();
    FlowLayout imageFlowLayout = new FlowLayout();
    JLabel imageCtrl = new JLabel();
    Component spacer1;

    JPanel infoPanel = new JPanel();
    GridBagLayout infoGridBagLayout = new GridBagLayout();
    JLabel productNameCtrl = new JLabel();
    JLabel versionCtrl = new JLabel();
    JLabel copyrightCtrl = new JLabel();
    JLabel ecdsVersionCtrl = new JLabel();

    JPanel topPanel = new JPanel();
    FlowLayout topFlowLayout = new FlowLayout();
    Component spacer2;

}