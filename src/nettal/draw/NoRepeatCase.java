package nettal.draw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class NoRepeatCase extends AbstractCase {
    MouseListener jLabelMouseListener;

    public NoRepeatCase(DrawGUI drawGUI, Config config, String[] lists) {
        super(drawGUI, config, lists);
    }

    @Override
    public void loadLists(boolean loadUnusedList) {
        if (config.unDrawList != null && config.unDrawList.size() != 0 && loadUnusedList) {
            drawList = config.unDrawList;
        } else if (strings == null) {
            int size = config.maxValue - config.minValue + 1;
            String[] s = new String[size];
            for (int i = 0; i < size; i++) {
                s[i] = String.valueOf(config.minValue + i);
            }
            drawList = new ArrayList<>(List.of(s));
            config.unDrawList = drawList;
        } else {
            drawList = new ArrayList<>(List.of(strings));
            config.unDrawList = drawList;
        }
        if (config.selectedList != null && config.selectedList.size() != 0 && loadUnusedList) {
            selectedList = config.selectedList;
        } else {
            selectedList = strings == null
                    ? new ArrayList<>(2 * (config.maxValue - config.minValue + 1))
                    : new ArrayList<>(2 * strings.length);
            config.selectedList = selectedList;
        }
        setJListData(selectedList);
    }

    int count;

    @Override
    public void onDraw() {
        if (drawList.size() == 0) {
            switch (count++) {
                case 0 -> setText("", TEXT_FINISHED);
                case 1 -> {
                    setText("", TEXT_AGAIN);
                    cleanLists();
                    loadLists(false);
                    count = 0;
                }
            }
            return;
        }
        int index = secureRandom.nextInt(0, drawList.size());
        String text = drawList.get(index);
        drawList.remove(index);
        setText(text, TEXT_NORMAL);
        addJListData(text);
        drawGUI.jLabel.removeMouseListener(jLabelMouseListener);
    }

    @Override
    public void beforeClosing() {//ignore config.loadUnusedList
        for (MouseListener mouseListener : drawGUI.jLabel.getMouseListeners()) {
            if (mouseListener.equals(jLabelMouseListener)) {
                selectedList = config.selectedList = selectedListSaved;
                drawList = config.unDrawList = unDrawListSaved;
                break;
            }
        }
        super.beforeClosing();
    }

    @Override
    public void onSwitch() {
        removeListener();//防止重复添加
        loadJLabel();
        addListener();
        if ((config.selectedList != null && config.selectedList.size() > 0) && !config.loadUnusedList) {
            loadLists(false);//点此加载已抽取，使得右侧列表为空
        } else setJListData(selectedList);
    }

    ArrayList<String> selectedListSaved;
    ArrayList<String> unDrawListSaved;

    @Override
    public void loadJLabel() {
        if (config.selectedList != null && config.selectedList.size() > 0) {
            selectedListSaved = new ArrayList<>(config.selectedList);
            unDrawListSaved = new ArrayList<>(config.unDrawList);
            drawGUI.jLabel.setText(
                    "<html>\n" +
                            "<head>\n" +
                            "<style type=\"text/css\">\n" +
                            "p {font-size: 50%}\n" +
                            "</style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<p style=\"color:" +
                            Utilities.color2Str(Utilities.getForegroundColor(config.color)) +
                            (config.loadUnusedList ? "\">点此清空已抽取</p>\n" : "\">点此加载已抽取</p>\n") +
                            "</body>\n" +
                            " </html>");
            jLabelMouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedList = config.selectedList = selectedListSaved;
                    drawList = config.unDrawList = unDrawListSaved;
                    loadLists(!config.loadUnusedList);
                    setText("完成", TEXT_NORMAL);
                    drawGUI.jLabel.removeMouseListener(this);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            };
        } else setText("不重复", TEXT_NORMAL);
    }

    @Override
    public void addListener() {
        super.addListener();
        if (config.selectedList != null && config.selectedList.size() > 0)
            drawGUI.jLabel.addMouseListener(jLabelMouseListener);//loadJLabel 的非else情况
    }

    @Override
    public void removeListener() {
        super.removeListener();
        drawGUI.jLabel.removeMouseListener(jLabelMouseListener);
    }
}
