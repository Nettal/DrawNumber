package draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingsGUI extends JFrame implements WindowListener {
	private static final long serialVersionUID = -5209828332008414779L;

	Event event = Main.event;
	JButton reSetButton = new JButton("重置");
	JButton reSetButton1 = new JButton("");
	JButton reSetButton2 = new JButton("");//
	JTextField textField = new JTextField(RGB2Str(Main.config.themeColor).toUpperCase());
	static JCheckBox jCheckBox = new JCheckBox("允许重复(及时生效)", Main.config.repeatable);
	JLabel about = new JLabel("关于");

	public SettingsGUI() {
		super("设置");
		this.setBounds(Main.drawGUI.getBounds().x, Main.drawGUI.getBounds().y, 440, 460);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.getContentPane().add(addComponent());
		this.addWindowListener(this);
	}

	private Component addComponent() {
		JPanel jp = new JPanel(null);
		jp.setOpaque(false);
		reSetButton.setActionCommand("reset");
		reSetButton.addActionListener(event);
		reSetButton.setBounds(10, 10, 200, 50);
		reSetButton.setFont(new Font("Dialog", 1, 20));
		jp.add(reSetButton);
		reSetButton1.setActionCommand("reset1");
		reSetButton1.addActionListener(event);
		reSetButton1.setBounds(220, 10, 200, 50);
		jp.add(reSetButton1);
//		reSetButton2.setActionCommand("reset2");
//		reSetButton2.addActionListener(event);
//		reSetButton2.setBounds(10, 70, 200, 50);
//		jp.add(reSetButton2);
		jCheckBox.setActionCommand("checkbox");
		jCheckBox.addItemListener(Main.event);
		jCheckBox.setBounds(10, 70, 200, 50);
		jCheckBox.setFont(new Font("Dialog", 1, 18));
		jp.add(jCheckBox);
		textField.setActionCommand("reset3");
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				checkColor();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				checkColor();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				checkColor();
			}
		});
		textField.setBounds(220, 70, 200, 50);
		jp.add(textField);
		about.setBounds(10, 80, 400, 400);
		about.setFont(new Font("Dialog", 1, 20));
		about.setText("<html>关于：<br>刘家瑞 github@SnowNF<br>刘家祥 github@37385<br>邮箱3374353308@qq.com<br>邮箱3186856156@qq.com<br>license:LGPL v2.1<br>项目地址：github.com/37385/drawnum</html> ");
		jp.add(about);
		return jp;
	}

	private void checkColor() {
		Integer i = Str2RGB(textField.getText());
		if (i != null) {
			Main.drawGUI.setTheme(new Color(i));
			Main.config.themeColor = i;
			textField.setForeground(Color.BLACK);
		} else
			textField.setForeground(Color.RED);
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		Main.drawGUI.setAlwaysOnTop(true);
	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	private String RGB2Str(int i) {
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

	private Integer Str2RGB(String argb) {
		if (argb.startsWith("#")) {
			argb = argb.replace("#", "");
		}

		if (argb.length() != 8 && argb.length() != 6 || argb.replaceAll("\\d+", "").replaceAll("[a-fA-F]", "").length() != 0) {
			return null;
		}

		int alpha = -1, red = -1, green = -1, blue = -1;

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
