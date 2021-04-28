package legacy;
public class LJXOutput
{
	static int Out;


	static void Output(int number){
		System.out.println(number);
		for(int i = 0;i<7;i++){
			for(int XH=0;XH<Getlength(number);XH++){
				System.out.print("      ");
				switch(Getlength(number)){
					case 1:Out=number;
					    break;
					case 2:
						if (XH==0){Out=number/10;}
						if (XH==1){Out=number%10;}
						break;
					case 3:
						if(XH==0){Out=number/100;}
						if(XH==1){Out=(number%100)/10;}
						if(XH==2){Out=number%10;}
						break;
					case 4:
						if(XH==0){Out=number/1000;}
						if(XH==1){Out=(number%1000)/100;}
						if(XH==2){Out=(number%100)/10;}
						if(XH==3){Out=number%10;}
						break;
					case 5:
						if(XH==0){Out=number/10000;}
						if(XH==1){Out=(number%10000)/1000;}
						if(XH==2){Out=(number%1000)/100;}
						if(XH==3){Out=(number%100)/10;}
						if(XH==4){Out=number%10;}
						break;
					case 6:
						if(XH==0){Out=number/100000;}
						if(XH==1){Out=(number%100000)/10000;}
						if(XH==2){Out=(number%10000)/1000;}
						if(XH==3){Out=(number%1000)/100;}
						if(XH==4){Out=(number%100)/10;}
						if(XH==5){Out=number%10;}
						break;
					case 7:
						if(XH==0){Out=number/1000000;}
						if(XH==1){Out=(number%1000000)/100000;}
						if(XH==2){Out=(number%100000)/10000;}
						if(XH==3){Out=(number%10000)/1000;}
						if(XH==4){Out=(number%1000)/100;}
						if(XH==5){Out=(number%100)/10;}
						if(XH==6){Out=number%10;}
						break;
					case 8:
						if(XH==0){Out=number/10000000;}
						if(XH==1){Out=(number%10000000)/1000000;}
						if(XH==2){Out=(number%1000000)/100000;}
						if(XH==3){Out=(number%100000)/10000;}
						if(XH==4){Out=(number%10000)/1000;}
						if(XH==5){Out=(number%1000)/100;}
						if(XH==6){Out=(number%100)/10;}
						if(XH==7){Out=number%10;}
						break;
					case 9:
						if(XH==0){Out=number/100000000;}
						if(XH==1){Out=(number%100000000)/10000000;}
						if(XH==2){Out=(number%10000000)/1000000;}
						if(XH==3){Out=(number%1000000)/100000;}
						if(XH==4){Out=(number%100000)/10000;}
						if(XH==5){Out=(number%10000)/1000;}
						if(XH==6){Out=(number%1000)/100;}
						if(XH==7){Out=(number%100)/10;}
						if(XH==8){Out=number%10;}
						break;
					case 10:
						if(XH==0){Out=number/1000000000;}
						if(XH==1){Out=(number%1000000000)/100000000;}
						if(XH==2){Out=(number%100000000)/10000000;}
						if(XH==3){Out=(number%10000000)/1000000;}
						if(XH==4){Out=(number%1000000)/100000;}
						if(XH==5){Out=(number%100000)/10000;}
						if(XH==6){Out=(number%10000)/1000;}
						if(XH==7){Out=(number%1000)/100;}
						if(XH==8){Out=(number%100)/10;}
						if(XH==9){Out=number%10;}
						break;
				}
				switch(Out){
					case 0:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("      ");}else
						if(i==2){System.out.print("      ");}else
						if(i==3){System.out.print("      ");}else
						if(i==4){System.out.print("      ");}else
						if(i==5){System.out.print("      ");}else
						if(i==6){System.out.print("");}
						break;
					case 1:
						if(i==0){System.out.print("     ");}else
						if(i==1){System.out.print("       ");}else
						if(i==2){System.out.print("       ");}else
						if(i==3){System.out.print("       ");}else
						if(i==4){System.out.print("       ");}else
						if(i==5){System.out.print("       ");}else
						if(i==6){System.out.print(" ");}
						break;
					case 2:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("        ");}else
						if(i==2){System.out.print("        ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("        ");}else
						if(i==5){System.out.print("        ");}else
						if(i==6){System.out.print("");}
						break;
					case 3:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("        ");}else
						if(i==2){System.out.print("        ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("        ");}else
						if(i==5){System.out.print("        ");}else
						if(i==6){System.out.print("");}
						break;
					case 4:
						if(i==0){System.out.print("      ");}else
						if(i==1){System.out.print("      ");}else
						if(i==2){System.out.print("      ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("        ");}else
						if(i==5){System.out.print("        ");}else
						if(i==6){System.out.print("        ");}
						break;
					case 5:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("        ");}else
						if(i==2){System.out.print("        ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("        ");}else
						if(i==5){System.out.print("        ");}else
						if(i==6){System.out.print("");}
						break;
					case 6:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("        ");}else
						if(i==2){System.out.print("        ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("      ");}else
						if(i==5){System.out.print("      ");}else
						if(i==6){System.out.print("");}
						break;
					case 7:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("        ");}else
						if(i==2){System.out.print("        ");}else
						if(i==3){System.out.print("        ");}else
						if(i==4){System.out.print("        ");}else
						if(i==5){System.out.print("        ");}else
						if(i==6){System.out.print("        ");}
						break;
					case 8:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("      ");}else
						if(i==2){System.out.print("      ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("      ");}else
						if(i==5){System.out.print("      ");}else
						if(i==6){System.out.print("");}
						break;
					case 9:
						if(i==0){System.out.print("");}else
						if(i==1){System.out.print("      ");}else
						if(i==2){System.out.print("      ");}else
						if(i==3){System.out.print("");}else
						if(i==4){System.out.print("        ");}else
						if(i==5){System.out.print("        ");}else
						if(i==6){System.out.print("");}
						break;
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	static int Getlength (int num){
		int count=0;
		while(num>=1) {
			num/=10;
			count++;
		}
		return count;
	}
}
