package smallworld.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import smallworld.model.Population.TypePopulation;
import smallworld.model.Power.TypePower;
import sound.Sound;


public class Game {
	
    private ArrayList<Player> players;
    private int nbPlayer;
    private Player currentPlayer;
    private Bank bank;
    private int turn;
    private int points;
    private boolean isFinished;
    private int countForChanceux;
    private JOptionPane joueur;
    //constructeur
    
    public Game(){
    	
        this.players = new ArrayList<Player>();
        this.bank =  new Bank();
        this.nbPlayer = 0;
        
        for(int i=0; i<3; i++)
        	players.add(new Player());
        
        
        currentPlayer = players.get(0);
        turn = 0;
        points = 0;
        isFinished = false;
        countForChanceux = 0;
    }
   

    
    //passer au joueur suivant
    public void nextPlayer(){
    	
    	countPoints();
    	
        if(players.indexOf(currentPlayer)+1  == nbPlayer){
            currentPlayer = players.get(0);
            turn++;
        }else{
            currentPlayer = players.get(players.indexOf(currentPlayer) +1);
        }
        if(turn == 10){
            isFinished = true;
            showPoints();//TODO plutot à faire dans la partie view par la suite.
        }
    }
    
    
    //show points
    public void showPoints(){
        if(isFinished==true){
            for(int i=0;i<nbPlayer; i++){
                System.out.println("player"+(i+1)+":"+players.get(i).getPoints()+"points");
            }
        }
    }
    
    
    //count points
    public void countPoints(){
        points = currentPlayer.getLands().size()+ pointsExtern();
        addPoints(points);
    }
    
    public void addPoints(int p){
    	int tmp = currentPlayer.getPoints();
    	tmp = tmp + p;
    	currentPlayer.setPoints(tmp);	
    }
    
    private int pointsExtern(){
        int pointsExtern=0;
        
        if(currentPlayer.getCurrentTribe()!=null)
        {
        	TypePower power = currentPlayer.getCurrentTribe().getPower().getPowertype();
            TypePopulation population = currentPlayer.getCurrentTribe().getPopulation().getType();
            switch(power){
                case INTELLOS:
                    for(Land l:currentPlayer.getLands()){
                        if(!l.isDeclining() && l.getType()== Land.Type.BIBLIO)
                            pointsExtern+=1;
                    }break;
                case CHANCEUX:
                    if(countForChanceux == 0){
                        pointsExtern+= 7;
                        this.countForChanceux+=1;
                    }break;
                case FETARD:
                    for(Land l:currentPlayer.getLands()){
                        if(!l.isDeclining() && l.getType()==Land.Type.FOYER)
                            pointsExtern+=4;
                    }break;
                default:pointsExtern+=0;
            }
            
            switch(population){
                case IMSI:
                    for(Land l:currentPlayer.getLands()){
                        if(!l.isDeclining() && l.getType()==Land.Type.COULOIR)
                            pointsExtern+=1;
                    }break;
           
                default:pointsExtern+=0;
            }
        }
        
        
   
        return pointsExtern;
    }
    
 
    //get
 
    public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getNbPlayer(){
        return nbPlayer;
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public Bank getBank(){
        return bank;
    }
    public int getTurn(){
        return turn;
    }
    public int getPoints(){
        return points;
    }
    
    public boolean isFinished()
    {
    	return isFinished;
    }
    
    //set
    public void setNbPlayer(int p){
        this.nbPlayer = p;
        
    }
    public void setIsFinished(boolean b){
        this.isFinished = b;
    }


    
}



/*
     for(int i;i<=players.size();++i)
     {
     if(players[i].getavaliablePop()!=0&&i!=currentPlayer){
     players[i].redeploy();
     }
     }
    if(players[currentPlayer].getavaliablePop()==0)*/

