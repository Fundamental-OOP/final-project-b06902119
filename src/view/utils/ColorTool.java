package view.utils;

import java.awt.*;

public class ColorTool {
    public static Color adjustAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    public static Color adjustIntensity(Color color, float factor){
        return new Color((int)(color.getRed()*factor), (int)(color.getGreen()*factor), (int)(color.getBlue()*factor));
    }
}
