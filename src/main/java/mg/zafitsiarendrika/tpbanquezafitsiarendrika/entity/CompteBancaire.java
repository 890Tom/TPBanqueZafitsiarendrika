/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kk
 */
@Entity
@NamedQueries({
    @NamedQuery(name="CompteBancaire.findAll", query = "SELECT DISTINCT c FROM CompteBancaire c JOIN FETCH c.operations"),
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private int solde;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
    private List<OperationBancaire> operations = new ArrayList<>();  
                    
    public List<OperationBancaire> getOperations() {  
      return operations;  
    }

    public Integer getId() {
        return id;
    }

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
    
    public CompteBancaire(String nom, int solde){
        this.nom = nom;
        this.solde = solde;
        operations.add(new OperationBancaire("Creation du compte",solde));
    }
    
    public void deposer(int montant){
        solde += montant;
        operations.add(new OperationBancaire("Debit", montant));
    }
    
    public void retirer(int montant){
        if(montant < solde){
            solde -= montant;
        }else{
            solde = 0;
        }
        operations.add(new OperationBancaire("Credit", -montant));
    }
    
    public CompteBancaire(){
        
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.zafitsiarendrika.tpbanquezafitsiarendrika.entity.CompteBancaire[ id=" + id + " ]";
    }
    
}
