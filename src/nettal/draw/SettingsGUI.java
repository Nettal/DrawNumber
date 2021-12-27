package nettal.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingsGUI extends JFrame implements WindowListener {
    AbstractCase abstractCase;
    JButton resetButton;
    JTextField textField;
    JCheckBox repeat;
    JCheckBox loadUnusedList;
    JLabel about;
    Config config;

    public SettingsGUI(Config config, AbstractCase abstractCase) {
        super("设置");
        this.config = config;
        this.abstractCase = abstractCase;
        JPanel jp = new JPanel(null);
        jp.setOpaque(false);
        /*重置*/
        resetButton = new JButton("重置");
        resetButton.setFont(new Font("Dialog", Font.BOLD, 20));
        resetButton.setBounds(10, 10, 200, 50);
        resetButton.addActionListener(e -> {
            ObjectLoader.deleteFile(Main.dataPath);
            System.exit(0);
        });
        jp.add(resetButton);
        /*RGB*/
        textField = new JTextField(Utilities.rgb2Str(config.color.getRGB()));
        textField.setFont(new Font("Dialog", Font.BOLD, 18));
        textField.setBounds(220, 10, 200, 50);
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
        jp.add(textField);
        /*重复*/
        repeat = new JCheckBox("允许重复(及时生效)", config.repeatable);
        repeat.setFont(new Font("Dialog", Font.BOLD, 18));
        repeat.setBounds(10, 70, 200, 50);
        repeat.addActionListener(e -> {
            config.repeatable = repeat.isSelected();
            loadUnusedList.setEnabled(!repeat.isSelected());
            if (Main.noRepeatCase == null)
                Main.noRepeatCase = new NoRepeatCase(abstractCase.drawGUI, config, abstractCase.strings);
            if (Main.repeatCase == null)
                Main.repeatCase = new RepeatCase(abstractCase.drawGUI, config, abstractCase.strings);
            if (repeat.isSelected()) {//重复
                AbstractCase.switchCase(Main.noRepeatCase, Main.repeatCase);
            } else {
                AbstractCase.switchCase(Main.repeatCase, Main.noRepeatCase);
            }
        });
        jp.add(repeat);
        /*加载已抽取*/
        loadUnusedList = new JCheckBox("自动加载已抽取", config.loadUnusedList);
        loadUnusedList.setFont(new Font("Dialog", Font.BOLD, 18));
        loadUnusedList.setBounds(220, 70, 200, 50);
        loadUnusedList.setEnabled(!config.repeatable);
        loadUnusedList.addItemListener((itemEvent) -> config.loadUnusedList = loadUnusedList.isSelected());
        jp.add(loadUnusedList);
        /*About*/
        about = new JLabel("关于");
        about.setFont(new Font("Dialog", Font.BOLD, 20));
        about.setBounds(10, 80, 400, 400);
        about.setText("<html>About：<br>Author:github.com/SnowNF<br>Author:github.com/Nettal<br>license:LGPL v2.1<br>Source:github.com/Nettal/DrawNumber</html> ");
        jp.add(about);
        this.setBounds(abstractCase.drawGUI.getX(), abstractCase.drawGUI.getY(), 470, 460);
        this.setResizable(false);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.getContentPane().add(jp);
    }

    private void checkColor() {
        Integer i = Utilities.str2RGB(textField.getText());
        if (i != null) {
            abstractCase.drawGUI.setColor(new Color(i));
            config.color = new Color(i);
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
        this.dispose();
    }
}
