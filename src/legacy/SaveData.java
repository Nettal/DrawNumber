package legacy;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveData {
	static DataOutputStream doStream;
	static DataInputStream DiStream;
	public static void Save(int RenShu) {
		try{
		doStream =new DataOutputStream(new FileOutputStream("DATA_old"));
		doStream.writeInt(RenShu);
		}catch(Exception e){
			System.out.println("Fail to save config");
		}finally{
					try {
						doStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}

	}
	public static boolean isexist(){
		File config=new File("DATA_old");
		return config.exists();
	}
	public  static void  Del() {
		File config=new File("DATA_old");
		config.delete();
		
	}
	public static int Load() {
		int RenShu = 0;
		try{
	   DiStream =new DataInputStream(new FileInputStream("DATA_old"));
	   RenShu=	DiStream.readInt();
		}catch(Exception e){
			System.out.println("Fail to load config");
		}finally{
		try {
			DiStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return RenShu;
	}
	
	}
