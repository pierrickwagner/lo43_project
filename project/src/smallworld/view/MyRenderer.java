/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import smallworld.model.Population;
import smallworld.model.Tribe;

/**
 *
 * @author Darty
 */
public class MyRenderer extends JLabel implements ListCellRenderer {
    HashMap<Population.TypePopulation, ImageIcon> iconPopulation;
    ArrayList<Tribe> listTribe;
    
    public MyRenderer(HashMap<Population.TypePopulation, ImageIcon> collection, ArrayList<Tribe> list)
    {
        iconPopulation = collection;
        listTribe=list;
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //recuperer l'indice
        
        System.out.println("LA VALEUR EST "+value);
        String[] decoupage=((String)value).split(" ");
        System.out.println(decoupage[0]);
        System.out.println(decoupage[1]);
        System.out.println("ind:"+index);
        System.out.println("nb tribu: "+listTribe.size());
        
        if(isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setText(listTribe.get(index).getPower().getName());
        setIcon(iconPopulation.get(listTribe.get(index).getPopulation().getType()));
        
        
        
        return this;
    }
    
    
}
