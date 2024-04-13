/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf.util.Util;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.service.GestionnaireCompte;

/**
 *
 * @author kk
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert {
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    private int idSource;
    private int idDestination;
    private int montant;

    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    
    
    public String transferer(){
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        // Si la source n'existe pas
        if(source == null){
            erreur = true;
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
        } 
        // si le solde de la source est insuffisant
        else{
            if(source.getSolde() < montant){
                erreur = true;
                Util.messageErreur("Solde insuffisant pour le transfert", "Solde insuffisant pour le transfert", "form:source");
            }
        }
        
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        // Si la destination n'existe pas
        if(destination == null){
            erreur = true;
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
        }
        
        // s'il y a des erreurs
        if(erreur){
            return null;
        }
       
        gestionnaireCompte.transferer(source, destination, montant);
        Util.addFlashInfoMessage("Transfert de "+ montant +" Ar correctement effectue de "+source.getNom()+" vers "+destination.getNom());
        return "listeComptes?faces-redirect=true";
    }
    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }
    
}
