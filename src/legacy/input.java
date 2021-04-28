package legacy;
import java.util.Scanner;

public class input {
	static  long in() {
		try (Scanner sca = new Scanner(System.in)) {
			String str;
			str= sca.nextLine();
			switch (str) {
			case "a":
			System.out.println("关于");
			System.out.println("版本：0.2.2_preview");
			System.out.println("作者：37385@github.com");
			return 0;
			case "d":
			SaveData.Del();
			System.out.println("已删除配置文件!");
			return 0;
			case "/n":
			return 1;
			default:
			try {
				return Long.parseLong(str);
				} catch (Exception e) {
					System.out.println("帮助");
					System.out.println("输入一次性抽取人数\n 回车默认生成一人\n 按q退出，按c删除已设置的配置文件"); 
				return 0;
				}
			}
		}
	}
}
