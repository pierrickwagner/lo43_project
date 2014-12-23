/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import smallworld.model.Land;
import smallworld.model.Population;


public class LandDisplayer {
    private JPanel panelLand;
    private JPanel landDisp;
    private Land land;
    private JLabel gif;
    private JLabel labNumberOfTroups;
    HashMap<Population.TypePopulation, ImageIcon> iconPopulation;
    public LandDisplayer(JPanel panelLand, JPanel labelLand, Land land,HashMap<Population.TypePopulation, ImageIcon> collection) {
        this.panelLand = panelLand;
        this.landDisp = labelLand;
        iconPopulation = collection;
        panelLand.setLayout(new BorderLayout());
		landDisp.setLayout(new BorderLayout());
		labNumberOfTroups = new JLabel("nombre d'unités dessus!");

//		ImageIcon icon = new ImageIcon(new ImageIcon(".\\image_peuple\\DEFAULT.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		gif = new JLabel();
		landDisp.add(labNumberOfTroups,BorderLayout.SOUTH);
		landDisp.add(gif,BorderLayout.CENTER);
        this.land = land;
        panelLand.add(landDisp,BorderLayout.CENTER);
        update();
        
        switch(land.getType())
        {
            case COULOIR:
                panelLand.setBackground(Color.WHITE);
                break;
            case AMPHI:
                panelLand.setBackground(Color.darkGray);
                break;
            case TP_GI:
                panelLand.setBackground(Color.BLUE);
                break;
            case LABO:
                panelLand.setBackground(Color.CYAN);
                break;
            case TOILETTES:
                panelLand.setBackground(Color.YELLOW);
                break;
            case BIBLIO:
                panelLand.setBackground(Color.GREEN);
                break;
            case FOYER:
                panelLand.setBackground(Color.ORANGE);
                break;
            default:
                
            
        }
    }
    
    public void update()
    {
    	labNumberOfTroups.setText(""+ land.getTroups());
    	landDisp.add(labNumberOfTroups,BorderLayout.SOUTH);
    	if(land.getOccupant()!=null){
    		
    		
    				gif.setIcon(iconPopulation.get(land.getPopulationType()));
    			

    	}else{
    		ImageIcon icon = new ImageIcon(new ImageIcon(".\\image_peuple\\DEFAULT.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    		gif.setIcon(icon);
    		
    	}
    	landDisp.add(gif,BorderLayout.CENTER);
		landDisp.repaint();
		panelLand.add(landDisp,BorderLayout.CENTER);
		panelLand.repaint();
    	
    }

    public JPanel getPanelLand() {
        return panelLand;
    }

	public JLabel getGif() {
		return gif;
	}

   
    
    
}
