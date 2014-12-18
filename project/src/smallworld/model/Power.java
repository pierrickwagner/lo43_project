package smallworld.model;

public class Power {
	
	
	public enum TypePower{INTELLOS,FETARD,BOURRES,CHARLATANTS,CHANCEUX,COURAGEUX,OVERDRIVES,NAINS}
	
	private String name;
	private String description;
	private boolean available;
	private int basePop;
	private TypePower Powertype;
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
	public TypePower getPowertype() {
		return Powertype;
	}

	public Power(String n,String d, int bp, TypePower tp) {
		// TODO Auto-generated constructor stub
		name=n;
		description=d;
		basePop=bp;
		Powertype=tp;
		available=true;
	}
	
}
