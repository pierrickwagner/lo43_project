package smallworld.model;

public class Population {
	
	private String name;
	private String description;
	private boolean available;
	private int basePop;
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
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


	public Population(String n,String d, int bp) {
		// TODO Auto-generated constructor stub
		name=n;
		description=d;
		basePop=bp;
		available=false;
	}
	

}
