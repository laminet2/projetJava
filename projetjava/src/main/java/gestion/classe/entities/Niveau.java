package gestion.classe.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Niveau {
    private static int nbreNiveau; 
    private int id;
    private String libelle;
    public  Niveau(String libelle){
       id=++nbreNiveau;
       this.libelle=libelle;
    }

    @Override
    public String toString() {
        return "id=" + id + ", libelle=" + libelle ;
    } 


}

