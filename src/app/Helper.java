package app;

public class Helper {
	
	public static boolean verifInt(int debut , int fin , char ascii){
		int codeAscii = (int) ascii;
		if(codeAscii >= debut && codeAscii <= fin){
			return true;
		}
		return false;
	}
}
