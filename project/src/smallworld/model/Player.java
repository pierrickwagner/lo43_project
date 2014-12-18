package smallworld.model;

import java.util.ArrayList;


import smallworld.exceptions.ImpossibleAttackException;
import smallworld.model.Population.TypePopulation;

public class Player {
	
	private Tribe currentTribe;
	private Tribe previousTribe;
	
	private int points;
	private int availablePop;
	
	private ArrayList<Land> lands;
	

	public void setPoints(int points) {
		this.points = points;
	}







	private ArrayList<TribeDeletedListener> listeners;
	
	
	
	public Player()
	{
		points = 5;
		availablePop = 0;
		lands = new ArrayList<Land>();
		currentTribe = null;
		previousTribe = null;
		listeners = new ArrayList<TribeDeletedListener>();
	}
	
	
	//Methods
	
	public void attack(Land target) throws ImpossibleAttackException
	{
		int neededTroops = simulateAttack(target);
		if(availablePop <= 0)
			throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.NO_TROOPS));
		else// Si l'attaque est possible
		{
			
			//On vérifie que la bataille n'est pas perdue d'avance
			if(availablePop + 3 < neededTroops)
				throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.NOT_ENOUGH_TROOPS));
			
			
			
			int sentTroops = Math.min(neededTroops, availablePop);
			availablePop -= sentTroops;
			
			
			int reinforcements = 0;
			// si les troupes sont insuffisantes, on lance le dé, sauf pour les IUT.
			//Les bourrés jettent toujours le dé.
			if((sentTroops < neededTroops && currentTribe.getPopulation().getType() == TypePopulation.IUT)
					||  currentTribe.getPower().getPowertype() == Power.TypePower.BOURRES)
			{
				reinforcements = throwDice();
			}
			
			if(sentTroops + reinforcements >= neededTroops)//Attaque réussie
			{
				if(target.getOccupant() != null)
					target.getOccupant().looseLand(target);
				target.setOccupant(this);
				target.setTribe(currentTribe);
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
					land.setPopulation(land.getTroups()+1);
				}
			}
			else
			{
				if(land.getTroups()>1)
				{
					availablePop ++;
					land.setPopulation(land.getTroups()-1);
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
		availablePop = tribe.getPopulation().getBasePop() + tribe.getPower().getBasePop();
	}
	
	
	
	
	
	public void beginTurn()
	{
		
		//Sets the population of each land to 1 and gets the pop tokens back.
		for(Land l : lands)
		{
			if(l.getTroups() > 1)
			{
				availablePop += l.getTroups()-1;
				l.setPopulation(1);
			}
		}
		
		//Les TC perdent une unité par tour
		if(currentTribe.getPopulation().getType() == TypePopulation.TC)
			availablePop --;
		//Les charlatans gagnent une unité par tour
		if(currentTribe.getPower().getPowertype() == Power.TypePower.CHARLATANTS)
			availablePop ++;
	}
	
	
	
	public int simulateAttack(Land target) throws ImpossibleAttackException
	{
		//TODO optimisation possible
		
		boolean legalMove = false;
		
		
		
		if(lands.isEmpty())
			legalMove = target.isBorder();
		else if(currentTribe.getPopulation().getType() == TypePopulation.ADMIN)
			legalMove = true; // Les admins vont où ils veulent
		else
		{
			//Boucle qui vérifie que la cible soit bien l'un des lands adjacents à ceux possédés.
			for(Land l : lands)
			{
				for(Land neighbor : l.getNeighbors())
				{
					if(neighbor == target)
						legalMove = true;
				}
			}
		}
		
		
		
		
		if(!legalMove)
			throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.NOT_REACHABLE));
		
		
		//On vérifie que le joueur ne s'attaque pas lui-meme
		if(target.getOccupant() == this)
			throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.FRIENDLY_FIRE));
		
		
		//Les GMC ne peuvent pas attaquer les GI
		if(currentTribe.getPopulation().getType() == TypePopulation.GMC &&target.getPopulationType() == TypePopulation.GI)
			throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.SPECIAL_RULE));
		
		//Le groupe ISO ne peut pas attaquer autre chose que les amphis
		if(currentTribe.getPopulation().getType() == TypePopulation.GROUPEISO && target.getType() != Land.Type.AMPHI)
			throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.SPECIAL_RULE));
		
		//Les nains ne peuvent pas perdre les couloirs
		if(target.getPowerType() == Power.TypePower.NAINS)
			throw(new ImpossibleAttackException(ImpossibleAttackException.Reason.SPECIAL_RULE));
		
		
		// Fonctionne dans le cas général, des conditions à rajouter pour les tribus / terrains particuliers
		int neededTroops = 2;
		neededTroops += target.getTroups();
		
		//Rend un défenseur inutile pour les EDIM
		if(target.getTroups()>0)
			neededTroops --;
		
		//Les profs ont un bonus de défense
		if(target.getPopulationType() == TypePopulation.PROFESSEURS)
			neededTroops++;
		
		//Les doctorants ont un bonus en attaquant les labos
		if(currentTribe.getPopulation().getType() == TypePopulation.DOCTORANTS && target.getType()==Land.Type.LABO)
			neededTroops --;
		
		//Les GI ont un bonus en attaquant les salles de TP
		if(currentTribe.getPopulation().getType() == TypePopulation.GI && target.getType() == Land.Type.TP_GI)
			neededTroops --;
		
		//Les anciens ont un bonus de défense
		if(target.getPopulationType() == TypePopulation.ANCIENS)
			neededTroops ++;
		
		//Les bourrés ont besoin d'un d'attaque en plus
		if(currentTribe.getPower().getPowertype() == Power.TypePower.BOURRES)
			neededTroops ++;
		
		//Les courageux ont besoin d'une unité en moins
		if(currentTribe.getPower().getPowertype() == Power.TypePower.COURAGEUX)
			neededTroops --;
		
		//Les overdrives ont un bonus de défense
		if(target.getPowerType() == Power.TypePower.OVERDRIVES)
			neededTroops ++;
		
		
		//On ne peut pas avoir 0 troupes sur une case, le minimum de troupes à utiliser pour attaquer est donc de 1 dans tous les cas.
		if(neededTroops <= 0)
			neededTroops = 1;
		
			
		return neededTroops;
	}
	
	
	
	
	public void looseLand(Land l)
	{
		//Quand on perd une case on rècupère les jetons de cette case moins 1. Sauf pour les E.
		if(currentTribe.getPopulation().getType()!=TypePopulation.E)
			availablePop += l.getTroups() -1;
		
		lands.remove(l);
	}
	
	
	
	
	
	
	
	private void tribeDeleted(Tribe t)
	{
		for(TribeDeletedListener l : listeners)
			l.tribeDeleted(t);
	}
	
	public void addListener(TribeDeletedListener l)
	{
		listeners.add(l);
	}
	
	
	
	
	
	public void addPoints(int count)
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
