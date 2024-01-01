package gestion.classe.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Filiere {
    private static int nbreFiliere; 
    private int id;
    private String libelle;
    public  Filiere(String libelle){
       id=++nbreFiliere;
       this.libelle=libelle;
    }
    @Override
    public String toString() {
        return "id=" + id + ", libelle=" + libelle ;
    } 
    
}

