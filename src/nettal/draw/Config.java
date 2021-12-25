package nettal.draw;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Config implements Serializable {

    @Serial
    private static final long serialVersionUID = -8486594206615491973L;

    public Config() {

    }

    public int minValue = 0;
    public int maxValue = 0;
    public boolean repeatable = false;
    public boolean loadUnusedList = true;
    ArrayList<String> unDrawList = null;
    ArrayList<String> selectedList = null;//选过的
    public Rectangle rectangle = null;
    public Color color = null;

    @Override
    public String toString() {
        return "Config{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", repeatable=" + repeatable +
                ", loadUnusedList=" + loadUnusedList +
                ", unDrawList=" + unDrawList +
                ", selectedList=" + selectedList +
                ", rectangle=" + rectangle +
                ", color=" + color +
                '}';
    }
}
