package nettal.draw;

import javax.swing.*;
import java.awt.*;

public class Utilities {

    public static void showException(Exception e) {
        JOptionPane.showMessageDialog(null, "程序错误：" + exception2String(e), "抽号", JOptionPane.ERROR_MESSAGE);
    }

    public static String exception2String(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.toString());
        for (StackTraceElement h : e.getStackTrace()) {
            stringBuilder.append("\n");
            stringBuilder.append("    at ");
            stringBuilder.append(h);
        }
        return stringBuilder.toString();
    }

    public static String RGB2Str(int i) {
        Color color = new Color(i);
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

    public static Integer Str2RGB(String argb) {
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
