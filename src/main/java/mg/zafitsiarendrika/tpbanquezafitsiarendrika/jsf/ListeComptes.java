/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf.util.Util;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.service.GestionnaireCompte;

/**
 *
 * @author kk
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private List<CompteBancaire> allComptes;
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    
    public List<CompteBancaire> getAllComptes(){
        if(allComptes == null){
            allComptes = gestionnaireCompte.getAllComptes();
        }
        return allComptes;
    }
    
    public String supprimerCompte(CompteBancaire compteBancaire){
        gestionnaireCompte.supprimer(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
    
}
