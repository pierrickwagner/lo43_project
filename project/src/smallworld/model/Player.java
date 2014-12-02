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
	
	
	
	public Player()
	{
		points = 5;
		availablePop = 0;
		lands = new ArrayList<Land>();
	}
	
	
	//Methods
	
	public void attack(Land target)
	{
		int neededTroops = simulateAttack(target);
		if(neededTroops != -1 && availablePop > 0)// Si l'attaque est possible
		{
			int sentTroops = Math.min(neededTroops, availablePop);
			availablePop -= sentTroops;
			
			
			int reinforcements = 0;
			if(sentTroops < neededTroops)
			{
				reinforcements = throwDice();
			}
			
			if(sentTroops + reinforcements >= neededTroops)//Attaque réussie
			{
				if(target.getOccupant() != null)
					target.getOccupant().looseLand(target);
				target.setOccupant(this);
				target.setPopulation(sentTroops);
				
				lands.add(target);
			}
				
		}
		
		
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
		
		for(Land l : lands)
		{
			l.setPopulation(1);
			l.setOccupant(null);
		}
		lands.clear();
		availablePop = 0;
		
		previousTribe = currentTribe;
		currentTribe = null;
	}
	
	
	
	
	public void chooseTribe(Tribe tribe, int cost)
	{
		currentTribe = tribe;
		points -= cost;
		availablePop = tribe.getPopulation();
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
	
	public int simulateAttack(Land target)
	{
		//TODO optimisation possible
		
		boolean legalMove = false;
		
		//Boucle qui vérifie que la cible soit bien l'un des lands adjacents à ceux possédés.
		for(Land l : lands)
		{
			for(Land neighbor : l.getNeighbors())
			{
				if(neighbor == target)
					legalMove = true;
			}
		}
		
		//On vérifie que le joueur ne s'attaque pas lui-meme
		if(target.getOccupant() == this)
			legalMove = false;
		
		
		if(legalMove)
		{
			// Fonctionne dans le cas général, des conditions à rajouter pour les tribus / terrains particuliers
			int neededTroops = 2;
			neededTroops += target.getPopulation();
		}
		else
			return -1; // Attaque impossible
	}
	
	
	
	
	public void looseLand(Land l)
	{
		//Quand on perd une case on rècupère les jetons de cette case moins 1
		availablePop += l.getPopulation() -1;
		
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
	
	
	
	
	
	
	
	//Dé
	private static int throwDice()
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
	
	
	
	
}
