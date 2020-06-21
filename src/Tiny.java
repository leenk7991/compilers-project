import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Tiny {
	List<TinyStatement> tinyStmt = new ArrayList<TinyStatement>();
	SymbolTable symbols;
	IR ir;
	TempList tempList;
	String register1, register2, register;
	String result;
	static int r = 1;
	List<String> termList = new ArrayList<String>();
	public Tiny(SymbolTable t, IR ir, TempList temp) {
		symbols = t;
		this.ir = ir;
		tempList = temp;
		generateTiny();
	}
	public void printTiny() {
		for(int i=0; i<tinyStmt.size(); i++) {
			System.out.println(tinyStmt.get(i).toString());
		}
	}
	public void printToFile(String fileName) throws FileNotFoundException {
		PrintStream out = new PrintStream(new File("D:\\spring 2019\\Compilers\\project\\step5\\"+fileName));
    	System.setOut(out);
		for(int i=0; i<tinyStmt.size(); i++) {
			out.println(tinyStmt.get(i).toString());
		}
		out.close();
	}
	public void generateTiny() {
		declareVariables();
		for(IRStatement i:ir.IR_stmt) {
			switch(i.opType) {
			case "arithmetic":
				addArithmetic(i);
				break;
			case "branch":
				addBranch(i);
				break;
			case "write":
				addWrite(i);
				break;
			case "read":
				addRead(i);
				break;
			case "store":
				addStore(i);
				break;
			case "return":
				addTiny(new TinyStatement("sys", "halt"));
				break;
				default: 
					System.out.println("opType error");
			}
		}
		addTiny(new TinyStatement("end"));
	}
	
	public void addStore(IRStatement stmt) {
		if(stmt.op1.startsWith("$")) {
			addTiny(new TinyStatement("move", result, stmt.Result));
			termList.remove(result);
			return;
		}
		addTiny(new TinyStatement("move", stmt.op1, stmt.Result));
		
	}
	public void addRead(IRStatement stmt) {
		if(stmt.opcode.contentEquals("READI"))
			addTiny(new TinyStatement("sys readi", stmt.Result));
		else
			addTiny(new TinyStatement("sys readf", stmt.Result));
	}
	public void addArithmetic(IRStatement stmt) {
		String opc;
		switch(stmt.opcode) {
		case "ADDI":
			opc="addi";
			break;
		case "ADDF":
			opc="addr";
			break;
		case "SUBI":
			opc="subi";
			break;
		case "SUBF":
			opc="subr";
			break;
		case "MULTI":
			opc="muli";
			break;
		case "MULTF":
			opc="mulr";
			break;
		case "DIVI":
			opc="divi";
			break;
		case "DIVF":
			opc="divr";
			break;
			default: opc="opc error";
		}
		register1 = stmt.op1;
		register2 = stmt.op2;
		if(stmt.op2.startsWith("$")) {
			register2 = result;
		}
		mapReg(stmt.op1, stmt.op2);
		result = "r"+r++;
		addTiny(new TinyStatement("move", register1, result ));
		addTiny(new TinyStatement(opc, register2, result));
		termList.add(result);
	}
	
	public void addWrite(IRStatement stmt) {
		String opcode = stmt.opcode;
		if(opcode.contentEquals("WRITEI"))
			addTiny(new TinyStatement("sys writei", stmt.Result));
		else if(opcode.contentEquals("WRITEF"))
			addTiny(new TinyStatement("sys writer", stmt.Result));
		else
			addTiny(new TinyStatement("sys writes", stmt.Result));
	}
	public void addBranch(IRStatement stmt) {
		String type = "INT";
		if(stmt.opcode.contentEquals("JUMP")) {
			addTiny(new TinyStatement("jmp", stmt.Result));
			return;
		}
		if(stmt.opcode.contentEquals("LABEL")) {
			addTiny(new TinyStatement("label", stmt.Result)); 
			return;	
		}
		
		if(stmt.op1.startsWith("$")) 
			type = tempList.getType(stmt.op1);
		else
			type = symbols.getSymbol(stmt.op1).getType();
		
		register1 = stmt.op1;
		register2 = stmt.op2;
		
		mapReg(stmt.op1, stmt.op2);
		
		if(!(stmt.op2.startsWith("$"))){
			register2 = "r"+r++;
			addTiny(new TinyStatement("move", stmt.op2, register2));
		}
		if(type.contentEquals("FLOAT"))
			addTiny(new TinyStatement("cmpr", register1, register2));
		else 
			addTiny(new TinyStatement("cmpi", register1, register2));
		String opcode2;
		switch(stmt.opcode) {
		case "NE":
			opcode2 = "jne";
			break;
		case "EQ":
			opcode2 = "jeq";
			break;
		case "GT":
			opcode2 = "jgt";
			break;
		case "LT":
			opcode2 = "jlt";
			break;
		case "GE":
			opcode2 = "jge";
			break;
		case "LE":
			opcode2 = "jle";
			break;
			default: opcode2="opcode2 error";
		}
			
		addTiny(new TinyStatement(opcode2, stmt.Result));
		
	}
	public void declareVariables() {
		for(Symbol i:symbols.symbolTable) {
			if(i.getType().contentEquals("STRING"))
				addTiny(new TinyStatement("str",i.getName(),i.getValue())); //declare a string variable
			else
				addTiny(new TinyStatement("var", i.getName()));//declare a float or int variable;
		}
	}
	public void addTiny(TinyStatement stmt) {
		tinyStmt.add(stmt);
	}
	void mapReg(String t1, String t2) {
		if(t1.startsWith("$")) {
			String str1 = t1.replace("$t", "r");
			for(String i:termList) {
				if(str1.contentEquals(i)) {
					register1 = i;
					termList.remove(i);
					break;
				}
			}
		}
		if(t2.startsWith("$")) {
			String str1 = t2.replace("$t", "r");
			for(String i:termList) {
				if(str1.contentEquals(i)) {
					register2 = i;
					termList.remove(i);
					break;
				}
			}
		}
	}
	/*public void newReg() {
		//if(r<3)
			r++;
		//else 
			//r=0;
	}
	public int getLastReg() {0
		//if(r==0) return 3;
	//	else 
			return r-1;
	}*/

}
