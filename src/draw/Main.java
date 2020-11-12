package draw;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	
	public static JList<String> Currect_jList;
	public static JList<Integer> jList;
	public static boolean isloading = false;
	public static Array array;
	public static Array array_Rep;
	public static DrawGUI drawGUI;
	public static Config config;
	public static boolean isMessageOnTop = true;
	public static Event event = new Event();
	
	public static void main(String[] args) {
		if (!(args.length==0)) {
			legacy.Main.main();
		} else {
			loadNum();
				SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					drawGUI =new DrawGUI();
				}
			});
		 }
	}
	public static void loadNum() {
		try {	
			isloading = true;
			(new ThreadDiag("请稍后")).start();
			Thread.sleep(200);
			config = new ObjectLoader("DATA").getConfig();
			System.out.println("Main: minValue:"+config.minValue+"  maxValue:"+ config.maxValue);
	
			if (!config.repeatable) {
			if (array==null||array.size()==0) {//满足其一
				System.out.println("Main: loading unrepeatable array..");
				array = new Array(config.minValue, config.maxValue);
				blendList(array);
			}
		}else {
			System.out.println("Main: loading repeatable array..");
			array_Rep = new Array(config.minValue, config.maxValue);
			blendList(array_Rep);
		}
		} catch (Throwable e) {
			System.err.println("Main: Create an array error");
			e.printStackTrace();
			new File("DATA").delete();
			JOptionPane.showMessageDialog(null,"程序错误："+e.toString(),"抽号",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
			isloading = false;
	}
	
	public static void blendList(Array a) {
		int leng = a.size();
		for (int i = 0; i < leng; i++) {
			int index = (int)(Math.random()*leng);
			Integer tempInteger = a.get(i);
			a.set(i, a.get(index));
			a.set(index, tempInteger);
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
        clueDiag = new JDialog(parentFrame,"抽号",true);
        clueDiag.setAlwaysOnTop(true);
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
            		clueDiag.setAlwaysOnTop(Main.isMessageOnTop);
            		sleep(100);
				}
            }catch(Exception e){
                System.out.println("Main: Exception:" + e);
            }
            clueDiag.dispose();//关闭提示框
        }
    } 
}