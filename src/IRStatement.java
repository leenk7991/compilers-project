
public class IRStatement {
	
	String opcode;
	String op1,op2;
	String Result;
	String opType; //keep opType for future use
	private int operands = 0;
	public IRStatement(String opcode, String op1, String op2, String Result, String opType) {
		
		this.opcode=opcode;
		this.op1=op1;
		this.op2=op2;
		this.Result=Result;
		this.operands = 4;
		this.opType = opType;
	}
	
	public IRStatement(String opcode, String Result, String opType) {
		this.opcode=opcode;
		this.Result=Result;
		this.operands = 2;
		this.opType = opType;
	}
	
	public IRStatement(String opcode, String op1, String Result, String opType) {
		//STORE operations
		this.opcode=opcode;
		this.opType=opType; //move
		this.op1=op1;
		this.Result=Result;
		this.operands = 3;
		
	}
	
	@Override
	public String toString() {
		if(operands==2) return opcode + "\t" + Result;
		else if(operands==3) return opcode + "\t" + op1 + "\t" + Result;
		else if(operands==4)return opcode +"\t" + op1 + "\t" + op2 + "\t" + Result;
		else return "error";
	}
	
	public static IRStatement generateArithmetic(String idType, String opcode, String op1, String op2, String result) {
		//arithmetic
		if(idType.contentEquals("INT")) 
			return new IRStatement(opcode+"I", op1, op2, result, "arithmetic" );
		else
			return new IRStatement(opcode+"F", op1, op2, result, "arithmetic");
	}
	
	public static IRStatement generateBranch(String compare, String op1, String op2, String result) {
		//branch
		String opcode;
		switch(compare) {
		case "=":
			opcode = "NE";
			break;
		case "!=":
			opcode = "EQ";
			break;
		case "<=":
			opcode = "GT";
			break;
		case ">=":
			opcode = "LT";
			break;
		case "<":
			opcode = "GE";
			break;
		case ">":
			opcode = "LE";
			break;
		default: opcode = "unkown";
			
		}
		return new IRStatement(opcode, op1, op2, result, "branch");
		
	}
	public static IRStatement generateJump(String opcode, String result, String opType) {
		//jump and label
		if(opType.contentEquals("branch")) return new IRStatement(opcode, result, "branch");
		else
			return new IRStatement(opcode, result, "return"); //return
		
	}
	public static IRStatement generateRead(String idType, String result) {
		//read
		if(idType.contentEquals("INT")) 
			return new IRStatement("READI", result, "read" );
		else
			return new IRStatement("READF", result, "read");
		
	}
	public static IRStatement generateWrite(String idType, String result) {
		//read
		if(idType.contentEquals("INT")) 
			return new IRStatement("WRITEI", result, "write" );
		else if(idType.contentEquals("FLOAT"))
			return new IRStatement("WRITEF", result, "write");
		else return new IRStatement("WRITES", result, "write" );																			
		
	}
	public static IRStatement generateStore(String idType, String op1, String result) {
		//store
		if(idType.contentEquals("INT")) 
			return new IRStatement("STOREI", op1, result, "store" );
		else
			return new IRStatement("STOREF", op1, result, "store");
		
	}
	
	

}
