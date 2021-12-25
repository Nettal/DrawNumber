package nettal.draw;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final String version = "@Version 2.0.0";
    public static String dataPath = "Data_2";
    public static RepeatCase repeatCase;
    public static NoRepeatCase noRepeatCase;

    public static void main(String[] args) {
        String[] list = null;
        /*Analyse args*/
        System.out.println(
                """
                        Usage:
                         [opinion] [args]
                          Available opinions:
                            --dataPath : define the data path
                            --legacy : use older ConSoleDrawNumber
                            --legacyGUI : use old GUIDrawNumber
                            --list : use String list instead of pure-integers
                               Example:
                               java -jar $ThisFile.jar --list aaa;bbb;ccc;ddd;eee --dataPath ./config/Data
                        """);


        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--legacy" -> {
                    legacy.Main.main();
                    return;
                }
                case "--legacyGUI" -> {
                    draw.Main.main(args);
                    return;
                }
                case "--list" -> list = args[++i].split(";");
                case "--dataPath" -> dataPath = args[++i];
                default -> {
                }
            }
        }
        /*ShowLoadingDialog*/
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setIndeterminate(true);// 未知时间
        jProgressBar.setBounds(0, 0, 300, 40);
        jProgressBar.setStringPainted(true);//显示加载中
        jProgressBar.setString("加载中");
        jProgressBar.setFont(new Font("Dialog", Font.BOLD, 20));
        JDialog jDialog = new JDialog();
        jDialog.setTitle("抽号");
        jDialog.setAlwaysOnTop(true);
        jDialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        jDialog.getContentPane().add(jProgressBar);
        jDialog.setBounds(100, 100, 300, 75);
        jDialog.setVisible(true);
        /*Load config*/
        Config config = ObjectLoader.getConfig(dataPath);
        /*Load GUI*/
        DrawGUI drawGUI = new DrawGUI(config);
        /*Load Case*/
        if (config.repeatable) {
            repeatCase = new RepeatCase(drawGUI, config, list);
        } else {
            noRepeatCase = new NoRepeatCase(drawGUI, config, list);
        }
        /*EndLoadingDialog*/
        jProgressBar.setVisible(false);
        jDialog.setVisible(false);
    }
}
