package nettal.draw;

import javax.swing.*;

public class Options {
    private Options() {
    }

    public static int[] getRange() {
        String minValueInfoString = "请输入最小值(包含)";
        String maxValueInfoString = "请输入最大值(包含)";
        String minRangesString;
        String maxRangesString;
        int[] rang = {0, 0};
        while (true) {
            minRangesString = JOptionPane.showInputDialog(null, minValueInfoString, "抽号", JOptionPane.PLAIN_MESSAGE);
            if (minRangesString == null) {
                System.exit(0);
            }
            try {
                rang[0] = Integer.parseInt(minRangesString);
                break;

            } catch (Exception e) {
                minValueInfoString = "最小值错误";
            }
        }
        while (true) {
            maxRangesString = JOptionPane.showInputDialog(null, maxValueInfoString, "抽号", JOptionPane.PLAIN_MESSAGE);
            if (maxRangesString == null) {
                System.exit(0);
            }
            try {
                rang[1] = Integer.parseInt(maxRangesString);
                break;

            } catch (Exception e) {
                maxValueInfoString = "最大值错误";
            }
        }

        if (rang[0] > rang[1]) {
            System.err.println("Options: 大小值有误，已纠正");
            int temp = rang[1];
            rang[1] = rang[0];
            rang[0] = temp;
        }
        return rang;
    }

    public static boolean repeatable() {
        return JOptionPane.showConfirmDialog(null, "是否重复？", "抽号", JOptionPane.YES_NO_OPTION) != 1;
    }
}
