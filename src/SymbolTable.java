import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SymbolTable {
	List<Symbol> symbolTable = new LinkedList<Symbol>();
	public String scopeName;
	
	public SymbolTable() {
		
	}
	public SymbolTable(String scopeName){
		this.scopeName = scopeName;
	}
	
	/*public void addSymbol(String name, String type, String value, String scopeName) {
		symbolTable.add(new Symbol(name, type, value));
		this.scopeName = scopeName;
	}*/
	
	/*public void	addSymbol(String name, String type, String scopeName) {
		symbolTable.add(new Symbol(name, type));
		this.scopeName = scopeName;
	}*/
	
	public void addSymbol(Symbol symbol) {
		symbolTable.add(symbol);
		
	}

	public Symbol getSymbol(String name){
		int i=0;
		while(i<symbolTable.size()) {
			if(symbolTable.get(i).getName().contentEquals(name)) return symbolTable.get(i);
			i++;
		}
		return null;
		/*ListIterator<Symbol> iterator = symbolTable.listIterator();
		while(iterator.hasNext()) {
			if(iterator.next().getName().equals(name)) break;
		}
		return iterator.next();*/
	}
	public Symbol getSymbol(int i) {
		return symbolTable.get(i);
	}
	
	public void printSymbols() {
		for(int i=0; i<symbolTable.size();i++) {
			System.out.println("printing table: \n" + symbolTable.get(i).toString());
		}
	}
	
	public int getSize() {return symbolTable.size();}

}
