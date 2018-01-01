package com.ecdsinc.uiutil;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class SegmentedEtchedBorder extends EtchedBorder {

    public SegmentedEtchedBorder() {
		setDrawOptions (true, true, true, true);
    }

	public SegmentedEtchedBorder(int etchType) {
		super(etchType);
		setDrawOptions(true, true, true, true);
	}

	public SegmentedEtchedBorder(int etchType,
								 boolean drawTop, boolean drawLeft,
								 boolean drawBottom, boolean drawRight) {
		super(etchType);
		setDrawOptions(drawTop, drawLeft, drawBottom, drawRight);
	}

	public void setDrawOptions (boolean drawTop, boolean drawLeft,
								boolean drawBottom, boolean drawRight) {
		m_DrawTop = drawTop;
		m_DrawLeft = drawLeft;
		m_DrawBottom = drawBottom;
		m_DrawRight = drawRight;
	}

	public boolean getDrawTop() {
		return m_DrawTop;
	}

	public boolean getDrawLeft() {
		return m_DrawLeft;
	}

	public boolean getDrawBottom() {
		return m_DrawBottom;
	}

	public boolean getDrawRight() {
		return m_DrawRight;
	}

    /**
     * Paints the border for the specified component with the
     * specified position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    public void paintBorder(Component c, Graphics g, int x, int y,
						    int w, int h) {

		Color   shadowColor = getShadowColor(c);
		Color   highlightColor = getHighlightColor(c);
		int     start;
		int     end;

		g.translate(x, y);

		if (m_DrawTop) {

			g.setColor(etchType == LOWERED? shadowColor : highlightColor);
			g.drawLine(0, 0, w-1, 0);

			g.setColor(etchType == LOWERED? highlightColor : shadowColor);
			g.drawLine(0, 1, w-1, 1);
		}

		if (m_DrawRight) {
			g.setColor(etchType == LOWERED? shadowColor : highlightColor);
			g.drawLine(w-2, 0, w-2, h-1);

			g.setColor(etchType == LOWERED? highlightColor : shadowColor);
			g.drawLine(w-1, 0, w-1, h-1);
		}

		if (m_DrawBottom) {
			g.setColor(etchType == LOWERED? shadowColor : highlightColor);
			end = m_DrawLeft ? w-2 : w-1;
			g.drawLine(0, h-2, end, h-2);

			g.setColor(etchType == LOWERED? highlightColor : shadowColor);
			g.drawLine(0, h-1, w-1, h-1);
		}

		if (m_DrawLeft) {
			g.setColor(etchType == LOWERED? shadowColor : highlightColor);
			end = m_DrawBottom ? h-2 : h-1;
			g.drawLine(0, 0, 0, end);

			g.setColor(etchType == LOWERED? highlightColor : shadowColor);
			start = m_DrawTop ? 1 : 0;
			end = m_DrawBottom ? h-3 : h-1;
			g.drawLine(1, start, 1, end);

		}
    }

    public Insets getBorderInsets(Component c, Insets insets) {

        insets.top = 2;
		insets.left = 2;
		insets.bottom = 2;
		insets.right = 2;

		if (c instanceof JToolBar) {
			Insets margin = ((JToolBar) c).getMargin();
			insets.top += margin.top;
			insets.left += margin.left;
			insets.bottom += margin.bottom;
			insets.right += margin.right;
		}
		return insets;
    }

    public Insets getBorderInsets(Component c) {
		return getBorderInsets(c, new Insets(0, 0, 0, 0));
    }

	private boolean     m_DrawTop;
	private boolean     m_DrawLeft;
	private boolean     m_DrawBottom;
	private boolean     m_DrawRight;

}