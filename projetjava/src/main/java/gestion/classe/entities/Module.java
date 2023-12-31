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
}
