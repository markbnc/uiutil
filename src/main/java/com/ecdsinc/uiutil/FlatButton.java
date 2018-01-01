package com.ecdsinc.uiutil;

import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class FlatButton extends JButton {

    public FlatButton() {
		setFocusPainted(false);
		setBorder(null);
		setRolloverEnabled(true);
	    putClientProperty("hideActionText", Boolean.TRUE);

    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

		ButtonModel model = getModel();
		if (model.isRollover()) {

		    Dimension   size = getSize();
			size.width -= 1;
			size.height -= 1;

			if (model.isPressed()) {
				graphics.setColor(SystemColor.controlShadow);
			}
			else {
		        graphics.setColor(SystemColor.controlLtHighlight);
			}
		    graphics.drawLine(0, 0, size.width, 0);
			graphics.drawLine(0, 0, 0, size.height);

			if (model.isPressed()) {
		        graphics.setColor(SystemColor.controlLtHighlight);
			}
			else {
				graphics.setColor(SystemColor.controlShadow);
			}
			graphics.drawLine(size.width, 0,
						      size.width, size.height);
			graphics.drawLine(0, size.height,
						      size.width, size.height);
		}

    }
}