package smallworld.model;

import java.util.ArrayList;

public class Land {
	
	
	
	public enum Type {COULOIR, AMPHI, TP_GI, LABO, TOILETTES, BIBLIO, FOYER};
	
	
	
	private Type type;
	private int population;
	private ArrayList<Land> adjacentLands;
	private boolean border;
	private Player occupant;
	private Tribe tribe;
	
	
	
	
	public Land(Type type, boolean border)
	{
		this.type = type;
		this.border = border;
		this.population = 0;
		this.adjacentLands = new ArrayList<Land>();
		this.occupant = null;
		this.tribe = null;
	}
	
	
	public void addAdjacent(Land l)
	{
		adjacentLands.add(l);
	}

	
	//Setters

	public void setPopulation(int population) {
		if(population >= 0)
			this.population = population;
	}


	public void setOccupant(Player occupant) {
		this.occupant = occupant;
	}
	
	public void setTribe(Tribe tribe) {
		this.tribe = tribe;
	}

	
	//Getters

	public Type getType() {
		return type;
	}


	public int getPopulation() {
		return population;
	}


	public ArrayList<Land> getNeighbors() {
		return adjacentLands;
	}


	public boolean isBorder() {
		return border;
	}


	public Player getOccupant() {
		return occupant;
	}
	
	public Tribe getTribe()
	{
		return tribe;
	}
}
