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
public class Utilisateur extends BaseModele{
    private String nom;
    private String mdp;

    public Utilisateur(int id, String nom, String mdp) {
        super(id);
        this.nom = nom;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
}
