package draw;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class DrawGUI extends JFrame{
	
	private static final long serialVersionUID = -8854186460449111662L;
	double division = 2;
	int posx = 10;
	int posy = 10;
	int width = 600;
	int height = 450;

	
	JButton settingButton = new JButton("…Ë÷√");
	JButton drawButton = new JButton("≥È∫≈");
	JLabel jLabel = new JLabel("");
	JScrollPane jScrollPane = new JScrollPane(jList());
	
	public DrawGUI() {
		super("≥È∫≈");
		posx = Main.config.shape.x;
		posy = Main.config.shape.y;
		width = Main.config.shape.width;
		height = Main.config.shape.height;
		setSize(width, height);
		this.setDefaultCloseOperation(3);
		this.getContentPane().add(getJpanel());
		this.setBounds(posx, posy ,width , height);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.addWindowStateListener(Main.event);
		this.addWindowListener(Main.event);
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				DrawGUI.this.setSize(DrawGUI.this.getWidth(), DrawGUI.this.getHeight());
			} 
		});
		}
	
	
	JPanel getJpanel() {
		JPanel jp = new JPanel(null);
		jp.setOpaque(false);
		settingButton.setActionCommand("settings");
		settingButton.addActionListener(Main.event);
		jp.add(settingButton);
		drawButton.setActionCommand("draw");
		drawButton.addActionListener(Main.event);
		jp.add(drawButton);
		jp.add(jLabel);
		jp.add(jScrollPane);
		return jp;
	}
	
	Component jList() {
		Integer[] list = {};
		JList<Integer> jList = new JList<Integer>(list);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jList;
	}
	
	void setJScrollPaneContent(String[] strings ) {
		JList<String> jList = new JList<String>(strings);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane.setViewportView(jList);
	}
	
	public void setSize(int width , int height) {
		this.width = width;
		this.height = height;
		settingButton.setBounds(0, 0, width/20, height/20);
		drawButton.setFont(new Font("Dialog",1,height/5));
		drawButton.setBounds(width/20, height/20, width/2, height/3);
		jLabel.setBounds(width/20, height/3+height/20, width/2, height/2);
		jLabel.setFont(new Font("Dialog",1,(int) (height/division)));
		jScrollPane.setBounds(width/10+width/2,height/20 , width/3, height-height/5);
	}
	

	public void setText(Integer value) {
		if (value==null) {
			jLabel.setText("“—≥ÈÕÍ£°");
			division = 8;
			jLabel.setFont(new Font("Dialog",1,(int) (height/division)));
		} else {
			if (getlength(value)==2) {
				division = 3.5;
			} else if (getlength(value)==3) {
				division = 4;
			}{
				division = getlength(value);
			}
			
		jLabel.setFont(new Font("Dialog",1,(int) (height/division)));
		jLabel.setText(value.toString());
		}
	}	
	
	private int getlength (int num){
		int count=0;
		if (num <= 9) {
			count++;
			}
		if (num==0) {
			count++;
		}
		num = Math.abs(num);
		while(num>=1) {
			num/=10;
			count++;
		}
		return count;
	}
	
}
