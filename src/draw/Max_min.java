package draw;

import javax.swing.*;

public class Max_min {
    static boolean isMinCurrent = false;
    static boolean isMaxCurrent = false;
    static String minValueInfoString = "请输入最小值(不含)";
    static String maxValueInfoString = "请输入最大值(包含)";
    static String minRangesString = "";
    static String maxRangesString = "";
    static int[] rang = {0, 0};

    public Max_min() {
    }

    public static int[] getRange() {
        while (!isMinCurrent) {
            minRangesString = JOptionPane.showInputDialog(null, minValueInfoString, "抽号", JOptionPane.PLAIN_MESSAGE);
            try {
                rang[0] = Integer.parseInt(minRangesString);
                isMinCurrent = true;

            } catch (Exception e) {
                minValueInfoString = "最小值错误";
            }
        }
        while (!isMaxCurrent) {
            maxRangesString = JOptionPane.showInputDialog(null, maxValueInfoString, "抽号", JOptionPane.PLAIN_MESSAGE);
            try {
                rang[1] = Integer.parseInt(maxRangesString);
                isMaxCurrent = true;

            } catch (Exception e) {
                minValueInfoString = "最大值错误";
            }
        }

        if (rang[0] > rang[1]) {
            System.err.println("Max_min: 大小值有误，已纠正");
            int temp = rang[1];
            rang[1] = rang[0];
            rang[0] = temp;
        }
        isMinCurrent = false;
        isMaxCurrent = false;
        return rang;
    }
}
