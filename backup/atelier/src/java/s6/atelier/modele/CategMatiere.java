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
public class CategMatiere extends BaseModele{
    private String nomcateg;

    public CategMatiere(int id, String nom) {
        super(id);
        this.nomcateg=nom;
    }

    public int getId() {
        return id;
    }

    public String getNomcateg() {
        return nomcateg;
    }

    public void setNomcateg(String nomcateg) {
        this.nomcateg = nomcateg;
    }
    
}
