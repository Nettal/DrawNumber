package draw;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.ArrayList;


import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Event implements ActionListener,WindowListener,WindowStateListener, ItemListener,KeyListener,ListSelectionListener{
	
	int windowState = 0;
	Integer getInteger;
	
	public ArrayList<String> arrayList = new ArrayList<String>();
	ArrayList<String> arrayList_Rep = new ArrayList<String>();
	public Event() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("settings")) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SettingsGUI();
			}
		});
		}
		if(e.getActionCommand().equals("draw")) {
			this.onDraw();
		}
		if (e.getActionCommand().equals("reset")) {
			//delete file
			new File("DATA").delete();
			System.exit(0);
//			Runtime.getRuntime().addShutdownHook(new Thread() {
//				public void run() {
//					String restartCmdString="java -jar "+System.getProperty("java.class.path");
//					
//				}
//			});
	//	System.out.println(System.getProperty("java.class.path"));
		}
	}

	public void onDraw() {
		if (Main.config.repeatable) {
			getInteger = Main.array_Rep.getInt_without_index_Rep();
		if (!(getInteger == null)) {
			arrayList_Rep.add(0,(arrayList_Rep.size()+1)+":"+ getInteger.toString());
			Main.drawGUI.setJScrollPaneContent(arrayList_Rep.toArray(new String[arrayList_Rep.size()]));//array_list转换成字符串组
		}
		Main.drawGUI.setText(getInteger , Color.BLACK);
		}else {
			getInteger = Main.array.getInt_without_index();	
			if (!(getInteger == null)) {
				arrayList.add(0,(arrayList.size()+1)+":"+ getInteger.toString());
				Main.drawGUI.setJScrollPaneContent(arrayList.toArray(new String[arrayList.size()]));//array_list转换成字符串组
			}
			Main.drawGUI
			.setText(getInteger , Color.BLACK);	
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自动生成的方法存根
		this.onClose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	void onClose() {
		if (!(windowState == 6)) {
			Main.config.shape = Main.drawGUI.getBounds();
		}
		new ObjectLoader("DATA").saveConfig(Main.config);
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		// TODO 自动生成的方法存根
		windowState = e.getNewState();
	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		set_Wrapper();
		set_Wrapper();
	}
	
	private void setRep() {
		if (SettingsGUI.jCheckBox.isSelected()) {
			Main.config.repeatable = true;
		}else {
			Main.config.repeatable = false;	
		}
		new ObjectLoader("DATA").saveConfig(Main.config);
		Main.loadNum();
	}
	
	boolean isFirst = true;
	long actionTime = 0;
	private void set_Wrapper() {
//		System.err.println("Wrapper!");
		if (actionTime == 0) {
			actionTime = System.currentTimeMillis();
		}
		if(isFirst){
			new Thread(new Runnable() {
				@Override
				public void run() {
					setRep();
				}
			}).start();
//			System.err.println("DEBUG!");
			isFirst = false;
		}
		if (actionTime + 100 <= System.currentTimeMillis()) {
			isFirst = true;
			actionTime = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		onDraw();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO 自动生成的方法存根
		int index = Main.Currect_jList.getSelectedIndex();
		if (Main.config.repeatable) {
			if (arrayList_Rep.isEmpty()) return;
			//截取字符串
			String cutStr = arrayList_Rep.get(index);
			Main.drawGUI.setText(Integer.parseInt(cutStr.substring(cutStr.indexOf(":")+1, cutStr.length())) , Color.BLUE);

		}else {
			if (arrayList.isEmpty()) return;
			//截取字符串
			String cutStr = arrayList.get(index);
			Main.drawGUI.setText(Integer.parseInt(cutStr.substring(cutStr.indexOf(":")+1, cutStr.length())) , Color.BLUE);
		}
	}
	
}
