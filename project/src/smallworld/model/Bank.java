package smallworld.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import smallworld.model.Power.TypePower;



public class Bank implements TribeDeletedListener{

	private ArrayList<Power> listOfPowers;
	private ArrayList<Population> listOfPopulations;
	private ArrayList<Tribe> AvailableTribes;
	private String listOfDescriptionsPop[] = {"Leur connaissance du terrain leur permet d'etre plus efficaces pour se defendre. Il faut une unite de plus pour les attaquer.", 
			"Etant d'une grande capacite d'adaptation ils peuvent attaquer les labos avec 1 unitee en moins", 
			"Leur envie de s'etablir sur un ordinateur est tellement grande que leur combativit� en est stimulee, ils ont besoin d'une unite en moins pour attaquer les salles GI" , 
			"Leur charme absolu leur permet de rendre inutile 1 defenseur du terrain attaqu�."
			,"Leur rapidite � juger leurs ennemis leur permet de se replier rapidement, ils ne perdent pas de troupe quand ils se font envahir."
			,"Leur habilete a s'installer la ou on les attend le moins leur assure 1 point de victoire de plus quand ils sont dans les couloirs"
			,"Ayant besoin des GI pour leurs travaux, ce peuple ne peut pas les attaquer."
			,"Leurs examens pour devenir soldats sont tellement eprouvants qu'une unite est reorientee � chaque tour."
			,"La chance ne leur est pas permise, ils ne peuvent donc pas compter sur le d�."
			,"D'une grande soif de savoirs ils attaquent seulement les salles de cours."
			,"Il faut 1 unite en + pour pouvoir les attaquer �tant donn�e leur violence sans �gal"
			,"Etant dot�s d'un pass tres efficace, ils peuvent s'�tablir n'importe ou"};
	
	private String listOfDescriptionsPower[] = {"+1 point de victoire bonus si install�s dans la biblioth�que",
			"+4 points de victoire si le foyer est poss�d�."
			,"Ont besoin d'1 d'attaque en plus pour attaquer mais jettent le d� � chaque fois"
			," A� chaque tour, re�oivent une unit� bonus"
			,"+ 7 points lorsque la tribu est choisie"
			,"ont besoin de 1 troupes en moins pour attaquer"
			,"resistants au attaques, 1 unités adverse supplementaire est necessaire pour les attaquer"
			,"leurs attaques sournoises leurs permettent de ne pas céder de terrain dans les couloirs"
			,"ne fournit pas d'avantages utiles"};

	private HashMap<Population.TypePopulation,ImageIcon> iconPopulation;
    private String [] pathImage= { "./image_peuple/PROFESSEURS.jpg", "./image_peuple/DOCTORANTS.jpg","./image_peuple/GI.jpg","./image_peuple/EDIM.jpg"
    		,"./image_peuple/E.jpg","./image_peuple/IMSI.jpg","./image_peuple/GMC.jpg","./image_peuple/TC.jpg","./image_peuple/IUT.jpg","./image_peuple/GROUPEISO.jpg","./image_peuple/ANCIENS.jpg"
    		,"./image_peuple/ADMIN.jpg"};
	public HashMap<Population.TypePopulation, ImageIcon> getIconPopulation() {
		return iconPopulation;
	}

