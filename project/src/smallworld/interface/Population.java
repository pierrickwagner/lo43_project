/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Darty
 */
public class Population {
    private String nom;
    private String pouvoir;
    private String description;

    public Population(String nom, String pouvoir, String description) {
        this.nom = nom;
        this.pouvoir = pouvoir;
        this.description = description;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    @Override
    public String toString() {
        return "Population{" + nom + "," + pouvoir + "," + description + '}';
    }
    
    
}
