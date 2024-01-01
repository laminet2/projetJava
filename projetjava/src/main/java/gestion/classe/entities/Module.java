package gestion.classe.entities;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Module {
    private int id;
    private String libelle;
    private Boolean isArchived;
    private ArrayList<Professeur> professeurs;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
        result = prime * result + ((isArchived == null) ? 0 : isArchived.hashCode());
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
        Module other = (Module) obj;
        if (libelle == null) {
            if (other.libelle != null)
                return false;
        } else if (!libelle.equals(other.libelle))
            return false;
        if (isArchived == null) {
            if (other.isArchived != null)
                return false;
        } else if (!isArchived.equals(other.isArchived))
            return false;
        return true;
    }
}
