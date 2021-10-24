package draw;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class DrawGUI extends JFrame {

	private static final long serialVersionUID = -8854186460449111662L;
	public Color color;
	public JLabel jLabel = new JLabel("");
	int posX;
	int posY;
	int width;
	int height;
	int count = 0;
	JButton settingButton = new JButton("设置");
	JButton drawButton = new JButton("抽");
	JScrollPane jScrollPane;

	public DrawGUI() {
		super(Main.list == null?
				String.format("抽号:[%d,%d]  @Version 1.2.1", Main.config.minValue , Main.config.maxValue):
				String.format("抽号:列表模式,共%d个 @Version 1.2.1",Main.list.length));
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
		JList<Integer> jList = new JList<>(list);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.addKeyListener(Main.event);
		jList.addListSelectionListener(Main.event);
		return jList;
	}

	void setJScrollPaneContent(String[] strings ) {
		Main.Current_jList = new JList<>(strings);
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
		jLabel.setBounds(width/20, height/3+height/20, (int) (width*1.3), height/2);
		jLabel.setFont(new Font("Dialog", Font.BOLD,(int) (Math.min(jLabel.getWidth() , jLabel.getHeight())*.4)));
		jScrollPane.setBounds(width/10+width/2,height/20 , width/3, height-height/5);
	}

	public void clear_list() {
				Main.event.selectedList = new ArrayList<>();
				Main.Current_jList = new JList<>() ;
	}

	public void setText(String value , boolean isSelected) {
		Font font;
		if (isSelected||value==null)
			font = new Font(null, Font.BOLD | Font.ITALIC, (int) (Math.min(jLabel.getWidth(), jLabel.getHeight()) * .4));
		else
			font = new Font(null, Font.BOLD, (int) (Math.min(jLabel.getWidth(), jLabel.getHeight()) * .4));
		jLabel.setFont(font);
		if (value==null) {
			if (count<1) {
				jLabel.setText("已抽完!");
				jLabel.setForeground(colorInversion());
				count++;
			} else {
				jLabel.setText("新一轮!");
				jLabel.setForeground(colorInversion());
				count = 0;
				clear_list();
				Main.loadNum(Main.list);
			}
		} else {
			jLabel.setText(value);
			jLabel.setForeground(colorInversion());
		}
	}

	private Color colorInversion(){
		return new Color(Integer.MAX_VALUE - this.color.getRGB() - Color.BLACK.getRGB());
	}
}
