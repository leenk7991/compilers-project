

public class Symbol {
	private String name;
	private String type;
	private String value;
	
	/*public Symbol() {
		
	}*/
	
	public Symbol(String name, String type){
		this.name=name;
		this.type=type;
	}
	
	public Symbol(String name, String type, String value) {
		this(name, type);
		this.value=value;
	}
	
	public String getName() {return name;}
	public String getType() {return type;}
	public String getValue() {return value;}
	
	@Override
	public boolean equals(Object obj){
		if(obj!=null && obj instanceof Symbol) 
			return name.equals(((Symbol)obj).getName()) && type.equals(((Symbol)obj).getType());
		return false;
	}
	
	@Override
	public String toString() {
		if(this.type.equals("STRING")) {
			return "name " + this.name + " type " + this.type + " value " +this.value;
		}
		return "name " + this.name + " type " + this.type;
	}
}
