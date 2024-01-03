package gestion.classe.entities;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Lieux {
    private static int nbreLieux; 
    private int id;
    private String libelle;
    public  Lieux(String libelle){
       id=++nbreLieux;
       this.libelle=libelle;
    }
    @Override
    public String toString() {
        return "id=" + id + ", libelle=" + libelle ;
    } 
}
