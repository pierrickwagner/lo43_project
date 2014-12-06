package smallworld.model;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import smallworld.model.Power.TypePower;



public class Bank {

	private ArrayList<Power> listOfPowers;
	private ArrayList<Population> listOfPopulations;
	private ArrayList<Tribe> listOfAvailableTribes;
	private String listOfDescriptionsPop[] = {"Leur connaissance du terrain leur permet d'être plus efficace pour se defendre. Il faut une unité de plus pour les attaquer.", 
			"Etant d'une grande capacité d'adaptation ils peuvent avec 1 unité en - attaquer les labos", 
			"Leur envie de s'établir sur un ordinateur est tellement grande que leur combativité en est stimulé ils ont besoin d'une unité en - pr attaquer les salles GI" , 
			"Leur charme absolu les rendent capable de rendre inutile 1 défenseur du terrain attaqué."
			,"Leur rapidité a jugé leurs ennemis leur permet de se replier rapidement ils ne perdent pas de troupe quand ils se font envahir."
			,"Leur abilité a s'installer la où on les attend le moins leur assure 1 pvic de + quand ils sont dans les couloirs"
			,"Ayant besoin des GI pour leurs travaux, Ce peuple ne peux pas les attaquer."
			,"Leur examens pour devenir soldats est tellement soutenu et quotidien qu'une unité est réorientées à chaque tour vers un autre travail."
			,"La chance ne leur est pas permise, ils ne peuvent donc pas compter sur le dé leur assurant l'espoir d'avoir des troupes en plus si ils attaquent avec un sous nombre."
			,"D'une grande sauf de savoirs ils attaquent seulement les salles de cours."
			,"Il faut 1 unités en + pour pouvoir les attaquer étant donné leur violence sans égal"
			,"Etant doté d'un pass très efficace, Ils peuvent s'établir n'importe où"};
	
	private String listOfDescriptionsPower[] = {"+1 point de victoire bonus si installés dans la bibliothèque",
			"+4 point de victoire si le foyer est possédé."
			,"besoin d'1 d'attaque en plus mais jettent le dé à chaque fois"
			," à chaque tour, reçoivent une unité bonus"
			,"+ 7 points lorsque la tribu sont choisis"
			,"le peule a besoin de 2 troupes en moins pour attaquer"
			,"résistant au attaque +2 unités adverses sont nécessaires pour les attaquer"
			,"leur attaque sournoise leur permet de ne pas céder de terrain dans les couloirs"};

	public Bank(){
			
		listOfPopulations  = new ArrayList<Population>();
		listOfPowers  = new ArrayList<Power>();
		listOfAvailableTribes =  new ArrayList<Tribe>();
		//création de la liste de la banque contenant les différents pouvoirs et leurs caractéristiques (population en + et description)
		
		listOfPowers.add(new Power("intellos",listOfDescriptionsPower[0],5,Power.TypePower.INTELLOS));
		listOfPowers.add(new Power("fétard",listOfDescriptionsPower[1],5,Power.TypePower.FETARD));
		listOfPowers.add(new Power("bourrés",listOfDescriptionsPower[2],5,Power.TypePower.BOURRES));
		listOfPowers.add(new Power("charlatants",listOfDescriptionsPower[3],5,Power.TypePower.CHARLATANTS));
		listOfPowers.add(new Power("chanceux",listOfDescriptionsPower[4],5,Power.TypePower.CHANCEUX));
		listOfPowers.add(new Power("courageux",listOfDescriptionsPower[5],5,Power.TypePower.COURAGEUX));
		listOfPowers.add(new Power("overdrives",listOfDescriptionsPower[6],5,Power.TypePower.OVERDRIVES));
		listOfPowers.add(new Power("nains",listOfDescriptionsPower[7],5,Power.TypePower.NAINS));
		
		//création de la liste de la banque contenant les différents peuples et leurs caractéristiques (population en + et description)
		
		listOfPopulations.add(new Population("Professeurs", listOfDescriptionsPop[0], 5, Population.TypePopulation.PROFESSEURS));
		listOfPopulations.add(new Population("Doctorants", listOfDescriptionsPop[1], 5, Population.TypePopulation.DOCTORANTS));
		listOfPopulations.add(new Population("Informaticiens", listOfDescriptionsPop[2], 5, Population.TypePopulation.GI));
		listOfPopulations.add(new Population("Disigners", listOfDescriptionsPop[3], 5, Population.TypePopulation.EDIM));
		listOfPopulations.add(new Population("Environementalistes", listOfDescriptionsPop[4], 5, Population.TypePopulation.E));
		listOfPopulations.add(new Population("Managers", listOfDescriptionsPop[5], 5, Population.TypePopulation.EDIM));
		listOfPopulations.add(new Population("Mecaniciens", listOfDescriptionsPop[6], 5, Population.TypePopulation.GMC));
		listOfPopulations.add(new Population("Néophytes", listOfDescriptionsPop[7], 5, Population.TypePopulation.TC));
		listOfPopulations.add(new Population("IUT", listOfDescriptionsPop[8], 5, Population.TypePopulation.IUT));
		listOfPopulations.add(new Population("Groupe ISO", listOfDescriptionsPop[0], 5, Population.TypePopulation.GROUPEISO));
		listOfPopulations.add(new Population("Anciens", listOfDescriptionsPop[0], 5, Population.TypePopulation.ANCIENS));
		listOfPopulations.add(new Population("Personnels Administratifs", listOfDescriptionsPop[0], 5, Population.TypePopulation.ADMIN));
		
		generate();
	}
	
	public void generate(){
		
		int minPop=0;
		int maxPop=listOfPopulations.size();
		int minPower=0;
		int maxPower=listOfPowers.size();
		int randPop=0;
		int randPower=0;
		while(listOfAvailableTribes.size()<=6){
			
			randPop = (int) (minPop + (Math.random() * (maxPop - minPop))) ;
			randPower = (int) (minPower + (Math.random() * (maxPower - minPower))) ;
			
			
			if(listOfPopulations.get(randPop).isAvailable() && listOfPowers.get(randPower).isAvailable()){
				
				listOfAvailableTribes.add(new Tribe(listOfPopulations.get(randPop),listOfPowers.get(randPower)));
				listOfPopulations.get(randPop).setAvailable(false);
				listOfPowers.get(randPower).setAvailable(false);
				
			}
			
		}
		
	}
	
	public void freeTribe(Tribe t){
		
		int index = listOfAvailableTribes.indexOf(t);
		listOfPopulations.get(listOfPopulations.indexOf(listOfAvailableTribes.get(index).getPopulation())).setAvailable(true);
		listOfPowers.get(listOfPowers.indexOf(listOfAvailableTribes.get(index).getPower())).setAvailable(true);
		listOfAvailableTribes.remove(index);
		generate();	
	}

	public ArrayList<Tribe> getListOfAvailableTribes() {
		return listOfAvailableTribes;
	}
	
	
	
	
}