	public Bank(){
			
		listOfPopulations  = new ArrayList<Population>();
		listOfPowers  = new ArrayList<Power>();
		AvailableTribes =  new ArrayList<Tribe>();
		//cr�ation de la liste de la banque contenant les différents pouvoirs et leurs caract�ristiques (population en + et description)
		
		listOfPowers.add(new Power("intellos",listOfDescriptionsPower[0],5,Power.TypePower.INTELLOS));
		listOfPowers.add(new Power("fetard",listOfDescriptionsPower[1],5,Power.TypePower.FETARD));
		listOfPowers.add(new Power("bourres",listOfDescriptionsPower[2],5,Power.TypePower.BOURRES));
		listOfPowers.add(new Power("charlatants",listOfDescriptionsPower[3],5,Power.TypePower.CHARLATANTS));
		listOfPowers.add(new Power("chanceux",listOfDescriptionsPower[4],5,Power.TypePower.CHANCEUX));
		listOfPowers.add(new Power("courageux",listOfDescriptionsPower[5],5,Power.TypePower.COURAGEUX));
		listOfPowers.add(new Power("overdrives",listOfDescriptionsPower[6],5,Power.TypePower.OVERDRIVES));
		listOfPowers.add(new Power("nains",listOfDescriptionsPower[7],5,Power.TypePower.NAINS));
		listOfPowers.add(new Power("humains",listOfDescriptionsPower[8],5,Power.TypePower.HUMAINS));
		
		//cr�ation de la liste de la banque contenant les diff�rents peuples et leurs caract�ristiques (population en + et description)
		
		listOfPopulations.add(new Population("Professeurs", listOfDescriptionsPop[0], 5, Population.TypePopulation.PROFESSEURS));
		listOfPopulations.add(new Population("Doctorants", listOfDescriptionsPop[1], 5, Population.TypePopulation.DOCTORANTS));
		listOfPopulations.add(new Population("Informaticiens", listOfDescriptionsPop[2], 5, Population.TypePopulation.GI));
		listOfPopulations.add(new Population("Disigners", listOfDescriptionsPop[3], 5, Population.TypePopulation.EDIM));
		listOfPopulations.add(new Population("Environementalistes", listOfDescriptionsPop[4], 5, Population.TypePopulation.E));
		listOfPopulations.add(new Population("Managers", listOfDescriptionsPop[5], 5, Population.TypePopulation.IMSI));
		listOfPopulations.add(new Population("Mecaniciens", listOfDescriptionsPop[6], 5, Population.TypePopulation.GMC));
		listOfPopulations.add(new Population("Neophytes", listOfDescriptionsPop[7], 5, Population.TypePopulation.TC));
		listOfPopulations.add(new Population("IUT", listOfDescriptionsPop[8], 5, Population.TypePopulation.IUT));
		listOfPopulations.add(new Population("Groupe ISO", listOfDescriptionsPop[9], 5, Population.TypePopulation.GROUPEISO));
		listOfPopulations.add(new Population("Anciens", listOfDescriptionsPop[10], 5, Population.TypePopulation.ANCIENS));
		listOfPopulations.add(new Population("Personnels Administratifs", listOfDescriptionsPop[11], 5, Population.TypePopulation.ADMIN));
		
		iconPopulation = new HashMap<Population.TypePopulation, ImageIcon>();
		
		for(int i=0;i<listOfPopulations.size();i++){
			
			iconPopulation.put(listOfPopulations.get(i).getType(), new ImageIcon(new ImageIcon(pathImage[i]).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
		}
		generate();
	}
	
	public void generate(){
		
		int minPop=0;
		int maxPop=listOfPopulations.size();
		int minPower=0;
		int maxPower=listOfPowers.size();
		int randPop=0;
		int randPower=0;
		while(AvailableTribes.size()<6){
			
			randPop = (int) (minPop + (Math.random() * (maxPop - minPop))) ;
			randPower = (int) (minPower + (Math.random() * (maxPower - minPower))) ;
			
			if(listOfPopulations.get(randPop).isAvailable() && listOfPowers.get(randPower).isAvailable()){
					AvailableTribes.add(new Tribe(listOfPopulations.get(randPop),listOfPowers.get(randPower)));
                                        listOfPopulations.get(randPop).setAvailable(false);
                                        listOfPowers.get(randPower).setAvailable(false);
                                        
				
			}
	
			
		}
		
		for(int i=0;i<listOfPopulations.size();i++){
			
			System.out.println(""+ listOfPopulations.get(i).getName() + " " + listOfPopulations.get(i).isAvailable());
		}
		
		for(int i=0;i<listOfPowers.size();i++){
			
			System.out.println(""+ listOfPowers.get(i).getName() + " " + listOfPowers.get(i).isAvailable());
		}
		System.out.println("\n\n\n");
	}
	
	private boolean containsPopulation(Population p){
		
		for(Tribe t: AvailableTribes){
			
			if(t.getPopulation().equals(p)){
				
				return true;
			}
			
		}
		return false;
		
	}
	
	private boolean containsPower(Power p){
			
			for(Tribe t: AvailableTribes){
				
				if(t.getPower().equals(p)){
					
					return true;
				}
				
			}
			return false;
			
		}
	
	public void setAvailable(Tribe t){
		
		listOfPopulations.get(listOfPopulations.indexOf(t.getPopulation())).setAvailable(true);
		listOfPowers.get(listOfPowers.indexOf(t.getPower())).setAvailable(true);
	}

	public void pickTribe(Tribe t){
		
		int index = AvailableTribes.indexOf(t);

		AvailableTribes.remove(index);
		generate();
	}

	public ArrayList<Tribe> getAvailableTribes() {
		return AvailableTribes;
	}

	@Override
	public void tribeDeleted(Tribe t) {
		setAvailable(t);
		
	}
	
	
	
	
}
