package draw;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    public static JList<String> Current_jList;
    public static JList<Integer> jList;
    public static boolean isLoading = false;
    public static Array array;
    public static Array array_Rep;
    public static DrawGUI drawGUI;
    public static Config config;
    public static boolean isMessageOnTop = true;
    public static Event event = new Event();
    public static String[] list = null;

    public static void main(String[] args) {
        System.out.println(
                "usage:\n [opinion] [args] \n" +
                        "  available opinions:\n" +
                        "    -legacy : use old draw num\n" +
                        "    -list : use String list instead of pure-integers\n" +
                        "       example command line: \n" +
                        "       java -jar $ThisFile.jar -list aaa;bbb;ccc;ddd;eee\n");


        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-legacy")) {
                legacy.Main.main();
                return;
            } else if (args[i].equals("-list")) {
                list = args[i + 1].split(";");
            }
        }
        loadNum(list);
        SwingUtilities.invokeLater(() -> drawGUI = new DrawGUI());
    }

    public static void loadNum(String[] str) {
        try {
            isLoading = true;
            (new ThreadDialog("请稍后")).start();
            Thread.sleep(100);
            config = new ObjectLoader("DATA").getConfig();
            System.out.println("Main: minValue:" + config.minValue + "  maxValue:" + config.maxValue);

            if (!config.repeatable) {
                if (array == null || array.size() == 0) {//满足其一
                    System.out.println("Main: loading unrepeatable array..");
                    if ((!config.saveUnusedList) || config.array == null || config.array.size() == 0
                            || (array != null && array.size() == 0)) {
                        System.out.println("Main: create a new unrepeatable array..");
                        if (str == null)
                            array = new Array(config.minValue, config.maxValue);
                        else
                            array = new Array(str);

                        config.array = array;
                    } else {
                        array = config.array;
                        System.out.println("Main: use saved unrepeatable array..");
                    }
                    array.blendList();
                }
            } else {
                System.out.println("Main: loading repeatable array..");
                if (str == null)
                    array_Rep = new Array(config.minValue, config.maxValue);
                else
                    array_Rep = new Array(str);
                array_Rep.blendList();
            }
            if (event.listTitleThread != null)
                Main.event.listTitleThread.stopSelf();
        } catch (Throwable e) {
            System.err.println("Main: Create an array error");
            e.printStackTrace();
            new File("DATA").delete();
            JOptionPane.showMessageDialog(null, "程序错误：" + e.toString(), "抽号", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        isLoading = false;
    }


}

class ThreadDialog extends Thread {

    private String messages;//提示框的提示信息
    private JFrame parentFrame = null;//提示框的父窗体
    private JDialog clueDialog = null;// “线程正在运行”提示框


    private Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = dimensions.width / 4, height = 60;
    private int left = 0, top = 0;

    public ThreadDialog(String messages) {
        this.messages = messages;
        initDialog();//初始化提示框
    }

    protected void initDialog() {
        clueDialog = new JDialog(parentFrame, "抽号", true);
        clueDialog.setAlwaysOnTop(true);
        clueDialog.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        JPanel testPanel = new JPanel();
        JLabel testLabel = new JLabel(messages);
        clueDialog.getContentPane().add(testPanel);
        testPanel.add(testLabel);
        (new DisposeDialog()).start();//启动关闭提示框线程
    }

    public void run() {
//        显示提示框
        left = (dimensions.width - width) / 2;
        top = (dimensions.height - height) / 2;
        clueDialog.setSize(new Dimension(100, 100));
        clueDialog.setLocation(left, top);
        clueDialog.setVisible(true);
    }

    class DisposeDialog extends Thread {
        public void run() {
            try {
                while (Main.isLoading) {
                    clueDialog.setAlwaysOnTop(Main.isMessageOnTop);
                    sleep(50);
                }
            } catch (Exception e) {
                System.out.println("Main: Exception:" + e);
            }
            clueDialog.dispose();//关闭提示框
        }
    }
}