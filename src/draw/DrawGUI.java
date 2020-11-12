package draw;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class DrawGUI extends JFrame {
	
	private static final long serialVersionUID = -8854186460449111662L;
	double division = 2;
	int posx = 10;
	int posy = 10;
	int width = 600;
	int height = 450;
	int count = 0;
	
	
	JButton settingButton = new JButton("设置");
	JButton drawButton = new JButton("抽号");
	public JLabel jLabel = new JLabel("");
	JScrollPane jScrollPane = null;
	
	public DrawGUI() {
		super(String.format("抽号:[%d,%d]  @Version 1.1.2", Main.config.minValue , Main.config.maxValue));
		jScrollPane = new JScrollPane(Main.jList);
		posx = Main.config.shape.x;
		posy = Main.config.shape.y;
		width = Main.config.shape.width;
		height = Main.config.shape.height;
		setSize(width, height);
		Main.jList = jList();
		this.setDefaultCloseOperation(3);
		this.getContentPane().add(getJpanel());
		this.setBounds(posx, posy ,width , height);
		this.addKeyListener(Main.event);
		drawButton.addKeyListener(Main.event);
		settingButton.addKeyListener(Main.event);
		jLabel.addKeyListener(Main.event);
		jScrollPane.addKeyListener(Main.event);
		this.setFocusable(true);
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
	
	JList<Integer> jList() {
		Integer[] list = {};
		JList<Integer> jList = new JList<Integer>(list);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.addKeyListener(Main.event); 
		jList.addListSelectionListener(Main.event);
		return jList;
	}
	
	void setJScrollPaneContent(String[] strings ) {
		Main.Currect_jList = new JList<String>(strings);
		Main.Currect_jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Main.Currect_jList.addKeyListener(Main.event); 
		Main.Currect_jList.addListSelectionListener(Main.event);
		jScrollPane.setViewportView(Main.Currect_jList);
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
	
	public void clear_list() {
				Main.event.arrayList = new  ArrayList<String>();
				Main.Currect_jList = new JList<String>() ;
	}

	public void setText(Integer value , Color color) {
		if (value==null) {
			if (count<1) {
				jLabel.setText("已抽完！");
				division = 8;
				jLabel.setFont(new Font("Dialog",1,(int) (height/division)));
				jLabel.setForeground(Color.RED);				count++;
			} else {
				jLabel.setText("即将进行新一轮");
				jLabel.setForeground(Color.RED);
				count = 0;
				division = 13;
				jLabel.setFont(new Font("Dialog",1,(int) (height/division)));
				clear_list();
				Main.loadNum();
			}
		} else {
			if (getlength(value)==2) {
				division = 3.5;
			} else if (getlength(value)==3) {
				division = 4;
			}else{
				division = getlength(value);
			}
			
		jLabel.setFont(new Font("Dialog",1,(int) (height/division)));
		jLabel.setText(value.toString());
		jLabel.setForeground(color);
		}
	}	
	
	private int getlength (int num){
		int length=0;
		if (num <= 9) {
			length++;
			}
		if (num==0) {
			length++;
		}
		num = Math.abs(num);
		while(num>=1) {
			num/=10;
			length++;
		}
		return length;
	}
}
