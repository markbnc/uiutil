package com.ecdsinc.uiutil;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ToolbarSeparator extends JPanel {

    public ToolbarSeparator() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

	public ToolbarSeparator(Dimension dimension) {
		this(dimension.width, dimension.height);
	}

	public ToolbarSeparator(int width, int height)
	{

		if (width < MINIMUM_WIDTH) {
			width = MINIMUM_WIDTH;
		}

		Dimension dim = new Dimension(width, height);
		setMaximumSize(dim);
		setMinimumSize(dim);
		setPreferredSize(dim);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Rectangle   clientRect = getBounds();

		int xPos = (clientRect.width - MINIMUM_WIDTH) / 2;
		int yPos = HEIGHT_GAP;
		int length = clientRect.height - (2 * HEIGHT_GAP);

		g.setColor(SystemColor.controlShadow);
		g.drawLine(xPos, yPos, xPos, yPos+length);

		xPos++;
		g.setColor(SystemColor.controlLtHighlight);
		g.drawLine(xPos, yPos, xPos, yPos+length);

    }

	private static int      DEFAULT_WIDTH = 10;
	private static int      DEFAULT_HEIGHT = 24;
	private static int      MINIMUM_WIDTH = 2;
	private static int      HEIGHT_GAP = 2;


}