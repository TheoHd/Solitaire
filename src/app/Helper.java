package app;

public class Helper {
	
	public boolean verifInt(int debut , int fin , String entry){
		int integer = -1;
		try {
			integer = Integer.parseInt(entry);
			if(integer >= debut && integer <= fin){
				return true;
			}
			return false;
		} catch(NumberFormatException e) {
			return false;
		}
	}



}
