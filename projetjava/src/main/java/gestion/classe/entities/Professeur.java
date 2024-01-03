package gestion.classe.entities;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professeur {
    private int id;
    private String nomComplet;
    private boolean isArchived=false;
    private ArrayList<Classe>classes;
    private ArrayList<Module>modules;

    public Professeur(int id,String nomComplet){
        this.id=id;
        this.nomComplet=nomComplet;
    }    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomComplet == null) ? 0 : nomComplet.hashCode());
        result = prime * result + (isArchived ? 1231 : 1237);
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
        Professeur other = (Professeur) obj;
        if (nomComplet == null) {
            if (other.nomComplet != null)
                return false;
        } else if (!nomComplet.equals(other.nomComplet))
            return false;
        if (isArchived != other.isArchived)
            return false;
        return true;
    } 

    
}
