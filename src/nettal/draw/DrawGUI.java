package nettal.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DrawGUI extends JFrame {
    Config config;
    JLabel jLabel;
    JList<String> jList;
    JButton settingButton;
    JButton drawButton;
    JScrollPane jScrollPane;

    public DrawGUI(Config config) {//initial basic layout
        super();
        this.config = config;
        drawButton = new JButton("抽");
        settingButton = new JButton("设置");
        jLabel = new JLabel("");
        jList = new JList<>();
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane = new JScrollPane(jList);
        JPanel jPanel = new JPanel(null);
        jPanel.setOpaque(false);
        jPanel.add(drawButton);
        jPanel.add(settingButton);
        jPanel.add(jLabel);
        jPanel.add(jScrollPane);
        this.getContentPane().add(jPanel);
        this.setColor(config.color);
        this.setBounds(config.rectangle);
        this.setGUISize(config.rectangle);
        this.setVisible(false);//wait until AbstractCase is ok ,call at AbstractCase
        this.setFocusable(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addComponentListener(
                new ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        DrawGUI.this.setGUISize(DrawGUI.this.getWidth(), DrawGUI.this.getHeight());
                    }
                });
    }

    public void setColor(Color color) {
        Color foregroundColor = Utilities.getForegroundColor(color);
        this.getContentPane().setBackground(color);
        drawButton.setBackground(color);
        drawButton.setForeground(foregroundColor);

        settingButton.setBackground(color);
        settingButton.setForeground(foregroundColor);

        jLabel.setBackground(color);
        jLabel.setForeground(foregroundColor);

        jList.setBackground(color);
        jList.setForeground(foregroundColor);

        jScrollPane.getViewport().setBackground(color);
    }

    public void setGUISize(Rectangle rectangle) {
        this.setGUISize(rectangle.width, rectangle.height);
    }

    public void setGUISize(int width, int height) {
        settingButton.setBounds(0, 0, width / 20, height / 20);
        drawButton.setFont(new Font("Dialog", Font.BOLD, (Math.min(height, width) / 5)));
        drawButton.setBounds(width / 20, height / 20, width / 2, height / 3);
        jLabel.setBounds(width / 20, height / 3 + height / 20, (int) (width * 1.3), height / 2);
        jLabel.setFont(new Font("Dialog", Font.BOLD, (int) (Math.min(jLabel.getWidth(), jLabel.getHeight()) * .4)));
        jScrollPane.setBounds(width / 10 + width / 2, height / 20, width / 3, height - height / 5);
    }
}
