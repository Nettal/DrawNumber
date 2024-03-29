package nettal.draw;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public abstract class AbstractCase implements KeyListener, WindowListener, WindowStateListener {
    public static final int TEXT_NORMAL = 0;
    public static final int TEXT_FINISHED = 1;
    public static final int TEXT_AGAIN = 2;
    public static final int TEXT_SELECTED = 5;

    final DrawGUI drawGUI;
    final Config config;
    final String[] strings;
    ArrayList<String> drawList;//将要抽取的
    ArrayList<String> selectedList;//已经选取的
    private final ActionListener settingListener;
    private final ActionListener drawListener;
    private final ListSelectionListener listSelectionListener;
    final java.security.SecureRandom secureRandom = new java.security.SecureRandom();

    public AbstractCase(DrawGUI drawGUI, Config config, String[] strings) {
        this.drawGUI = drawGUI;
        this.config = config;
        this.strings = strings;
        drawListener = e -> onDraw();
        settingListener = e -> SwingUtilities.invokeLater(() -> new SettingsGUI(config, this));
        listSelectionListener = e -> {
            if (!e.getValueIsAdjusting()) {//某些情况会导致下面为null
                return;
            }
            String string = drawGUI.jList.getSelectedValue();
            setText(string.substring(string.indexOf(":") + 1), TEXT_SELECTED);
        };
        drawGUI.setTitle(strings == null
                ? String.format("抽号:[%d,%d] " + Main.version, config.minValue, config.maxValue)
                : String.format("抽号:列表模式,共%d个 " + Main.version, strings.length));
        loadJLabel();//must load before lists load
        addListener();
        loadLists(config.loadUnusedList);
        drawGUI.setVisible(true);
    }

    public abstract void loadLists(boolean loadUnusedList);

    public abstract void onDraw();

    public abstract void loadJLabel();

    public void beforeClosing() {
        removeListener();
    }

    public void onSwitch() {
        removeListener();//防止重复添加
        loadJLabel();
        addListener();
        setJListData(selectedList);
    }

    public void setText(String text, int status) {
        drawGUI.jLabel.setText(
                switch (status) {
                    case TEXT_FINISHED -> "已抽完!";
                    case TEXT_AGAIN -> "新一轮!";
                    default -> text;
                });
        drawGUI.jLabel.setFont(
                switch (status) {
                    case TEXT_AGAIN, TEXT_SELECTED, TEXT_FINISHED -> new Font(null, Font.ITALIC, (int) (Math.min(drawGUI.jLabel.getWidth(), drawGUI.jLabel.getHeight()) * .4));
                    default -> new Font(null, Font.BOLD, (int) (Math.min(drawGUI.jLabel.getWidth(), drawGUI.jLabel.getHeight()) * .4));
                });
    }

    public void setJListData(ArrayList<String> arrayList) {
        String[] strings = arrayList.toArray(new String[0]);
        for (int i = 0, mid = strings.length >> 1, j = strings.length - 1; i < mid; i++, j--) {
            String tmp = strings[i];
            strings[i] = strings[j];
            strings[j] = tmp;
        }
        drawGUI.jList.setListData(strings);
    }

    public void addJListData(String string) {
        selectedList.add(selectedList.size() + 1 + ":" + string);
        setJListData(selectedList);
    }

    public void cleanLists() {
        drawList.clear();
        selectedList.clear();
        drawGUI.jList.setListData(new String[0]);
    }

    public static void switchCase(AbstractCase from, AbstractCase to) {
        from.beforeClosing();
        to.onSwitch();
    }

    public void addListener() {
        drawGUI.addKeyListener(this);
        drawGUI.addWindowListener(this);
        drawGUI.jList.addKeyListener(this);
        drawGUI.jLabel.addKeyListener(this);
        drawGUI.addWindowStateListener(this);
        drawGUI.drawButton.addKeyListener(this);
        drawGUI.jScrollPane.addKeyListener(this);
        drawGUI.drawButton.addActionListener(drawListener);
        drawGUI.settingButton.addActionListener(settingListener);
        drawGUI.jList.addListSelectionListener(listSelectionListener);
    }

    public void removeListener() {//null is available
        drawGUI.removeKeyListener(this);
        drawGUI.removeWindowListener(this);
        drawGUI.jList.removeKeyListener(this);
        drawGUI.jLabel.removeKeyListener(this);
        drawGUI.removeWindowStateListener(this);
        drawGUI.drawButton.removeKeyListener(this);
        drawGUI.jScrollPane.removeKeyListener(this);
        drawGUI.drawButton.removeActionListener(drawListener);
        drawGUI.settingButton.removeActionListener(settingListener);
        drawGUI.jList.removeListSelectionListener(listSelectionListener);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        onDraw();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    int windowState = 0;

    @Override
    public void windowClosing(WindowEvent e) {
        beforeClosing();
        if (!(windowState == 6)) {
            config.rectangle = drawGUI.getBounds();
        }
        ObjectLoader.saveConfig(config, Main.dataPath);
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
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        windowState = e.getNewState();
    }
}
