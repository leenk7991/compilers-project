import java.util.ArrayList;
import java.util.List;

public class IR {
	List<IRStatement> IR_stmt = new ArrayList<IRStatement>();
	
	public IR() {}
	
	public IR(IRStatement statement) {
		IR_stmt.add(statement);
	}
	
	public void addStmt(IRStatement stmt) {
		IR_stmt.add(stmt);
	}
	
	public void printIR() {
		for(int i=0; i<IR_stmt.size(); i++) {
			System.out.println(IR_stmt.get(i).toString());
		}
	}
	//used for debugging
	public String getLastStatement(IR ir) {
		String x = ir.IR_stmt.get(IR_stmt.size()-1).toString();
		return x;
	}

}
