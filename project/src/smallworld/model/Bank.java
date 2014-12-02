package smallworld.model;

import java.util.ArrayList;
import java.util.Hashtable;

import smallworld.model.Power.TypePower;



public class Bank {

	private ArrayList<Power> listOfPowers;
	private ArrayList<Population> listOfPopulations;
	private String listOfDescriptionsPop[] = {"Leur connaissance du terrain leur permet d'�tre plus efficace pour se defendre. Il faut une unit� de plus pour les attaquer.", 
			"Etant d'une grande capacit� d'adaptation ils peuvent avec 1 unit� en - attaquer les labos", 
			"Leur envie de s'�tablir sur un ordinateur est tellement grande que leur combativit� en est stimul� ils ont besoin d'une unit� en - pr attaquer les salles GI" , 
			"Leur charme absolu les rendent capable de rendre inutile 1 d�fenseur du terrain attaqu�."
			,"Leur rapidit� a jug� leurs ennemis leur permet de se replier rapidement ils ne perdent pas de troupe quand ils se font envahir."
			,"Leur abilit� a s'installer la o� on les attend le moins leur assure 1 pvic de + quand ils sont dans les couloirs"
			,"Ayant besoin des GI pour leurs travaux, Ce peuple ne peux pas les attaquer."
			,"Leur examens pour devenir soldats est tellement soutenu et quotidien qu'une unit� est r�orient�es � chaque tour vers un autre travail."
			,"La chance ne leur est pas permise, ils ne peuvent donc pas compter sur le d� leur assurant l'espoir d'avoir des troupes en plus si ils attaquent avec un sous nombre."
			,"D'une grande sauf de savoirs ils attaquent seulement les salles de cours."
			,"Il faut 1 unit�s en + pour pouvoir les attaquer �tant donn� leur violence sans �gal"
			,"Etant dot� d'un pass tr�s efficace, Ils peuvent s'�tablir n'importe o�"};
	
	private String listOfDescriptionsPower[] = {"+1 point de victoire bonus si install�s dans la biblioth�que",
			"+4 point de victoire si le foyer est poss�d�."
			,"besoin d'1 d'attaque en plus mais jettent le d� � chaque fois"
			," � chaque tour, re�oivent une unit� bonus"
			,"+ 7 points lorsque la tribu sont choisis"
			,"le peule a besoin de 2 troupes en moins pour attaquer"
			,"r�sistant au attaque +2 unit�s adverses sont n�cessaires pour les attaquer"
			,"leur attaque sournoise leur permet de ne pas c�der de terrain dans les couloirs"};

	public Bank(){
			
		listOfPopulations  = new ArrayList<Population>();
		listOfPowers  = new ArrayList<Power>();
		listOfPowers.add(new Power("intellos",listOfDescriptionsPower[0],5,Power.TypePower.INTELLOS));
		listOfPowers.add(new Power("f�tard",listOfDescriptionsPower[1],5,Power.TypePower.FETARD));
		listOfPowers.add(new Power("bourr�s",listOfDescriptionsPower[2],5,Power.TypePower.BOURRES));
		listOfPowers.add(new Power("charlatants",listOfDescriptionsPower[3],5,Power.TypePower.CHARLATANTS));
		listOfPowers.add(new Power("chanceux",listOfDescriptionsPower[4],5,Power.TypePower.CHANCEUX));
		listOfPowers.add(new Power("courageux",listOfDescriptionsPower[5],5,Power.TypePower.COURAGEUX));
		listOfPowers.add(new Power("overdrives",listOfDescriptionsPower[6],5,Power.TypePower.OVERDRIVES));
		listOfPowers.add(new Power("nains",listOfDescriptionsPower[7],5,Power.TypePower.NAINS));
		
		listOfPopulations.add(new Population("Professeurs", listOfDescriptionsPop[0], 5));
		
	}

	
}
