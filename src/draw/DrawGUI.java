package draw;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.*;

public class DrawGUI extends JFrame {
	
	private static final long serialVersionUID = -8854186460449111662L;
	double division = 2;
	int posX = 10;
	int posY = 10;
	int width = 600;
	int height = 450;
	int count = 0;
	public Color color;

	JButton settingButton = new JButton("设置");
	JButton drawButton = new JButton("抽号");
	public JLabel jLabel = new JLabel("");
	JScrollPane jScrollPane = null;
	
	public DrawGUI() {
		super(Main.list == null?
				String.format("抽号:[%d,%d]  @Version 1.2", Main.config.minValue , Main.config.maxValue):
				String.format("抽号:列表模式,共%d个 @Version 1.2",Main.list.length));
		jScrollPane = new JScrollPane(Main.jList);
		posX = Main.config.shape.x;
		posY = Main.config.shape.y;
		width = Main.config.shape.width;
		height = Main.config.shape.height;
		setSize(width, height);
		Main.jList = jList();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane().add(getJPanel());
		this.setBounds(posX, posY,width , height);
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
		this.addWindowFocusListener(Main.event);
		this.addComponentListener(
				new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				DrawGUI.this.setSize(DrawGUI.this.getWidth(), DrawGUI.this.getHeight());
			}
		});
			this.setTheme(new Color(Main.config.themeColor));
	}

	public void setTheme(Color color){
		this.color = color;
		this.getContentPane().setBackground(color);
		drawButton.setBackground(color);
		settingButton.setBackground(color);
		settingButton.setForeground(new Color(Integer.MAX_VALUE-color.getRGB()));
		jLabel.setBackground(color);
		jLabel.setForeground(new Color(Integer.MAX_VALUE-color.getRGB()));
		jScrollPane.getViewport().setBackground(color);
		drawButton.setForeground(new Color(Integer.MAX_VALUE-color.getRGB()));
		if(Main.Current_jList != null){
			Main.Current_jList.setBackground(this.color);
			Main.Current_jList.setForeground(new Color(Integer.MAX_VALUE - this.color.getRGB()));
		}
	}
	
	
	JPanel getJPanel() {
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
		Main.Current_jList = new JList<String>(strings);
		Main.Current_jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Main.Current_jList.addKeyListener(Main.event);
		Main.Current_jList.addListSelectionListener(Main.event);
		if(this.color != null){
			Main.Current_jList.setBackground(this.color);
			Main.Current_jList.setForeground(new Color(Integer.MAX_VALUE - this.color.getRGB()));
		}
		jScrollPane.setViewportView(Main.Current_jList);
	}
	
	public void setSize(int width , int height) {
		this.width = width;
		this.height = height;
		settingButton.setBounds(0, 0, width/20, height/20);
		drawButton.setFont(new Font("Dialog", Font.BOLD,(Math.min(height, width)/5)));
		drawButton.setBounds(width/20, height/20, width/2, height/3);
		jLabel.setBounds(width/20, height/3+height/20, width/2, height/2);
		jLabel.setFont(new Font("Dialog", Font.BOLD,(int) (Math.min(height, width)/division)));
		jScrollPane.setBounds(width/10+width/2,height/20 , width/3, height-height/5);
	}
	
	public void clear_list() {
				Main.event.arrayList = new  ArrayList<String>();
				Main.Current_jList = new JList<String>() ;
	}

	public void setText(String value , Color color) {
		if (value==null) {
			if (count<1) {
				jLabel.setText("已抽完！");
				division = 8;
				jLabel.setFont(new Font("Dialog", Font.BOLD,(int) (Math.min(height, width)/division)));
				jLabel.setForeground(Color.RED);				count++;
			} else {
				jLabel.setText("即将进行新一轮");
				jLabel.setForeground(Color.RED);
				count = 0;
				division = 13;
				jLabel.setFont(new Font("Dialog", Font.BOLD,(int) (Math.min(height, width)/division)));
				clear_list();
				Main.loadNum(Main.list);
			}
		} else {
			if (getLength(value)==2) {
				division = 3.5;
			} else if (getLength(value)==3) {
				division = 4;
			}else{
				division = getLength(value);
			}

		jLabel.setFont(new Font("Dialog", Font.BOLD,(int) (Math.min(height, width)/division)));
		jLabel.setText(value);
		jLabel.setForeground(this.color == null?color:new Color(Integer.MAX_VALUE - this.color.getRGB() - color.getRGB()));
		}
	}

	private int getLength(String num){
		int ret = num.length();
		if(ret == 1)
			return 2;
		return ret;
	}
}
