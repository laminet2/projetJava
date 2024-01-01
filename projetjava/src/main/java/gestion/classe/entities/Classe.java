package gestion.classe.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Classe {
    private int id;
    private String libelle;
    private boolean isArchived=false;
    private String niveau;
    private String filiere;
    private ArrayList<Professeur> professeurs=new ArrayList<>();
    private ArrayList<Cours> cours=new ArrayList<>();
    private Map<Professeur,ArrayList<Module>> professeurModules = new HashMap<>();
       
    public Classe(int id,String nomClasse,String niveauLibelle,String filiereLibelle){
        this.id=id;
        this.libelle=nomClasse;
        this.niveau=niveauLibelle;
        this.filiere=filiereLibelle;
    }
    public boolean getIsArchived(){
        return this.isArchived;
    }
    
    @Override
    public String toString(){
        return "id: "+id+" libelle :"+libelle+" niveau:"+this.niveau+" filiere:"+this.filiere+" isArchived:"+isArchived;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
        result = prime * result + (isArchived ? 1231 : 1237);
        result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
        result = prime * result + ((filiere == null) ? 0 : filiere.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Classe other = (Classe) obj;
        if (libelle == null) {
            if (other.libelle != null)
                return false;
        } else if (!libelle.equals(other.libelle))
            return false;
        if (isArchived != other.isArchived)
            return false;
        if (niveau == null) {
            if (other.niveau != null)
                return false;
        } else if (!niveau.equals(other.niveau))
            return false;
        if (filiere == null) {
            if (other.filiere != null)
                return false;
        } else if (!filiere.equals(other.filiere))
            return false;
        return true;
    }
    
}
