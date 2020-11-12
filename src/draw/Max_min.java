package draw;

import javax.swing.JOptionPane;

public class Max_min {
	static boolean isMinCurrect=false;
	static boolean isMaxCurrect=false;
	static String minValueinfoString = "请输入最小值(不含)";
	static String maxValueinfoString = "请输入最大值(包含)";
	static String minrangesString = "";
	static String maxrangesString = "";
	static int[] rang = {0,0};
	public Max_min() {
	}
	
	public static int[] getRange() {
		while(!isMinCurrect) {
			minrangesString =JOptionPane.showInputDialog(null,minValueinfoString , "抽号",JOptionPane.PLAIN_MESSAGE );
			try {
				rang[0] =Integer.parseInt(minrangesString);
				isMinCurrect=true;
				
			} catch (Exception e) {
				minValueinfoString="最小值错误";
				}
			}
		while(!isMaxCurrect) {
			maxrangesString=JOptionPane.showInputDialog(null,maxValueinfoString, "抽号",JOptionPane.PLAIN_MESSAGE );	
			try {
				rang[1] =Integer.parseInt(maxrangesString);
				isMaxCurrect=true;
				
			} catch (Exception e) {
				minValueinfoString="最大值错误";
				}
			}

			if (rang[0]>rang[1]) {
				System.err.println("Max_min: 大小值有误，已纠正");
				int temp = rang[1];
				rang[1] = rang[0];
				rang[0] = temp;
			}
		isMinCurrect=false;
		isMaxCurrect=false;
		return rang;
	}	
}
