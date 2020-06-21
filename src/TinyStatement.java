
public class TinyStatement {
	String opcode, op1, op2;
	int operands;
	
	public TinyStatement(String opcode, String op1) {
		this.opcode = opcode;
		this.op1 = op1;
		operands = 2;
	}
	public TinyStatement(String opcode, String op1, String op2) {
		this(opcode, op1);
		this.op2 = op2;
		operands = 3;
	}
	public TinyStatement(String end) {
		opcode = end;
		operands = 1;
	}
	
	@Override
	public String toString() {
		if(operands==2) return opcode + " " + op1;
		else if(operands==3) return opcode + " " + op1 + " " + op2;
		else if(operands==1)return opcode;
		else return "error";
	}

}
