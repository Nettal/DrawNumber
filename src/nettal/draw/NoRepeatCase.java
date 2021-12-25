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
    public void loadLists(Config config, String[] strings, boolean loadUnusedList) {
        if (config.unDrawList != null && config.unDrawList.size() != 0 && loadUnusedList) {
            drawList = config.unDrawList;
        } else if (strings == null) {
            int size = config.maxValue - config.minValue + 1;
            String[] s = new String[size];
            for (int i = 0; i < size; i++) {
                s[i] = String.valueOf(i + 1);
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
                    loadLists(config, strings, false);
                    count = 0;
                }
            }
            return;
        }
        int index = (int) (Math.abs(drawList.size() * 100 * secureRandom.nextDouble()) + 1) % drawList.size();
        String text = drawList.get(index);
        drawList.remove(index);
        setText(text, TEXT_NORMAL);
        addJListData(text);
        drawGUI.jLabel.removeMouseListener(jLabelMouseListener);
    }

    @Override
    public void loadJLabel() {
        if (config.selectedList != null && config.selectedList.size() > 0) {
            ArrayList<String> selectedListSaved = new ArrayList<>(config.selectedList);
            ArrayList<String> unDrawListSaved = new ArrayList<>(config.unDrawList);
            drawGUI.jLabel.setText(config.loadUnusedList ? "<html>点此清空<br>已抽取</html>" : "<html>点此载入<br>已抽取</html>");
            jLabelMouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    config.selectedList = selectedListSaved;
                    config.unDrawList = unDrawListSaved;
                    loadLists(config, strings, !config.loadUnusedList);
                    drawGUI.jLabel.setText("完成");
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
        } else drawGUI.jLabel.setText("不重复");
    }

    @Override
    public void addListener() {
        super.addListener();
        drawGUI.jLabel.addMouseListener(jLabelMouseListener);
    }

    @Override
    public void removeListener() {
        super.removeListener();
        drawGUI.jLabel.removeMouseListener(jLabelMouseListener);
    }
}
