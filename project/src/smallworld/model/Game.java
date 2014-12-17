package smallworld.model;

import java.util.ArrayList;


public class Game {
    private ArrayList<Player> players;
    private int nbPlayer;
    private Player currentPlayer;
    private Bank bank;
    private int turn;
    private int points;
    private boolean isFinished;
    private int countForChanceux;
    
    //constructeur
    public Game(int nbPlayer){
        this.players = new ArrayList<Player>();
        nbPlayer = 2;
        for(int i=0; i<nbPlayer; ++i){players.add(new Player());}
        currentPlayer = players.get(0);
        turn = 0;
        points = 0;
        isFinished = false;
        countForChanceux = 0;
    }
   
    //passer au joueur suivant
    public void nextPlayer(){
        countPoints();
        if(players.getIndex(currentPlayer) +1 = nbPlayer){
            currentPlayer = players.get(0);
            turn++;
        }else{
            currentPlayer = players.get(players.getIndex(currentPlayer) +1);
        }
        if(turn == 10){
            isFinished = true;
            showPoints();
        }
    }
    
    
    //show points
    public void showPoints(){
        if(isFinished==true){
            for(int i=1;i<=nbPlayer; ++i){
                System.out.println("player"+i+":"+players.get(i).getPoints()+"points");
            }
        }
    }
    
    
    //count points
    public void countPoints(){
        points = currentPlayer.getLands().size()+ pointsExtern();
        currentPlayer.addPoints(points);
    }
    
    
    public int pointsExtern(){
        int pointsExtern=0;
        String power = currentPlayer.getCurrentTribe().getPower().getPowertype();
        String population = currentPlayer.getCurrentTribe().getPopulation().getType();
        switch(power){
            case "INTELLOS":
                for(Land l:currentPlayer.getLands()){
                    if(l.getType()="BIBLIO")
                        pointsExtern+=1;
                }break;
            case "CHANCEUX":
                if(countForChanceux = 0){
                    pointsExtern+= 7;
                    this.countForChanceux+=1;
                }break;
            case "FETARD":
                for(Land l:currentPlayer.getLands()){
                    if(l.getType()="FOYER")
                        pointsExtern+=4;
                }break;
            default:pointsExtern+=0;
        }
        
        switch(population){
            case "IMSI":
                for(Land l:currentPlayer.getLands()){
                    if(l.getType()="BIBLIO")
                        pointsExtern+=1;
                }break;
       
            default:pointsExtern+=0;
        }
   
        return pointsExtern;
    }
    
 
    //get
    public int getPlayers(){
        return players;
    }
    public int getNbPlayer(){
        return nbPlayer;
    }
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public int getBank(){
        return bank;
    }
    public int getTurn(){
        return turn;
    }
    public int getPoints(){
        return points;
    }
    public int getIsBoolean(){
        return nbPlayer;
    }
    
    //set
    public void setNbPlayer(int p){
        this.nbPlayer = p;
    }
    public void setIsFinished(boolean b){
        this.isFinished = b;
    }


    
}



/*需要知道当前是哪个玩家在玩，然后判断其他玩家是否被攻击 for(玩家：所有玩家） 检验是否手中有可用兵力；如果有，则执行部署。
     for(int i;i<=players.size();++i)
     {
     if(players[i].getavaliablePop()!=0&&i!=currentPlayer){
     players[i].redeploy();
     }
     }
    if(players[currentPlayer].getavaliablePop()==0)*/

