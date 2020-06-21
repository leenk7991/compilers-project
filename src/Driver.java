// Students: Seif Abukhalaf 0151551 - Leen Kilani 0154493


import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Driver {
	public static void main(String[] args) throws Exception{
		// read input MICRO code
		InputStream is=null;
		try{
			String inputFile;
			inputFile = args[0];
			is = new FileInputStream(inputFile);
		}
		catch ( Exception e){
			System.out.println("You must specify an input file");
			System.exit(0);
		}
			ANTLRInputStream input = new ANTLRInputStream(is);
			MicroLexer lexer = new MicroLexer(input);
			CommonTokenStream cts = new CommonTokenStream(lexer);
			MicroParser parser = new MicroParser(cts);
			ParseTree tree = parser.program();
			//checkParser(parser);
			SymbolTable globalTable = new SymbolTable();
			IR globalIR = new IR();
			
			MyVisitor visitor = new MyVisitor(globalTable);
			visitor.visit(tree);
			//System.out.println("printing symbols: ");
			
			//globalTable.printSymbols();
			
			TempList tempList = new TempList();
			Step4Visitor visitor2 = new Step4Visitor(globalTable, globalIR, tempList);
			visitor2.visit(tree);
			
			//globalIR.printIR();
			
			//System.out.println("printing tiny: ");
			Tiny globalTiny = new Tiny(globalTable, globalIR, tempList);
			globalTiny.printTiny();
			//uncomment the following to print to file, change directory in method inside Tiny
			//globalTiny.printToFile(args[0].replace("micro", "out"));
	}
	/*static void checkParser(MicroParser parser){
		parser.program();
		if(parser.getNumberOfSyntaxErrors() == 0)
			System.out.println("Accepted");
		else
			System.err.println("NOT ACCEPTED!");
	}*/
}
