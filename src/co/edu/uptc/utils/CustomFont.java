package co.edu.uptc.utils;

import java.awt.*;
import java.io.File;

public class CustomFont {

    private Font font;

    public CustomFont(){
        font = null;
    }

    public Font customizeFont( String fontName, int style, float size)
    {
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File(fontName));
        } catch (Exception ex) {
            System.err.println(fontName);
            font = null;
        }
        Font customFont = font.deriveFont(style, size);
        return customFont;
    }
}
