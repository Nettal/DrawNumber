package draw;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsGUI extends JFrame{
	private static final long serialVersionUID = -5209828332008414779L;
	
	Event event = new Event();
	JButton reSetButton = new JButton("重置");
	JButton reSetButton1 = new JButton("");
	JButton reSetButton2 = new JButton("");//
	JButton reSetButton3 = new JButton("");
	static JCheckBox jCheckBox = new JCheckBox("允许重复(及时生效)", Main.config.repeatable);
	JLabel about = new JLabel("关于");

	public SettingsGUI() {
		super("设置");
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.getContentPane().add(addComponent());
		this.setBounds(Main.drawGUI.getBounds().x, Main.drawGUI.getBounds().y ,440 , 460);
	}

	private Component addComponent() {
		JPanel jp = new JPanel(null);
		jp.setOpaque(false);
		reSetButton.setActionCommand("reset");
		reSetButton.addActionListener(event);
		reSetButton.setBounds(10, 10, 200, 50);
		reSetButton.setFont(new Font("Dialog",1,20));
		jp.add(reSetButton);
		reSetButton1.setActionCommand("reset1");
		reSetButton1.addActionListener(event);
		reSetButton1.setBounds(220, 10, 200, 50);
		jp.add(reSetButton1);
//		reSetButton2.setActionCommand("reset2");
//		reSetButton2.addActionListener(event);
//		reSetButton2.setBounds(10, 70, 200, 50);
//		jp.add(reSetButton2);
		jCheckBox.setActionCommand(null);
		jCheckBox.addItemListener(Main.event);
		jCheckBox.setBounds(10, 70, 200, 50);
		jCheckBox.setFont(new Font("Dialog",1,18));
		jp.add(jCheckBox);
		reSetButton3.setActionCommand("reset3");
		reSetButton3.addActionListener(event);
		reSetButton3.setBounds(220, 70, 200, 50);
		jp.add(reSetButton3);
		about.setBounds(10,80,400, 400);
		about.setFont(new Font("Dialog",1,20));
		about.setText("<html>关于：<br>LJR github@SnowNF<br>LJX github@37385<br>邮箱3374353308@qq.com<br>邮箱3186856156@qq.com<br>license:LGPL v2.1<br>项目地址：github.com/37385/drawnum</html> ");
		jp.add(about);
		return jp;
	}
}
