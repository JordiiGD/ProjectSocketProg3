package co.edu.uptc.utils;

import java.awt.*;

public class MathUtils {
    public static Point calculatePoint(int x, int y, int angle, int padding) {
        return new Point((int) (x + Math.cos(Math.toRadians(angle)) * padding), (int) (y + Math.sin(Math.toRadians(angle)) * padding));
    }
}
