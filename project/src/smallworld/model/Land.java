package smallworld.model;

import java.util.ArrayList;

import smallworld.model.Population.TypePopulation;
import smallworld.model.Power.TypePower;

public class Land {
	
	
	
	public enum Type {COULOIR, AMPHI, TP_GI, LABO, TOILETTES, BIBLIO, FOYER};
	
	
	
	private Type type;
	private int troups;
	private ArrayList<Land> adjacentLands;
	private boolean border;
	private Player occupant;
	private Tribe tribe;
	
	
	
	
	public Land(Type type, boolean border)
	{
		this.type = type;
		this.border = border;
		this.troups = 0;
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
			this.troups = population;
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


	public int getTroups() {
		return troups;
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
	
	public TypePopulation getPopulationType()
	{
		if(tribe != null)
			return tribe.getPopulation().getType();
		else
			return null;
	}
	
	public TypePower getPowerType()
	{
		if(tribe != null)
			return tribe.getPower().getPowertype();
		else
			return null;
	}
}
