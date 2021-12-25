package nettal.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingsGUI extends JFrame {
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
        textField = new JTextField(RGB2Str(config.color.getRGB()));
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
        this.setBounds(config.rectangle.x, config.rectangle.y, 470, 460);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.getContentPane().add(jp);
    }

    private void checkColor() {
        Integer i = Str2RGB(textField.getText());
        if (i != null) {
            abstractCase.drawGUI.setColor(new Color(i));
            config.color = new Color(i);
            textField.setForeground(Color.BLACK);
        } else
            textField.setForeground(Color.RED);
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

        if (argb.length() != 8 && argb.length() != 6 ||
                argb.replaceAll("\\d+", "").replaceAll("[a-fA-F]", "").length() != 0) {
            return null;
        }

        int alpha, red, green, blue;

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
