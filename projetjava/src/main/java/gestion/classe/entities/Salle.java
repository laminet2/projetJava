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
    
}
