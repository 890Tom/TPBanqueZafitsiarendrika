/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.OperationBancaire;
import mg.zafitsiarendrika.tpbanquezafitsiarendrika.service.GestionnaireCompte;

/**
 *
 * @author kk
 */
@Named(value = "operation")
@RequestScoped
public class Operation {
    @Inject
    GestionnaireCompte gestionnaireCompte;
    
    private int idCompteBancaire;
    private CompteBancaire compte;
    private List<OperationBancaire> operations;

    public List<OperationBancaire> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationBancaire> operations) {
        this.operations = operations;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public int getIdCompteBancaire() {
        return idCompteBancaire;
    }

    public void setIdCompteBancaire(int idCompteBancaire) {
        this.idCompteBancaire = idCompteBancaire;
    }
    
    public void loadCompte(){
        this.compte = gestionnaireCompte.findById(idCompteBancaire);
    }
    
    /**
     * Creates a new instance of Operation
     */
    public Operation() {
    }
    
}
