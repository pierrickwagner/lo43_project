/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import smallworld.exceptions.ImpossibleAttackException;
import smallworld.model.Game;
import smallworld.model.Land;
import smallworld.model.Tribe;
import smallworld.model.TribeDeletedListener;
import sound.Sound;


public class MyWindow extends javax.swing.JFrame implements TribeDeletedListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean redeploy;
    int numberClickedLand;
    DefaultListModel<String> listModel;
    
    ArrayList<Tribe> listTribe;
    
    public static final int MAP_WIDTH=6;
    public static final int MAP_HEIGHT=5;
    
    Game game;
    ArrayList<LandDisplayer> landDisplayer;
    ArrayList<Land> lands;
    
    
    
    /**
     * Creates new form MyWindow
     */
    public MyWindow() {
    	
    	super();
    	
        initComponents();
       
        this.setVisible(true);
        this.setName("Small UTBM World");
        
        game=new Game();
        lands=new ArrayList<Land>();
        landDisplayer=new ArrayList<LandDisplayer>();
        createLands();
        createLandDisplayer();
        addListenerPanel();
        computeNeighbours();
        init();
        
        
        
        this.repaint();

        
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
    
    public void createLands()
    {
        lands.add(new Land(Land.Type.TP_GI, true));
        lands.add(new Land(Land.Type.LABO, true));
        lands.add(new Land(Land.Type.AMPHI, true));
        lands.add(new Land(Land.Type.AMPHI, true));
        lands.add(new Land(Land.Type.TP_GI, true));
        lands.add(new Land(Land.Type.LABO, true));
        
        lands.add(new Land(Land.Type.AMPHI, true));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.AMPHI, true));
        
        lands.add(new Land(Land.Type.COULOIR, true));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.BIBLIO, false));
        lands.add(new Land(Land.Type.TOILETTES, false));
        lands.add(new Land(Land.Type.FOYER, false));
        lands.add(new Land(Land.Type.TP_GI, true));
        
        lands.add(new Land(Land.Type.LABO, true));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.COULOIR, false));
        lands.add(new Land(Land.Type.LABO, true));
        
        lands.add(new Land(Land.Type.AMPHI, true));
        lands.add(new Land(Land.Type.LABO, true));
        lands.add(new Land(Land.Type.TP_GI, true));
        lands.add(new Land(Land.Type.AMPHI, true));
        lands.add(new Land(Land.Type.TP_GI, true));
        lands.add(new Land(Land.Type.TOILETTES, true));
        
        
    }
    
    
    //Calcul des lands voisins pour chaque land
    public void computeNeighbours()
    {
            for(int i=0;i<lands.size();i++)
            {
                    int y = i/MAP_WIDTH;
                    int x = i%MAP_WIDTH;

                    int xMin = (x-1<0) ? 0 : x-1;
                    int xMax = (x+1>MAP_WIDTH-1) ? MAP_WIDTH-1 : x+1;
                    int yMin = (y-1<0) ? 0 : y-1;
                    int yMax = (y+1>MAP_HEIGHT-1) ? MAP_HEIGHT-1 : y+1;


                    for(int nx = xMin; nx<=xMax; nx++)
                    {
                            for(int ny = yMin; ny<=yMax; ny++)
                            {
                                    if(nx!=x || ny!=y)
                                            lands.get(i).addAdjacent(lands.get(ny*MAP_WIDTH+nx));
                            }
                    }

            }
    }
    
    //Gere l'affichage des lands
    public void createLandDisplayer()
    {
        
        landDisplayer.add(new LandDisplayer(land0, label0, lands.get(0),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land1, label1, lands.get(1),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land2, label2, lands.get(2),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land3, label3, lands.get(3),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land4, label4, lands.get(4),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land5, label5, lands.get(5),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land6, label6, lands.get(6),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land7, label7, lands.get(7),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land8, label8, lands.get(8),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land9, label9, lands.get(9),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land10, label10, lands.get(10),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land11, label11, lands.get(11),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land12, label12, lands.get(12),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land13, label13, lands.get(13),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land14, label14, lands.get(14),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land15, label15, lands.get(15),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land16, label16, lands.get(16),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land17, label17, lands.get(17),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land18, label18, lands.get(18),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land19, label19, lands.get(19),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land20, label20, lands.get(20),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land21, label21, lands.get(21),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land22, label22, lands.get(22),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land23, label23, lands.get(23),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land24, label24, lands.get(24),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land25, label25, lands.get(25),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land26, label26, lands.get(26),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land27, label27, lands.get(27),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land28, label28, lands.get(28),game.getBank().getIconPopulation()));
        landDisplayer.add(new LandDisplayer(land29, label29, lands.get(29),game.getBank().getIconPopulation()));
        
    }
    
   
    //Remplit la Jlist
    public void fillListPopulation() // remplit la Jlist
    {
    	
        listTribe= new ArrayList<Tribe>();
        
        listTribe=game.getBank().getAvailableTribes();
        
        
        for(int i =0;i<listTribe.size();i++)
        {
           listModel.addElement(listTribe.get(i).getPopulation().getName()+" "+listTribe.get(i).getPower().getName());
        }
        
        listPeuple.setSelectedIndex(0);
        textAreaDescription.setEditable(false);
        textAreaDescription.setLineWrap(true);
                
        textAreaDescription.setText(
                        listTribe.get(listPeuple.getSelectedIndex()).getPopulation().getName()
                        +" : "+listTribe.get(listPeuple.getSelectedIndex()).getPopulation().getDescription()
                        +"\n\n" +listTribe.get(listPeuple.getSelectedIndex()).getPower().getName()
                        +" : "+listTribe.get(listPeuple.getSelectedIndex()).getPower().getDescription()
                        );
        
        
    }
    
    public void fillDescPop() // ajoute un listener a la Jlist.
    {
    
        //Lorsqu'on clique sur une ligne de la JList, on remplit le textArea qui affiche la description de la population
        listPeuple.addListSelectionListener(new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
                if(listPeuple.getSelectedIndex() != -1)
                {
                   textAreaDescription.setText(
                		listTribe.get(listPeuple.getSelectedIndex()).getPopulation().getName()
                		+" : "+listTribe.get(listPeuple.getSelectedIndex()).getPopulation().getDescription()
                		+"\n\n" +listTribe.get(listPeuple.getSelectedIndex()).getPower().getName()
                		+" : "+listTribe.get(listPeuple.getSelectedIndex()).getPower().getDescription()
                		); 
                }
                
            }
        });
    }
    
   
    
    //Initialisation des valeurs et des affichages
    public void init()
    {
        panelGame.setVisible(false);
        buttonEndTurn.setVisible(false);
        buttonPasserDeclin.setVisible(false);
        
        buttonRedeploy.setVisible(false);
        redeploy=false;
        
        
        listModel = new DefaultListModel<String>();
        listPeuple.setModel(listModel);
        
        
        fillListPopulation();
        fillDescPop();
        	
    }
    
    //Fonction appelee lors du clic sur une land
    public void landsClick(int ncl, boolean rc)
    {
    	buttonPasserDeclin.setVisible(false);
        numberClickedLand=ncl;
        if(!redeploy)
        {
            if(!buttonChoice.isVisible())
            {
            	//Attack
                try{
                    game.getCurrentPlayer().attack(lands.get(ncl));
                    this.repaint();
                }catch(ImpossibleAttackException e)
                {
                	switch(e.getReason())
                	{
					case FRIENDLY_FIRE:
			            JOptionPane.showMessageDialog(this,"Vous ne pouvez pas vous attaquer vous-meme"," Noob !",JOptionPane.ERROR_MESSAGE);
						break;
					case NOT_ENOUGH_TROOPS:
			            JOptionPane.showMessageDialog(this,"Vous n'avez pas assez de troupes disponibles pour attaquer ici."," Noob !",JOptionPane.ERROR_MESSAGE);
						break;
					case NOT_REACHABLE:
			            JOptionPane.showMessageDialog(this,"Cette region est inatteignable."," Noob !",JOptionPane.ERROR_MESSAGE);
						break;
					case NO_TROOPS:
			            JOptionPane.showMessageDialog(this,"Vous n'avez plus de troupe disponible."," Noob !",JOptionPane.ERROR_MESSAGE);
						break;
					case SPECIAL_RULE:
			            JOptionPane.showMessageDialog(this,"Attaque impossible en raison d'une regle speciale"," Noob !",JOptionPane.ERROR_MESSAGE);
						break;
					default:
						break;
                	
                	}
                }
                
            }
            
        }
        else
        {
        //redeploy
            game.getCurrentPlayer().redeploy(lands.get(ncl), !rc);
        }
        
        //Met à jour l'affichage
        landDisplayer.get(ncl).update();
        
    }
    
    //Fonction appelee lors de la fin du tour d'un joueur
    private void finishTurn()
    {
    	
    	game.nextPlayer();
    	majInformationPlayer();
    	if(game.isFinished())
    	{
    		finishGame();
    	}
    	else if(game.getCurrentPlayer().getCurrentTribe() == null)
    	{
    		redeploy = false;
    		buttonEndTurn.setVisible(false);
    		buttonRedeploy.setVisible(false);
    		buttonPasserDeclin.setVisible(false);
    		buttonChoice.setVisible(true);
    		JOptionPane.showMessageDialog(this,"Joueur " + (game.getPlayers().indexOf(game.getCurrentPlayer())+1) + " � vous de jouer!"," Information",JOptionPane.INFORMATION_MESSAGE);
        	
    	}
    	else
    	{
    		redeploy=false;
            buttonEndTurn.setVisible(false);
            buttonRedeploy.setVisible(true);
            buttonPasserDeclin.setVisible(true);
            buttonChoice.setVisible(false);
            JOptionPane.showMessageDialog(this,"Joueur " + (game.getPlayers().indexOf(game.getCurrentPlayer())+1) + " � vous de jouer!"," Information",JOptionPane.INFORMATION_MESSAGE);
        	
    	}
    	
    	game.getCurrentPlayer().beginTurn();
        for(LandDisplayer ld : landDisplayer)
        {
        	ld.update();
        }
        	
    	
         //TODO à compléter pour toujours afficher les bons boutons, et les scores si la partie est finie
         
    }
    
    //Termine le jeu, affiche le score et quitte le programme
    private void finishGame() {
    	
    	
    	String texte = "Scores : ";
    	
    	ArrayList<Integer> scores = game.getScores();
    	
    	for(int i=0;i<game.getNbPlayer();i++)
    		texte += "\nJoueur "+(i+1)+" : "+scores.get(i)+"pts";
    	
    	JOptionPane.showMessageDialog(this,texte," Partie terminee",JOptionPane.INFORMATION_MESSAGE);
       
        Sound.MUSIC.stop();
        System.exit(0);
        
        
	}

    //Ajoute un listener � chaque land
	public void addListenerPanel()
    {
        for(int i=0; i<landDisplayer.size(); i++)
        {
            final int j = i;
            landDisplayer.get(i).getPanelLand().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                boolean rightClick = (evt.getButton() == MouseEvent.BUTTON3);
                landsClick(j, rightClick);
                
            }
        });
            
        }
    }

   
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGame = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPeuple = new JList<String>();
        buttonRedeploy = new javax.swing.JButton();
        panelTerritoires = new javax.swing.JPanel();
        land0 = new javax.swing.JPanel();
        label0 = new javax.swing.JPanel();
        land1 = new javax.swing.JPanel();
        label1 = new javax.swing.JPanel();
        land2 = new javax.swing.JPanel();
        label2 = new javax.swing.JPanel();
        land3 = new javax.swing.JPanel();
        label3 = new javax.swing.JPanel();
        land4 = new javax.swing.JPanel();
        label4 = new javax.swing.JPanel();
        land5 = new javax.swing.JPanel();
        label5 = new javax.swing.JPanel();
        land6 = new javax.swing.JPanel();
        label6 = new javax.swing.JPanel();
        land7 = new javax.swing.JPanel();
        label7 = new javax.swing.JPanel();
        land8 = new javax.swing.JPanel();
        label8 = new javax.swing.JPanel();
        land9 = new javax.swing.JPanel();
        label9 = new javax.swing.JPanel();
        land10 = new javax.swing.JPanel();
        label10 = new javax.swing.JPanel();
        land11 = new javax.swing.JPanel();
        label11 = new javax.swing.JPanel();
        land12 = new javax.swing.JPanel();
        label12 = new javax.swing.JPanel();
        land13 = new javax.swing.JPanel();
        label13 = new javax.swing.JPanel();
        land14 = new javax.swing.JPanel();
        label14 = new javax.swing.JPanel();
        land15 = new javax.swing.JPanel();
        label15 = new javax.swing.JPanel();
        land16 = new javax.swing.JPanel();
        label16 = new javax.swing.JPanel();
        land17 = new javax.swing.JPanel();
        label17 = new javax.swing.JPanel();
        land18 = new javax.swing.JPanel();
        label18 = new javax.swing.JPanel();
        land19 = new javax.swing.JPanel();
        label19 = new javax.swing.JPanel();
        land20 = new javax.swing.JPanel();
        label20 = new javax.swing.JPanel();
        land21 = new javax.swing.JPanel();
        label21 = new javax.swing.JPanel();
        land22 = new javax.swing.JPanel();
        label22 = new javax.swing.JPanel();
        land23 = new javax.swing.JPanel();
        label23 = new javax.swing.JPanel();
        land24 = new javax.swing.JPanel();
        label24 = new javax.swing.JPanel();
        land25 = new javax.swing.JPanel();
        label25 = new javax.swing.JPanel();
        land26 = new javax.swing.JPanel();
        label26 = new javax.swing.JPanel();
        land27 = new javax.swing.JPanel();
        label27 = new javax.swing.JPanel();
        land28 = new javax.swing.JPanel();
        label28 = new javax.swing.JPanel();
        land29 = new javax.swing.JPanel();
        label29 = new javax.swing.JPanel();
        buttonEndTurn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        labelDesc = new javax.swing.JLabel();
        buttonChoice = new javax.swing.JButton();
        buttonPasserDeclin = new javax.swing.JButton();
        panelAccueil = new javax.swing.JPanel();
        buttonPlay = new javax.swing.JButton();
        textPlayerCurrentTribe = new javax.swing.JTextArea();
        buttonInfoLandColor = new javax.swing.JButton();
        
        button2Players = new JRadioButton("2 joueurs", true);
        button3Players = new JRadioButton("3 joueurs", false);
        
        button2Players.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	button2Players.setSelected(true);
                button3Players.setSelected(false);
            }
        });
        button3Players.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	button3Players.setSelected(true);
                button2Players.setSelected(false);
            }
        });
        
        

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelGame.setBackground(new java.awt.Color(255, 204, 153));

        listPeuple.setBackground(new java.awt.Color(204, 204, 204));
        listPeuple.setModel(new javax.swing.AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listPeuple);
        buttonInfoLandColor.setText("Legende Couleur");
        buttonInfoLandColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	JOptionPane
  	          .showMessageDialog(
  	              null,
  	              "orange -> foyer \n"
  	              + "vert -> biblioth�que \n"
  	              + "jaune -> toilettes \n"
  	              + "cyan -> laboratoires \n"
  	              + "bleu -> salle de tp informatique \n"
  	              + "gris -> amphith�atre \n"
  	              + "blanc -> couloir \n");
            }
        });
        
        buttonRedeploy.setText("Redeployer");
        buttonRedeploy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonRedeployMouseClicked(evt);
            }
        });

        panelTerritoires.setBackground(new java.awt.Color(204, 204, 204));
        panelTerritoires.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTerritoires.setForeground(new java.awt.Color(0, 153, 255));

        land0.setBackground(new java.awt.Color(0, 153, 153));
        land0.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label0.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land0Layout = new javax.swing.GroupLayout(land0);
        land0.setLayout(land0Layout);
        land0Layout.setHorizontalGroup(
            land0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land0Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label0, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land0Layout.setVerticalGroup(
            land0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land0Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label0, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land1.setBackground(new java.awt.Color(255, 51, 51));
        land1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label1.setForeground(new java.awt.Color(255, 255, 255));
//        label1.setText("1");

        javax.swing.GroupLayout land1Layout = new javax.swing.GroupLayout(land1);
        land1.setLayout(land1Layout);
        land1Layout.setHorizontalGroup(
            land1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land1Layout.setVerticalGroup(
            land1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land2.setBackground(new java.awt.Color(204, 204, 0));
        land2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land2Layout = new javax.swing.GroupLayout(land2);
        land2.setLayout(land2Layout);
        land2Layout.setHorizontalGroup(
            land2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land2Layout.setVerticalGroup(
            land2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land3.setBackground(new java.awt.Color(255, 51, 51));
        land3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land3Layout = new javax.swing.GroupLayout(land3);
        land3.setLayout(land3Layout);
        land3Layout.setHorizontalGroup(
            land3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land3Layout.setVerticalGroup(
            land3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land4.setBackground(new java.awt.Color(204, 0, 204));
        land4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land4Layout = new javax.swing.GroupLayout(land4);
        land4.setLayout(land4Layout);
        land4Layout.setHorizontalGroup(
            land4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land4Layout.setVerticalGroup(
            land4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land5.setBackground(new java.awt.Color(0, 153, 153));
        land5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        label5.setForeground(new java.awt.Color(255, 255, 255));
//        label5.setText("1");

        javax.swing.GroupLayout land5Layout = new javax.swing.GroupLayout(land5);
        land5.setLayout(land5Layout);
        land5Layout.setHorizontalGroup(
            land5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land5Layout.setVerticalGroup(
            land5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land5Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land6.setBackground(new java.awt.Color(102, 102, 102));
        land6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
       

        label6.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land6Layout = new javax.swing.GroupLayout(land6);
        land6.setLayout(land6Layout);
        land6Layout.setHorizontalGroup(
            land6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land6Layout.setVerticalGroup(
            land6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land6Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land7.setBackground(new java.awt.Color(102, 255, 51));
        land7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land7Layout = new javax.swing.GroupLayout(land7);
        land7.setLayout(land7Layout);
        land7Layout.setHorizontalGroup(
            land7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land7Layout.setVerticalGroup(
            land7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land7Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land8.setBackground(new java.awt.Color(0, 153, 153));
        land8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label8.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land8Layout = new javax.swing.GroupLayout(land8);
        land8.setLayout(land8Layout);
        land8Layout.setHorizontalGroup(
            land8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land8Layout.setVerticalGroup(
            land8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land8Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land9.setBackground(new java.awt.Color(102, 255, 51));
        land9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label9.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land9Layout = new javax.swing.GroupLayout(land9);
        land9.setLayout(land9Layout);
        land9Layout.setHorizontalGroup(
            land9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land9Layout.setVerticalGroup(
            land9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land9Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land10.setBackground(new java.awt.Color(204, 204, 0));
        land10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label10.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land10Layout = new javax.swing.GroupLayout(land10);
        land10.setLayout(land10Layout);
        land10Layout.setHorizontalGroup(
            land10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land10Layout.setVerticalGroup(
            land10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land10Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land11.setBackground(new java.awt.Color(255, 51, 51));
        land11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land11Layout = new javax.swing.GroupLayout(land11);
        land11.setLayout(land11Layout);
        land11Layout.setHorizontalGroup(
            land11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land11Layout.setVerticalGroup(
            land11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land11Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land12.setBackground(new java.awt.Color(204, 204, 0));
        land12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land12Layout = new javax.swing.GroupLayout(land12);
        land12.setLayout(land12Layout);
        land12Layout.setHorizontalGroup(
            land12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land12Layout.setVerticalGroup(
            land12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land12Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land13.setBackground(new java.awt.Color(204, 204, 0));
        land13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label13.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land13Layout = new javax.swing.GroupLayout(land13);
        land13.setLayout(land13Layout);
        land13Layout.setHorizontalGroup(
            land13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land13Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land13Layout.setVerticalGroup(
            land13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land13Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land14.setBackground(new java.awt.Color(204, 0, 204));
        land14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label14.setForeground(new java.awt.Color(255, 255, 255));
//        label14.setText("1");

        javax.swing.GroupLayout land14Layout = new javax.swing.GroupLayout(land14);
        land14.setLayout(land14Layout);
        land14Layout.setHorizontalGroup(
            land14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land14Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land14Layout.setVerticalGroup(
            land14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land14Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land15.setBackground(new java.awt.Color(204, 204, 255));
        land15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label15.setForeground(new java.awt.Color(255, 255, 255));
//        label15.setText("1");

        javax.swing.GroupLayout land15Layout = new javax.swing.GroupLayout(land15);
        land15.setLayout(land15Layout);
        land15Layout.setHorizontalGroup(
            land15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land15Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land15Layout.setVerticalGroup(
            land15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land15Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land16.setBackground(new java.awt.Color(102, 102, 102));
        land16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label16.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land16Layout = new javax.swing.GroupLayout(land16);
        land16.setLayout(land16Layout);
        land16Layout.setHorizontalGroup(
            land16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land16Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land16Layout.setVerticalGroup(
            land16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land16Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land17.setBackground(new java.awt.Color(0, 153, 153));
        land17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label17.setForeground(new java.awt.Color(255, 255, 255));
//        label17.setText("1");

        javax.swing.GroupLayout land17Layout = new javax.swing.GroupLayout(land17);
        land17.setLayout(land17Layout);
        land17Layout.setHorizontalGroup(
            land17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land17Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land17Layout.setVerticalGroup(
            land17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land17Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land18.setBackground(new java.awt.Color(204, 0, 204));
        land18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label18.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land18Layout = new javax.swing.GroupLayout(land18);
        land18.setLayout(land18Layout);
        land18Layout.setHorizontalGroup(
            land18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land18Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land18Layout.setVerticalGroup(
            land18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land18Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land19.setBackground(new java.awt.Color(204, 204, 255));
        land19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land19Layout = new javax.swing.GroupLayout(land19);
        land19.setLayout(land19Layout);
        land19Layout.setHorizontalGroup(
            land19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land19Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land19Layout.setVerticalGroup(
            land19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land19Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land20.setBackground(new java.awt.Color(102, 255, 51));
        land20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label20.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land20Layout = new javax.swing.GroupLayout(land20);
        land20.setLayout(land20Layout);
        land20Layout.setHorizontalGroup(
            land20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land20Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land20Layout.setVerticalGroup(
            land20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land20Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land21.setBackground(new java.awt.Color(0, 153, 153));
        land21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label21.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land21Layout = new javax.swing.GroupLayout(land21);
        land21.setLayout(land21Layout);
        land21Layout.setHorizontalGroup(
            land21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land21Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land21Layout.setVerticalGroup(
            land21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land21Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land22.setBackground(new java.awt.Color(204, 204, 255));
        land22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label22.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land22Layout = new javax.swing.GroupLayout(land22);
        land22.setLayout(land22Layout);
        land22Layout.setHorizontalGroup(
            land22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land22Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land22Layout.setVerticalGroup(
            land22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land22Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land23.setBackground(new java.awt.Color(255, 51, 51));
        land23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        

        label23.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land23Layout = new javax.swing.GroupLayout(land23);
        land23.setLayout(land23Layout);
        land23Layout.setHorizontalGroup(
            land23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land23Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land23Layout.setVerticalGroup(
            land23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land23Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land24.setBackground(new java.awt.Color(102, 102, 102));
        land24.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label24.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land24Layout = new javax.swing.GroupLayout(land24);
        land24.setLayout(land24Layout);
        land24Layout.setHorizontalGroup(
            land24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land24Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land24Layout.setVerticalGroup(
            land24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land24Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land25.setBackground(new java.awt.Color(255, 51, 51));
        land25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label25.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land25Layout = new javax.swing.GroupLayout(land25);
        land25.setLayout(land25Layout);
        land25Layout.setHorizontalGroup(
            land25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land25Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land25Layout.setVerticalGroup(
            land25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land25Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land26.setBackground(new java.awt.Color(0, 153, 153));
        land26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label26.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land26Layout = new javax.swing.GroupLayout(land26);
        land26.setLayout(land26Layout);
        land26Layout.setHorizontalGroup(
            land26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land26Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land26Layout.setVerticalGroup(
            land26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land26Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land27.setBackground(new java.awt.Color(204, 204, 0));
        land27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label27.setForeground(new java.awt.Color(255, 255, 255));
//        label27.setText("1");

        javax.swing.GroupLayout land27Layout = new javax.swing.GroupLayout(land27);
        land27.setLayout(land27Layout);
        land27Layout.setHorizontalGroup(
            land27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land27Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land27Layout.setVerticalGroup(
            land27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land27Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land28.setBackground(new java.awt.Color(255, 51, 51));
        land28.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label28.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land28Layout = new javax.swing.GroupLayout(land28);
        land28.setLayout(land28Layout);
        land28Layout.setHorizontalGroup(
            land28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land28Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land28Layout.setVerticalGroup(
            land28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land28Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        land29.setBackground(new java.awt.Color(204, 0, 204));
        land29.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label29.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout land29Layout = new javax.swing.GroupLayout(land29);
        land29.setLayout(land29Layout);
        land29Layout.setHorizontalGroup(
            land29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(land29Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        land29Layout.setVerticalGroup(
            land29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, land29Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(label29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout panelTerritoiresLayout = new javax.swing.GroupLayout(panelTerritoires);
        panelTerritoires.setLayout(panelTerritoiresLayout);
        panelTerritoiresLayout.setHorizontalGroup(
            panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTerritoiresLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTerritoiresLayout.createSequentialGroup()
                        .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(land0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(land6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTerritoiresLayout.createSequentialGroup()
                                .addComponent(land1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTerritoiresLayout.createSequentialGroup()
                                .addComponent(land7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(land11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelTerritoiresLayout.createSequentialGroup()
                        .addComponent(land12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTerritoiresLayout.createSequentialGroup()
                        .addComponent(land18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTerritoiresLayout.createSequentialGroup()
                        .addComponent(land24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(land29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        panelTerritoiresLayout.setVerticalGroup(
            panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTerritoiresLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(land5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(land11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(land16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(land21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTerritoiresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(land28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(land29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        buttonEndTurn.setText("Terminer le tour");
        buttonEndTurn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEndTurnMouseClicked(evt);
            }
        });
        textPlayerCurrentTribe.setEnabled(false);
        textPlayerCurrentTribe.setText("");
        textPlayerCurrentTribe.setFont(new Font("font",PROPERTIES,20));
        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        jScrollPane2.setViewportView(textAreaDescription);

        labelDesc.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        labelDesc.setLabelFor(textAreaDescription);
        labelDesc.setText("Description:");

        buttonChoice.setText("Choisir tribu");
        buttonChoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonChoiceMouseClicked(evt);
            }
        });

        buttonPasserDeclin.setText("Passer en déclin");
        buttonPasserDeclin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPasserDeclinMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelGameLayout = new javax.swing.GroupLayout(panelGame);
        panelGame.setLayout(panelGameLayout);
        panelGameLayout.setHorizontalGroup(
            panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGameLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelGameLayout.createSequentialGroup()
                        .addComponent(buttonPasserDeclin, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(559, 559, 559)
                        .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonEndTurn)
                            .addComponent(buttonRedeploy)))
                    .addGroup(panelGameLayout.createSequentialGroup()
                        .addComponent(panelTerritoires, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(labelDesc)
                    .addComponent(textPlayerCurrentTribe)
                     .addComponent(buttonInfoLandColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                    .addComponent(buttonChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGameLayout.setVerticalGroup(
            panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTerritoires, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGameLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(labelDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonChoice)
                        .addComponent(textPlayerCurrentTribe)
                        .addComponent(buttonInfoLandColor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonRedeploy)
                        .addComponent(buttonEndTurn))
                    .addComponent(buttonPasserDeclin))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        panelAccueil.setBackground(new java.awt.Color(204, 204, 204));
        panelAccueil.setAutoscrolls(true);

        buttonPlay.setText("Jouer");
        buttonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	int nbPlayers = (button2Players.isSelected() ? 2 : 3);
            	
            	game.setNbPlayer(nbPlayers);
            	
                buttonPlayActionPerformed(evt);
                Sound.MUSIC.play();
                
            }
        });

        //javax.swing.GroupLayout panelAccueilLayout = new javax.swing.GroupLayout(panelAccueil);
        //panelAccueil.setLayout(panelAccueilLayout);
        /*panelAccueilLayout.setHorizontalGroup(
            panelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPlay, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );*/
        /*panelAccueilLayout.setVerticalGroup(
            panelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelAccueilLayout.createSequentialGroup()
                //.addComponent(button2Players)
            	//.addComponent(button3Players)
                .addComponent(buttonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );*/
        panelAccueil.setLayout(new BorderLayout());
        panelAccueil.add(buttonPlay, BorderLayout.SOUTH);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        radioPanel.add(button2Players);
        radioPanel.add(button3Players);
        
		ImageIcon icon = new ImageIcon(new ImageIcon("./image_peuple/titre.jpg").getImage().getScaledInstance(600, 200, Image.SCALE_DEFAULT));
		JLabel labelTitre = new JLabel();
		labelTitre.setIcon(icon);
		
		panelAccueil.add(labelTitre, BorderLayout.NORTH);
        
        panelAccueil.add(radioPanel, BorderLayout.CENTER);
        
        //accueilLayout.addLayoutComponent(radioLayout, BorderLayout.CENTER);
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 254, Short.MAX_VALUE)
                    .addComponent(panelAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 254, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelAccueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    
    private void buttonRedeployMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRedeployMouseClicked

        redeploy=true;
        buttonRedeploy.setVisible(false);
        buttonEndTurn.setVisible(true);
      
    }//GEN-LAST:event_buttonRedeployMouseClicked

    private void buttonEndTurnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEndTurnMouseClicked

       finishTurn();
        
    }//GEN-LAST:event_buttonEndTurnMouseClicked
    
    private void buttonPasserDeclinMouseClicked(java.awt.event.MouseEvent evt) {                                           
    	
    	game.getCurrentPlayer().getCurrentTribe().getPopulation().setAvailable(true);
    	game.getCurrentPlayer().getCurrentTribe().getPower().setAvailable(true);
    	
    	game.getCurrentPlayer().abandonTribe();
    	
    	for(int i=0;i<landDisplayer.size();i++){

    		if(game.getCurrentPlayer()==landDisplayer.get(i).getLand().getOccupant()){
    			
    			if(game.getCurrentPlayer().getPreviousTribe()!=landDisplayer.get(i).getLand().getTribe() && 
    				game.getCurrentPlayer().getCurrentTribe()!=landDisplayer.get(i).getLand().getTribe()){
    			
    			landDisplayer.get(i).getLand().setOccupant(null);
    			}
    		}
    			
    		
    	}
        
        finishTurn();
         
     }

    private void buttonChoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonChoiceMouseClicked

        if(listPeuple.getSelectedIndex()!=-1)
        {
            buttonRedeploy.setVisible(true);
            buttonChoice.setVisible(false);

            game.getCurrentPlayer().chooseTribe(listTribe.get(listPeuple.getSelectedIndex()),listPeuple.getSelectedIndex() ); 
            game.getBank().pickTribe(listTribe.get(listPeuple.getSelectedIndex()));
            textPlayerCurrentTribe.setLineWrap(true);
            
           majInformationPlayer();
            try
            {
                    System.out.println(listPeuple.getSelectedIndex());
                    listModel.clear();
                    textAreaDescription.setText("");
            }
            catch(Exception e){}
            fillListPopulation();//Mise a� jour de l'affichage 
        }
        else
            JOptionPane.showMessageDialog(this,"Veuillez choisir une tribu"," Choix tribu ",JOptionPane.INFORMATION_MESSAGE);
        
        
    }//GEN-LAST:event_buttonChoiceMouseClicked

    private void buttonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayActionPerformed
        
        panelAccueil.setVisible(false);
       
        panelGame.setVisible(true);
        listPeuple.setCellRenderer(new MyRenderer(game.getBank().getIconPopulation(),listTribe));
        JOptionPane.showMessageDialog(this,"Joueur " + (game.getPlayers().indexOf(game.getCurrentPlayer())+1) + " � vous de jouer!"," Information",JOptionPane.INFORMATION_MESSAGE);   

        
    }//GEN-LAST:event_buttonPlayActionPerformed

    
    public void majInformationPlayer(){// permet de mettre a� jour les informations du joueur
    	
    	 if(game.getCurrentPlayer().getPreviousTribe()!=null && game.getCurrentPlayer().getCurrentTribe()!=null){
             textPlayerCurrentTribe.setText("tribu actuelle: " + "\n"+ game.getCurrentPlayer().getCurrentTribe().toString() + "\n\n"
             								+ "tribu precedente: "+"\n" + game.getCurrentPlayer().getPreviousTribe().toString()
             								+ "\n points:" + game.getCurrentPlayer().getPoints());
             }else{
            	 if(game.getCurrentPlayer().getCurrentTribe()==null && game.getCurrentPlayer().getPreviousTribe()==null ){
            		 
            		 textPlayerCurrentTribe.setText("");
            	 }else{
            		 if(game.getCurrentPlayer().getCurrentTribe()==null){
            			 
            			 textPlayerCurrentTribe.setText("tribu actuelle: "+ "\n\n\n"+ "tribu precedente: "+"\n" + game.getCurrentPlayer().getPreviousTribe().toString()+"\n points:" + game.getCurrentPlayer().getPoints());
            		 }else{
            			 
            			 textPlayerCurrentTribe.setText("tribu actuelle: "+ "\n"+game.getCurrentPlayer().getCurrentTribe().toString() + "\n\n"
  								+ "tribu precedente: "+"\n" + "\n points:" + game.getCurrentPlayer().getPoints()
  								+ "\n tour:"+(game.getTurn()+1));
            		 }
            		 
            	 }
             	
             	
             }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea textPlayerCurrentTribe;
    private javax.swing.JButton buttonChoice;
    private javax.swing.JButton buttonEndTurn;
    private javax.swing.JButton buttonPasserDeclin;
    private javax.swing.JButton buttonPlay;
    private javax.swing.JButton buttonRedeploy;
    private javax.swing.JButton buttonInfoLandColor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel label0;
    private javax.swing.JPanel label1;
    private javax.swing.JPanel label10;
    private javax.swing.JPanel label11;
    private javax.swing.JPanel label12;
    private javax.swing.JPanel label13;
    private javax.swing.JPanel label14;
    private javax.swing.JPanel label15;
    private javax.swing.JPanel label16;
    private javax.swing.JPanel label17;
    private javax.swing.JPanel label18;
    private javax.swing.JPanel label19;
    private javax.swing.JPanel label2;
    private javax.swing.JPanel label20;
    private javax.swing.JPanel label21;
    private javax.swing.JPanel label22;
    private javax.swing.JPanel label23;
    private javax.swing.JPanel label24;
    private javax.swing.JPanel label25;
    private javax.swing.JPanel label26;
    private javax.swing.JPanel label27;
    private javax.swing.JPanel label28;
    private javax.swing.JPanel label29;
    private javax.swing.JPanel label3;
    private javax.swing.JPanel label4;
    private javax.swing.JPanel label5;
    private javax.swing.JPanel label6;
    private javax.swing.JPanel label7;
    private javax.swing.JPanel label8;
    private javax.swing.JPanel label9;
    private javax.swing.JLabel labelDesc;
    private javax.swing.JPanel land0;
    private javax.swing.JPanel land1;
    private javax.swing.JPanel land10;
    private javax.swing.JPanel land11;
    private javax.swing.JPanel land12;
    private javax.swing.JPanel land13;
    private javax.swing.JPanel land14;
    private javax.swing.JPanel land15;
    private javax.swing.JPanel land16;
    private javax.swing.JPanel land17;
    private javax.swing.JPanel land18;
    private javax.swing.JPanel land19;
    private javax.swing.JPanel land2;
    private javax.swing.JPanel land20;
    private javax.swing.JPanel land21;
    private javax.swing.JPanel land22;
    private javax.swing.JPanel land23;
    private javax.swing.JPanel land24;
    private javax.swing.JPanel land25;
    private javax.swing.JPanel land26;
    private javax.swing.JPanel land27;
    private javax.swing.JPanel land28;
    private javax.swing.JPanel land29;
    private javax.swing.JPanel land3;
    private javax.swing.JPanel land4;
    private javax.swing.JPanel land5;
    private javax.swing.JPanel land6;
    private javax.swing.JPanel land7;
    private javax.swing.JPanel land8;
    private javax.swing.JPanel land9;
    private javax.swing.JList<String> listPeuple;
    private javax.swing.JPanel panelAccueil;
    private javax.swing.JPanel panelGame;
    private javax.swing.JPanel panelTerritoires;
    private javax.swing.JTextArea textAreaDescription;
    
    private JRadioButton button2Players;
    private JRadioButton button3Players;
    
    
    // End of variables declaration//GEN-END:variables

    
}
