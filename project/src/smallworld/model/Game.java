package smallworld.model;

import java.util.ArrayList;


public class Game {
    private ArrayList<Player> players;
   // private ArrayList<tribe> tribe;
    private int nbPlayer;
    private int currentPlayer;
    private int turn;
    private int bonus;
    private int countForChanceux;
    
    

    
    
    //构造函数  开始游戏
    public Game(){
        this.players = new ArrayList<Player>();
        nbPlayer = 0;
        currentPlayer = 0;
        turn = 0;
        bonus = 0;
        countForChanceux = 0;
        }
    
    
    //构造函数里可以调用别的函数？
    public startGame(){
        setNbPlayer();
        
        for(turn = 1; turn <=10; ++turn){
            for(currentPlayer= 1; currentPlayer <= nbPlayer; ++currentPlayer){ //需要知道当前是哪个玩家在玩，然后判断其他玩家是否被攻击 for(玩家：所有玩家） 检验是否手中有可用兵力；如果有，则执行部署。
                for(int i;i<=players.size();++i)
                {
                    if(players[i].getavaliablePop()!=0&&i!=currentPlayer){
                        players[i].redeploy();
                    }
                }
                
              //  if(players[currentPlayer].getavaliablePop()==0)
            //    {
                if(!players[currentPlayer]){players.Add(new Player());}
                if(!players[currentPlayer].getCurrentTribe()){
                    //if(players[currentPlayer].points >= ){}//---------根据表的顺序判断金币够不够--------
                    //怎么得到tribe和cost
                  //  chooseTribe(........);
                  //  players[currentPlayer].minusPoints(cost);
                    players[currentPlayer].chooseTribe(...);
                }
                //conceptuell
                //进行游戏（好几种情况？？？）
                switch(....){
                case "attack":
                    players[currentPlayer].beginTurn()
                    players[currentPlayer].attack(target);
                    players[currentPlayer].redeploy(land,addPop);
                    break;
                case "abandon"
                    if(turn!== 1)
                        players[currentPlayer].abandonTribe();
                    break;
                }
                    
                countBonus(players[currentPlayer]);
                
                
                }
            /*    else
                {
                    players[currentPlayer].redepoly();//redeploy from base;
                }*/
                
            
            }
        }
    }

    
    
    //退出游戏
    public void quitGame(){
    }
    
    
  //函数实现
    //#########这里面应该是什么参数
    public void setNbPlayer(int p){
        this.nbPlayer = p;
    }
    
    public int countBonus(player p){
        return bouns = p.getLands().size()+ bonusExtern(p);
        p.addPoints(bonus);
    }
    
    
    public int bonusExtern(Player p){
        int bonusExtern=0;
        String power = p.getCurrentTribe().getPower().getPowertype();
        switch(power){
            case "INTELLOS":
                for(Land l:p.getLands()){
                    if(l.getType()="BIBLIO")
                        bonusExtern+=1;
                }break;
            case "BOURRES":
            case "CHARLATANTS":
                bonusExtern = 1;
                break;
            case "CHANCEUX":
                if(countForChanceux = 0){
                    bonusExtern = 7;
                    countForChanceux+=1;
                }break;
            case "FETARD":
                for(Land l:p.getLands()){
                    if(l.getType()="FOYER")
                        bonusExtern+=4;
                }break;
            case "COURAGEUX": break;
            case "OVERDRIVES": break;
            case "NAINS": break;
            default:bonusExtern+=0;
        }
   
        return bonusExtern;
    }
    
    /*
    public int chooseTribe(//mouseLisener player p){
                           //cost
        if(players[currentPlayer].getPoints()>cost)
          p.tribe=t;
        return cost;
    }
    */
    
    
    
    
}
//AWT

//players.get(i)
//players[currentPlayer]

//arraylist get(i)
/*
 
