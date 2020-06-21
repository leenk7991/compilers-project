
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Step4Visitor extends MicroBaseVisitor<String> {
	TempList tempList;
	SymbolTable globalTable;
	IR	globalIR;
	//List<String> currentList = new ArrayList<String>();
	
	String currentType;
	
	int t=0;
	int y=0;
	
	Temporary temp;
	
	String trueLabel, falseLabel;
	Stack<String> trueLabelStack = new Stack<String>();
	Stack<String> falseLabelStack = new Stack<String>();
	
	Step4Visitor (SymbolTable globalTable, IR globalIR, TempList t){
		tempList = t;
		this.globalTable = globalTable;
		this.globalIR = globalIR;
	}
	@Override 
	public String visitFunc_decl(MicroParser.Func_declContext ctx) { 
		IRStatement function_decl = new IRStatement("LABEL", ctx.id().getText(), "branch");
		globalIR.addStmt(function_decl);
		return visitChildren(ctx);
	}
	
	@Override 
	public String visitAssign_expr(MicroParser.Assign_exprContext ctx) { 
		
		String result = ctx.id().getText();
		currentType = globalTable.getSymbol(result).getType();
		String id = visit(ctx.expr());
		globalIR.addStmt(IRStatement.generateStore(currentType, id, result));
		String x = globalIR.getLastStatement(globalIR);
		
		return x; 
		
	}
	
	@Override 
	public String visitWrite_stmt(MicroParser.Write_stmtContext ctx) { 
		
		String ids = visit(ctx.id_list1());
		String[] idList = ids.split(",");
		for(int i=0; i<idList.length; i++) {
			if (idList[i]==null) break;
			String symbolType = globalTable.getSymbol(idList[i]).getType();
			globalIR.addStmt(IRStatement.generateWrite(symbolType, idList[i]));
		}
		return visitChildren(ctx); 
		
	}
	
	@Override 
	public String visitRead_stmt(MicroParser.Read_stmtContext ctx) { 
		
		String ids = visit(ctx.id_list1());
		String[] idList = ids.split(",");
		for(int i=0; i<idList.length; i++) {
			if (idList[i]==null) break;
			String symbolType = globalTable.getSymbol(idList[i]).getType();
			globalIR.addStmt(IRStatement.generateRead(symbolType, idList[i]));
		}
		return visitChildren(ctx); 
		
	}
	
	@Override public String visitId_list1(MicroParser.Id_list1Context ctx) { 
		
		return ctx.id().getText() + visit(ctx.id_tail1());
	
	}
	
	@Override
	public String visitNoId1(MicroParser.NoId1Context ctx) { 
		
		return "";
		
	}
	
	@Override 
	public String visitIdtail1(MicroParser.Idtail1Context ctx) {
	
		return "," + ctx.id().getText() + visit(ctx.id_tail1()); 
		
	}
	
	@Override
	public String visitId(MicroParser.IdContext ctx) { 
		return ctx.getText(); 
		
	}
	
	@Override
	public String visitExpr(MicroParser.ExprContext ctx) { 
		//System.out.println("number of time visitExpr called: " + ++t);
		String prefix = visit(ctx.expr_prefix());
		String expr = prefix + visit(ctx.term());
		
		//System.out.println("in visit expr: "+expr);
		//System.out.println("in visit expr: prefix is: "+prefix);
		if((prefix.contentEquals(""))) return expr;
		String op1, op2, result;
		String type = currentType;
		String[] ids = expr.split("\\-|\\+");
	    List<String> operands = new ArrayList<String>();
	    List<Character> addops = new ArrayList<Character>();
	    
	    for(int i=0;i<expr.length();i++) {
	    	if(expr.charAt(i)=='+' || expr.charAt(i)=='-')
	    		addops.add(expr.charAt(i));
	    }
	    //create a list of addops
	    
	    //create a list of operands  
	    for(String i:ids) 
	    	  operands.add(i);
	    
	    op1 = operands.get(0);
	    op2 = operands.get(1);
	    temp = new Temporary(type);
	    result = temp.fullName;
	    tempList.addT(temp);
	    //System.out.println("in visit expr, ops are: "+op1+" "+ op2);
	    //System.out.println("in visit expr, result is: "+ result);
	    if(addops.get(0)=='+') {
	    	globalIR.addStmt(IRStatement.generateArithmetic(type, "ADD", op1, op2, result));
	    	operands.remove(0); operands.remove(0); addops.remove(0);
	    }
	    	
	    else {
	    	globalIR.addStmt(IRStatement.generateArithmetic(type, "SUB", op1, op2, result));
	    	operands.remove(0); operands.remove(0); addops.remove(0);
	    }
	    
	    	
	    if(operands.size()==0) return result;
	    
	    for(int i=0; i<operands.size();i++) {
	    	op1 = result;
	    	op2 = operands.get(i);
	    	temp = new Temporary(type);
	    	result = temp.fullName;
	    	tempList.addT(temp);
	    	if(addops.get(0)=='+') {
		    	globalIR.addStmt(IRStatement.generateArithmetic(type, "ADD", op1, op2, result));
		    	addops.remove(0);
	    	}
		    	
		    else {
		    	globalIR.addStmt(IRStatement.generateArithmetic(type, "SUB", op1, op2, result));
		    	addops.remove(0);
		    }
		    	
	    }
	    return result; 
		
	}
	
	@Override 
	public String visitExprPrfx(MicroParser.ExprPrfxContext ctx) { 
		
		return visit(ctx.expr_prefix()) + visit(ctx.term()) + ctx.addop().getText(); 
		
	}
	
	@Override 
	public String visitNoExprPrfx(MicroParser.NoExprPrfxContext ctx) { 
		
		return ""; 
		
	}
	
	@Override
	public String visitTerm(MicroParser.TermContext ctx) {
		//System.out.println("number of time visitTerm called: " + ++y);
		String prefix = visit(ctx.factor_prefix());
		String termExpr = prefix + visit(ctx.factor());
		//System.out.println("in term :"+termExpr);
		//System.out.println("in term, prefix is:"+prefix);
		if((prefix.contentEquals(""))) return termExpr;
		String op1, op2, result;
		String type = currentType;
		String[] ids = termExpr.split("/|\\*");
	    List<String> operands = new ArrayList<String>();
	    List<Character> mulops = new ArrayList<Character>();
	    
	    //create a list of mulops
	    for(int i=0;i<termExpr.length();i++) {
	    	if(termExpr.charAt(i)=='*' || termExpr.charAt(i)=='/')
	    		mulops.add(termExpr.charAt(i));
	    }
	    //create a list of operands  
	    for(String i:ids) 
	    	operands.add(i);
	    
	    op1 = operands.get(0);
	    op2 = operands.get(1);
	   //System.out.println("in term op1  op2: "+op1 +" " + op2);
	    temp = new Temporary(type);
    	result = temp.fullName;
    	tempList.addT(temp);
	   //System.out.println("in term result: "+result);
	    if(mulops.get(0)=='*') {
	    	globalIR.addStmt(IRStatement.generateArithmetic(type, "MULT", op1, op2, result));
	    	//System.out.println(globalIR.getLastStatement(globalIR));
	    	operands.remove(0); operands.remove(0); mulops.remove(0);
	    }
	    	
	    else {
	    	globalIR.addStmt(IRStatement.generateArithmetic(type, "DIV", op1, op2, result));
	    	//System.out.println(globalIR.getLastStatement(globalIR));
	    	operands.remove(0); operands.remove(0); mulops.remove(0);
	    }
	    
	    	
	    if(operands.size()==0) return result;
	    //System.out.println("AFTER IF");
	    for(int i=0; i<operands.size();i++) {
	    	op1 = result;
	    	op2 = operands.get(i);
	    	temp = new Temporary(type);
	    	result = temp.fullName;
	    	tempList.addT(temp);
	    	if(mulops.get(0)=='*') {
		    	globalIR.addStmt(IRStatement.generateArithmetic(type, "MULT", op1, op2, result));
		    	//System.out.println(globalIR.getLastStatement(globalIR));
		    	mulops.remove(0);
	    	}
		    	
		    else {
		    	globalIR.addStmt(IRStatement.generateArithmetic(type, "DIV", op1, op2, result));
		    	//System.out.println(globalIR.getLastStatement(globalIR));
		    	mulops.remove(0);
		    }
		    	
	    }
	    return result;
		
	}
	
	@Override
	public String visitNoFactorPrfx(MicroParser.NoFactorPrfxContext ctx) { 
		
		return "";
		
	}
	
	@Override 
	public String visitFactorPrfx(MicroParser.FactorPrfxContext ctx) {
		
		return visit(ctx.factor_prefix()) + visit(ctx.factor()) + ctx.mulop().getText(); 
		
	}
	
	@Override 
	public String visitFactor(MicroParser.FactorContext ctx) {
		
		return visit(ctx.primary()); 
		
	}
	
	@Override 
	public String visitPrimaryId(MicroParser.PrimaryIdContext ctx) { 
		
		return ctx.id().getText();
		
	}
	
	@Override 
	public String visitPrimaryInt(MicroParser.PrimaryIntContext ctx) {
		
		return ctx.INTLITERAL().getText(); 
		
	}
	
	@Override
	public String visitPrimaryFloat(MicroParser.PrimaryFloatContext ctx) {
		
		return ctx.FLOATLITERAL().getText(); 
		
	}
	@Override
	public String visitPrimaryExpr(MicroParser.PrimaryExprContext ctx) {
		
		return visit(ctx.expr()); 
		}
	
	@Override
	public String visitFor_stmt(MicroParser.For_stmtContext ctx) {
		visit(ctx.init_expr());
		String L = new Label().fullName;
		trueLabel = L;
		trueLabelStack.push(trueLabel);
		globalIR.addStmt(IRStatement.generateJump("LABEL", L, "branch"));
		visit(ctx.cond());
		visit(ctx.stmt_list());
		visit(ctx.incr_expr());
		globalIR.addStmt(IRStatement.generateJump("JUMP", trueLabelStack.pop(), "branch"));
		globalIR.addStmt(IRStatement.generateJump("LABEL", falseLabelStack.pop(), "branch"));
		return "";
		
	}
	
	@Override
	public String visitCond(MicroParser.CondContext ctx) { 
		String expr1 = visit(ctx.e1);
		String expr2 = visit(ctx.e2);
		String compare = ctx.compare().getText();
		String L = new Label().fullName;
		falseLabel = L;
		falseLabelStack.push(falseLabel);
		globalIR.addStmt(IRStatement.generateBranch(compare, expr1, expr2, L));
		return ""; 
		
	}
	
	@Override 
	public String visitNoStatement(MicroParser.NoStatementContext ctx) {

		return ""; 
		
	}
	
	@Override 
	public String visitStatementList(MicroParser.StatementListContext ctx) { 
		
		return visit(ctx.stmt())+visit(ctx.stmt_list()); 
		
	}
	
	@Override
	public String visitIf_stmt(MicroParser.If_stmtContext ctx) { 
		
		visit(ctx.cond());
		visit(ctx.stmt_list());		
		visit(ctx.else_part());
		
		return ""; 
		
	}
	@Override 
	public String visitNoElse(MicroParser.NoElseContext ctx) {
		globalIR.addStmt(IRStatement.generateJump("LABEL", falseLabelStack.pop(), "branch"));
		return visitChildren(ctx); 
		
	}
	
	@Override
	public String visitEsleScope(MicroParser.EsleScopeContext ctx) { 
		
		String L2 = new Label().fullName;
		
		falseLabelStack.push(L2);
		globalIR.addStmt(IRStatement.generateJump("JUMP", falseLabelStack.pop(), "branch"));
		globalIR.addStmt(IRStatement.generateJump("LABEL", falseLabelStack.pop(), "branch"));
		visit(ctx.stmt_list());
		globalIR.addStmt(IRStatement.generateJump("LABEL", L2, "branch"));
		return ""; 
		
	}



}

	