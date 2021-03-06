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
	
	private boolean declining;
	
	
	
	public Land(Type type, boolean border, int initTroups)
	{
		construction(type, border, initTroups);
	}
	
	public Land(Type type, boolean border)
	{
		construction(type, border, 0);
	}
	
	
	private void construction(Type type, boolean border, int initTroups)
	{
		this.type = type;
		this.border = border;
		this.troups = initTroups;
		this.adjacentLands = new ArrayList<Land>();
		this.occupant = null;
		this.tribe = null;
		this.declining = false;
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

	public boolean isDeclining() {
		return declining;
	}

	public void setDeclining(boolean declining) {
		this.declining = declining;
	}
}
