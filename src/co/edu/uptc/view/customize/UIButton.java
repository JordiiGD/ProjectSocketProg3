package co.edu.uptc.view.customize;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class UIButton extends BasicButtonUI {

    private Color buttonColor;

    public UIButton(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(buttonColor);
        g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);

        super.paint(g2d, c);

    }
}
