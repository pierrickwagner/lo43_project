package smallworld.model;

import java.util.ArrayList;
import java.util.Hashtable;

import smallworld.model.Power.TypePower;



public class Bank {

	private ArrayList<Power> listOfPowers;
	private ArrayList<Population> listOfPopulations;
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
		listOfPowers.add(new Power("intellos",listOfDescriptionsPower[0],5,Power.TypePower.INTELLOS));
		listOfPowers.add(new Power("fétard",listOfDescriptionsPower[1],5,Power.TypePower.FETARD));
		listOfPowers.add(new Power("bourrés",listOfDescriptionsPower[2],5,Power.TypePower.BOURRES));
		listOfPowers.add(new Power("charlatants",listOfDescriptionsPower[3],5,Power.TypePower.CHARLATANTS));
		listOfPowers.add(new Power("chanceux",listOfDescriptionsPower[4],5,Power.TypePower.CHANCEUX));
		listOfPowers.add(new Power("courageux",listOfDescriptionsPower[5],5,Power.TypePower.COURAGEUX));
		listOfPowers.add(new Power("overdrives",listOfDescriptionsPower[6],5,Power.TypePower.OVERDRIVES));
		listOfPowers.add(new Power("nains",listOfDescriptionsPower[7],5,Power.TypePower.NAINS));
		
		listOfPopulations.add(new Population("Professeurs", listOfDescriptionsPop[0], 5));
		
	}

	
}
