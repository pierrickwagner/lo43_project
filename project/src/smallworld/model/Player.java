package smallworld.model;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.LAND;

public class Player {
	
	private Tribe currentTribe;
	private Tribe previousTribe;
	
	private int points;
	private int availablePop;
	
	private ArrayList<LAND> lands;
	
	

	private ArrayList<TribeDeletedListener> listeners;
	
	
	
	
	
	//Methods
	
	public void attack(Land land)
	{
		
	}
	
	public void redeploy(Land land, Boolean addPop)
	{
		if(lands.contains(land))
		{
			if(addPop)
			{
				if(availablePop > 0)
				{
					availablePop--;
					land.setPopulation(land.getPopulation()+1);
				}
			}
			else
			{
				if(land.getPopulation()>1)
				{
					availablePop ++;
					land.setPopulation(land.getPopulation()-1);
				}
			}
		}
		
	}
	
	public void abandonTribe()
	{
		if(previousTribe != null)
		{
			tribeDeleted(previousTribe);
		}
		
		previousTribe = currentTribe;
		currentTribe = null;
	}
	
	
	
	
	public void chooseTribe(Tribe tribe, int cost)
	{
		currentTribe = tribe;
		points -= cost;
	}
	
	
	
	
	
	public void beginTurn()
	{
		
		//Sets the population of each land to 1 and gets the pop tokens back.
		for(Land l : lands)
		{
			if(l.getPopulation() > 1)
			{
				availablePop += l.getPopulation()-1;
				l.setPopulation(1);
			}
		}
	}
	
	public int throwDice()
	{
		double rand = Math.random();
		int result;
		if(rand < 1d/6d)
			result = 3;
		else if(rand < 1d/3d)
			result = 2;
		else if(rand < 1d/2d)
			result = 1;
		else
			result = 0;
		
		return result;
	}
	
	public int simulateAttack()
	{
		return 0;
	}
	
	
	
	
	public void looseLand(Land l)
	{
		lands.remove(l);
	}
	
	
	
	
	
	
	
	private void tribeDeleted(Tribe t)
	{
		for(TribeDeletedListener l : listeners)
			t.tribeDeleted(t);
	}
	
	public void addListener(TribeDeletedListener l)
	{
		listeners.add(l);
	}
	
	
	
	
	
	private void addPoints(int count)
	{
		points += count;
	}
	
	
	//Getters
	
	public Tribe getCurrentTribe() {
		return currentTribe;
	}

	public Tribe getPreviousTribe() {
		return previousTribe;
	}

	public int getPoints() {
		return points;
	}

	public int getAvailablePop() {
		return availablePop;
	}

	public ArrayList<Land> getLands() {
		return lands;
	}
	
}
