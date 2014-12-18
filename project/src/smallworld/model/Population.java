package smallworld.model;

public class Population {
	
	public enum TypePopulation{PROFESSEURS,DOCTORANTS,GI,EDIM,E,IMSI,
		GMC,TC,IUT,GROUPEISO,ANCIENS,ADMIN}
	private String name;
	private String description;
	private boolean available;
	private int basePop;
	private TypePopulation type;
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public TypePopulation getType() {
		return type;
	}
	public int getBasePop() {
		return basePop;
	}
	public void setBasePop(int basePop) {
		this.basePop = basePop;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}


	public Population(String n,String d, int bp, TypePopulation t) {
		// TODO Auto-generated constructor stub
		name=n;
		description=d;
		basePop=bp;
		available=true;
		type=t;
	}
	

}
