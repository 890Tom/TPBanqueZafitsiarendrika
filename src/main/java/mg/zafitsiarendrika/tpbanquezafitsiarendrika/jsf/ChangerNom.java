/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf.util.Util;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.service.GestionnaireCompte;

/**
 *
 * @author kk
 */
@Named(value = "changerNom")
@ViewScoped
public class ChangerNom implements Serializable {

    @Inject
    GestionnaireCompte gestionnaireCompte;

    private int idCompteBancaire;
    private CompteBancaire compteBancaire;
    private String ancienNom;
    
    public int getIdCompteBancaire() {
        return idCompteBancaire;
    }

    public void setIdCompteBancaire(int idCompteBancaire) {
        this.idCompteBancaire = idCompteBancaire;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void loadCompteBancaire() {
        this.compteBancaire = gestionnaireCompte.findById(idCompteBancaire);
        this.ancienNom = this.compteBancaire.getNom();
    }
    
    public String changerDeNom(){
        gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Nom : "+this.ancienNom +" change en : " + compteBancaire.getNom());
        return "listeComptes?faces-redirect=true";
    }
    /**
     * Creates a new instance of ChangerNom
     */
    public ChangerNom() {
    }

}
