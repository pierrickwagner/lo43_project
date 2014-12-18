/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld.view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import smallworld.model.Land;


public class LandDisplayer {
    private JPanel panelLand;
    private JLabel labelLand;
    private Land land;

    public LandDisplayer(JPanel panelLand, JLabel labelLand, Land land) {
        this.panelLand = panelLand;
        this.labelLand = labelLand;
        this.land = land;
        
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
        labelLand.setText(""+ land.getTroups());
    }

    public JPanel getPanelLand() {
        return panelLand;
    }

    public JLabel getLabelLand() {
        return labelLand;
    }

    public void setLabelLand(JLabel labelLand) {
        this.labelLand = labelLand;
    }

    public LandDisplayer(JPanel panelLand, JLabel labelLand) {
        this.panelLand = panelLand;
        this.labelLand = labelLand;
    }
    
    
    
}
