package draw;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	
	public static boolean isloading = false;
	public static Array array;
	public static DrawGUI drawGUI;
	public static Config config;
	public static Event event = new Event();
	
	public static void main(String[] args) {
		if (!(args.length==0)) {
			legacy.Main.main();
		} else {
		isloading = true;
		(new ThreadDiag("Loading")).start();
		config = new ObjectLoader("DATA").getConfig();
		System.out.println("minValue:"+config.minValue+"  maxValue:"+ config.maxValue);
		array = new Array(config.minValue, config.maxValue);
		isloading = false;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					drawGUI =new DrawGUI();
				}
			});
		 }
	}
}

class ThreadDiag extends Thread
{

    private String messages = "";//提示框的提示信息
    private JFrame parentFrame = null;//提示框的父窗体
    private JDialog clueDiag = null;// “线程正在运行”提示框

    private Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = dimensions.width / 4, height = 60;
    private int left = 0, top = 0;

    public ThreadDiag(String messages)
    {

        this.messages= messages;
        initDiag();//初始化提示框
    }

    protected void initDiag()
    {
        clueDiag = new JDialog(parentFrame,messages,true);
        clueDiag.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        JPanel testPanel = new JPanel();
        JLabel testLabel = new JLabel(messages);
        clueDiag.getContentPane().add(testPanel);
        testPanel.add(testLabel);
        (new DisposeDiag()).start();//启动关闭提示框线程
    }

    public void run()
    {
//        显示提示框
        left = (dimensions.width - width)/2;
        top = (dimensions.height - height)/2;
        clueDiag.setSize(new Dimension(100,100));
        clueDiag.setLocation(left, top);
        clueDiag.setVisible(true);
    }

    class DisposeDiag extends Thread
    {
        public void run()
        {
            try
            {
            	while (Main.isloading) {
            		sleep(100);
				}
            	
            }catch(Exception e){
                System.out.println("Exception:" + e);
            }
            clueDiag.dispose();//关闭提示框
        }
    } 
}