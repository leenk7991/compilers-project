
public class MyVisitor extends MicroBaseVisitor<Symbol>{
	
	SymbolTable currentTable = new SymbolTable();
	String currentType;
	int currentBlock = 0;
	SymbolTable globalTable;
	
	MyVisitor(SymbolTable globalTable){
		this.globalTable = globalTable;
		
	}
	
	@Override
	public Symbol visitProgram(MicroParser.ProgramContext ctx) { 
		String scopeName = ctx.id().getText();
		globalTable.scopeName=scopeName;
		SymbolTable programScope = new SymbolTable(scopeName);
		currentTable = globalTable;
		//System.out.println("<<Scope Global>>");
		return visitChildren(ctx); 
		}
	
	
	@Override
	public Symbol visitString_decl(MicroParser.String_declContext ctx) {
		Symbol str = new Symbol(ctx.id().getText(),ctx.typeKey.getText(),ctx.str().getText());
		currentTable.addSymbol(str);
		//System.out.println(str.toString());
		return visitChildren(ctx); 
		}
	
	@Override 
	public Symbol visitVar_decl(MicroParser.Var_declContext ctx) { 
		String type = ctx.var_type().getText();
		currentType = type;
		return visitChildren(ctx); 
		}
	
	@Override public Symbol visitIdList(MicroParser.IdListContext ctx) {
		Symbol s = new Symbol (ctx.id().getText(), currentType, currentTable.scopeName);
		//System.out.println(s.toString());
		currentTable.addSymbol(s);
		return visitChildren(ctx);
		}
	
	@Override public Symbol visitIdTail(MicroParser.IdTailContext ctx) { 
		Symbol s = new Symbol (ctx.id().getText(), currentType, currentTable.scopeName);
		//System.out.println(s.toString());
		currentTable.addSymbol(s);
		return visitChildren(ctx); 
		}
	
	@Override
	public Symbol visitFunc_decl(MicroParser.Func_declContext ctx) { 
		String scopeName = ctx.id().getText();
		SymbolTable funcScope = new SymbolTable(scopeName);
		currentTable = funcScope;
		//System.out.println();
		//System.out.println("<<Scope "+ scopeName+">>");
		return visitChildren(ctx); 
		}
	
	@Override
	public Symbol visitParam_decl(MicroParser.Param_declContext ctx) {
		Symbol s = new Symbol (ctx.id().getText(), ctx.var_type().getText(), currentTable.scopeName);
		//System.out.println(s.toString());
		currentTable.addSymbol(s);
		return visitChildren(ctx); 
		}
	
	@Override
	public Symbol visitIf_stmt(MicroParser.If_stmtContext ctx) { 
		currentBlock++;
		String scopeName = "BLOCK #"+currentBlock;
		SymbolTable ifScope = new SymbolTable(scopeName);
		currentTable = ifScope;
		//System.out.println();
		//System.out.println("<<Scope "+scopeName+">>");
		return visitChildren(ctx);
		}
	
	@Override 
	public Symbol visitEsleScope(MicroParser.EsleScopeContext ctx) { 
		currentBlock++;
		String scopeName = "BLOCK #"+currentBlock;
		SymbolTable elseScope = new SymbolTable(scopeName);
		currentTable = elseScope;
		//System.out.println();
		//System.out.println("<<Scope "+scopeName+">>");
		return visitChildren(ctx); 
		}
	
	@Override 
	public Symbol visitFor_stmt(MicroParser.For_stmtContext ctx) {
		currentBlock++;
		String scopeName = "BLOCK #"+currentBlock;
		SymbolTable forScope = new SymbolTable(scopeName);
		currentTable = forScope;
		//System.out.println();
		//System.out.println("<<Scope "+scopeName+">>");
		return visitChildren(ctx); 
		}
	
}
