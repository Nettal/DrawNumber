package draw;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectLoader {
	File file = null;

	public ObjectLoader(String path) {
		this.file = new File(path);
	}
	
	
	public  Config getConfig() {
		boolean isLoadSuccess = false;
		while (!isLoadSuccess) {
		try {
		if (file.exists()) {
			Config config = null;
			config = this.loadConfig();
			if (config == null) {
				isLoadSuccess = false;
			}else {
				isLoadSuccess = true;
				return config;
			}
			
		} else {
			System.out.println("Config does not exist,trying to create a new config at:"+file.getAbsolutePath());
			Config tempConfig = null;
			int[] value = Options.getRange();
			tempConfig = new Config();
			tempConfig.minValue = value[0];
			tempConfig.maxValue = value[1];
			tempConfig.repeatable = Options.repeatable();
			tempConfig.shape = new Rectangle(100, 100, 600, 450);
			saveConfig(tempConfig);
			isLoadSuccess = true;
			return tempConfig;
	}}catch (Exception e) {
		isLoadSuccess = false;
		}
	}//end while
		return null;
}
	

	
	
	
	
	public  Config loadConfig() {
		if (file.exists()) {
		String PATH = file.getAbsolutePath();
	    FileInputStream fileInputStream = null;
	    ObjectInputStream objectInputStream = null;
		System.out.println("Config PATH:"+PATH);
		try {
		System.out.println("Trying to load config at:"+PATH);
		Config loadConfig = null;
		fileInputStream = new FileInputStream(file);
		objectInputStream = new ObjectInputStream(fileInputStream);
		loadConfig = (Config)objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		System.out.println("Loaded config!");
		return loadConfig;
		} catch (Exception e) {
			System.err.println("Fail to load config at:"+PATH);
			try {
				fileInputStream.close();
				objectInputStream.close();
			} catch (IOException e1) {
				System.err.println("Fail to close stream");
				e1.printStackTrace();
			}
			System.out.println("Trying to delete at:"+PATH);
				try {
					file.delete();
					} catch (Exception e2) {
					System.err.println("Fail to delete:"+PATH);
					e2.printStackTrace();
				}
			return null;
		}
		}else {
			System.err.println(file.getAbsolutePath()+": does not exist");
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
			System.out.println("Saving config at:"+PATH);
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(config_to_save);
			objectOutputStream.flush();
			objectOutputStream.close();
			fileOutputStream.close();
			System.out.println("Done!");
		} catch (Exception e) {
			System.err.println("Fail to save config at:"+PATH);
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
