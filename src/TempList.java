import java.util.ArrayList;
import java.util.List;

public class TempList {
	List<Temporary> list = new ArrayList<Temporary>();
	public TempList() {
		
	}
	public void addT(Temporary t) {
		list.add(t);
	}
	public String getType(String temp) {
		  for(Temporary i:list) {
			  if(i.fullName.contentEquals(temp)) return i.type;
		  }
		  return "temp does not exist";
	 }

}
