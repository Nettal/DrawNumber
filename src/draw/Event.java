package draw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Event implements ActionListener,WindowListener,WindowStateListener, ItemListener{
	
	int windowState = 0;
	Integer getInteger;
	
	ArrayList<String> arrayList = new ArrayList<String>();
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
			if (Main.config.repeatable) {
				onDraw_Rep();
			}else {
				onDraw();
			}
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

	private void onDraw() {
			getInteger = Main.array.getInt_without_index();	
			if (!(getInteger == null)) {
				arrayList.add(0,(arrayList.size()+1)+":"+ getInteger.toString());
				Main.drawGUI.setJScrollPaneContent(arrayList.toArray(new String[arrayList.size()]));
			}
			Main.drawGUI.setText(getInteger);
	}
	
	private void onDraw_Rep() {
		getInteger = Main.array_Rep.getInt_without_index_Rep();
		if (!(getInteger == null)) {
			arrayList_Rep.add(0,(arrayList_Rep.size()+1)+":"+ getInteger.toString());
			Main.drawGUI.setJScrollPaneContent(arrayList_Rep.toArray(new String[arrayList_Rep.size()]));
		}
		Main.drawGUI.setText(getInteger);
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
		// TODO 自动生成的方法存根
		if (SettingsGUI.jCheckBox.isSelected()) {
			Main.config.repeatable = true;
		}else {
			Main.config.repeatable = false;	
		}
		Main.loadNum();
	}
	
}
