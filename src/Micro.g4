// Define a grammar called Micro
grammar Micro;

// Lexer Rules
KEYWORD		: 'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'RETURN' | 'READ' | 'WRITE' | 'IF' | 'ELSE' | 'ENDIF' | 'FOR' | 'ENDFOR' | 'INT' | 'VOID' | 'STRING' | 'FLOAT';

OPERATOR	: ':=' | '+' | '-' | '*' | '/' | '=' | '!=' | '<' | '>' | '<=' | '>=' | ';' | ',' | '(' | ')';

IDENTIFIER	: (('A'..'Z')|('a'..'z'))(('A'..'Z')|('a'..'z')|('0'..'9'))*;

INTLITERAL	: ('0'|('1'..'9')('0'..'9')*);

FLOATLITERAL : ( |'0'|('1'..'9')('0'..'9')*)'.'(('0'..'9')*('1'..'9')|'0');

STRINGLITERAL : '"'.*?'"';

COMMENT		: '--'(.)*?('\r'|'\n')		-> skip;

WS : (' '|'\t'|'\r'|'\n')+ -> skip; 



// Parser Rules		
program 	: 'PROGRAM' id 'BEGIN' pgm_body 'END'	; 
id 			: IDENTIFIER;
pgm_body	: decl func_declarations;
decl 		: 
			| string_decl decl
			| var_decl decl;

string_decl	: typeKey='STRING' id ':=' str ';'	;	
str 		: STRINGLITERAL;				//value of string

var_decl 	: var_type id_list ';'			;
var_type	: type='FLOAT' 		#floatType
			 | type='INT'		#intType;
any_type	: var_type 		#varType
			 | 'VOID'		#voidType;
id_list		: id id_tail					#idList;
id_tail		: 								#noID
				| ',' id id_tail			#idTail;

param_decl_list	: | param_decl param_decl_tail;
param_decl 		: var_type id				;
param_decl_tail : | ',' param_decl param_decl_tail;

func_declarations 	: | func_decl func_declarations;
func_decl 			: funcKey='FUNCTION' any_type id '('param_decl_list')' begKey='BEGIN' func_body endKey='END' ;
func_body			: decl stmt_list;

stmt_list	: #noStatement| stmt stmt_list #statementList;
stmt 		: basic_stmt | if_stmt | for_stmt;
basic_stmt	: assign_stmt | read_stmt | write_stmt | return_stmt;

assign_stmt	: assign_expr  ';';
assign_expr	: id ':=' expr;
read_stmt	: 'READ''('id_list1')' ';';
write_stmt 	: 'WRITE''('id_list1')' ';';
return_stmt : 'RETURN' expr ';';
id_list1		: id id_tail1					;
id_tail1	: 								
				#noId1| ',' id id_tail1	#idtail1		;
				


if_stmt 	: 'IF' '('cond')' decl stmt_list else_part 'ENDIF'		;
else_part	: #noElse| 'ELSE' decl stmt_list #esleScope;									
cond		: e1=expr compare e2=expr;
compare 	: '=' | '!=' | '<=' | '>=' | '<' | '>';

for_stmt	: 'FOR' '('init_expr ';' cond ';' incr_expr')' decl stmt_list 'ENDFOR' ;
init_expr 	: | assign_expr;
incr_expr 	: | assign_expr;

expr 			: expr_prefix term;
expr_prefix 	: #noExprPrfx| expr_prefix term addop #exprPrfx; 
term 			: factor_prefix factor;
factor_prefix 	: #noFactorPrfx| factor_prefix factor mulop #factorPrfx;
factor 			: primary | call_expr ;
primary 		: '('expr')' #primaryExpr
				|id #primaryId
				|INTLITERAL #primaryInt
				|FLOATLITERAL #primaryFloat;
call_expr		: id'('expr_list')';
expr_list 		: | expr expr_list_tail;
expr_list_tail 	: | ',' expr expr_list_tail;
addop 			: '+' | '-';
mulop 			: '*' | '/';

