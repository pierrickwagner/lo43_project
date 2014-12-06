package smallworld.model;

public class Tribe {

	private Population population;
	private Power power;
	
	public Tribe( Population pop , Power p) {
		// TODO Auto-generated constructor stub
		population=pop;
		power=p;
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}
	
	
	
}
