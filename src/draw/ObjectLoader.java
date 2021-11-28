package draw;

import java.awt.*;
import java.io.*;

public class ObjectLoader {
	File file = null;

	public ObjectLoader(String path) {
		this.file = new File(path);
	}


	public Config getConfig() {
		while (true) {
			try {
				if (file.exists()) {
					Config config = null;
					config = this.loadConfig();
					if (config != null)
						return config;
				} else {
					System.out.println("ObjectLoader: Config does not exist,trying to create a new config at:" + file.getAbsolutePath());
					Config tempConfig = null;
					Main.isMessageOnTop = false;
					int[] value = Options.getRange();
					tempConfig = new Config();
					tempConfig.themeColor = Color.WHITE.getRGB();
					tempConfig.minValue = value[0];
					tempConfig.maxValue = value[1];
					tempConfig.repeatable = Options.repeatable();
					Main.isMessageOnTop = true;
					tempConfig.shape = new Rectangle(100, 100, 600, 450);
					saveConfig(tempConfig);
					return tempConfig;
				}
			} catch (Exception e) {
				return null;
			}
		}//end while
	}


	public Config loadConfig() {
		if (file.exists()) {
			String PATH = file.getAbsolutePath();
			FileInputStream fileInputStream = null;
			ObjectInputStream objectInputStream = null;
			System.out.println("ObjectLoader: Config PATH:" + PATH);
			try {
				System.out.println("ObjectLoader: Trying to load config at:" + PATH);
				Config loadConfig = null;
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				loadConfig = (Config) objectInputStream.readObject();
				objectInputStream.close();
				fileInputStream.close();
				System.out.println("ObjectLoader: Loaded config!");
				return loadConfig;
			} catch (Exception e) {
				System.err.println("ObjectLoader: Fail to load config at:" + PATH);
				try {
					fileInputStream.close();
					objectInputStream.close();
				} catch (IOException e1) {
					System.err.println("ObjectLoader: Fail to close stream");
					e1.printStackTrace();
				}
				System.out.println("ObjectLoader: Trying to delete at:" + PATH);
				try {
					file.delete();
				} catch (Exception e2) {
					System.err.println("ObjectLoader: Fail to delete:" + PATH);
					e2.printStackTrace();
				}
				return null;
			}
		} else {
			System.err.println("ObjectLoader: " + file.getAbsolutePath() + ": does not exist");
			return null;
		}
	}


	public boolean saveConfig(Config config_to_save) {
		String PATH = file.getAbsolutePath();
		try {
			if (file.exists()) {
				file.delete();
			}
			FileOutputStream fileOutputStream = null;
			ObjectOutputStream objectOutputStream = null;
			System.out.println("ObjectLoader: Saving config at:" + PATH);
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(config_to_save);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("ObjectLoader: Done!");
		} catch (Exception e) {
			System.err.println("ObjectLoader: Fail to save config at:" + PATH);
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
