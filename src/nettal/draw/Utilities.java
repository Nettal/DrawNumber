package nettal.draw;

import javax.swing.*;
import java.awt.*;

public class Utilities {
    public static void showThrowable(Throwable t, boolean detailedInfo) {
        JOptionPane.showMessageDialog(null, "程序错误："
                + (detailedInfo ? throwable2String(t) : t), "抽号", JOptionPane.ERROR_MESSAGE);
    }

    public static String throwable2String(Throwable t) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(t.toString());
        for (StackTraceElement h : t.getStackTrace()) {
            stringBuilder.append("\n");
            stringBuilder.append("    at ");
            stringBuilder.append(h);
        }
        return stringBuilder.toString();
    }

    public static Color getForegroundColor(Color color) {
        return new Color(Integer.MAX_VALUE - color.getRGB());
    }

    public static String color2Hex(Color color) {
        String red = Integer.toHexString(color.getRed());
        String green = Integer.toHexString(color.getGreen());
        String blue = Integer.toHexString(color.getBlue());
        if (red.length() == 1) {
            red = "0" + red;
        }
        if (green.length() == 1) {
            green = "0" + green;
        }
        if (blue.length() == 1) {
            blue = "0" + blue;
        }
        return "#" + red + green + blue;
    }

    public static String rgb2Hex(int i) {
        return color2Hex(new Color(i));
    }

    public static Integer hex2RGB(String argb) {
        if (argb.startsWith("#")) {
            argb = argb.replace("#", "");
        }

        if (argb.length() != 8 && argb.length() != 6 ||
                argb.replaceAll("\\d+", "").replaceAll("[a-fA-F]", "").length() != 0) {
            return null;
        }

        int alpha, red, green, blue;

        if (argb.length() == 8) {
            alpha = Integer.parseInt(argb.substring(0, 2), 16);
            red = Integer.parseInt(argb.substring(2, 4), 16);
            green = Integer.parseInt(argb.substring(4, 6), 16);
            blue = Integer.parseInt(argb.substring(6, 8), 16);
        } else {
            alpha = 255;
            red = Integer.parseInt(argb.substring(0, 2), 16);
            green = Integer.parseInt(argb.substring(2, 4), 16);
            blue = Integer.parseInt(argb.substring(4, 6), 16);
        }

        return new Color(red, green, blue, alpha).getRGB();
    }
}
