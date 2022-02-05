package nettal.draw;

import java.util.ArrayList;
import java.util.List;

public class RepeatCase extends AbstractCase {//config里存贮的list这里不会用，这里的两个list与其他的不共享

    public RepeatCase(DrawGUI drawGUI, Config config, String[] strings) {
        super(drawGUI, config, strings);
    }

    @Override
    public void loadLists(boolean loadUnusedList) {
        if (strings == null) {
            int size = config.maxValue - config.minValue + 1;
            String[] s = new String[size];
            for (int i = 0; i < size; i++) {
                s[i] = String.valueOf(config.minValue + i);
            }
            drawList = new ArrayList<>(List.of(s));
        } else {
            drawList = new ArrayList<>(List.of(strings));
        }
        selectedList = strings == null
                ? new ArrayList<>(2 * (config.maxValue - config.minValue + 1))
                : new ArrayList<>(2 * strings.length);
        setJListData(selectedList);
    }

    @Override
    public void onDraw() {
        String text = drawList.get(secureRandom.nextInt(0, drawList.size()));
        setText(text, TEXT_NORMAL);
        addJListData(text);
    }

    @Override
    public void loadJLabel() {
        setText("重复", TEXT_NORMAL);
    }
}
