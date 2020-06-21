// Generated from Micro.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MicroParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MicroVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MicroParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MicroParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(MicroParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#pgm_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPgm_body(MicroParser.Pgm_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(MicroParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#string_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_decl(MicroParser.String_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(MicroParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(MicroParser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatType}
	 * labeled alternative in {@link MicroParser#var_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatType(MicroParser.FloatTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MicroParser#var_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(MicroParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varType}
	 * labeled alternative in {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(MicroParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidType(MicroParser.VoidTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idList}
	 * labeled alternative in {@link MicroParser#id_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(MicroParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noID}
	 * labeled alternative in {@link MicroParser#id_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoID(MicroParser.NoIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idTail}
	 * labeled alternative in {@link MicroParser#id_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdTail(MicroParser.IdTailContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#param_decl_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_decl_list(MicroParser.Param_decl_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#param_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_decl(MicroParser.Param_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#param_decl_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_decl_tail(MicroParser.Param_decl_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#func_declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_declarations(MicroParser.Func_declarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#func_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_decl(MicroParser.Func_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#func_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_body(MicroParser.Func_bodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noStatement}
	 * labeled alternative in {@link MicroParser#stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoStatement(MicroParser.NoStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementList}
	 * labeled alternative in {@link MicroParser#stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(MicroParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MicroParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#basic_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasic_stmt(MicroParser.Basic_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_stmt(MicroParser.Assign_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#assign_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_expr(MicroParser.Assign_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#read_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead_stmt(MicroParser.Read_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#write_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite_stmt(MicroParser.Write_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(MicroParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#id_list1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list1(MicroParser.Id_list1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code noId1}
	 * labeled alternative in {@link MicroParser#id_tail1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoId1(MicroParser.NoId1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code idtail1}
	 * labeled alternative in {@link MicroParser#id_tail1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdtail1(MicroParser.Idtail1Context ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(MicroParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noElse}
	 * labeled alternative in {@link MicroParser#else_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoElse(MicroParser.NoElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code esleScope}
	 * labeled alternative in {@link MicroParser#else_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEsleScope(MicroParser.EsleScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(MicroParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(MicroParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(MicroParser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#init_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit_expr(MicroParser.Init_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#incr_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncr_expr(MicroParser.Incr_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MicroParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPrfx}
	 * labeled alternative in {@link MicroParser#expr_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrfx(MicroParser.ExprPrfxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noExprPrfx}
	 * labeled alternative in {@link MicroParser#expr_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoExprPrfx(MicroParser.NoExprPrfxContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(MicroParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noFactorPrfx}
	 * labeled alternative in {@link MicroParser#factor_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoFactorPrfx(MicroParser.NoFactorPrfxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorPrfx}
	 * labeled alternative in {@link MicroParser#factor_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorPrfx(MicroParser.FactorPrfxContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(MicroParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(MicroParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryId}
	 * labeled alternative in {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryId(MicroParser.PrimaryIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryInt}
	 * labeled alternative in {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryInt(MicroParser.PrimaryIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryFloat}
	 * labeled alternative in {@link MicroParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryFloat(MicroParser.PrimaryFloatContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#call_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_expr(MicroParser.Call_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(MicroParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#expr_list_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_tail(MicroParser.Expr_list_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#addop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddop(MicroParser.AddopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroParser#mulop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulop(MicroParser.MulopContext ctx);
}