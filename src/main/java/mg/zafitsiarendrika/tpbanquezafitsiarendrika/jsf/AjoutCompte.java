/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.service.GestionnaireCompte;

/**
 *
 * @author kk
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private String nom;
    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    @Transactional
    public String ajouter(){
        CompteBancaire compte = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(compte);
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
}
