package nettal.draw;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ObjectLoader {
    public static void deleteFile(String destination) {
        deleteFile(new File(destination));
    }

    private static void deleteFile(File file) {
        System.out.println(file.delete()
                ? "ObjectLoader: Success to delete:" + file.getAbsolutePath()
                : "ObjectLoader: Fail to delete:" + file.getAbsolutePath());
    }

    public static Config getConfig(String destination) {
        File file = new File(destination);
        Config config = loadConfig(file);
        if (config == null) {
            System.out.println("ObjectLoader: Config does not exist,trying to create a new config at:" + file.getAbsolutePath());
            int[] value = Options.getRange();
            config = new Config();
            config.minValue = value[0];
            config.maxValue = value[1];
            config.repeatable = Options.repeatable();
            config.loadUnusedList = true;
            config.unDrawList = new ArrayList<>();
            config.selectedList = new ArrayList<>();
            config.rectangle = new Rectangle(100, 100, 600, 450);
            config.color = Color.WHITE;
            saveConfig(config, file);
        }
        System.out.println("ObjectLoader: Config content:\n" + config);
        return config;
    }

    private static Config loadConfig(File file) {
        if (!file.exists()) {
            System.err.println("ObjectLoader: " + file.getAbsolutePath() + ": does not exist");
            return null;
        }
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Config) objectInputStream.readObject();
        } catch (Exception e) {
            System.err.println("ObjectLoader: Fail to load config at:" + file.getAbsolutePath());
            e.printStackTrace();
            System.out.println(file.delete()
                    ? "ObjectLoader: Success to delete:" + file.getAbsolutePath()
                    : "ObjectLoader: Fail to delete:" + file.getAbsolutePath());
            return null;
        }
    }

    public static void saveConfig(Config config, String destination) {
        saveConfig(config, new File(destination));
    }

    private static void saveConfig(Config config, File file) {
        deleteFile(file);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(config);
            objectOutputStream.flush();
            System.out.println("ObjectLoader: Saved config at:" + file.getAbsolutePath());
            System.out.println("ObjectLoader: Config content:\n" + config);
        } catch (Exception e) {
            System.err.println("ObjectLoader: Fail to save config at:" + file.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
