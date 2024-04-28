/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf.util.Util;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.service.GestionnaireCompte;

/**
 *
 * @author kk
 */
@Named(value = "transaction")
@ViewScoped
public class Transaction implements Serializable {

    @Inject
    GestionnaireCompte gestionnaireCompte;

    private int idCompteBancaire;
    private CompteBancaire compteBancaire;
    private String typeTransaction;
    private int montant;

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
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Méthode validatrice pour le montant du mouvement. Remarque : La méthode
     * doit toujours avoir cette signature.
     *
     * @param fc
     * @param composant le composant JSF dans lequel on valide.
     * @param valeur valeur à valider (le montant pour ce cas)
     */
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeTransaction = (UIInput) composant.findComponent("typeTransaction");
        // Il faut savoir si c'est un retrait ou un dépôt.
        // Sans entrer dans les détails, il faut parfois utiliser
        // getSubmittedValue() à la place de getLocalValue.
        // typeMouvement n'est pas encore mis tant que la validation n'est pas finie.
        String valeurTypeTransaction = (String) composantTypeTransaction.getLocalValue();
        if (valeurTypeTransaction == null) {
            // Pour le cas où l'utilisateur a soumis le formulaire sans indiquer le type du mouvement,
            // Le test valeurTypeMouvement.equals("retrait") ci-dessous génèrera une erreur car
            // il est exécuté avant que JSF ne vérifie que l'utilisateur a bien choisi
            // entre ajout et retrait (le choix est requis dans la page mouvement.xhtml)
            return;
        }
        if (valeurTypeTransaction.equals("retrait")) {
            int retrait = (int) valeur;
            if (compteBancaire.getSolde() < retrait) {
                FacesMessage message
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Le retrait doit être inférieur au solde du compte",
                                "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    public String enregistrerTransaction() {
        try {

            if (typeTransaction.equals("depot")) {
                gestionnaireCompte.deposer(compteBancaire, montant);
            } else {
                gestionnaireCompte.retirer(compteBancaire, montant);
            }
            Util.addFlashInfoMessage("Transaction enregistré sur compte de " + compteBancaire.getNom());
            return "listeComptes?faces-redirect=true";
        } catch (OptimisticLockException ex) {
            Util.addFlashInfoMessage("Le compte de " + compteBancaire.getNom()
                    + " a été modifié ou supprimé par un autre utilisateur ! Essayer a nouveau ");
            return "listeComptes?faces-redirect=true";
        }
    }

    /**
     * Creates a new instance of Transaction
     */
    public Transaction() {
    }

}
