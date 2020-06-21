

public class Temporary {

	 static String name = "$t";
	 static int num = 1;
	 public String fullName = name+num;
	 public String type;
	 
	 public Temporary(String type) {
		 this.type = type;
		 num++;
	 }
	 
}
