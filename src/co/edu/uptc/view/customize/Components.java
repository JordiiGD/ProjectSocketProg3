package co.edu.uptc.view.customize;

import javax.swing.*;
import java.awt.*;

public class Components {
    public static JLabel customizeJLabel(Color fontColor, Color backgroundColor, Dimension preferredSize,
                                         Font font, int alignment) {

        JLabel label = new JLabel();
        label.setPreferredSize(preferredSize);
        label.setMaximumSize(preferredSize);
        label.setMinimumSize(preferredSize);
        label.setFont(font);
        label.setForeground(fontColor);
        label.setBackground(backgroundColor);
        label.setHorizontalAlignment(alignment);
        return label;
    }

    public static JButton customizeButtonWithTextOnly(Font font, Color fontColor, Color backgroundColor,
                                                      Dimension preferredSize, String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize);
        button.setMinimumSize(preferredSize);
        button.setFont(font);
        button.setForeground(fontColor);
        button.setBackground(backgroundColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
