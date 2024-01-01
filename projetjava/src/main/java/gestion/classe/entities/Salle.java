package gestion.classe.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Salle {
    private int id;  
    private String libelle;
    private boolean isArchived;
    private String lieux;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
        result = prime * result + (isArchived ? 1231 : 1237);
        result = prime * result + ((lieux == null) ? 0 : lieux.hashCode());
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
        Salle other = (Salle) obj;
        if (libelle == null) {
            if (other.libelle != null)
                return false;
        } else if (!libelle.equals(other.libelle))
            return false;
        if (isArchived != other.isArchived)
            return false;
        if (lieux == null) {
            if (other.lieux != null)
                return false;
        } else if (!lieux.equals(other.lieux))
            return false;
        return true;
    }
    
}
