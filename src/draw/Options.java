package draw;

import javax.swing.JOptionPane;

public class Options {
	static boolean isMinCurrent =false;
	static boolean isMaxCurrent =false;
	static String minValueInfoString = "请输入最小值(包含)";
	static String maxValueInfoString = "请输入最大值(包含)";
	static String minRangesString = "";
	static String maxRangesString = "";
	static int[] rang = {0,0};
	public Options() {
	}
	
	public static int[] getRange() {
		while(!isMinCurrent) {
			minRangesString =JOptionPane.showInputDialog(null, minValueInfoString, "抽号",JOptionPane.PLAIN_MESSAGE );
			if (minRangesString ==null) {
				System.exit(0);
			}
			try {
				rang[0] =Integer.parseInt(minRangesString);
				isMinCurrent =true;
				
			} catch (Exception e) {
				minValueInfoString ="最小值错误";
				}
			}
		while(!isMaxCurrent) {
			maxRangesString =JOptionPane.showInputDialog(null, maxValueInfoString, "抽号",JOptionPane.PLAIN_MESSAGE );
			if (maxRangesString ==null) {
				System.exit(0);
			}
			try {
				rang[1] =Integer.parseInt(maxRangesString);
				isMaxCurrent =true;
				
			} catch (Exception e) {
				minValueInfoString ="最大值错误";
				}
			}

			if (rang[0]>rang[1]) {
				System.err.println("Options: 大小值有误，已纠正");
				int temp = rang[1];
				rang[1] = rang[0];
				rang[0] = temp;
			}
		isMinCurrent =false;
		isMaxCurrent =false;
		return rang;
	}	
	
	public static boolean repeatable() {
		int intRep = JOptionPane.showConfirmDialog(null, "是否不重复？", "抽号", JOptionPane.YES_NO_OPTION);
		System.out.println(intRep);
		return intRep == 1;
	}
}
