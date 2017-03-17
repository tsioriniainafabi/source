package s6.atelier.modele;
import s6.atelier.dao.BaseModele;
public class Atelier  extends BaseModele{
    private String designation;
    private int hmod;
    private int uo;
    private int hm;
    private String fonction;

    public Atelier(int id, String designation, int hmod, int uo, int hm, String fonction ) {
        super(id);
        this.designation = designation;
        this.hmod = hmod;
        this.uo = uo;
        this.hm = hm;
        this.fonction = fonction;
    }

    public String getDesignation() {
        return designation;
    }

    public int getHmod() {
        return hmod;
    }

    public int getUo() {
        return uo;
    }

    public int getHm() {
        return hm;
    }

    public String getFonction() {
        return fonction;
    }

    public int getId() {
        return id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setHmod(int hmod) {
        this.hmod = hmod;
    }

    public void setUo(int uo) {
        this.uo = uo;
    }

    public void setHm(int hm) {
        this.hm = hm;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    
}
