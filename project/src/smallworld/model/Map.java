package smallworld.model;

import java.util.ArrayList;

public class Map implements TribeDeletedListener {
	
	
	
	
	private ArrayList<Land> lands;
	
	
	
	public Map()
	{
		
	}
	
	
	public Land getLand(int id)
	{
		return lands.get(id);
	}


	@Override
	public void tribeDeleted(Tribe t) {
		for(Land l : lands)
		{
			if(l.getTribe() == t)
			{
				l.setTribe(null);
				l.setPopulation(0);
				l.setOccupant(null);
			}
		}
		
	}
	
	
	
}
