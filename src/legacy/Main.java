package legacy;
import java.util.*;

public class Main
{
 public static void main()
 { 
 System.out.println("版本：0.2.1_preview");
 Scanner scanner =new Scanner(System.in);
 java.security.SecureRandom RAND =new java.security.SecureRandom();
 boolean iswhile=true;
 int size=1;
 int RenShu=0;
 String line;
 if(SaveData.isexist()){
 size =SaveData.Load();
 System.out.println("已读取配置文件，最大值:"+size);
 }else{
 System.out.println("配置文件不存在,请输入最大值");
 size = scanner.nextInt();
 SaveData.Save(size);
 scanner.nextLine();
 }
 MyArray my =new MyArray(size);
 System.out.println("输入一次性抽取人数\n 回车默认生成一人\n 按q退出，按c删除已设置的配置文件"); 
 while(iswhile){

 line =scanner.nextLine();
 if(line.equals("")){
 RenShu=1;
 }else if(line.equals("q")){
 break;
 }else if(line.equals("c")){
 SaveData.Del();
 System.out.println("已删除配置文件!");
 break;
 }else {
 RenShu=Integer.parseInt(line);
 }

 
 for(;RenShu>0;RenShu--){
 int random = (int) (my.size()*RAND.nextDouble() );
 //System.out.println(my.get(random));
 LJXOutput.Output(((Integer)my.get(random)).intValue());
 my.remove(random);
 if(my.size()==0){
 System.out.println("已抽完所有人");
 iswhile=false;
 break;
 }
 
 };
 }
 scanner.close();
}
}