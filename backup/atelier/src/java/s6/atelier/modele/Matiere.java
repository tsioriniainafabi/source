/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s6.atelier.modele;

import s6.atelier.dao.BaseModele;

/**
 *
 * @author Admin
 */
public class Matiere extends BaseModele{
    private CategMatiere categmatiere;
    private String desmatiere;
    private String unite;
    private int seuil;
    private int prixunitaire;

    public Matiere(int id, CategMatiere categmatiere, String designation, String unite, int seuil, int prixunitaire) {
        super(id);
        this.categmatiere=categmatiere;
        this.desmatiere=designation;
        this.unite=unite;
        this.seuil=seuil;
        this.prixunitaire=prixunitaire;
    }

    public int getId() {
        return id;
    }
    

    public CategMatiere getCategmatiere() {
        return categmatiere;
    }

    public String getDesmatiere() {
        return desmatiere;
    }

    public String getUnite() {
        return unite;
    }

    public int getSeuil() {
        return seuil;
    }

    public int getPrixunitaire() {
        return prixunitaire;
    }

    public void setCategmatiere(CategMatiere categmatiere) {
        this.categmatiere = categmatiere;
    }

    public void setDesmatiere(String desmatiere) {
        this.desmatiere = desmatiere;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }

    public void setPrixunitaire(int prixunitaire) {
        this.prixunitaire = prixunitaire;
    }
    
}
