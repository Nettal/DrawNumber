package draw;

import java.util.ArrayList;
import java.util.Arrays;

public class Array extends ArrayList<String> {
    /**
     *
     */
    java.security.SecureRandom rand = new java.security.SecureRandom();
    private static final long serialVersionUID = 1L;
    private int minValue;
    private final boolean isStr;
//	private int maxValue;

    //整数
    public Array(int minValue, int maxValue) {
        super(2 * (maxValue - minValue));
        this.isStr = false;
//		this.maxValue = maxValue;
        this.minValue = minValue;
        int size = maxValue - minValue + 1;
        System.out.println("Array: The size of array to load is:" + size);
        for (int i = 1; i <= size; i++) {
            this.add(String.valueOf(i));
            //	System.err.println(i);
        }
        System.out.println("Array: Load array done!");
        try {
            get((int) (this.size() * rand.nextDouble()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //字符串
    public Array(String[] s) {
        super(s.length);
        this.isStr = true;
        this.addAll(Arrays.asList(s));
        try {
            get((int) (this.size() * rand.nextDouble()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //字符串
    private String getStr(int index) {
        try {
            String ret = get(index);
            this.remove(index);
            return ret;
        } catch (Exception e) {
            System.out.println("Array: " + e.toString());
            return null;
        }
    }

    //字符串
    private String getStr_Rep(int index) {
        changeLocation(index, index);
        try {
            return this.get(index);
        } catch (Exception e) {
            System.out.println("Array: " + e.toString());
            return null;
        }
    }


    //整数
    private String getInt(int index) {
        try {
            int temp = Integer.parseInt(this.get(index));
            this.remove(index);
            return String.valueOf(temp + minValue - 1);
        } catch (Exception e) {
            System.out.println("Array: " + e.toString());
            return null;
        }
    }

    //整数
    private String getInt_Rep(int index) {
        changeLocation(index, index);
        try {
            int temp = Integer.parseInt(this.get(index));
            return String.valueOf(temp + minValue - 1);
        } catch (Exception e) {
            System.out.println("Array: " + e.toString());
            return null;
        }
    }


    public String getStr_without_index() {
        if (isStr)
            return getStr((int) (this.size() * rand.nextDouble()));
        else
            return getInt((int) (this.size() * rand.nextDouble()));
    }

    public String getStr_without_index_Rep() {
        if (isStr)
            return getStr_Rep((int) (this.size() * rand.nextDouble()));
        else
            return getInt_Rep((int) (this.size() * rand.nextDouble()));
    }

    private void swapLocation(int a, int b) {
        String temp = this.get(a);
        this.set(a, this.get(b));
        this.set(b, temp);
    }

    public void blendList() {
        int len = this.size();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * len);
            swapLocation(i, index);
        }
    }

    private void changeLocation(int index, int times) {
        int src = index;
        int dest;
        for (int i = 0; i < times; i++) {
            dest = (int) (this.size() * Math.random());
            swapLocation(src, dest);
            src = dest;
        }
    }
}	