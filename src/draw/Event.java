package draw;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;


import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Event implements ActionListener,WindowListener,WindowStateListener, ItemListener,KeyListener,ListSelectionListener, WindowFocusListener {
	
	int windowState = 0;
	String getInteger;
	SettingsGUI settingsGUI;
	public ListTitleThread listTitleThread;

	public ArrayList<String> selectedList = new ArrayList<>();
	ArrayList<String> selectedList4Rep = new ArrayList<>();
	public Event() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("settings")) {
			Main.drawGUI.setAlwaysOnTop(false);
			SwingUtilities.invokeLater(() -> settingsGUI = new SettingsGUI());
		}
		if(e.getActionCommand().equals("draw")) {
			this.onDraw();
			if (listTitleThread == null) {
				listTitleThread = new ListTitleThread();
				listTitleThread.start();
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

	public void onDraw() {
		if (Main.config.repeatable) {
			getInteger = Main.array_Rep.getStr_without_index_Rep();
		if (!(getInteger == null)) {
			selectedList4Rep.add(0,(selectedList4Rep.size()+1)+":"+ getInteger);
			Main.drawGUI.setJScrollPaneContent(selectedList4Rep.toArray(new String[selectedList4Rep.size()]));//array_list转换成字符串组
		}
		}else {
			getInteger = Main.array.getStr_without_index();
			if (!(getInteger == null)) {
				selectedList.add(0,(selectedList.size()+1)+":"+ getInteger);
				Main.drawGUI.setJScrollPaneContent(selectedList.toArray(new String[selectedList.size()]));//array_list转换成字符串组
			}
		}
		Main.drawGUI.setText(getInteger , false);
	}

	@Override
	public void windowOpened(WindowEvent e) { }

	@Override
	public void windowClosing(WindowEvent e) {
		if(settingsGUI != null){
			settingsGUI.dispose();
			settingsGUI = null;
		}
		this.onClose();
	}

	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) { }

	void onClose() {
		if (!(windowState == 6)) {
			Main.config.shape = Main.drawGUI.getBounds();
		}
		new ObjectLoader("DATA").saveConfig(Main.config);
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		windowState = e.getNewState();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		set_Wrapper();
		set_Wrapper();
	}
	
	private void setRep() {
		Main.config.repeatable = SettingsGUI.jCheckBox.isSelected();
		new ObjectLoader("DATA").saveConfig(Main.config);
		Main.loadNum(Main.list);
	}
	
	boolean isFirst = true;
	long actionTime = 0;
	private void set_Wrapper() {
//		System.err.println("Wrapper!");
		if (actionTime == 0) {
			actionTime = System.currentTimeMillis();
		}
		if(isFirst){
			new Thread(this::setRep).start();
//			System.err.println("DEBUG!");
			isFirst = false;
		}
		if (actionTime + 100 <= System.currentTimeMillis()) {
			isFirst = true;
			actionTime = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		onDraw();
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index = Main.Current_jList.getSelectedIndex();
		if (Main.config.repeatable) {
			if (selectedList4Rep.isEmpty()) return;
			//截取字符串
			String cutStr = selectedList4Rep.get(index);
			Main.drawGUI.setText(cutStr.substring(cutStr.indexOf(":")+1) , true);

		}else {
			if (selectedList.isEmpty()) return;
			//截取字符串
			String cutStr = selectedList.get(index);
			Main.drawGUI.setText(cutStr.substring(cutStr.indexOf(":")+1) , true);
		}
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		if(settingsGUI != null){
			settingsGUI.dispose();
			Main.drawGUI.setAlwaysOnTop(true);
			settingsGUI = null;
		}
	}

	@Override
	public void windowLostFocus(WindowEvent e) { }

	public class ListTitleThread extends Thread{
		boolean running;
		String title;
		public ListTitleThread() {
			running = true;
			title = Main.drawGUI.getTitle();
		}

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException interruptedException) {
				System.out.println(interruptedException); }

			int i = 0;
			while (true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException interruptedException) {
					System.out.println(interruptedException); }
				StringBuilder buffer = new StringBuilder();
				for (int j = 0; j < Math.min(getCurrentList().size(), 6); j++) {
					if (i >= getCurrentList().size()) {
						i = 0;
					}
					buffer.append(String.format(" [%s]", getCurrentList().get(i)));
					i++;
				}
				if (getCurrentList().size() == 0){
					Main.drawGUI.setTitle(title);
					break; }
				if (!running)
					break;
				Main.drawGUI.setTitle((Main.config.repeatable ? "All:" : "Remain:") + buffer.toString());
			}
		}

		public Array getCurrentList(){
			return Main.config.repeatable ? Main.array_Rep:Main.array;
		}

		public void stopSelf(){
			running =  false;
			Main.drawGUI.setTitle(title);
			Main.event.listTitleThread = null;
		}
	}
}
